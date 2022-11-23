package com.voika.myundefined.infrastructure.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration("springConfig")
public class SpringConfig implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (null == this.applicationContext) {
            this.applicationContext = applicationContext;
        }
    }

    /**
     * 获取spring上下文容器
     *
     * @return
     */
    public ApplicationContext getContent() {
        return this.applicationContext;
    }

    /**
     * 获取当前环境
     */
    public String getActiveProfile() {
        return getContent().getEnvironment().getActiveProfiles()[0];
    }

}
