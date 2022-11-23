package com.voika.myundefined.interfaces.controller;

import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.MyConfigProperties;
import com.voika.myundefined.infrastructure.config.SpringConfig;
import com.voika.myundefined.infrastructure.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Properties;

@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {


    @Resource(name = "myConfigProperties")
    private MyConfigProperties myConfigProperties;

    @Resource(name = "springConfig")
    private SpringConfig springConfig;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public JsonData upload(MultipartFile file) {
        try {
            // service
            YamlPropertiesFactoryBean yml = new YamlPropertiesFactoryBean();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            org.springframework.core.io.Resource resource = resolver.getResource("/dev/bootstrap-dev.yml");
            yml.setResources(resource);
            Properties object = yml.getObject();
            return JsonData.success(object);
        } catch (BusinessException e) {
            int code = 0;
            return JsonData.error(e.getMessage(), code);
        } catch (Exception e) {
            String msg = "上传文件时出现异常";
            log.error(msg, e);
            return JsonData.error(msg);
        }
    }

}
