package com.tomcatservlet.filter;

import com.tomcatservlet.javabean.Studen;
import com.tomcatservlet.service.UserAction;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebFilter("/*")
public class IndexFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession();
        Studen studen =(Studen) session.getAttribute("studen");

        /*if(studen == null){
            Cookie[] cookies = request.getCookies();
            for (Cookie cookie : cookies) {
                if("studen".equals(cookie.getName())){
                    String[] split = cookie.getValue().split("#");
                    String id = split[0];
                    String name = split[1];
                    UserAction ua = new UserAction();
                    Studen newstuden = ua.getStuden(id, name);
                    session.setAttribute("studen",newstuden);
                }
            }
        }*/

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
