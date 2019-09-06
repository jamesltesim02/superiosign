package space.maganda.superiosign.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 解决接口跨域问题配置
 */
@Configuration
public class Cors {
  
  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration config = new CorsConfiguration();
      config.addAllowedOrigin("*");
      config.setAllowCredentials(true);
      config.addAllowedMethod("*");
      config.addAllowedHeader("*");
      // config.addExposedHeader("*");

    UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
    configSource.registerCorsConfiguration("/**", config);

    return new CorsFilter(configSource);
  }
}
