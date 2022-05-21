<%--
  Created by IntelliJ IDEA.
  User: 19079
  Date: 2021/12/28
  Time: 22:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<title>主界面-管理员</title>
<head>
    <title>管理员界面</title>
<%--    <%--%>
<%--        //权限验证--%>
<%--        if (session.getAttribute("currentUser") == null) {--%>
<%--            response.sendRedirect("index.jsp");--%>
<%--        }--%>
<%--    %>--%>
    <!--easyui-->
    <script src="jquery-easyui-1.7.0/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="jquery-easyui-1.7.0/jquery.easyui.min.js" type="text/javascript" charset="utf-8"></script>
    <script src="jquery-easyui-1.7.0/locale/easyui-lang-zh_CN.js" type="text/javascript" charset="utf-8"></script>
    <!--测试dist-->
    <link rel="stylesheet" href="jquery-easyui-1.7.0/themes/super/css/font-awesome.min.css">
    <link rel="stylesheet" href="jquery-easyui-1.7.0/themes/super/superRed.css" id="themeCss">
    <!--模源-->
    <script src="jquery-easyui-1.7.0/themes/super/super.js"></script>
    <script src="jquery-easyui-1.7.0/themes/super/superDemo.js" type="text/javascript" charset="utf-8"></script>
    <script src="echarts/echarts.min.js"></script>

    <!--波浪线-->
    <style type="text/css">
        .svg-wave {
            background: url("data:image/svg+xml,%3Csvg xmlns='http://www.w3.org/2000/svg' viewBox='0 0 20 4'%3E%3Cpath fill='none' stroke='%23333' d='M0 3.5c5 0 5-3 10-3s5 3 10 3 5-3 10-3 5 3 10 3'/%3E%3C/svg%3E") repeat-x 0 100%;
            background-size: 20px auto;
        }
    </style>


</head>
<body id="main2" class="easyui-layout">

            <!--north 顶部-->
<div data-options="region:'north',border:false" class="super-north" >
    <!--顶部-->
    <div class="super-navigation">
        <!--系统名称-->
        <div class="super-navigation-title">马拉松名人堂</div>
        <!--自定义导航-->
        <div class="super-navigation-main">
            <div class="super-setting-left">
                <ul>
                    <li><i class="fa fa-commenting-o"></i>消息</li>
                    <li><i class="fa fa-envelope-o"></i>邮件</li>
                    <li><i class="fa fa-bell-o"></i>通知</li>
                </ul>
            </div>
            <div class="super-setting-right">
                <ul>
                    <!--详情消息简介-->
                    <li data-url="example/message.html">
                        <div class="super-theme-example">
                            <a id="msgDemo4" href="javascript:void(0)" class="easyui-linkbutton"  style="background-color:transparent;border:transparent ">
                                <i class="fa fa-info"  style="color: white"></i>
                            </a>
                            <script type="text/javascript">
                                $("#msgDemo4").on('click', function() {
                                    $.messager.show({
                                        title: '<i class="fa fa-info-circle"></i>&nbsp系统简介',
                                        msg: '马拉松名人堂”管理系统，又名“marathon runner management system”。为博子采用Bootstrap和Github上流行的开源美化easyui框架，使用HTML+CSS+JS结合Java技术结合ajax，jQuery等进行独立开发。',
                                        timeout: 6000,
                                        showType: 'slide'
                                    });
                                });
                            </script>
                        </div>
                    </li>
                    <!--设置菜单-->
                    <li>
                        <div class="super-setting-icon">
                            <i class="fa fa-cog fa-spin"></i>
                        </div>
                        <div id="mm" class="easyui-menu">
                            <div>
                                <p id="msgDemo5" href="javascript:void(0)" >个人中心</p>
                                <script type="text/javascript">
                                    $("#msgDemo5").on('click', function() {
                                        $.messager.show({
                                            height: 42,
                                            timeout: 8000,
                                            showSpeed: 200,
                                            msg: '<i class="fa fa-info-circle"></i>&nbsp; 欢迎管理员:${currentUser.userName}使用本管理系统',
                                            style: {
                                                right: '',
                                                top: document.body.scrollTop + document.documentElement.scrollTop + 20,
                                                bottom: '',
                                                'z-index': 999,
                                                'box-shadow': '0 1px 6px rgba(0,0,0,.2)'
                                            }
                                        });
                                    });
                                </script>
                            </div>
                            <div id="themeSetting">主题</div>
                            <div class="menu-sep"></div>
                            <div id="logout2">退出
                                <script type="text/javascript">
                                    $("#logout2").on('click', function() {
                                        $.messager.confirm('提示', '确定退出系统？', function(r) {
                                            if(r) {
                                                console.log('确定退出');
                                                top.location = "index.jsp";　　// top    该变更永远指分割窗口最高层次的浏览器窗口。如果计划从分割窗口的最高层次开始执行命令，就可以用top变量。
                                                return false;
                                            }else{
                                                top.location = "main.jsp";
                                            }
                                        });
                                    });
                                </script>
                            </div>
                        </div>
                    </li>
                    <!--管理员简介-->
                    <li class="user">
                        <span class="user-icon"><img src="images/jpqg.png"/></span>管理员
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>

            <!--west 左侧-->
