package com.tomcatservlet.start;

import com.tomcatservlet.util.JdbcUtil;
import com.tomcatservlet.util.Studen;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login")
public class LogingTest extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String id = request.getParameter("id");
        String sql = "select * from studen where name=? and id=?";
        JdbcTemplate jdbc = new JdbcTemplate(JdbcUtil.getDataSource());

        response.setContentType("text/html;charset=utf-8");
        PrintWriter writer = response.getWriter();
        Studen studen= null;
        try{
            studen = jdbc.queryForObject(sql, new BeanPropertyRowMapper<>(Studen.class), username, Integer.valueOf(id));
            writer.write("学生信息: " + studen.toString());
        }catch (Exception e){
            writer.write("数据库尚无该学生记录" );

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
