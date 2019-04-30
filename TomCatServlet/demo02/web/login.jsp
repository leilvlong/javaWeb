<%--@elvariable id="status" type="java"--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%

    /*String status;
    status = (String) request.getAttribute("status");
    if(status == null){
        status = "";
    }*/

    /*String username = "";
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        if("username".equals(cookie.getName())){
            username = cookie.getValue();
            break;
        }
    }*/

    /*String checked = "checked='checked'";
    if("".equals(username)){
        checked = "";
    }*/
%>
<body>

    <%--<h1>${cookie.username.value}</h1>--%>
    <%--<div><%=status%></div>--%>
    <div>${empty status ? "": status}</div>
    <form method="post" action="login" >
          id: <input type="text" name="id"><br>
        姓名: <input type="text" name="username" value=${cookie.username.value}><br>
        验证码<input type="text" name="vcode"><img src="codeimg"  onclick="changeVcode(this)"><br>
        记住账号:<input type="checkbox" name="isusername" ${empty cookie.username.value ? "":"checked='checked'" }><br>
        自动登录<input type="checkbox" name="auto"><br>
        <input type="submit" value="提交">
    </form>

    <script>
        function changeVcode(obj) {
            //改变验证码，其实就是重新设置这个img标签的src属性
            obj.src = "codeimg?time="+new Date()
        }
    </script>
</body>
</html>
