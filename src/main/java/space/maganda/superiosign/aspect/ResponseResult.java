package space.maganda.superiosign.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import space.maganda.superiosign.constant.ResultStatus;
import space.maganda.superiosign.exception.BusinessException;
import space.maganda.superiosign.xo.vo.ResultVO;

/**
 * 全局结果集处理
 */
@ControllerAdvice
public class ResponseResult implements ResponseBodyAdvice<Object> {

  /**
   * 异常处理
   *
   * @param request http请求对象
   * @param response http响应对象
   * @param exception 异常
   *
   * @return 响应结果
   */
  @ExceptionHandler(RuntimeException.class)
  @ResponseBody
  public Object defaultExceptionHandler(
    HttpServletRequest request,
    HttpServletResponse response,
    Exception exception
  ) {
    if (exception instanceof BusinessException) {
      BusinessException be = (BusinessException) exception;
      return new ResultVO(be.getCode(), be.getMessage());
    }
    return new ResultVO(ResultStatus.ERROR);
  }

  @Override
  public boolean supports(MethodParameter returnType, Class converterType) {
    return true;
  }

  /**
   * 全局结果集处理
   */
  @Override
  public Object beforeBodyWrite(
    Object body,
    MethodParameter returnType,
    MediaType selectedContentType,
    Class selectedConverterType,
    ServerHttpRequest request,
    ServerHttpResponse response
  ) {
    if (body instanceof ResultVO) {
      return body;
    }

    return new ResultVO(body);
  }
}