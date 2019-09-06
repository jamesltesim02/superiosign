package space.maganda.superiosign.xo.po;

import java.util.Date;

/**
 * 登录日志模型
 */
public class LoginLog {

  /**
   * 主键
   */
  private String id;
  /**
   * 登录用户id
   */
  private String userId;
  /**
   * 登录时间
   */
  private Date loginTime;
  /**
   * 登录ip
   */
  private String loginIp;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Date getLoginTime() {
    return loginTime;
  }

  public void setLoginTime(Date loginTime) {
    this.loginTime = loginTime;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getLoginIp() {
    return loginIp;
  }

  public void setLoginIp(String loginIp) {
    this.loginIp = loginIp;
  }
}