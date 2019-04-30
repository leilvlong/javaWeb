package com.tomcat.web;

import com.tomcat.doman.Contact;
import com.tomcat.service.ServiceContact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/showall")
public class GetAllContactServlet extends HttpServlet {
    private ServiceContact contact;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        contact = new ServiceContact();
        List<Contact> allContact = contact.getAllContact();
        if(allContact != null){
            request.setAttribute("list",allContact);
            request.getRequestDispatcher("list.jsp").forward(request,response);
        }
        request.setAttribute("status","你尚无联系人");
        request.getRequestDispatcher("index.jsp").forward(request,response);


    }
}
