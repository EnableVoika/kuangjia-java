package com.voika.myundefined.interfaces.controller;

import cn.hutool.json.JSONUtil;
import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.MyConfigProperties;
import com.voika.myundefined.infrastructure.config.SpringConfig;
import com.voika.myundefined.infrastructure.exception.BusinessException;
import com.voika.myundefined.infrastructure.utils.UrlUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/file")
public class FileController {


    @Resource(name = "myConfigProperties")
    private MyConfigProperties myConfigProperties;

    @Resource(name = "springConfig")
    private SpringConfig springConfig;

    @Value("${my-config.file.upload-dir}")
    private String uploadDir;

    @Value("${my-config.file.show-mapping.dns}")
    private String dns;

    @Value("${my-config.file.show-mapping.port}")
    private int port;

    @Value("${my-config.file.show-mapping.mapping}")
    private String mapping;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public JsonData upload(MultipartFile file, HttpServletRequest request) {
        try {
            // service
            HttpClient httpClient = HttpClients.createDefault();
            URIBuilder uriBuilder = new URIBuilder("http://124.221.243.242:8989/uploadfile/file/upload");
            URI uri = uriBuilder.build();
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setCharset(Charset.forName("UTF8"));
            builder.addBinaryBody("file",file.getInputStream(), ContentType.MULTIPART_FORM_DATA,file.getOriginalFilename());
            HttpEntity entity = builder.build();
            HttpPost post = new HttpPost(uri);
//            post.setHeader("Content-Type","multipart/form-data; boundary=<calculated when request is sent>");
            post.setEntity(entity);
            HttpResponse response = httpClient.execute(post);
            String respJson = EntityUtils.toString(response.getEntity(),"UTF-8");
            JsonData jsonData = JSONUtil.toBean(respJson, JsonData.class);
            return jsonData;
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
