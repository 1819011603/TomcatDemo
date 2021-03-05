package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPOutputStream;

@WebServlet("/DataGzip")
public class DataGzip extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        String ss = "faasdsadasddsadasdsdsadasdsadsdasdasdasdasdghasdgasjdgasjdgausdygausydgasudygsaudgasdasddasd";
        /*输出二进制数据 图片等  getOutputStream和getWriter这两个方法互相排斥，调用了其中的任何一个方法后，就不能再调用另一方法。*/
//        resp.getWriter().write("原来长度是：" + ss.getBytes(StandardCharsets.UTF_8).length + "</br>");
//        resp.getWriter().write(ss);

        /*getWriter和getOutputStream细节
getWriter()和getOutputStream()两个方法不能同时调用。如果同时调用就会出现异常
Servlet程序向ServletOutputStream或PrintWriter对象中写入的数据将被Servlet引擎从response里面获取，Servlet引擎将这些数据当作响应消息的正文，然后再与响应状态行和各响应头组合后输出到客户端。
Servlet的serice()方法结束后【也就是doPost()或者doGet()结束后】，Servlet引擎将检查getWriter或getOutputStream方法返回的输出流对象是否已经调用过close方法，如果没有，Servlet引擎将调用close方法关闭该输出流对象.*/
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gzipOutputStream.write(ss.getBytes());
        gzipOutputStream.close();
        byte[] bytes = byteArrayOutputStream.toByteArray();
        resp.setHeader("Content-Encoding","gzip");
        resp.getOutputStream().write(bytes);
    }
}
