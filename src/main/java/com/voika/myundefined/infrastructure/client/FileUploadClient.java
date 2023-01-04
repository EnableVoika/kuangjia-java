package com.voika.myundefined.infrastructure.client;

import com.voika.myundefined.infrastructure.interfaces.IHttpClient;
import org.apache.http.HttpEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
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
