package space.maganda.superiosign.utils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * 常用公共方法
 */
public class CommonUtils {

  /**
   * 将util.Date转为time.LocalDate
   * 
   * @param source 被转换的Date对象
   * 
   * @return 转换后的LocalDate
   */
  public static LocalDate toLocalDate(Date source) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(source.getTime());
    return LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
    // return LocalDate.ofInstant(
    //   source.toInstant(),
    //   ZoneId.systemDefault()
    // );
  }

  /**
   * 生成uuid
   *
   * @return uuid
   */
  public static String uuid() {
    return UUID.randomUUID().toString().replace("-", "");
  }
}
