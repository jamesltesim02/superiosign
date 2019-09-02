package space.maganda.superiosign.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import space.maganda.superiosign.model.User;
import space.maganda.superiosign.service.UserService;
import space.maganda.superiosign.utils.HttpUtils;

@RestController
@RequestMapping("/users")
public class UserController {

  private UserService userService;

  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }

  @RequestMapping
  public List<User> listUsers() {
    return userService.list();
  }

  /**
   * 登录操作
   *
   * @param username
   * @param password
   * @return
   */
  @RequestMapping(path = "/login", method = RequestMethod.POST)
  public User login(
    @RequestParam("username") String username,
    @RequestParam("password") String password,
    HttpServletRequest request
  ) {
    return userService.login(
      username,
      password,
      HttpUtils.getClientIp(request)
    );
  }
}