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
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;

@Component("iHttpClientImpl")
public class IHttpClientImpl implements IHttpClient {
    @Override
    public String getMethod() {
        return null;
    }

    @Override
    public String postMethod(URIBuilder uriBuilder, HttpEntity entity) throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        URI uri = uriBuilder.build();
        HttpPost post = new HttpPost(uri);
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        String respJson = EntityUtils.toString(response.getEntity(),"UTF-8");
        return respJson;
    }

    @Override
    public String postMethod(String url, HttpEntity entity) throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder(url);
        URI uri = uriBuilder.build();
        HttpPost post = new HttpPost(uri);
        post.setEntity(entity);
        HttpResponse response = httpClient.execute(post);
        String respJson = EntityUtils.toString(response.getEntity(),"UTF-8");
        return respJson;
    }

//    @Override
//    public String postMethod(String url, MultipartFile file) throws URISyntaxException, IOException {
//        HttpClient httpClient = HttpClients.createDefault();
//        URIBuilder uriBuilder = new URIBuilder(url);
//        URI uri = uriBuilder.build();
//        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
//        builder.setCharset(Charset.forName("UTF8"));
//        builder.addBinaryBody("file",file.getInputStream(), ContentType.MULTIPART_FORM_DATA,file.getOriginalFilename());
//        HttpEntity entity = builder.build();
//        HttpPost post = new HttpPost(uri);
//        post.setEntity(entity);
//        HttpResponse response = httpClient.execute(post);
//        String respJson = EntityUtils.toString(response.getEntity(),"UTF-8");
//        return respJson;
//    }


}
