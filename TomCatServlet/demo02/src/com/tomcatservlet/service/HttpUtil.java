package com.tomcatservlet.service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HttpUtil {
    public static void requestFroward(HttpServletRequest request, HttpServletResponse response, String target) throws ServletException, IOException {
        request.getRequestDispatcher(target).forward(request,response);
    }
}
