package com.example.demo;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

/*
转发是发生在服务器的  在同一个web应用中的资源
response.sendRedirect("/web应用/资源名");
转发是由服务器进行跳转的，细心的朋友会发现，在转发的时候，浏览器的地址栏是没有发生变化的，
在我访问Servlet111的时候，即使跳转到了Servlet222的页面，浏览器的地址还是Servlet111的。
也就是说浏览器是不知道该跳转的动作，转发是对浏览器透明的。通过上面的转发时序图我们也可以发现，
实现转发只是一次的http请求，一次转发中request和response对象都是同一个。这也解释了，
为什么可以使用request作为域对象进行Servlet之间的通讯。
* */

/*
重定向是发生在浏览器的
request.getRequestDispatcher("/资源名").forward(request,response)
重定向是由浏览器进行跳转的，进行重定向跳转的时候，浏览器的地址会发生变化的。
曾经介绍过：实现重定向的原理是由response的状态码和Location头组合而实现的。
这是由浏览器进行的页面跳转实现重定向会发出两个http请求，request域对象是无效的，因为它不是同一个request对象
*/

/*
1.给服务器用的直接从资源名开始写，给浏览器用的要把应用名写上
2.能够去往的URL的范围不一样 转发是服务器跳转只能去往当前web应用的资源 重定向是浏览器跳转，可以去往任何的资源
3. 传递数据的类型不同 转发的request对象可以传递各种类型的数据，包括对象   重定向只能传递字符串
4. 跳转的时间不同    转发时：执行到跳转语句时就会立刻跳转  重定向：整个页面执行完之后才执行跳转
5.转发是带着转发前的请求的参数的。重定向是新的请求。
6.转发: 访问 Servlet 处理业务逻辑，然后 forward 到 jsp 显示处理结果，浏览器里 URL 不变
7.重定向: 提交表单，处理成功后 redirect 到另一个 jsp，防止表单重复提交，浏览器里 URL 变了
 */


@WebServlet("/dispatcher1")
public class Dispatcher1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("username","18190");
        // 都是myTomcat Web应用中的资源 所以myTomcat可以省去
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/dispatcher");

        // 写了数据那么forward将不会执行
//        OutputStream outputStream = resp.getOutputStream();
//        outputStream.write("--------------------------------------------".getBytes());
//        //关闭流，确保让数据到浏览器中  不加下面这句  抛出java.lang.IllegalStateException:
//        outputStream.close();


        // 并不会影响正常转发
//        resp.getWriter().write("aaaaa");


        /*如果在调用forward方法之前，在Servlet程序中写入的部分内容已经被真正地传送到了客户端，forward方法将抛出IllegalStateException异常。
         也就是说：不要在转发之前写数据给浏览器*/
        requestDispatcher.forward(req,resp);
    }
}
