package com.voika.myundefined.infrastructure.beans;

import com.voika.myundefined.infrastructure.redis.IRedis;
import com.voika.myundefined.infrastructure.redis.impl.IRedisImpl;
import com.voika.myundefined.infrastructure.email.MailClient;
import com.voika.myundefined.infrastructure.jwt.IJwt;
import com.voika.myundefined.infrastructure.jwt.impl.IJwtImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 配置bean的地方
 */
@Configuration
public class BeanFactories implements BeanFactoryPostProcessor {



    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

        // 未能解决@Value晚于BeanFactoryPostProcessor的问题，导致密钥和过期时间无法注入
//        configurableListableBeanFactory.registerSingleton("jwt",jwt());
//        configurableListableBeanFactory.registerSingleton("mailSender",new JavaMailSenderImpl());
        // 过滤器
//        configurableListableBeanFactory.registerResolvableDependency(CorsFilter.class, corsFilter());
        // 自定义redis
//        configurableListableBeanFactory.registerSingleton("iRedisImpl",iRedis());
    }


    /**
     * 过滤器
     *
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.addAllowedMethod("*");
        config.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);
        return new CorsFilter(configSource);
    }

    /**
     * jwt加密
     * @return
     */
    @Bean("jwt")
    public IJwt jwt() {
        IJwtImpl iJwt = new IJwtImpl();
        return iJwt;
    }

    /**
     * redis
     * @return
     */
    @Bean("iRedisImpl")
    public IRedis iRedis() {
        IRedis iRedis = new IRedisImpl();
        return iRedis;
    }

    /**
     * 邮件客户端
     * @return
     */
    @Bean
    public MailClient mailClient() {
        MailClient mailClient = new MailClient();
        return mailClient;
    }

}
