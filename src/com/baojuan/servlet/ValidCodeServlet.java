package com.baojuan.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ValidCodeServlet
 */
@WebServlet("/ValidCode")
public class ValidCodeServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//创建一张图片
		//单位：像素
		BufferedImage image=new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);
		ServletOutputStream os=res.getOutputStream();
		//创建画板
		//在画板上画东西之前必须要设置画笔
		Graphics2D gra=image.createGraphics();
		//设为白色
		gra.setColor(Color.WHITE);
		//从那个坐标开始填充，后边两个为矩形区域
		gra.fillRect(0, 0, 200, 100);
		List<Integer> randList=new ArrayList<Integer>();
		Random random=new Random();
		for (int i = 0; i < 4; i++) {
			randList.add(random.nextInt(10));
		}
		gra.setFont(new Font("宋体", Font.ITALIC|Font.BOLD, 40));
		//写字
		Color[] colors=new Color[] {Color.PINK,Color.BLACK,Color.BLUE,Color.GREEN,Color.YELLOW};
		for (int i = 0; i < randList.size(); i++) {
			gra.setColor(colors[random.nextInt(colors.length)]);
			//设置字体
			gra.drawString(randList.get(i)+"", i*40, 70+(random.nextInt(21)-10));		
		}
		for(int i=0;i<=1;i++) {
			gra.setColor(colors[random.nextInt(colors.length)]);
			//划横线
			gra.drawLine(0, random.nextInt(101), 200, random.nextInt(101));
		}
		/*gra.setColor(colors[random.nextInt(colors.length)]);
		//划横线
		gra.drawLine(0, 50, 200, 50);*/
		//工具类
		ImageIO.write(image, "jpg", os);
		
		//把验证码放入session对象中
		HttpSession session=req.getSession();
		session.setAttribute("code", ""+randList.get(0)+randList.get(1)+randList.get(2)+randList.get(3));
	}
}
