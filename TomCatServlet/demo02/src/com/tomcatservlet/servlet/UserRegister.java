package com.tomcatservlet.servlet;

import com.tomcatservlet.javabean.DataBean;
import com.tomcatservlet.javabean.Studen;
import com.tomcatservlet.javabean.DataBean;
import com.tomcatservlet.service.HttpUtil;
import com.tomcatservlet.service.UserAction;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/register")
public class UserRegister extends HttpServlet {
    UserAction ua = new UserAction();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, String[]> studenMsg = request.getParameterMap();
        try {
            //Studen studen = DataBean.dataBean(Studen.class, studenMsg);

            Studen studen = new Studen();
            BeanUtils.populate(studen,studenMsg);




            boolean flag = ua.registerStuden(studen);
            if(flag){
                help(request,response,"注册成功,请登录吧","login.jsp");
            }else{
                help(request,response,"注册失败,请重新尝试","register.jsp");
            }
        } catch (Exception e) {
            help(request,response,"注册失败,请重新尝试","register.jsp");
        }

    }

    private void help(HttpServletRequest request, HttpServletResponse response, String msg, String targer) throws ServletException, IOException {
        /*request.setAttribute("status","注册失败,请重新尝试");
            HttpUtil.requestFroward(request,response,"register.jsp");*/
        request.setAttribute("status",msg);
        HttpUtil.requestFroward(request,response,targer);
    }
}
