package space.maganda.superiosign.xo.vo;

import space.maganda.superiosign.constant.ResultStatus;

/**
 * 响应消息实体
 */
public class ResultVO {

  /**
   * 响应码
   */
  private Integer code;

  /**
   * 响应消息
   */
  private String message;

  /**
   * 数据对象
   */
  private Object data;

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

  public Object getData() {
    return data;
  }

  public void setData(Object data) {
    this.data = data;
  }

  public ResultVO(Integer code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public ResultVO() {
  }

  public ResultVO(Integer code, String message) {
    this.code = code;
    this.message = message;
  }

  public ResultVO(Object data) {
    this(ResultStatus.SUCCESS);
    this.data = data;
  }

  public ResultVO(ResultStatus status, Object data) {
    this(status);
    this.data = data;
  }

  public ResultVO(ResultStatus status) {
    this.code = status.getCode();
    this.message = status.getMessage();
  }
}