package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author 18190
 */
@WebServlet("/FormSubmit")
public class FormSubmit extends HttpServlet {
    void requests(HttpServletRequest req){
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String gender=req.getParameter("gender");
        String[] hobbies = req.getParameterValues("hobbies");
        String from = req.getParameter("from");
        String description = req.getParameter("description");

        String hidden = req.getParameter("hid");
        System.out.println(username+" " +password+" " +gender+ " " +Arrays.toString(hobbies)+ " " + from+" "+ description);
        System.out.println(hidden);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");
        requests(req);
        resp.setHeader("Cache-Control","no-cache");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        System.out.println("post");
        requests(req);
        resp.setHeader("Cache-Control","no-cache");
    }
}
