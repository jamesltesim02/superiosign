package space.maganda.superiosign.constant;

/**
 * <h3>常用状态列表</h3>
 *
 * <pre>
 * 状态码列表
 *    基础业务
 *      200: 操作成功
 *      404: 资源不存在
 *      500: 服务器内部错误
 * 
 *    用户相关(1000+)
 *      10010001: 账号不存在或密码错误
 *      10010002: 登录错误超过最大限度
 *      10010003: 账号已被锁定
 *      10010004: 账号已被删除
 *      10010005: 账号已存在
 * </pre>
 */
public class ResultStatus {

  // ===========================================================================
  // 基础业务相关定义
  // ===========================================================================
  /**
   * 操作成功
   */
  public static final ResultStatus SUCCESS = new ResultStatus(200, "操作成功");
  /**
   * 资源不存在
   */
  public static final ResultStatus NOT_FOUND = new ResultStatus(404, "资源不存在");
  /**
   * 服务器内部出错
   */
  public static final ResultStatus ERROR = new ResultStatus(500, "服务器内部出错");

  // ===========================================================================
  // 用户业务相关定义
  // ===========================================================================
  /**
   * 账号不存在或密码错误
   */
  public static final ResultStatus ACCOUNT_FAILED = new ResultStatus(10010001, "账号不存在或密码错误");
  /**
   * 登录错误超过最大限度
   */
  public static final ResultStatus LOGIN_FIALED_MAXAGE = new ResultStatus(10010002, "登录错误超过最大限度");
  /**
   * 账号已被锁定
   */
  public static final ResultStatus ACCOUNT_LOCKED = new ResultStatus(10010003, "账号已被锁定");
  /**
   * 账号已被删除
   */
  public static final ResultStatus ACCOUNT_DELETED = new ResultStatus(10010004, "账号已被删除");
  /**
   * 账号已存在
   */
  public static final ResultStatus ACCOUNT_ALREADY_EXISTS = new ResultStatus(10010005, "账号已存在");

  /**
   * 状态码
   */
  private Integer code;
  /**
   * 响应消息
   */
  private String message;

  private ResultStatus(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}