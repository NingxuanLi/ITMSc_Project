package pers.hspt.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

	public ImageServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//生成验证码
		int width=70;
		int height=35;
		
		BufferedImage img=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		//TYPE_INT_RGB 表示一个图像，它具有合成整数像素的 8 位 RGB 颜色分量。
        
		//获取画笔 
		Graphics g=img.getGraphics();
		//设置图片颜色边框
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);  //rect矩形
		//设置边框颜色
		g.setColor(Color.black);
		g.drawRect(0, 0, width-1, height-1);
		
		//生成文字，随机
		Random random=new Random();
		int num=random.nextInt(8999)+1000;
		String str=String.valueOf(num);
		//讲str设置到session中去
		request.getSession().setAttribute("str", str);
		g.setColor(Color.red);
		g.setFont(new Font("黑体",Font.ITALIC,30));
		g.drawString(str, 5, 25); //10,20是坐标位置，其实坐标0,0是左下角，这是画文字的区别
		
		//生成干扰线,产生两个坐标
		for(int i=0;i<10;i++){
			int x0=random.nextInt(width);
			int y0=random.nextInt(height);
			int x1=random.nextInt(width);
			int y1=random.nextInt(height);
			g.drawLine(x1, y1, x0, y0);
		}
		
		
		//？
		ImageIO.write(img, "JPEG", response.getOutputStream());
		
		
	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
