<%--
  Created by IntelliJ IDEA.
  User: Fanyi Xiao
  Date: 2019/1/2
  Time: 17:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>首页</title>
  </head>
  <body>

  <div>${empty status ? "": status}</div>
  <a href="showall">查看所有联系人信息</a>
  </body>
</html>
