package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * @author 18190
 */
@WebServlet("/bookSession")
public class BookSession extends HttpServlet {
    private static final LinkedHashMap<String,Book> linkedHashMap = new LinkedHashMap<>();
    private static String url = "/myTomcat/bookSession";
    private static Set<Book> list = null;
    static {
        linkedHashMap.put("1", new Book("1", "javaweb", "zhong"));
        linkedHashMap.put("2", new Book("2", "java", "fu"));
        linkedHashMap.put("3", new Book("3", "oracle", "cheng"));
        linkedHashMap.put("4", new Book("4", "mysql", "ou"));
        linkedHashMap.put("5", new Book("5", "ajax", "zi"));
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter =resp.getWriter();
        if(id == null){
            printWriter.write("网页上所有的书籍："+"<br/>");
            //拿到数据库所有的书
            Set<Map.Entry<String, Book>> entry = linkedHashMap.entrySet();

            //显示所有的书到网页上
            for (Map.Entry<String, Book> stringBookEntry : entry) {
                Book book = stringBookEntry.getValue();
                printWriter.write("<a href='"+url+"?id=" + book.getId() + "'>" + book.getName() + "</a>");
                printWriter.write("<br/>");
            }
            HttpSession httpSession = req.getSession();
            list = (Set)httpSession.getAttribute("list");
            if(list==null || list.size() == 0){
                printWriter.write("对不起，你还没有买过任何商品！");
            }else{

                printWriter.write("你购买了以下的商品：</br>");
                for(Book book:list){
                    printWriter.write(book.getName() + "</br>");
                }
            }
        }else{

            Book book = linkedHashMap.get(id);
            HttpSession httpSession = req.getSession();
            list= (Set)httpSession.getAttribute("list");
            if(list == null){
                list = new HashSet();
                httpSession.setAttribute("list",list);
            }
            if(!list.contains(book)){
                list.add(book);
            }



            // 禁用cookie 原理是将该用户Session的id信息重写到URL地址中。
            // 服务器能够解析重写后的URL获取Session的id。这样即使客户端不支持Cookie，也可以使用Session来记录用户状态。
            // 如果已经调用了encodeRedirectURL 或者 encodeURL方法一次的话 就不同再调用了
            if(!url.contains("jsessionid")){
                url = resp.encodeRedirectURL(url);
            }

            resp.sendRedirect(url);
        }
    }
}
