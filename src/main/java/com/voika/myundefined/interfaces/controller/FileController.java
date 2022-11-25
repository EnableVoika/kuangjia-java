package com.voika.myundefined.interfaces.controller;

import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.MyConfigProperties;
import com.voika.myundefined.infrastructure.config.SpringConfig;
import com.voika.myundefined.infrastructure.exception.BusinessException;
import com.voika.myundefined.infrastructure.utils.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Properties;
import java.util.UUID;

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
            String savePath = myConfigProperties.getFile().getUploadDir();
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdirs();
            }
            String newFileName = UUID.randomUUID().toString().replace("-", "");
            String oldAllName = file.getOriginalFilename();
            String[] split = oldAllName.split("\\.");
            String suffix = split[split.length-1];
            savePath = savePath + newFileName + "." + suffix;
            File newFile = new File(savePath);
            file.transferTo(newFile);
            return JsonData.success();
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
