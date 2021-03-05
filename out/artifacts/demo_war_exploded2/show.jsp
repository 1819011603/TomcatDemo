<%--
  Created by IntelliJ IDEA.
  User: 18190
  Date: 2021/2/7
  Time: 12:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    String name = request.getParameter("uname");
    int age = Integer.parseInt(request.getParameter("uage"));
    String passwd = request.getParameter("upw");
    String[] hoppies = request.getParameterValues("uiter");

%>
注册成功 信息如下 <br/>
名字<%= name%> <br/>
年龄<%= age%> <br/>
密码<%= passwd%> <br/>
爱好<br/><%
    if(hoppies!=null)
    for(String s: hoppies){
    out.print(s + "&nbsp;");
}

%> <br/>
</body>
</html>
