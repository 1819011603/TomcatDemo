<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "首页默认为index.jsp " %>
</h1>
<br/>
<img src="4.png">
<a href="${pageContext.request.contextPath}/demo5">调用 hello-servlet 的servlet服务器</a>
<a href="HttpServletRequest">资源防盗链</a>
</body>
</html>