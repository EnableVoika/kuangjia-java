package com.voika.myundefined.infrastructure.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONUtil;
import com.voika.myundefined.infrastructure.JsonData;
import com.voika.myundefined.infrastructure.interfaces.IJwt;
import com.voika.myundefined.infrastructure.requestdata.Header;
import com.voika.myundefined.infrastructure.requestdata.RequestData;
import com.voika.myundefined.infrastructure.requestdata.TokenUser;
import com.voika.myundefined.infrastructure.utils.StringUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class CustomerInterceptor extends HandlerInterceptorAdapter {

    @Resource
    private IJwt jwt;

    private void out(JsonData jsonData, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        String jsonResponse = JSONUtil.toJsonStr(jsonData);
        out.print(jsonResponse);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean mark = true;
//        mark = jwtValidate(request,response);
        return mark;
    }

    private boolean jwtValidate(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String tokenJson = request.getHeader("token");
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        if (StringUtil.isEmpty(tokenJson)) {
            PrintWriter out = response.getWriter();
            String jsonResponse = JSONUtil.toJsonStr(JsonData.noToken());
            out.println(jsonResponse);
            return false;
        }
        try {
            jwt.validateToken(tokenJson);
        } catch (ExpiredJwtException e) {
            PrintWriter out = response.getWriter();
            String jsonResponse = JSONUtil.toJsonStr(JsonData.tokenExpired());
            out.print(jsonResponse);
            return false;
        } catch (MalformedJwtException e) {
            PrintWriter out = response.getWriter();
            String jsonResponse = JSONUtil.toJsonStr(JsonData.malformedToken());
            out.print(jsonResponse);
            return false;
        } catch (SignatureException e) {
            PrintWriter out = response.getWriter();
            String jsonResponse = JSONUtil.toJsonStr(JsonData.tokenSignatureErr());
            out.print(jsonResponse);
            return false;
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            String jsonResponse = JSONUtil.toJsonStr(JsonData.parseErrToken());
            out.print(jsonResponse);
            return false;
        }

        // 解密tokeng
        try {
            Claims claims = jwt.parse(tokenJson);
            TokenUser tokenUser = new TokenUser();
            BeanUtil.copyProperties(claims,tokenUser);
            tokenUser.setUserId((String) claims.get("bizId"));
            RequestData.TokenUser = tokenUser;
            Header header = new Header();
            header.setToken(request.getHeader("token"));
            RequestData.header = header;
            if (null == tokenUser) {
                PrintWriter out = response.getWriter();
                String jsonResponse = JSONUtil.toJsonStr(JsonData.noAccount());
                out.print(jsonResponse);
                return false;
            }
        } catch (Exception e) {
            PrintWriter out = response.getWriter();
            String jsonResponse = JSONUtil.toJsonStr(JsonData.parseErrToken());
            out.print(jsonResponse);
            return false;
        }
        return true;
    }

}
