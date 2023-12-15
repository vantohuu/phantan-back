package com.example.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;



public class VeFilter implements Filter {


    QueueClass queueClass;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        CachedBodyHttpServletRequest cachedBodyHttpServletRequest =
                new CachedBodyHttpServletRequest((HttpServletRequest) request);

        byte[] body = StreamUtils.copyToByteArray(cachedBodyHttpServletRequest.getInputStream());

        Map<String, Object> jsonRequest = new ObjectMapper().readValue(body, Map.class);

        System.out.println(jsonRequest);

        String username = (String) jsonRequest.get("username");
        QueueClass.addUser(username);
        System.out.println("Remote Host:"+request.getRemoteHost());
        System.out.println("Remote Address:"+request.getRemoteAddr());
        for (String i : QueueClass.getUserQueue()) {
            System.out.println("username:" + i);
        }
        chain.doFilter((ServletRequest) cachedBodyHttpServletRequest, response);

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
