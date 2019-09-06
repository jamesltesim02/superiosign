package space.maganda.superiosign.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import space.maganda.superiosign.xo.po.User;

/**
 * 用户Mapper
 */
public interface UserMapper {
  /**
   * 根据用户id查询用户信息
   *
   * @param id 用户id
   *
   * @return 用户信息
   */
  User getUser(String id);

  /**
   * 根据账号查询用户信息
   *
   * @param username 账号
   *
   * @return 用户信息
   */
  User getUserByUsername(@Param("username") String username);

  /**
   * 保存用户信息
   *
   * @param user
   *    被保存的用户信息
   *
   * @return 保存后的用户信息
   */
  User saveUser(User user);

  /**
   * 查询用户列表
   *
   * @return 用户列表
   */
  List<User> list();

  /**
   * 修改登录错误次数
   *
   * @param user 用户信息对象
   */
  void updateLoginFailCount(@Param("user") User user);

  /**
   * 修改用户登录成功相关信息
   *
   * @param user 用户信息对象
   */
  void updateLoginDetail(@Param("user") User user);
}