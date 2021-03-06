package com.ibmcapsule.zuulservice.config;

import com.ibmcapsule.zuulservice.filter.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.logging.Filter;

@Configuration
public class BeanConfiguration {

    @Bean
    public FilterRegistrationBean jwtFilter()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new JwtFilter());
        filterRegistrationBean.addUrlPatterns("/favouriteservice/api/v1/favouriteservice/user/*");
        return filterRegistrationBean;
    }

    @Bean
    public ErrorFilter errorFilter()
    {
        return new ErrorFilter();
    }

    @Bean
    public PreFilter preFilter()
    {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter()
    {
        return new PostFilter();
    }

    @Bean
    public RouterFilter routerFilter()
    {
        return new RouterFilter();
    }

    @Bean
    public CorsFilter corsFilter()
    {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("*", config);
        return new CorsFilter(source);
    }
}
