package space.maganda.superiosign.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {

  /**
   * 主键
   */
  private String id;
  /**
   * 账号
   */
  private String username;
  /**
   * 密码
   */
  private String password;
  /**
   * 密码加密因子
   */
  private String passSalt;
  /**
   * 用户邮箱
   */
  private String email;
  /**
   * 用户类型 1: 超级管理员 2: 普通管理员 3: 普通用户
   */
  private Integer roleType;
  /**
   * 创建时间
   */
  private Date createTime;
  /**
   * 创建者
   */
  private User createUser;
  /**
   * 最后一次登录ip
   */
  private String lastLoginIp;
  /**
   * 最后一次登录时间
   */
  private Date lastLoginTime;
  /**
   * 登录失败次数
   */
  private Integer loginFailCount;
  /**
   * 登录失败时间
   */
  private Date loginFailTime;
  /**
   * 登录失败ip
   */
  private String loginFailIp;
  /**
   * 是否被锁定
   */
  private Integer locked;
  /**
   * 是否被删除
   */
  private Integer deleted;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  
  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  @JsonIgnore
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @JsonIgnore
  public String getPassSalt() {
    return passSalt;
  }

  public void setPassSalt(String passSalt) {
    this.passSalt = passSalt;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getRoleType() {
    return roleType;
  }

  public void setRoleType(Integer roleType) {
    this.roleType = roleType;
  }

  public Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  public User getCreateUser() {
    return createUser;
  }

  public void setCreateUser(User createUser) {
    this.createUser = createUser;
  }

  public String getLastLoginIp() {
    return lastLoginIp;
  }

  public void setLastLoginIp(String lastLoginIp) {
    this.lastLoginIp = lastLoginIp;
  }

  public Date getLastLoginTime() {
    return lastLoginTime;
  }

  public void setLastLoginTime(Date lastLoginTime) {
    this.lastLoginTime = lastLoginTime;
  }

  public Integer getLoginFailCount() {
    return loginFailCount == null ? 0 : loginFailCount;
  }

  public void setLoginFailCount(Integer loginFailCount) {
    this.loginFailCount = loginFailCount;
  }

  public Date getLoginFailTime() {
    return loginFailTime;
  }

  public void setLoginFailTime(Date loginFailTime) {
    this.loginFailTime = loginFailTime;
  }

  public String getLoginFailIp() {
    return loginFailIp;
  }

  public void setLoginFailIp(String loginFailIp) {
    this.loginFailIp = loginFailIp;
  }

  public boolean isLocked() {
    return this.locked == 1;
  }

  public void setLocked(boolean locked) {
    this.locked = locked ? 1 : 0;
  }

  public boolean isDeleted() {
    return this.deleted == 1;
  }

  public void setDeleted(boolean deleted) {
    this.deleted = deleted ? 1 : 0;
  }
}