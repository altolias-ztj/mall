package com.woniuxy.mall.config;

import com.woniuxy.mall.filter.AuthFilter;
import com.woniuxy.mall.filter.CorsFilter;
import com.woniuxy.mall.filter.PermFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(corsFilter());
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setOrder(0);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(authFilter());
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<PermFilter> permFilterFilterRegistrationBean() {
        FilterRegistrationBean<PermFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(permFilter());
        filterRegistrationBean.addUrlPatterns("/api/*");
        filterRegistrationBean.setOrder(2);
        return filterRegistrationBean;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter();
    }

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter();
    }

    @Bean
    public PermFilter permFilter() {
        return new PermFilter();
    }
}

