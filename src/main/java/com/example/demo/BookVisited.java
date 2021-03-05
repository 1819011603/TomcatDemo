package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.PrivateKey;
import java.util.*;


// https://www.zhihu.com/question/307708744
// https://segmentfault.com/a/1190000013129480


class Book{
    private String id;
    private String name;
    private String author;

    public Book(){

    }
    public Book(String id,String name,String author){
        this.id=id;
        this.name = name;
        this.author=author;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAuthor(String author){
        this.author=author;
    }
    public String getId(){
        return this.id;
    }
    public String getName(){
        return this.name;
    }
    public String getAuthor(){
        return this.author;
    }
}


@WebServlet("/book")
public class BookVisited extends HttpServlet {
    private static final LinkedHashMap<String,Book> linkedHashMap = new LinkedHashMap<>();
    static {
        linkedHashMap.put("1", new Book("1", "javaweb", "zhong"));
        linkedHashMap.put("2", new Book("2", "java", "fu"));
        linkedHashMap.put("3", new Book("3", "oracle", "cheng"));
        linkedHashMap.put("4", new Book("4", "mysql", "ou"));
        linkedHashMap.put("5", new Book("5", "ajax", "zi"));
    }


    public String makeHistory(HttpServletRequest req,String id){
        Cookie[] cookies = req.getCookies();
        if(cookies == null){
            return  id;
        }
        String bookHistory = null;
        for(int i = 0; i < cookies.length; i++){
            if("bookHistory".equals(cookies[i].getName())){
                bookHistory = cookies[i].getValue();
                break;
            }
        }
        if(bookHistory == null){
            return id;
        }
        String[] ids = bookHistory.split("_");
        boolean is = false;
        for(String i:ids){
            if(i.equals(id)){
                is = true;
                break;
            }
        }
        StringBuilder stringBuilder = new StringBuilder(id);
        if(is){
            for(String i:ids){
                if(!i.equals(id)){
                    stringBuilder.append("_").append(i);
                }
            }

        }else {
            if(ids.length == 3){
                System.out.println(bookHistory.lastIndexOf("_"));
                stringBuilder.append("_").append(bookHistory, 0, 3);

            }else{
                stringBuilder.append("_").append(bookHistory);
            }
        }
        System.out.println(stringBuilder);
        return new String(stringBuilder);


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter printWriter =resp.getWriter();
        // id == null 未访问过
        if(id == null){

            printWriter.write("网页上所有的书籍："+"<br/>");
            //拿到数据库所有的书
            Set<Map.Entry<String, Book>> entry = linkedHashMap.entrySet();

            //显示所有的书到网页上
            for (Map.Entry<String, Book> stringBookEntry : entry) {
                Book book = stringBookEntry.getValue();
                printWriter.write("<a href='/myTomcat/book?id=" + book.getId() + "'>" + book.getName() + "</a>");
                printWriter.write("<br/>");
            }
            Cookie[] cookies = req.getCookies();
            if (cookies!=null){

                String bookHistory = null;
                for(int i = 0; i < cookies.length; i++){
                    if("bookHistory".equals(cookies[i].getName())){
                        bookHistory = cookies[i].getValue();
                        break;
                    }
                }
                if(bookHistory != null){
                    printWriter.write("您曾浏览过的商品："+"<br/>");
                    String[] ids = bookHistory.split("_");
                    for(String i:ids){
                        Book book = linkedHashMap.get(i);
                        printWriter.write("<a target='_blank' href='/myTomcat/book?id=" + book.getId() + "'>" + book.getName() + "</a>");
                        printWriter.write("<br/>");
                    }
                }
            }

        }else{
            //
            Book book = linkedHashMap.get(id);
            String bookHistory = makeHistory(req,id);
            Cookie cookie = new Cookie("bookHistory",bookHistory);
            System.out.println(bookHistory);
            cookie.setMaxAge(120);
            //输出书的详细信息
            printWriter.write("书的编号是：" + book.getId()+"<br/>");
            printWriter.write("书的名称是：" + book.getName()+"<br/>");
            printWriter.write("书的作者是：" + book.getAuthor()+"<br/>");
            resp.addCookie(cookie);
        }

    }
}
