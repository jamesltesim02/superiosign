package space.maganda.superiosign.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import space.maganda.superiosign.xo.po.LoginLog;

/**
 * 登录日志Mapper
 */
public interface LoginLogMapper {
  /**
   * 根据id查询登录日志
   *
   *  @param id 主键
   *
   * @return 登录日志
   */
  LoginLog getLoginLog(@Param("id") String id);
  /**
   * 查询所有
   *
   * @return 所有登录日志
   */
  List<LoginLog> list();

  /**
   * 保存登录日志
   * @param loginLog 登录日志对象
   */
  void save(@Param("loginLog") LoginLog loginLog);
}
