package com.example.demo;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.HttpCookie;
import java.util.Random;

/*一次性校验码其实就是为了防止暴力猜测密码
在讲response对象的时候，我们使用response对象输出过验证码，但是没有去验证！
验证的原理也非常简单：生成验证码后，把验证码的数据存进Session域对象中，判断用户输入验证码是否和Session域对象的数据一致。
生成验证码图片，并将验证码存进Session域中*/

//生成验证码后，把验证码的数据存进Session域对象中，判断用户输入验证码是否和Session域对象的数据一致。
//生成验证码图片，并将验证码存进Session域中
@WebServlet("/CheckCode")
public class CheckCode extends HttpServlet {

    private String makeNum() {

        Random random = new Random();

        //生成0-6位的随机数
        int num = random.nextInt(999999);

        //验证码的数位全都要6位数，于是将该随机数转换成字符串，不够位数就添加
        String randomNum = String.valueOf(num);

        //使用StringBuffer来拼凑字符串
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 6 - randomNum.length(); i++) {
            stringBuffer.append("0");
        }

        return stringBuffer.append(randomNum).toString();


    }
    public void produceCheckCodeImage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(80,20,BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = (Graphics2D) bufferedImage.getGraphics();
        graphics2D.setColor(Color.WHITE);
        graphics2D.fillRect(0,0,80,20);
        graphics2D.setFont(new Font(null,Font.BOLD,20));
        graphics2D.setColor(Color.BLACK);
        String randomNum = makeNum();
        graphics2D.drawString(randomNum,0,20);
        req.getSession().setAttribute("randomNum",randomNum);
        resp.setHeader("Expires","-1");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setHeader("Pragma", "no-cache");

        //通知浏览器以图片的方式打开
        resp.setHeader("Content-type", "image/jpeg");

        //把图片写给浏览器
        ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        produceCheckCodeImage(req,resp);
    }
}
