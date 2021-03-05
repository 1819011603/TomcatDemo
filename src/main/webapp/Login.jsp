<%--
  Created by IntelliJ IDEA.
  User: 18190
  Date: 2021/3/4
  Time: 20:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript">

        //定义一个全局标识量：是否已经提交过表单数据
        var isCommitted = false;

        function doSubmit() {

            //false表示的是没有提交过，于是就可以让表单提交给Servlet
            if(isCommitted==false) {

                isCommitted = true;
                return true;
            }else {
                return false;
            }
        }
    </script>
</head>
<body>
<form action="SimpleUseLogin" onsubmit="doSubmit()" method="post">
    <tr>
        <td>用户名</td>
        <td>
            <input type="text" name="name"><br>
        </td>
    </tr>
    <tr>
        <td>密码</td>
        <td>
            <input type="password" name="passwd"><br>
        </td>
    </tr>

    <tr>
        <td>验证码</td>
        <td>
            <input type="text" name="randomNum">
        </td>
        <td>
            <img src="CheckCode"><br><br>
        </td>
    </tr>
    <tr>
    <input type="submit" value="提交">
    </tr>
    <%--使用EL表达式取出session中的Token--%>
    <input type="hidden" name="token" value="${token}" >
</form>
</body>
</html>
