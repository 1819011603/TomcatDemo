package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/*Session 是另一种记录浏览器状态的机制。不同的是Cookie保存在浏览器中，Session保存在服务器中。
用户使用浏览器访问服务器的时候，服务器把用户的信息以某种的形式记录在服务器，这就是Session*/

/*如果说Cookie是检查用户身上的”通行证“来确认用户的身份，那么Session就是通过检查服务器上的”客户明细表“来确认用户的身份的。
Session相当于在服务器中建立了一份“客户明细表”。*/
//Session比Cookie使用方便，Session可以解决Cookie解决不了的事情【Session可以存储对象，Cookie只能存储字符串。】。
/*一般来讲，当我们要存进的是用户级别的数据就用Session，那什么是用户级别呢？只要浏览器不关闭，希望数据还在，就使用Session来保存。*/


/*
Session在用户第一次访问服务器Servlet，jsp等动态资源就会被自动创建，Session对象保存在内存里，这也就为什么上面的例子可以直接使用request对象获取得到Session对象。
如果访问HTML,IMAGE等静态资源Session不会被创建。
Session生成后，只要用户继续访问，服务器就会更新Session的最后访问时间，无论是否对Session进行读写，服务器都会认为Session活跃了一次。
由于会有越来越多的用户访问服务器，因此Session也会越来越多。为了防止内存溢出，服务器会把长时间没有活跃的Session从内存中删除，这个时间也就是Session的超时时间。
Session的超时时间默认是30分钟，有三种方式可以对Session的超时时间进行修改
 */


@WebServlet("/session1")
public class Session1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("username","18190");
        httpSession.setAttribute("useless","use");
//        有三种方式可以对Session的超时时间进行修改
//        第一种方式：在tomcat/conf/web.xml文件中设置，时间值为20分钟，所有的WEB应用都有效
//        第二种方式：在单个的web.xml文件中设置，对单个web应用有效，如果有冲突，以自己的web应用为准。
//        第三种方式：通过setMaxInactiveInterval()方法设置
        // 这里的单位是秒
        httpSession.setMaxInactiveInterval(600);
        System.out.println(httpSession.getMaxInactiveInterval());
    }
}
