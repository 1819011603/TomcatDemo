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
import java.util.Random;

@WebServlet("/RandomPicture")
public class RandomPicture extends HttpServlet {



    private String makeNum() {

        Random random = new Random();

        //这样就会生成0-7位的随机数，现在问题又来了，如果随机数不够7位呢？如果不够7位，我们加到7位就行了
        int anInt = random.nextInt(9999999);

        //将数字转成是字符串
        String num = String.valueOf(anInt);

        //判断位数有多少个，不够就加
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 7 - num.length(); i++) {
            stringBuffer.append("0");
        }

        return stringBuffer.append(num).toString();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //在内存中生成一张图片,宽为80,高为20，类型是RGB
        BufferedImage bufferedImage = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);

        //获取到这张图片
        Graphics graphics = bufferedImage.getGraphics();

        //往图片设置颜色和字体
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 80, 20);
        graphics.setFont(new Font(null, Font.BOLD, 20));

        //往图片上写数据，先写个12345，横坐标是0，纵坐标是20【高度】
        graphics.setColor(Color.BLACK);
        graphics.drawString(makeNum(), 0, 20);

        //要往浏览器写一张图片，那要告诉浏览器回送的类型是一张图片
        resp.setHeader("ContentType", "jpeg");

        //java提供了图片流给我们使用，这是一个工具类
        //把图片传进去，类型是jpg，写给浏览器
        ImageIO.write(bufferedImage, "jpg", resp.getOutputStream());

    }
}
