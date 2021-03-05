package com.example.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @author 18190
 */
@WebServlet(urlPatterns = {"/demo1","/demo/*"})
public class ServletDemo1 implements Servlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("hello zzl");
        ServletContext servletContext = servletRequest.getServletContext();
        // 根据web的目录规范，Servlet编译后的class文件是存放在WEB-INFclasses文件夹中的
        InputStream fileInputStream = servletContext.getResourceAsStream("/WEB-INF/4.png");
        System.out.println(fileInputStream);


    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
