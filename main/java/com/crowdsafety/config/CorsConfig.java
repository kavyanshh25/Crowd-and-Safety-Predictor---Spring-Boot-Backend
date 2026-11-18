package com.crowdsafety.config;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;
@Configuration public class CorsConfig {
 @Bean WebMvcConfigurer corsConfigurer(){ return new WebMvcConfigurer(){
  @Override public void addCorsMappings(CorsRegistry r){ r.addMapping("/api/**").allowedOriginPatterns("*").allowedMethods("GET","POST","OPTIONS").allowedHeaders("*"); }
 }; }
}
