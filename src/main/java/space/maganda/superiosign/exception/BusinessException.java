package space.maganda.superiosign.exception;

import space.maganda.superiosign.constant.ResultStatus;

public class BusinessException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private Integer code;
  private String message;

  public BusinessException() {
  }

  public BusinessException(Integer code, String message) {
    super(message);
    this.code = code;
    this.message = message;
  }

  public BusinessException(ResultStatus status) {
    this(status.getCode(), status.getMessage());
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
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