package com.example.demo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

/**
 * @author 18190
 */
@WebServlet("/demo4")
public class ServletDemo4 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        System.out.println("doget");
        ServletContext servletContext = req.getServletContext();
        String name = servletContext.getInitParameter("name");
        System.out.println("所有Servlet配置：    " + name );
        System.out.println("中文乱码改正");
        resp.setHeader("Content-Type","text/html;charset=UTF-8");
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        servletOutputStream.print("demo1 print no ISO 8859-1");
        servletOutputStream.println();

        servletOutputStream.write(" demo1 中国！\n".getBytes(StandardCharsets.UTF_8));

        // 输出二进制数据 图片等  getOutputStream和getWriter这两个方法互相排斥，调用了其中的任何一个方法后，就不能再调用另一方法。
        PrintWriter printWriter = resp.getWriter();
        printWriter.write("aaa");



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("dopost");
    }

    /**
     * @author 18190
     */

}
