<%--
  Created by IntelliJ IDEA.
  User: HJ
  Date: 2019/11/21
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <title>管理员登录</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <script type="text/javascript"></script>
    <style>
        .container{
            width: 400px;
            background-color: white;
            margin-top: 10%;
            margin-left: 25%;
            border-radius: 5px;
            border: 1px solid darkgrey;
        }

    </style>
    <script>
        function refreshCode() {
            document.getElementById("vcode").src="/UserManager/checkCodeServlet?time="+new Date().getTime()
        }
    </script>
    <!--<script>
        //验证码换一换功能
        function refreshCode() {
          //图片是一个静态资源，浏览器会缓存静态资源
          document.getElementById("vcode").src="/UserManager/checkCodeServlet?time="+new Date().getTime();
        }
    </script>-->
</head>
<%
    String errorMsg="请速速上车!";
    if (request.getAttribute("errorMsg")!=null){
        errorMsg = (String) request.getAttribute("errorMsg");
    }
%>
<body style="background: url('./img/02.jpg')">
<div class="container" style="">
    <h3 style="text-align: center;">管理员登录</h3>
    <form action="/UserManager/login" method="post">
        <div class="form-group">
            <label for="user">用户名：</label>
            <input type="text" name="username" class="form-control" id="user" value="${cookie.remember.value}" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码"/>
        </div>

        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input type="text" name="verifycode" class="form-control" id="verifycode" placeholder="请输入验证码" style="width: 120px;"/>
            <a href="javascript:refreshCode()"><img src="/UserManager/checkCodeServlet" title="看不清点击刷新" id="vcode"/></a>
        </div>
        <hr/>
        <div class="form-group form-horizontal">
            <input type="checkbox" name="remember" value="rem" ${empty cookie.remember?"":"checked"}>记住用户名
            <input class="btn btn-primary" style="display:inline-block;width: 60%;margin-left: 10%" type="submit" value="登 录">
        </div>
    </form>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span>&times;</span></button>
        <strong><%= errorMsg%></strong>
    </div>
</div>
</body>
</html>
