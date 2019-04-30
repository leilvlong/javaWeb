<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>Bootstrap模板</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">显示所有联系人</h3>
    <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <%--从域对象中读取出来联系人的集合，并遍历这些集合--%>
        <c:forEach items="${page.list}" var="contact">
            <tr>
                <td>${contact.id}</td>
                <td>${contact.name}</td>
                <td>${contact.sex}</td>
                <td>${contact.age}</td>
                <td>${contact.address}</td>
                <td>${contact.qq}</td>
                <td>${contact.email}</td>
                <td>
                    <a class="btn btn-default btn-sm" href="upcontact?id=${contact.id}&name=${contact.name}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="javascript:;" onclick="del('${contact.id}','${contact.name}')">删除</a>
                </td>
            </tr>
        </c:forEach>
        <script>
            /**
             * 删除联系人的函数
             */
            function del(id,name) {
                var flag = confirm("您确定要删除吗?");
                if (flag) {
                    //要删除
                    //发送一个请求到服务器的Servlet，删除指定商品，要向服务器传递要删除的联系人的id
                    //在这里怎么发送请求到服务器的Servlet
                    location.href = "delcontact?id="+id+"&name="+name;
                }
            }
        </script>
        <%--<tr>
            <td>2</td>
            <td>张三</td>
            <td>男</td>
            <td>20</td>
            <td>广东</td>
            <td>44444222</td>
            <td>zs@qq.com</td>
            <td><a class="btn btn-default btn-sm" href="修改联系人.html">修改</a>&nbsp;<a class="btn btn-default btn-sm" href="修改联系人.html">删除</a></td>
        </tr>--%>
        <tr>
            <td colspan="8" align="center"><a class="btn btn-primary" href="add.jsp">添加联系人</a></td>
        </tr>
        <tr>
            <td align="center" colspan="8">
                总共有${page.totalPage}页|当前是第${page.curPage}页|总共有${page.totalSize}条数据|每页显示${page.pageSize}条数据
            </td>
        </tr>
        <%--展示页码--%>
        <tr>
            <td align="center" colspan="8">
                <nav aria-label="Page navigation">
                    <ul class="pagination">
                        <%--上一页的小图标
                            什么时候显示上一页的小图标，当前页数不是1
                        --%>
                        <c:if test="${page.curPage != 1}">
                            <li>
                                    <%--上一页是当前页-1--%>
                                <a href="page?curPage=${page.curPage - 1}" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>

                        <%--展示页码
                            从1开始，到总页数结束，每遍历一下就加一个页码
                        --%>
                        <c:forEach begin="1" end="${page.totalPage}" var="i">
                            <%--当前页要高亮显示--%>
                            <c:if test="${page.curPage == i}">
                                <li class="active"><a href="javascript:;">${i}</a></li>
                            </c:if>

                            <c:if test="${page.curPage != i}">
                                <li><a href="page?curPage=${i}">${i}</a></li>
                            </c:if>
                        </c:forEach>
                        <%--下一页的小图标
                            什么时候显示下一页的小图标,当前页数不等于总页数
                        --%>
                        <c:if test="${page.curPage != page.totalPage}">
                            <li>
                                    <%--下一页是当前页+1--%>
                                <a href="page?curPage=${page.curPage + 1}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                    </ul>
                </nav>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
