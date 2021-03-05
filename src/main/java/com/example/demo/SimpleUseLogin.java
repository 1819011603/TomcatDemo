package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class User{
    private String name=null;
    private String passwd=null;
    public User(){

    }
    public User(String name,String passwd){
        this.name = name;
        this.passwd = passwd;
    }
    public String getName(){
        return name;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

}

/**
 * @author 18190
 */
@WebServlet("/SimpleUseLogin")
public class SimpleUseLogin extends HttpServlet {
    private static List<User> list = new ArrayList<>();
    static {

        list.add(new User("aaa","111"));
        list.add(new User("bbb","222"));
        list.add(new User("ccc","333"));
    }
    public static User find(String username, String password) {

        for (User user : list) {
            if (user.getName().equals(username) && user.getPasswd().equals(password)) {

                return user;
            }
        }

        return null;
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        System.out.println("post");
        String client_randomNum = req.getParameter("randomNum");

        //获取Session中的数据
        String session_randomNum = (String) req.getSession().getAttribute("randomNum");

        //判断他俩数据是否相等，用户是否有输入验证码，Session中是否为空
        if (client_randomNum == null || session_randomNum == null || !client_randomNum.equals(session_randomNum)) {
            System.out.println("验证码错误了！！！");
            return ;
        }
        String serverValue = (String) req.getSession().getAttribute("token");
        String clientValue = req.getParameter("token");
        System.out.println(serverValue);
        System.out.println(clientValue);
        // severValue == null说明第一次进入  相等说明后退刷新  而不是 后退提交数据
        /*
                serverValue  clientValue
                1.null          ""         处理数据
     退回提交    2.值1            ""         重复提交
     退回提交    3.值2            ""         重复提交
     退回刷新    4.值2            值2        处理数据
     提交       5.值2             值2       处理数据后 serverValue变为值3 只有刷新页面才能使得隐藏数据进行更新

         */
        if (serverValue == null || serverValue.equals(clientValue)) {

            System.out.println("处理请求");
            String name = req.getParameter("name");
            String passwd = req.getParameter("passwd");
            User user = find(name,passwd);
            //生出随机数
            String token = TokenProcessor.makeToken();

            //将随机数存进Session中
            req.getSession().setAttribute("token", token);
            System.out.println(token);
            if (user == null){

                //跳转到显示页面
                req.getRequestDispatcher("/Login.jsp").include(req, resp);
                resp.getWriter().write("用户名或密码错误");
                return;
            }
            HttpSession httpSession = req.getSession();
            httpSession.setAttribute("user",user);
            resp.sendRedirect(resp.encodeURL("/myTomcat/SuccessLogin.jsp"));
            //登录进去了就可以 清除Session域对象数据
            req.getSession().removeAttribute("token");

        }else {
            // 不相等就说明 index.jsp中的值并未改变
            System.out.println("请不要重复提交数据！");
            return;
        }



    }
}
