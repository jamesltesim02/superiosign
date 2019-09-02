package space.maganda.superiosign.service;

import java.util.List;

import space.maganda.superiosign.model.User;

/**
 * 用户Service
 */
public interface UserService {
  /**
   * 查询所有用户信息
   *
   * @return 用户信息列表
   */
  List<User> list();

  /**
   * 登录操作
   *
   * @param username 账号
   * @param password 密码
   * @param ip 登录ip
   *
   * @return 登录后的用户信息
   */
  User login(String username, String password, String ip);
}