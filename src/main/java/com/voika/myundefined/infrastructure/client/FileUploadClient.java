package com.voika.myundefined.infrastructure.client;

import cn.hutool.json.JSONUtil;
import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.interfaces.IHttpClient;
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
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

@Component
public class FileUploadClient {

    @Value("${file-upload.url}")
    private String url;

    @Resource(name = "iHttpClientImpl")
    private IHttpClient httpClient;

    public String upload(MultipartFile file) throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(url);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setCharset(Charset.forName("UTF8"));
        builder.addBinaryBody("file",file.getInputStream(), ContentType.MULTIPART_FORM_DATA,file.getOriginalFilename());
        HttpEntity entity = builder.build();
        String respJson = httpClient.postMethod(uriBuilder, entity);
        return respJson;
    }

}
