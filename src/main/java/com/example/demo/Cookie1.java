package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet("/cookie")
public class Cookie1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        String host = req.getRequestURI();


//        新建的Cookie除了value、maxAge之外的所有属性都要与原Cookie相同。否则浏览器将视为不同的Cookie，不予覆盖，导致删除修改失败！
//        Cookie的path属性决定允许访问Cookie的路径
//        Cookie发布出来，整个网页的资源都可以使用。现在我只想Servlet1可以获取到Cookie，其他的资源不能获取。 cookie.setPath("/Servlet1");
//        Cookie的隐私安全机制决定Cookie是不可跨域名的。也就是说www.baidu.com和www.google.com之间的Cookie是互不交接的。
//        即使是同一级域名，不同二级域名也不能交接，也就是说：www.goole.com和www.image.goole.com的Cookie也不能访问
//        现在我希望一级域名相同的网页Cookie之间可以相互访问。也就是说www.image.zhongfucheng.com可以获取到www.zhongfucheng.com的Cookie就需要使用到domain方法。   cookie.setDomain(".zhongfucheng.com");


            String name = "美国";
            Cookie cookie = new Cookie("username", URLEncoder.encode(name,"UTF-8"));
            /*
            1.如果MaxAge为正数，浏览器会把Cookie写到硬盘中，只要还在MaxAge秒之前，登陆网站时该Cookie就有效【不论关闭了浏览器还是电脑】
            2.如果MaxAge为负数，Cookie是临时性的，仅在本浏览器内有效，关闭浏览器Cookie就失效了，Cookie不会写到硬盘中。Cookie默认值就是-1。这也就为什么在我第一个例子中，如果我没设置Cookie的有效期，在硬盘中就找不到对应的文件。
            3.如果MaxAge为0，则表示删除该Cookie。Cookie机制没有提供删除Cookie对应的方法，把MaxAge设置为0等同于删除Cookie
            */
            cookie.setMaxAge(1000);
            resp.addCookie(cookie);

        resp.getWriter().write("使用"+ host  +"已向浏览器发送Cookie " + name);


    }
}
