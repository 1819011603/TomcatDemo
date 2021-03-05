<%--
  Created by IntelliJ IDEA.
  User: 18190
  Date: 2021/3/3
  Time: 15:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FormSubmit</title>
</head>
<body>
<%--// method = "get" or "post";--%>
<form action="FormSubmit" method="post">
    <table>
        <tr>
            <td>用户名</td>
            <td><input type="text" name="username"></td>
        </tr>
        <tr>
            <td>密码</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td>性别</td>
            <td>
                <input type="radio" name="gender" value="男">男
                <input type="radio" name="gender" value="女">女

            </td>
        </tr>
        <tr>
            <td>爱好</td>
            <td>
                <input type="checkbox" name="hobbies" value="游泳">游泳
                <input type="checkbox" name="hobbies" value="跑步">跑步
                <input type="checkbox" name="hobbies" value="打羽毛球">打羽毛球

            </td>
        </tr>
        <tr>
            <td>你来自哪里</td>
            <td>
                <select name="from" id="from">
                    <option value="北京">北京</option>
                    <option value="上海">上海</option>
                    <option value="广州">广州</option>
                    <option value="长沙" selected="selected">长沙</option>
                    <option value="重庆">重庆</option>
                </select>
            </td>

            
        </tr>
        <input type="hidden" name="hid" value="hidden">
        <tr>
            <td>详细描述</td>
            <td>
                <textarea name="description" id="description" cols="30" rows="3"></textarea>
            </td>

        </tr>
        <tr>
            <td>
                <input type="submit"  value="提交">
                <input type="reset" value="重置">
            </td>


        </tr>
    </table>
</form>
<a href="requestSubmit?username=这种xxx">超链接用get方式将数据带给浏览器</a>

</body>
</html>
