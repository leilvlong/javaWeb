package com.tomcat.web;

import com.tomcat.service.ServiceContact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/updateContact")
public class UpdateContactServlet extends HttpServlet {
    private ServiceContact contact;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        contact = new ServiceContact();
        try {
            if (contact.updateContact(request)) {
                /*request.setAttribute("status","修改成功!");
                request.getRequestDispatcher("showall").forward(request,response);*/
                response.sendRedirect("showall");
            }
        } catch (Exception e) {
            request.setAttribute("status","修改失败请重试!");
            request.getRequestDispatcher("update.jsp").forward(request,response);
        }
    }
}
