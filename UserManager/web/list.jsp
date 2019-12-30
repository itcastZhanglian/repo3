<%@ page import="com.itheima.domain.Manager" %>
<%--
  Created by IntelliJ IDEA.
  User: HJ
  Date: 2019/11/21
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>用户信息管理系统</title>

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
    <script>
        function delUser(id) {
            if(confirm("您确认要删除吗")){
                location.href="${pageContext.request.contextPath}/delUserServlet?id="+id;
            }
        }

        window.onload=function () {

            document.getElementById("delSelected").onclick=function () {
                if(confirm("您确认删除选中的条目吗?一旦删除将无法恢复")){
                    document.getElementById("form").submit();
                }
            }

            /*全选按钮*/
            document.getElementById("firstCb").onclick=function () {
                var cbs = document.getElementsByName("uid");
                for (var cb of cbs) {
                    cb.checked=this.checked;
                }
            }
        }

        function setRows(rows) {
            location.href="${pageContext.request.contextPath}/findUserByPageServlet?rows="+rows+"&name=${condition.name}&address=${condition.address}&email=${condition.email}";
        }
    </script>
</head>
<%--<%
    Manager manager = (Manager) request.getSession().getAttribute("manager");
    String username="";
    if(manager!=null){
        username = manager.getUsername();
    }
%>--%>
<body>
<%--<c:choose>
    <c:when test="${ empty manager}">
        您还未登录,无权查看该页面,请<a href="${pageContext.request.contextPath}/login.jsp">登录</a>
    </c:when>
    <c:otherwise>

    </c:otherwise>
</c:choose>--%>
    <div class="container">
    <p align="right">欢迎您，<font color="red">${sessionScope.manager.username}</font>先生/女士
        <a href="${pageContext.request.contextPath}/logoutServlet">退出</a>
    </p>
    <h3 style="text-align: center">用户信息列表</h3>

    <!--搜索输入框-->
    <div style="float: left;">
        <form class="form-inline" action="${pageContext.request.contextPath}/findUserByPageServlet" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" name="name" class="form-control" id="exampleInputName2" value="${condition.name}" >
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" name="address" class="form-control" id="exampleInputName3" value="${condition.address}">
            </div>

            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="text" name="email" class="form-control" id="exampleInputEmail2" value="${condition.email}" >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <div style="float: right;margin: 5px;">

        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a>

    </div>
    <!--用户列表信息-->
    <form action="${pageContext.request.contextPath}/delSelectedServlet" method="post" id="form">
        <table border="1" class="table table-bordered table-hover">
            <tr class="success">
                <th><input type="checkbox" id="firstCb"></th>
                <th>编号</th>
                <th>姓名</th>
                <th>性别</th>
                <th>年龄</th>
                <th>籍贯</th>
                <th>QQ</th>
                <th>邮箱</th>
                <th>操作</th>
            </tr>
            <%--通过jstl标签库遍历User的list集合--%>
            <c:forEach items="${pb.list}" var="user" varStatus="status">
                <tr>
                    <td><input type="checkbox" name="uid" value="${user.id}"></td>
                    <td>${status.count}</td>
                    <td>${user.name}</td>
                    <td>${user.gender}</td>
                    <td>${user.age}</td>
                    <td>${user.address}</td>
                    <td>${user.qq}</td>
                    <td>${user.email}</td>
                    <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/findUserServlet?id=${user.id}">修改</a>&nbsp;
                        <a class="btn btn-default btn-sm" href="javascript:delUser(${user.id})">删除</a>
                    </td>
                </tr>
            </c:forEach>


        </table>
    </form>

    <!--分页条信息-->
    <div>
        <nav>
            <ul class="pagination">
                <%--如果当前是第一页,则上一页按钮不可用--%>
                <c:if test="${pb.currentPage==1}">
                    <li class="disabled">
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${pb.currentPage!=1}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage-1}&rows=${pb.rows}&name=${condition.name}&address=${condition.address}&email=${condition.email}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>

                <%--<li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li class="active"><a href="#">4</a></li>
                <li><a href="#">5</a></li>--%>
                <c:forEach begin="${pb.startPage}" end="${pb.endPage}" var="i" step="1">
                    <li ${i==pb.currentPage?"class='active'":""}>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=${pb.rows}&name=${condition.name}&address=${condition.address}&email=${condition.email}">${i}</a>
                    </li>
                </c:forEach>
                <%--如果当前是第一页,则上一页按钮不可用--%>
                <c:if test="${pb.currentPage==pb.totalPage}">
                    <li class="disabled">
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:if test="${pb.currentPage!=pb.totalPage}">
                    <li>
                        <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage+1}&rows=${pb.rows}&name=${condition.name}&address=${condition.address}&email=${condition.email}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>

                <span style="font-size: 25px;margin-left: 5px;">
                            共${pb.totalCount}条记录，共${pb.totalPage}页
                        </span>
                <select name="rows" id="rows" style="font-size: 25px;height:30px;weight:80px" onchange=setRows(this.value)>
                    <option value="5" ${pb.rows==5?"selected":""}>每页5条</option>
                    <option value="10" ${pb.rows==10?"selected":""}>每页10条</option>
                    <option value="20" ${pb.rows==20?"selected":""}>每页20条</option>
                    <option value="100" ${pb.rows==100 ?"selected":""}>每页100条</option>
                </select>
            </ul>
        </nav>

    </div>
</div>
</body>
</html>