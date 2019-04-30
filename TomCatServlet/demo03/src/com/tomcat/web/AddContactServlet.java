package com.tomcat.web;

import com.tomcat.service.ServiceContact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Enumeration;

@WebServlet("/addcontact")
public class AddContactServlet extends HttpServlet {
    private ServiceContact contact;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        contact = new ServiceContact();
        try {
            if (contact.addContact(request)) {
                /*request.setAttribute("status","<a href=\"add.jsp\"> 添加成功 是否继续添加</a>");
                request.getRequestDispatcher("index.jsp").forward(request,response);*/
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }
        } catch (Exception ignored) {
            request.setAttribute("status","添加失败,请重试");
            request.getRequestDispatcher("add.jsp").forward(request,response);
        }
    }
}
