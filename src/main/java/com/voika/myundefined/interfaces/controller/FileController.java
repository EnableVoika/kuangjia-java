package com.voika.myundefined.interfaces.controller;

import cn.hutool.json.JSONUtil;
import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.client.FileUploadClient;
import com.voika.myundefined.infrastructure.config.SpringConfig;
import com.voika.myundefined.infrastructure.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {

    @Resource(name = "springConfig")
    private SpringConfig springConfig;

    @Resource
    private FileUploadClient fileUploadClient;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public JsonData upload(MultipartFile file) {
        try {
            String respJson = fileUploadClient.upload(file);
            return JSONUtil.toBean(respJson, JsonData.class);
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
