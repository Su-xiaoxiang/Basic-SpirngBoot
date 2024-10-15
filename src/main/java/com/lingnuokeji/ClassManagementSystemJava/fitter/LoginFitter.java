package com.lingnuokeji.ClassManagementSystemJava.fitter;


import com.alibaba.fastjson.JSONObject;
import com.lingnuokeji.ClassManagementSystemJava.pojo.Result;
import com.lingnuokeji.ClassManagementSystemJava.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginFitter implements Filter {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("----------------------------------进入过滤器------------------------------------------");
        HttpServletRequest sreq = (HttpServletRequest) servletRequest;
        HttpServletResponse srep = (HttpServletResponse) servletResponse;

        // 过滤 OPTIONS 请求
        if ("OPTIONS".equalsIgnoreCase(sreq.getMethod())) {
            log.info("OPTIONS 请求，直接放行");
            log.info("----------------------检测OPTIONS请求完毕------------------------------------------");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String url = sreq.getRequestURL().toString();
        log.info("获取到的后端url为: " + url);

        // 放行登录和注册请求
        if (url.contains("login")) {
            log.info("登录或注册操作...成功放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String token = sreq.getHeader("Authorization-Token");
        log.info("获取到的用户token: {}", token);

        if (!StringUtils.hasLength(token)) {
            log.info("token不正确或已经过期,返回登录页面");
            sendErrorResponse(srep, "失败验证，请重新登录");
            return;
        }

        try {
            // 解析 JWT 令牌，提取用户信息
            Claims claims = jwtUtils.parseJWT(token);
            String userId = (String) claims.get("id");

            // 验证 JWT 是否有效
            if (!jwtUtils.isJwtValid(token, userId)) {
                log.info("JWT 无效或已过期");
                sendErrorResponse(srep, "失败验证，请重新登录");
                return;
            }

            log.info("----------------------------------------------------");
            log.info("啦啦啦啦......验证成功放行");
            log.info("----------------------------------------------------");
        } catch (Exception e) {
            log.info("解析 JWT 失败", e);
            sendErrorResponse(srep, "失败验证，请重新登录");
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void sendErrorResponse(HttpServletResponse response, String message) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        Result error = Result.error(message);
        String json = JSONObject.toJSONString(error);
        response.getWriter().write(json);
    }
}
