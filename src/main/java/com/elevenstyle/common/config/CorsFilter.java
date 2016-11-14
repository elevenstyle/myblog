/**
 * 
 */
package com.elevenstyle.common.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 解决跨域
 * @author lijie
 * @email  lijie@6mi.com
 * @time   2016年11月10日 上午9:53:29
 */
public class CorsFilter extends OncePerRequestFilter {  
  
    @Override  
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)  
            throws ServletException, IOException {  
    response.addHeader("Access-Control-Allow-Origin", "*");  
    response.addHeader("Access-Control-Allow-Methods",  
            "GET, POST, PUT, DELETE, OPTIONS");  
    response.addHeader("Access-Control-Allow-Headers",  
            "origin, content-type, accept, x-requested-with, sid, mycustom, smuser");  
        filterChain.doFilter(request, response);  
    }
}