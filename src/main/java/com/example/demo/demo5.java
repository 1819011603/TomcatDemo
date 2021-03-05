package com.example.demo;

import java.io.*;
import java.util.Date;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

/**
 * @author 18190
 */
@WebServlet("/demo5")
public class demo5 extends HttpServlet {
    private String message;

    @Override
    public void init() {
        message = "这是 HelloServlet 继承 httpServlet类";
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-Type","text/html;charset=UTF-8");

        // Hello

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
        // 中文乱码解决

        // 输出二进制数据 图片等  getOutputStream和getWriter这两个方法互相排斥，调用了其中的任何一个方法后，就不能再调用另一方法。

        out.write("看完得demo5点赞！");

        //浏览器有三消息头设置缓存，为了兼容性！将三个消息头都设置了
        response.setDateHeader("Expires", -1);
        response.setHeader("Cache-Control","no-cache");
        response.setHeader("Pragma", "no-cache");


        //这里为了看效果
        out.print("你好啊" + new Date().toString());


    }

    @Override
    public void destroy() {
    }
}