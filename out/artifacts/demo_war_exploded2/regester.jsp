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
<form action="show.jsp" method="post">
    username <input type="text" name="uname"> <br/>
    password <input type="password" name="upw"> <br/>
    userage <input type="text" name="uage"> <br/>
    interest <br/>
    <input type="checkbox" name="uiter" value="足球"> 足球
    <input type="checkbox" name="uiter" value="篮球"> 篮球
    <input type="checkbox" name="uiter" value="羽毛球"> 羽毛球<br/>
    <input type="submit" value="注册">
</form>
</body>
</html>
