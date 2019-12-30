<%@ page import="java.net.URLDecoder" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.net.URLEncoder" %>
<%@ page import="com.itheima.utils.CookieUtils" %><%--
  Created by IntelliJ IDEA.
  User: HJ
  Date: 2019/11/21
  Time: 21:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>首页</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">


    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="js/bootstrap.min.js"></script>
    <style>
        .paddtop{
            padding-top: 10px;
        }
        .search-btn{
            float: left;
            border:1px solid #ffc900;
            width: 90px;
            height: 35px;
            background-color:#ffc900 ;
            text-align: center;
            line-height: 35px;
            margin-top: 15px;
        }

        .search-input{
            float: left;
            border:2px solid #ffc900;
            width: 400px;
            height: 35px;
            padding-left: 5px;
            margin-top: 15px;
        }
        .jx{
            border-bottom: 2px solid #ffc900;
            padding: 5px;
        }
        .company{
            height: 40px;
            background-color: #ffc900;
            text-align: center;
            line-height:40px ;
            font-size: 8px;
        }
    </style>
</head>
<%
    response.setContentType("text/html;charset=utf-8");
    //获取Cookie数组
    Cookie[] cookies = request.getCookies();
    //调用Cookie工具类方法
    Cookie lastTime = CookieUtils.findCookie(cookies, "lastTime");
    String lasttime="";
    if (lastTime==null){
        //首次访问
        lasttime="欢迎您首次访问";

    }else{
        //不是第一次访问
        lasttime="您上一次访问的时间是"+URLDecoder.decode(lastTime.getValue(), "utf-8");
    }
    //记录系统时间,返回浏览器cookie
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String dateStr = sdf.format(new Date());
    //对格式化的时间进行编码,以防空格问题
    dateStr= URLEncoder.encode(dateStr,"utf-8");
    Cookie c = new Cookie("lastTime", dateStr);
    c.setMaxAge(60*60*24*30);//一个月的cookie存储时间
    response.addCookie(c);
%>

<body>

<!-- 1.页眉部分-->
<header class="container-fluid">
    <div class="row">
        <img src="img/top_banner.jpg" class="img-responsive">
    </div>
    <p><%= lasttime%></p>
    <div class="row paddtop">
        <div class="col-md-4">
            <img src="img/logo.jpg" class="img-responsive">
        </div>
        <div class="col-md-5">
            <input class="search-input" placeholder="请输入线路名称">
            <a class="search-btn" href="#">搜索</a>
        </div>
        <div class="col-md-3">
            <img src="img/hotel_tel.png" class="img-responsive">
        </div>
    </div>
    <!--导航栏-->
    <div class="row">
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <!-- 定义汉堡按钮 -->
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">首页</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <!--<li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>
                        <li><a href="#">Link</a></li>-->
                        <li><a href="#">更多功能正在开发中... ...</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">管理员 <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="./login.jsp">管理员登录</a></li>
                                <li><a href="#">Another action</a></li>
                                <li><a href="#">Something else here</a></li>
                                <li role="separator" class="divider"></li>
                                <li><a href="#">Separated link</a></li>
                            </ul>
                        </li>
                    </ul>
                </div><!-- /.navbar-collapse -->
            </div><!-- /.container-fluid -->
        </nav>

    </div>

    <!--轮播图-->
    <div class="row">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <img src="img/banner_1.jpg" alt="...">
                </div>
                <div class="item">
                    <img src="img/banner_2.jpg" alt="...">
                </div>
                <div class="item">
                    <img src="img/banner_3.jpg" alt="...">
                </div>

            </div>

            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>



    </div>

</header>
<!-- 2.主体部分-->
<div class="container-fluid">
    <div class="row jx">
        <img src="img/icon_5.jpg">
        <span>黑马精选</span>
    </div>

    <div class="row paddtop">
        <div class="col-md-3">
            <div class="thumbnail">
                <img src="img/jiangxuan_1.jpg" alt="">
                <p>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</p>
                <font color="red">&yen; 699</font>
            </div>
        </div>
        <div class="col-md-3">
            <div class="thumbnail">
                <img src="img/jiangxuan_2.jpg" alt="">
                <p>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</p>
                <font color="red">&yen; 699</font>
            </div>

        </div>
        <div class="col-md-3">

            <div class="thumbnail">
                <img src="img/jiangxuan_3.jpg" alt="">
                <p>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</p>
                <font color="red">&yen; 699</font>
            </div>
        </div>
        <div class="col-md-3">

            <div class="thumbnail">
                <img src="img/jiangxuan_4.jpg" alt="">
                <p>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</p>
                <font color="red">&yen; 699</font>
            </div>
        </div>


    </div>
    <div class="row jx">
        <img src="img/icon_6.jpg">
        <span>国内游</span>
    </div>
    <div class="row paddtop">
        <div class="col-md-4">
            <img src="img/guonei_1.jpg">
        </div>
        <div class="col-md-8">
            <div class="row">
                <div class="col-md-4">
                    <div class="thumbnail">
                        <img src="img/jingxuan_2.jpg" alt="">
                        <p>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</p>
                        <font color="red">&yen; 699</font>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="thumbnail">
                        <img src="img/jiangxuan_5.jpg" alt="">
                        <p>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</p>
                        <font color="red">&yen; 699</font>
                    </div>

                </div>
                <div class="col-md-4">

                    <div class="thumbnail">
                        <img src="img/jiangxuan_4.jpg" alt="">
                        <p>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</p>
                        <font color="red">&yen; 699</font>
                    </div>
                </div>

            </div>
            <div class="row">
                <div class="col-md-4">
                    <div class="thumbnail">
                        <img src="img/jiangxuan_3.jpg" alt="">
                        <p>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</p>
                        <font color="red">&yen; 699</font>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="thumbnail">
                        <img src="img/jiangxuan_2.jpg" alt="">
                        <p>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</p>
                        <font color="red">&yen; 699</font>
                    </div>

                </div>
                <div class="col-md-4">

                    <div class="thumbnail">
                        <img src="img/jiangxuan_1.jpg" alt="">
                        <p>上海直飞三亚5天4晚自由行(春节预售+亲子/蜜月/休闲游首选+豪华酒店任选+接送机)</p>
                        <font color="red">&yen; 699</font>
                    </div>
                </div>


            </div>

        </div>

    </div>
</div>
<!-- 3.页脚部分-->
<footer class="container-fluid">
    <div class="row">
        <img src="img/footer_service.png" class="img-responsive">
    </div>
    <div class="row company">
        江苏传智播客教育科技股份有限公司 版权所有Copyright 2006-2018, All Rights Reserved 苏ICP备16007882
    </div>

</footer>


</body>
</html>
