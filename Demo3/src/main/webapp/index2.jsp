<%--
  Created by IntelliJ IDEA.
  User: 56207
  Date: 2022/6/16
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--不以/开始的相对路径找资源，以当前资源的路径为基础，容易出错--%>
<%--以/开头的绝对路径，找资源，以服务器的路径为标准，http：//localhost:3306,需加上项目名--%>
<html>
<head>
    <title>员工列表</title>
    <%
        pageContext.setAttribute("APP_PATH",request.getContextPath());
    %>
    <script type="text/javascript"
            src="${APP_PATH}/static/js/jquery-1.12.4.min.js"></script>
    <link
            href="${APP_PATH}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css"
            rel="stylesheet">
    <script
            src="${APP_PATH}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-lg-12">
            <h1>部门人员信息查询</h1>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-12">
            <table class="table table-bordered table-hover ">
                <tr>
                    <th>ID</th>
                    <th>名字</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>部门名称</th>
                    <th>操作</th>
                </tr>

            </table>
        </div>
    </div>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <button type="button" class="btn btn-primary">新增</button>
            <button type="button" class="btn btn-danger">删除</button>
        </div>
    </div>
    <div class="row">
        <div class="col-md-6">
            当前页，总页，总条记录
        </div>
        <div class="col-md-6">

        </div>
    </div>
</div>
<script type="text/javascript">
    //1.页面加载完成，发送一个ajax请求，获取分页数据
    $(function (){
        $.ajax({
            uri:"${APP_PATH}/emps",
            data:{"pn":1},
            type:"GET",
            dataType:"json",
            success:function (result){
                //console.log(result);
                //解析显示数据
                //解析显示分页信息
                alert("信息");
            }
        });
    });
    function build_emps_table(result){
        var emps = result.extend.pageInfo.list;
        $.each(emps,function (index,item){
           alert(item);
        });
    }
    function build_page_nav(result) {

    }
</script>
</body>
</html>
