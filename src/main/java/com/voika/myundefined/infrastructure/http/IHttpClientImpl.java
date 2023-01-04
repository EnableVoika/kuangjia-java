package com.voika.myundefined.infrastructure.http;

import cn.hutool.json.JSONUtil;
import com.voika.myundefined.infrastructure.interfaces.IHttpClient;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String respJson = EntityUtils.toString(response.getEntity(), "UTF-8");
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
        String respJson = EntityUtils.toString(response.getEntity(), "UTF-8");
        return respJson;
    }
    @Override
    public String postMethod(String url, List<Header> headers, List<NameValuePair> params,String charset) throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder(url);
        URI uri = uriBuilder.build();
        HttpPost post = new HttpPost(uri);
        // 请求头
        if (!CollectionUtils.isEmpty(headers)) {
            for (Header header : headers) {
                post.addHeader(header.getName(),header.getValue());
            }
        }
        // 请求体
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params, charset);
        post.setEntity(formEntity);
        HttpResponse response = httpClient.execute(post);
        String respJson = EntityUtils.toString(response.getEntity(), "UTF-8");
        return respJson;
    }
    @Override
    public String postMethod(String url, List<Header> headers, String jsonBody) throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder(url);
        URI uri = uriBuilder.build();
        HttpPost post = new HttpPost(uri);
        // 请求头
        if (!CollectionUtils.isEmpty(headers)) {
            for (Header header : headers) {
                post.addHeader(header.getName(),header.getValue());
            }
        }
        // 请求体
        post.setEntity(new StringEntity(jsonBody,"UTF-8"));
        HttpResponse response = httpClient.execute(post);
        String respJson = EntityUtils.toString(response.getEntity(), "UTF-8");
        return respJson;
    }
    @Override
    public String postMethod(String url, Map<String,String> headers, String jsonBody) throws URISyntaxException, IOException {
        HttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder(url);
        URI uri = uriBuilder.build();
        HttpPost post = new HttpPost(uri);
        // 请求头
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach((k,v)->{
                post.addHeader(k,v);
            });
        }
        // 请求体
        post.setEntity(new StringEntity(jsonBody,"UTF-8"));
        HttpResponse response = httpClient.execute(post);
        String respJson = EntityUtils.toString(response.getEntity(), "UTF-8");
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
