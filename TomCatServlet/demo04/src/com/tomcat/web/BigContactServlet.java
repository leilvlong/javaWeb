package com.tomcat.web;

import com.tomcat.dao.IJdbcContact;
import com.tomcat.doman.Contact;
import com.tomcat.doman.PageBean;
import com.tomcat.factory.GetCoantactProperty;
import com.tomcat.factory.IGetBean;
import com.tomcat.service.IServiceContact;
import com.tomcat.service.ServiceContact;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

@WebServlet("/big")
public class BigContactServlet extends HttpServlet {
    private IGetBean gc = GetCoantactProperty.getGCP();
    private IServiceContact contact = (ServiceContact) gc.getBean("contactService");

    // private IServiceContact contact = new ServiceContact();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String action = request.getParameter("action");


        try {
            Method servlet = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            servlet.invoke(this,request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回所有联系人
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void showall(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Contact> allContact = contact.getAllContact();
        if(allContact != null){
            request.setAttribute("list",allContact);
            request.getRequestDispatcher("list.jsp").forward(request,response);
        }else{
            request.setAttribute("status","你尚无联系人");
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }

    }

    /**
     * 添加联系人
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void addcontact(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println();
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

    /**
     * 删除联系人
     * @param request
     * @param response
     */
    private void delcontact(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException {
        if (contact.delContace(request)) {
            /*request.setAttribute("status","删除成功!");
            request.getRequestDispatcher("showall").forward(request,response);*/
            response.sendRedirect("big?action=showall");
        }else{
            request.setAttribute("status","删除失败,请重试!");
            request.getRequestDispatcher("big?action=showall").forward(request,response);
        }
    }



    private void upcontact(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        System.out.println();
        String id = request.getParameter("id");
        String name = request.getParameter("name");

        request.setAttribute("id",id);
        request.setAttribute("name",name);
        request.getRequestDispatcher("update.jsp").forward(request,response);
    }
    private void updateContact(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        try {
            System.out.println();
            if (contact.updateContact(request)) {
                /*request.setAttribute("status","修改成功!");
                request.getRequestDispatcher("showall").forward(request,response);*/
                response.sendRedirect("big?action=showall");
            }
        } catch (Exception e) {
            request.setAttribute("status","修改失败请重试!");
            request.getRequestDispatcher("update.jsp").forward(request,response);
        }
    }
    private void pagecontact(HttpServletRequest request,HttpServletResponse response){
        try {
            PageBean pageContact = contact.getPageContact(request);
            request.setAttribute("page",pageContact);
            request.getRequestDispatcher("page.jsp").forward(request,response);
        } catch (InvocationTargetException | ServletException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }

    }
}
