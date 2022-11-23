package com.voika.myundefined.infrastructure.config;

import com.voika.myundefined.infrastructure.interceptor.CustomerInterceptor;
import org.apache.naming.factory.BeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Resource
    private CustomerInterceptor customerInterceptor;

    /**
     * 静态资源处理
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");

        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    // 增加拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(customerInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**", "/test/**");
    }

//    @Bean
//    public InternalResourceViewResolver viewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setPrefix("classpath:/WEB-INF/jsp");
//        viewResolver.setSuffix(".jsp");
//        return viewResolver;
//    }

}
