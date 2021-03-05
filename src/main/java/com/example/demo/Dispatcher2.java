package com.example.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
我们在写网页的时候，一般网页的头部和尾部是不需要改变的。如果我们多个地方使用Servlet输出网头和网尾的话，需要把代码重新写一遍。
而使用RequestDispatcher的include()方法就可以实现包含网头和网尾的效果了。
*/


/**
 * @author 18190
 */
@WebServlet("/dispatcher2")
public class Dispatcher2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("/head").include(req,resp);
        resp.getWriter().write("</br>"+"-------------------------------------------" + "</br>");
        req.getRequestDispatcher("/foot").include(req,resp);
    }
}