<div data-options="region:'west',title:'MAIN NAVIGATION',border:false" class="super-west">
    <!--左侧导航-->
<%--    文字不能一样，不然不能共同显示--%>
    <div class="easyui-accordion" data-options="border:false,fit:true,selected:true">
        <div title="管理员管理" data-options="iconCls:'fa fa-group'" >
            <ul>
                <li data-url='infoPages/adminInfoManage.html'>管理员信息</li>
                <li data-url='infoPages/adminManage.html'>账号/密码管理(管理员)</li>
            </ul>
        </div>
        <div title="用户管理" data-options="iconCls:'fa fa-user-circle-o'" >
            <ul>
                <li data-url='infoPages/userInfoManage.html'>用户信息</li>
                <li data-url='infoPages/userManage.html'>账号/密码管理(用户)</li>
            </ul>
        </div>
        <div title="运动员管理" data-options="iconCls:'fa fa-hand-spock-o'" >
            <ul>
                <li data-url='infoPages/marathonerInfoManage.html'>运动员信息</li>
            </ul>
        </div>
        <div title="可视化大屏（运动员）" data-options="iconCls:'fa fa-area-chart'" >
            <ul>
                <li data-url='infoPages/chartLine.html'>折线图</li>
                <li data-url='infoPages/diagramNightingaleRose.html'>南丁格尔玫瑰图</li>
            </ul>
        </div>

<%--        <div title="布局" data-options="iconCls:'fa fa-desktop'" >--%>
<%--            <ul>--%>
<%--                <li data-url='example/panel.html'>面板</li>--%>
<%--                <li data-url='example/tabs.html'>选项卡</li>--%>
<%--                <li data-url='example/accordion.html'>分类</li>--%>
<%--                <li data-url='example/layout.html'>布局</li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--        <div title="基本元素" data-options="iconCls:'fa fa-wpforms'">--%>
<%--            <ul>--%>
<%--                <li data-url='example/button.html'>按钮</li>--%>
<%--                <li data-url='example/form.html'>表单</li>--%>
<%--                <li data-url="example/menu.html">菜单</li>--%>
<%--                <li data-url='example/other.html'>其他</li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--        <div title="窗口" data-options="iconCls:'fa fa-window-maximize'">--%>
<%--            <ul>--%>
<%--                <li data-url='example/window.html'>窗口</li>--%>
<%--                <li data-url='example/dialog.html'>对话框</li>--%>
<%--                <li data-url="example/message.html">消息窗口</li>--%>
<%--            </ul>--%>
<%--        </div>--%>
<%--        <div title="表格和树" data-options="iconCls:'fa fa-table'">--%>
<%--            <ul>--%>
<%--                <li data-url='example/datagrid.html'>表格</li>--%>
<%--                <li data-url='example/tree.html'>树</li>--%>
<%--            </ul>--%>
<%--        </div>--%>
        <div title="扩展" data-options="iconCls:'fa fa-sitemap'">
            <ul>
                <li data-url=''>HELLO FRIENDS!!!</li>
            </ul>
        </div>
    </div>
</div>


            <!--center 主要内容-->
<div data-options="region:'center'" style="padding-top: 2px;">
    <!--主要内容-->
    <div id="tt" class="easyui-tabs" data-options="border:false,fit:true">
        <div title="首页" data-options="iconCls:'fa fa-home'" style="background-image: url(images/center.jpg)">
            <div style="padding: 20px;">
                        <table  width="240" height="150">
                            <tr></tr>
                        </table>
            </div>
                <p align="center" ><img src="images/n3.ico"><font class="svg-wave" color="#ffd700" size="40"  style="font-weight: bold;font-family:王羲之书法字体" >欢迎使用“马拉松名人堂”管理系统</font></p>
            </div>
        </div>
    </div>
</div>


            <!--south 页脚-->
<div data-options="region:'south'" class="super-south">
    <!--页脚-->
    <div class="super-footer-info">
        <span><i class="fa fa-info-circle"></i> 作者：博子</span>
        <span><i class="fa fa-copyright"></i> CopyRight 2022 版权所有 <i class="fa fa-caret-right"></i></span>
    </div>
</div>

<!--主题设置弹窗-->
<div id="win">
    <div class="themeItem">
        <ul>
            <li>
                <div class="superGreen" style="background: #1abc9c;">green</div>
            </li>
            <li class="themeActive">
                <div class="superBlue" style="background: #3498db;">blue</div>
            </li>
            <li>
                <div class="superGray" style="background: #95a5a6;">gray</div>
            </li>
            <li>
                <div class="superAmethyst" style="background: #9b59b6;">amethyst</div>
            </li>
            <li>
                <div class="superBlack" style="background: #34495e;">black</div>
            </li>
            <li>
                <div class="superYellow" style="background: #e67e22;">yellow</div>
            </li>
            <li>
                <div class="superEmerald" style="background: #2ecc71;">emerald</div>
            </li>
            <li>
                <div class="superRed" style="background: #e74c3c;">red</div>
            </li>
        </ul>
    </div>
</div>

</body>

</html>