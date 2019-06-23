package com.ibmcapsule.zuulservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpSerlvetRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        String authHeader = httpSerlvetRequest.getHeader("authorization");
        System.out.println("Authorization Header: " + authHeader);
        if("OPTIONS".equals(httpSerlvetRequest.getMethod())){
            httpServletResponse.setStatus(HttpServletResponse.SC_OK);
            chain.doFilter(httpSerlvetRequest, httpServletResponse);
        } else {
            if(authHeader == null || !authHeader.startsWith("Bearer "))
                throw new ServletException("Missing or invalid authorization header");

            String token = authHeader.substring(7);
            Claims claims = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
            httpSerlvetRequest.setAttribute("claims", claims);
            chain.doFilter(httpSerlvetRequest, httpServletResponse);
        }

    }
}
