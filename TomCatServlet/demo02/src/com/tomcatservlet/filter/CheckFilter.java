package com.tomcatservlet.filter;

import com.tomcatservlet.javabean.Studen;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class CheckFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        Studen studen = (Studen) session.getAttribute("studen");

        if(studen == null){
            String requestURI = request.getRequestURI();
            if(requestURI.contains("car")){
                HttpServletResponse response = (HttpServletResponse) resp;
                response.sendRedirect("login.jsp");
                return;
            }
        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
