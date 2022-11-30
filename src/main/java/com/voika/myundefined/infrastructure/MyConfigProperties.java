package com.voika.myundefined.infrastructure;

import com.voika.myundefined.infrastructure.utils.StringUtil;
import com.voika.myundefined.infrastructure.utils.UrlUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;

//@EnableConfigurationProperties({MyConfigProperties.class})
@ConfigurationProperties(prefix = "my-config")
@Configuration("myConfigProperties")
public class MyConfigProperties {

}


