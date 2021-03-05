package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/redirection")
public class Redirection extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
//        其实sendRedirect()方法就是对setStatus()和setHeader()进行封装，原理就是setStatus()和setHeader()
//        resp.sendRedirect("refresh.jsp");

//        resp.setStatus(302);
        resp.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);

        // 重定向是可以在任何资源进行跳转  所以要加上web应用程序的名字 不然不知道是哪个web应用程序的资源
        resp.setHeader("Location","/myTomcat/hhhh.html");

//        resp.setHeader("Refresh","3;url='/myTomcat/refresh.jsp");

    }
}
