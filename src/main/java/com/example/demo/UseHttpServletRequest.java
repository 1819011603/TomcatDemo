package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 18190
 */
@WebServlet("/HttpServletRequest")
public class UseHttpServletRequest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获得浏览器的信息
        System.out.println(req.getRequestURL());
        System.out.println(req.getQueryString());
        System.out.println(req.getPathInfo());
        System.out.println(req.getRemoteAddr());
        System.out.println(req.getRemoteHost());
        System.out.println(req.getRemotePort());
        System.out.println(req.getRemoteUser());
        System.out.println(req.getLocalAddr());
        System.out.println(req.getLocalName());

        // 获得客户机的请求头
        System.out.println(req.getHeaders("Content-Type"));
        System.out.println(req.getHeaderNames());

        // 获得客户机请求参数(客户端提交的数据)
        System.out.println(req.getParameter("name"));

        // 防盗链
        String referer = req.getHeader("Referer");
        if(referer == null || !referer.contains("localhost/myTomcat/index.jsp")){
            resp.sendRedirect("index.jsp");
            return;
        }
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("防盗链！");


    }
}
