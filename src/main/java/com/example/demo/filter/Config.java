package com.example.demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public FilterRegistrationBean<VeFilter> filterRegistrationBean() {
        FilterRegistrationBean<VeFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new VeFilter());
        registrationBean.addUrlPatterns("/ve/order-ticket");
        return registrationBean;
    }
}
