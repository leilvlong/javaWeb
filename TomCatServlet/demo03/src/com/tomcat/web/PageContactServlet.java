package com.tomcat.web;

import com.tomcat.doman.PageBean;
import com.tomcat.service.ServiceContact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@WebServlet("/page")
public class PageContactServlet extends HttpServlet {
    private ServiceContact contact;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        contact = new ServiceContact();
        try {
            PageBean pageContact = contact.getPageContact(request);
            request.setAttribute("page",pageContact);
            request.getRequestDispatcher("page.jsp").forward(request,response);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
