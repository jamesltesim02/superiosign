package space.maganda.superiosign.service.impl;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import space.maganda.superiosign.constant.CacheKey;
import space.maganda.superiosign.constant.ResultStatus;
import space.maganda.superiosign.constant.UserConstant;
import space.maganda.superiosign.exception.BusinessException;
import space.maganda.superiosign.mapper.LoginLogMapper;
import space.maganda.superiosign.mapper.UserMapper;
import space.maganda.superiosign.xo.po.LoginLog;
import space.maganda.superiosign.xo.po.User;
import space.maganda.superiosign.service.UserService;
import space.maganda.superiosign.utils.CommonUtils;

/**
 * 用户相关服务实现类
 */
@Service("userService")
public class UserServiceImpl implements UserService {

  /**
   * 用户数据操作mapper
   */
  private UserMapper userMapper;
  @Autowired
  public void setUserMapper(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  /**
   * 登录日志mapper
   */
  private LoginLogMapper loginLogMapper;
  @Autowired
  public void setLoginLogMapper(LoginLogMapper loginLogMapper) {
    this.loginLogMapper = loginLogMapper;
  }

  /**
   * redis操作对象
   */
  @Autowired
  private RedisTemplate<String, Object> template;
  public void setTemplate(RedisTemplate<String, Object> template) {
    this.template = template;
  }

  @Override
	public List<User> list() {
		return userMapper.list();
	}

  /**
   * 记录登录错误次数
   * @param user 用户相关信息
   */
  public void updateLoginFailCount(User user) {
    userMapper.updateLoginFailCount(user);
  }

  /**
   * 更新登录成功相关信息
   * @param user 用户相关信息
   */
  public void updateLoginDetail(User user) {
    // 更新用户表数据
    userMapper.updateLoginDetail(user);

    // 保存登录日志到数据库中
    LoginLog loginLog = new LoginLog();
    loginLog.setId(CommonUtils.uuid());
    loginLog.setUserId(user.getId());
    loginLog.setLoginIp(user.getLastLoginIp());
    loginLog.setLoginTime(user.getLastLoginTime());

    loginLogMapper.save(loginLog);
  }

  @Override
  public User login(String username, String password, String ip) {
    User user = userMapper.getUserByUsername(username);

    // 是否不存在或已经被删除
    if (user == null || user.isDeleted()) {
      throw new BusinessException(ResultStatus.ACCOUNT_FAILED);
    }

    // 最后一次登录失败是否是今天之前,如果是则重置为0
    if (user.getLoginFailTime() != null) {
      // 将错误时间转为LocalDate以用作比较
      LocalDate failDate = CommonUtils.toLocalDate(user.getLoginFailTime());
      if (failDate.isBefore(LocalDate.now())) {
        user.setLoginFailCount(0);
      }
    }

    // 当前时间
    Date now = new Date();

    // 是否登录已经超过最大次数
    if (user.getLoginFailCount() >= UserConstant.MAX_LOGIN_FAIL_TIMES) {
      throw new BusinessException(ResultStatus.LOGIN_FIALED_MAXAGE);
    }

    // 密码是否错误
    if (!user.getPassword().equals(password)) {
      // 保存登录错误信息, 
      user.setLoginFailCount(user.getLoginFailCount() + 1);
      user.setLoginFailIp(ip);
      user.setLoginFailTime(now);

      // 保存登录失败到数据库中
      this.updateLoginFailCount(user);

      throw new BusinessException(ResultStatus.ACCOUNT_FAILED);
    }

    // 账号是否被锁定
    if (user.isLocked()) {
      throw new BusinessException(ResultStatus.ACCOUNT_LOCKED);
    }

    // 设置登录成功相关信息
    user.setLastLoginIp(ip);
    user.setLastLoginTime(now);
    user.setLoginFailCount(0);
    user.setLoginFailTime(null);
    user.setLoginFailIp(null);

    // 保存登录成功信息到数据库中
    this.updateLoginDetail(user);

    // 保存到缓存中
    this.template.opsForValue().set(CacheKey.CURRENT_USER, user);;

    System.out.println("aaaaaaaaaa: login success");

    return user;
  }
}