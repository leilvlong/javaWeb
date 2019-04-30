<%--
  Created by IntelliJ IDEA.
  User: L1455013965
  Date: 2019/4/23
  Time: 19:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%

    String status;
    status = (String) request.getAttribute("status");
    if(status == null){
        status = "";
    }
%>
<body>
<div><%=status%></div>
<form method="post" action="register" >
    姓名: <input type="text" name="name"><br>
    性别:   男<input type="radio" name="gender" value="true">女<input type="radio" name="gender" value="false"><br>
    年龄: <input type="text" name="age"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>
