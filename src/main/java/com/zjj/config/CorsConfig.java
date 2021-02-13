package com.zjj.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 解决跨域问题 配置
 */
@Configuration
public class CorsConfig {

    //@Override
    //public void addCorsMappings(CorsRegistry registry) {
    //    registry.addMapping("/**")
    //            .allowedHeaders("*")
    //            .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
    //            .allowCredentials(true)
    //            .maxAge(3600)
    //            // springboot2.4.0引入的spring-web-5.3.1.jar里面的CorsConfiguration.class
    //            // 多了validateAllowCredentials方法，是这个方法抛出的异常,解决办法是:
    //            // 1.使用低版本的spring-boot-starter-parent(2.4.0RELEASE往下的版本);
    //            // 2.指定跨域访问的客户端 .allowedOrigins("http://localhost:8000"),不能设置成"*"
    //            .allowedOrigins("http://localhost:8080");
    //}

    /**
     * 跨域配置
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        // 设置访问源地址
        config.addAllowedOriginPattern("*");
        // 设置访问源请求头
        config.addAllowedHeader("*");
        // 设置访问源请求方法
        config.addAllowedMethod("*");
        // 对接口配置跨域设置
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
