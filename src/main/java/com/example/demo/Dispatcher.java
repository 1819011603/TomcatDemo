package com.example.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/*
重定向是在浏览器中发生的 两次不同的request请求  跳转另一个网页
转发是在服务器中 一次request请求 直接将服务器中的资源进行返回即可  /dispatcher 其实是没有的 我们直接将其映射到了index.jsp
 */

/*
如上图所示，Servlet222成功拿到了request对象在Servlet111存进的数据。
现在问题又来了，我们可以使用ServletContext和request实现Servlet之间的通讯，那么我们用哪一种呢？一般的原则：
可以使用request就尽可能使用request。因为ServletContext代表着整个web应用，使用ServletContext会消耗大量的资源，而request对象会随着请求的结束而结束，资源会被回收。使用request域进行Servlet之间的通讯在开发中是非常频繁的。
转发的时序图
*/
/**
 * @author 18190
 */
@WebServlet("/dispatcher")
public class Dispatcher extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");

        String username = (String) req.getAttribute("username");
        resp.getWriter().write("i am " + username);
    }
}
