package com.example.demo;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.InputStream;

// servlet在内存中是单对象  多个线程访问会出现线程安全问题  尽量不要在Servlet定义成员变量，即便定义了成员对象  不要对其进行修改
@WebServlet("/demo2")
public class ServletDemo2 implements Servlet {

    /**
     * 初始化执行 servlet创建时被执行  只会创建一次
     * @param servletConfig
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("init ...");
    }

    /**
     * 获取servletconfig对象
     * @return
     */
    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    /**
     * 每次都会执行
     * @param servletRequest
     * @param servletResponse
     * @throws ServletException
     * @throws IOException
     */

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        System.out.println("servlet demo2");
        ServletContext servletContext = servletRequest.getServletContext();
        String value = "demo2";
        servletContext.setAttribute("This_Servlet",value);
        InputStream fileInputStream = servletContext.getResourceAsStream("4.png");
        System.out.println(fileInputStream);
    }

    /**
     * 获取servlet的信息
     * @return
     */
    @Override
    public String getServletInfo() {
        return null;
    }

    /**
     * servlet正常关闭时会执行一次 释放资源
     */
    @Override
    public void destroy() {
        System.out.println("destroy...");
    }
}
