<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2019/4/29
  Time: 17:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>首页</title>
</head>

<%

    Map<String,String> list = new HashMap<>();

    list.put("1","孙悟空");

    list.put("2","吉莲");

    session.setAttribute("list",list);

%>
<body>
    <%--
        怎么判断是否已登录，看session域对象中有没有存放user
    --%>
    <c:if test="${empty studen}">
        <%--未登录--%>
        <div>
            <a href="login.jsp">登录</a>
            <a href="register.jsp">注册</a>
            <a href="car.jsp">购物车</a>
        </div>
    </c:if>

    <c:if test="${not empty studen}">
        <%--已登录--%>
        <div>
            <h2>欢迎${studen.name}登录,上次登录时间是:${cookie.time.value}</h2>

            <a href="car.jsp">购物车</a>
        </div>
    </c:if>
</body>
</html>
