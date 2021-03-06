package com.baojuan.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */
@WebServlet("/DemoServlet")
public class DemoServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//输出文字流
		//PrintWriter out=res.getWriter();
		//获取响应流
		ServletOutputStream os=res.getOutputStream();
		//获取当前路径下的文件夹
		InputStream is=new FileInputStream(new File(getServletContext().getRealPath("images"),"a.png"));
		int index=-1;
		while((index=is.read())!=-1) {
			os.write(index);
		}
		os.flush();
	}
}
