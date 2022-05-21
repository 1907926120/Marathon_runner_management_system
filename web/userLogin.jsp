<%--
  Created by IntelliJ IDEA.
  User: 19079
  Date: 2021/12/27
  Time: 23:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>马拉松名人堂-用户登录</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap4/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap4/css/bootstrap-theme.min.css">
    <script src="${pageContext.request.contextPath}/bootstrap4/js/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/bootstrap4/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="jquery-easyui-1.7.0/themes/super/css/font-awesome.min.css">

    <style type="text/css">
        .c{
            border: 1px solid slategray;
        }
        .font1{
            /*标题字体*/
            font-family: "可口可乐在乎体 楷体";
            font-size: 40px;
        }
        .font2{
            /*按钮字体*/
            font-family: 楷体;
            font-size: 28px;
            color: #9d9d9d;
        }
        .font3{
            /*其他*/
            font-family: 楷体;
            font-size: 28px;
            color: #333333;
        }
    </style>
    <script type="text/javascript">
        function resetValue() {
            document.getElementById("userName").value="";
            document.getElementById("password").value="";

        }
    </script>
  </head>
  <body style="background-image:url(images/userLogin.jpg);background-origin: content-box">

<div class="container-fluid">
    <div class="row" >
        <div class="col-md-2" align="center">
           <img src="images/n9.ico">
            <a href="index.jsp" class="btn btn-default btn-lg active" role="button"><i class="fa fa-circle-o-notch fa-spin"></i>管理员入口</a>
        </div>
    </div>
</div>
<div align="center"   class="container-fluid">
    <div class="row ">
        <div class="col-md-6 col-md-offset-3 ">
            <blockquote>
                <p class="bg-warning font1" >马拉松名人堂<small>Marathoner management system</small></p>
            </blockquote>
        </div>
    </div>
</div>
<form action="login2" method="post">
    <div class="container-fluid">
        <div class="row" >
            <div class="col-md-1 col-md-offset-4">
                <kbd class="font2">账  号:</kbd>
            </div>
            <div class="col-md-3" style="padding-left: 0px">
                <input type="text" class="form-control" value="${userName2}" id="userName2" name="userName2" placeholder="请输入您的账号" >
                <small id="userNameHelp" class="form-text text-muted">We'll never share your userName with anyone else.</small>
            </div>
        </div>
        <div class="row" >
            <div class="col-md-1 col-md-offset-4">
                <kbd class="font2">密  码:</kbd>
            </div>
            <div class="col-md-3 " style="padding-left: 0px">
                <input type="password" class="form-control" value="${password2}" id="password2" name="password2" placeholder="请输入您的密码">
            </div>
        </div>
        <div class="row" >
<%--           <--% submit与servlet相联系--%>
            <div class="col-md-1 col-md-offset-5" align="center">
                <button type="submit" class="btn btn-success ">
                    <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>登录
                </button>
            </div>

            <div class="col-md-1" align="center">
                <button type="button" class="btn btn-warning" onclick="resetValue()">
                    <span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>重置
                </button>
            </div>
            <div class="col-md-2">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                    <span aria-hidden="true" style="color:crimson">${error}</span>
                </button>
            </div>
        </div>
    </div>
</form>


<%--<div class="container-fluid">--%>
<%--    <div class="row" >--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--        <div class="col-md-1 c">.col-md-1</div>--%>
<%--    </div>--%>
<%--</div>--%>
  </body>
</html>
