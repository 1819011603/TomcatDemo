package com.example.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@WebServlet("/demo3")
public class demo3 extends GenericServlet {
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("demo3..");
        // 这个可以获取到demo2 servlet中的信息  为什么可以通过 servletContext获取呢？
        /*
        一个web应用对应一个ServletContext，所以ServletContext的作用范围是整个应用，明确这点很重要，这是基础中的基础。
         */
        ServletContext servletContext =  servletRequest.getServletContext();
        String value = (String) servletContext.getAttribute("This_Servlet");
        System.out.println(value);

        String name = servletContext.getInitParameter("name");
        System.out.println("所有Servlet配置：    " + name );

    }
}
