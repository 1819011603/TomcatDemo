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


@WebServlet("/cookie2")
public class Cookie2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter = resp.getWriter();
        Cookie[] cookies = req.getCookies();
        String host ="";
        String page = req.getRequestURI();
        if(cookies == null || cookies.length == 0){
            resp.getWriter().write(page+"无Cookie");
        }
        for(int i=0;cookies!=null && i < cookies.length; i++){
            String name  = cookies[i].getName();
            String s =  cookies[i].getValue();
            String value = URLDecoder.decode(name,"UTF-8");
            s = URLDecoder.decode(s,"UTF-8");
            printWriter.write(value + "=" + s + "</br>");
            host = cookies[i].getDomain();
        }
        resp.getWriter().write(page  +"已向浏览器发送Cookie " );
    }
}
