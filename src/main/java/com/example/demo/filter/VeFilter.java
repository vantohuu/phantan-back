package com.example.demo.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class VeFilter implements Filter {


    QueueClass queueClass;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("INIT FILTER");
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletResponse resp =(HttpServletResponse) response;

        System.out.println("DO FILTER");
        System.out.println(((HttpServletRequest) request).getMethod());
        if ("OPTIONS".equals(((HttpServletRequest) request).getMethod())) {
            resp.setStatus(HttpServletResponse.SC_OK);
//            System.out.println("A1");
            resp.setHeader("Access-Control-Allow-Origin", "*");
            resp.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            resp.setHeader("Access-Control-Allow-Headers", "*");
            chain.doFilter((ServletRequest) request, resp);
        } else {
            CachedBodyHttpServletRequest cachedBodyHttpServletRequest =
                    new CachedBodyHttpServletRequest((HttpServletRequest) request);

            byte[] body = StreamUtils.copyToByteArray(cachedBodyHttpServletRequest.getInputStream());

            Map<String, Object> jsonRequest = new ObjectMapper().readValue(body, Map.class);
            String username = (String) jsonRequest.get("username");
            System.out.println("Push Queue: " + username);
            QueueClass.addUser(username);
            int count = 0;
            for (String i : QueueClass.getUserQueue()) {
                count++;
                System.out.println(count + ".username:" + i);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            chain.doFilter((ServletRequest) cachedBodyHttpServletRequest, response);
        }

    }

    @Override
    public void destroy() {
        System.out.println("DESTROY FILTER");
        Filter.super.destroy();
    }
}
