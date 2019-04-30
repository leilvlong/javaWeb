package com.tomcatservlet.servlet;

import com.tomcatservlet.javabean.Studen;
import com.tomcatservlet.jdbcutil.LoginUtil;
import com.tomcatservlet.service.HttpUtil;
import com.tomcatservlet.service.UserAction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/login")
public class UserLogin extends HttpServlet {
    private UserAction ua = new UserAction();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置编码方式
        request.setCharacterEncoding("utf-8");

        // 用户获取信息
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        //相关信息
        String isusername = request.getParameter("isusername");
        String vcode = request.getParameter("vcode");
        String auto = request.getParameter("auto");

        // 验证码验证
        HttpSession session = request.getSession();
        String code_session = (String) session.getAttribute("code_session");
        if(vcode.equalsIgnoreCase(code_session)){
            // 是否记住用户名
            Cookie cookie1;
            if(isusername != null){
                cookie1 = LoginUtil.getCookie("username",username, 7 * 24 * 60 * 60, request.getContextPath());
            }else{
                cookie1 = LoginUtil.getCookie("username", "", 0, request.getContextPath());
            }
            response.addCookie(cookie1);

            //根据信息获取对应数据
            Studen studen = ua.getStuden(id, username);
            //如果登录成功
            if(studen != null){
                //设置cookie 如果用户选择记住用户名

                // 设置cookie 记录登录时间
                Date date = new Date();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd/HH:mm:ss");
                String format = df.format(date);
                Cookie time = LoginUtil.getCookie("time", format, 7 * 24 * 60 * 60, request.getContextPath());
                response.addCookie(time);

                 if(auto != null){
                     Cookie studenCookie = LoginUtil.getCookie("studen", studen.getId() + "#" + studen.getName(), 7 * 24 * 60 * 60, request.getContextPath());
                     response.addCookie(studenCookie);
                 }

                 session.setAttribute("studen",studen);

                // 返回
                response.sendRedirect("index.jsp");
            }else{
                request.setAttribute("status","账号或密码错误");
                HttpUtil.requestFroward(request,response,"login.jsp");
            }
        }else{
            request.setAttribute("status","验证码错误");
            HttpUtil.requestFroward(request,response,"login.jsp");
        }

    }
}
