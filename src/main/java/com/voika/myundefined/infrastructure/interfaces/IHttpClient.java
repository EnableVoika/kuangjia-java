package com.voika.myundefined.infrastructure.interfaces;

import org.apache.http.HttpEntity;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IHttpClient {

     /**
      * get请求
      * @return
      */
    String getMethod();

    /**
     * post请求
     * @param uriBuilder
     * @param entity
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    String postMethod(URIBuilder uriBuilder,HttpEntity entity) throws URISyntaxException, IOException;
    String postMethod(String url, HttpEntity entity) throws URISyntaxException, IOException;

    /**
     * 上传文件的方法
     * @param url
     * @param file
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
//    String postMethod(String url, MultipartFile file) throws URISyntaxException, IOException;

}
