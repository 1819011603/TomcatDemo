package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Time;

@WebServlet("/download_pic")
public class DownloadPicture extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletContext().getRealPath("/download/4.png");
        System.out.println(path);
        FileInputStream fileInputStream = new FileInputStream(path);
        String fileName = path.substring(path.lastIndexOf("\\") + 1);
        System.out.println(fileName);
        resp.setHeader("Content-Disposition","attachment; filename="+ URLEncoder.encode(fileName,"UTF-8"));
        int len = 0;
        byte[] bytes = new byte[1024];
        ServletOutputStream servletOutputStream = resp.getOutputStream();
        while((len=fileInputStream.read(bytes))>0){
            servletOutputStream.write(bytes,0,len);
        }

        servletOutputStream.close();
        fileInputStream.close();

    }
}
