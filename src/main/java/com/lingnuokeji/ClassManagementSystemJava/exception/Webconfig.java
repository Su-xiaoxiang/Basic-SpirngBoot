package com.lingnuokeji.ClassManagementSystemJava.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class Webconfig {
    /**
     * CORS跨域配置
     * @Username 程序员-Su_xiaoxiang
     * @date 2024/9/10 16:23
     * @return WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                log.info("WebConfig已启动.......");
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:5173")
             //         .allowedOrigins("http://47.120.63.101")  // 明确指定允许的来源
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);  // 允许跨域请求携带cookie或其他凭证
            }
        };
    }
}
