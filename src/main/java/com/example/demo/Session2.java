package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/*一般来讲，当我们要存进的是用户级别的数据就用Session，那什么是用户级别呢？只要浏览器不关闭，希望数据还在，就使用Session来保存。*/
/*
Session在用户第一次访问服务器Servlet，jsp等动态资源就会被自动创建，Session对象保存在内存里，这也就为什么上面的例子可以直接使用request对象获取得到Session对象。
如果访问HTML,IMAGE等静态资源Session不会被创建。
Session生成后，只要用户继续访问，服务器就会更新Session的最后访问时间，无论是否对Session进行读写，服务器都会认为Session活跃了一次。
由于会有越来越多的用户访问服务器，因此Session也会越来越多。为了防止内存溢出，服务器会把长时间没有活跃的Session从内存中删除，这个时间也就是Session的超时时间。
Session的超时时间默认是30分钟，有三种方式可以对Session的超时时间进行修改
 */

@WebServlet("/session2")
public class Session2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        服务器是如何实现一个session为一个用户浏览器服务的？换个说法：为什么服务器能够为不同的用户浏览器提供不同session？
//        HTTP协议是无状态的，Session不能依据HTTP连接来判断是否为同一个用户。于是乎：服务器向用户浏览器发送了一个名为JESSIONID的Cookie，它的值是Session的id值。其实Session依据Cookie来识别是否是同一个用户。
//        JSESSIONID=0AF924B6140403912D20AE46ACBA7191
        //0AF924B6140403912D20AE46ACBA7191 为Session的id值
//        Session 之所以可以识别不同的用户，依靠的就是Cookie该Cookie是服务器自动颁发给浏览器的，不用我们手工创建的。该Cookie的maxAge值默认是-1，也就是说仅当前浏览器使用，不将该Cookie存在硬盘中
//        服务器就会创建一个Session对象，执行我们的程序代码，并自动颁发个Cookie给用户浏览器
        HttpSession httpSession = req.getSession();
        String value = (String) httpSession.getAttribute("username");
        System.out.println(value);
        // 删除属性
        httpSession.removeAttribute("useless");
        System.out.println(httpSession.getAttribute("useless"));
// 浏览器禁用Cookie 那么浏览器在发送请求时不会将Cookie发送给服务器 此时服务器将识别不了这是哪个浏览器发送过来的
        // 可以通过URL地址重写  encodeURL或者encodeRedirectURL方法
        //这两个方法会自动判断该浏览器是否支持Cookie，如果支持Cookie，重写后的URL地址就不会带有jsessionid了
        // 【当然了，即使浏览器支持Cookie，第一次输出URL地址的时候还是会出现jsessionid（因为没有任何Cookie可带）】

    }
}
