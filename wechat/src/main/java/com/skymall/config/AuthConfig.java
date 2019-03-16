package com.skymall.config;

import com.skymall.interceptor.AuthorizationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {


    @Bean
    public AuthorizationInterceptor getUserInterceptor(){
        return new AuthorizationInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /**
         * 拦截小程序端
         */
        registry.addInterceptor(getUserInterceptor())
                .addPathPatterns("/mshop/**")
                .excludePathPatterns("/", "/admin/**");

        /**
         * 拦截后台管理员端
         */


//                .excludePathPatterns("/","user/login","/index.html")
//                .excludePathPatterns("/public/**","/resources/**");

    }



}
