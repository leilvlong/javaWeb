package com.tomcat.web;

import com.tomcat.service.ServiceContact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.util.Set;

@WebServlet("/delcontact")
public class DelContactServlet extends HttpServlet {
    private ServiceContact sc = new ServiceContact();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        if (sc.delContace(request)) {
            /*request.setAttribute("status","删除成功!");
            request.getRequestDispatcher("showall").forward(request,response);*/
            response.sendRedirect("showall");
        }else{
            request.setAttribute("status","删除失败,请重试!");
            request.getRequestDispatcher("showall").forward(request,response);
        }

    }
}
