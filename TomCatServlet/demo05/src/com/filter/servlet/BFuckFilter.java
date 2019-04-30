package com.filter.servlet;

import com.filter.agency.newRequest;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

@WebFilter("/fuck")
public class BFuckFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        newRequest newRequest = new newRequest((HttpServletRequest) req);
        HttpServletRequest request = (HttpServletRequest) Proxy.newProxyInstance(newRequest.getClass().getClassLoader(), new Class[]{HttpServletRequest.class}, newRequest);
        chain.doFilter(request, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
