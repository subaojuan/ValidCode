package com.baojuan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.baojuan.pojo.Users;
import com.baojuan.service.UsersService;
import com.baojuan.service.impl.UsersServiceImpl;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private UsersService usersService;

	@Override
	public void init() throws ServletException {
		ApplicationContext ac=WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		usersService=ac.getBean("usersService",UsersServiceImpl.class);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String code = req.getParameter("code");
		String codeSession = req.getSession().getAttribute("code").toString();
		if(codeSession.equals(code)){
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			Users users = new Users();
			users.setPassword(password);
			users.setUsername(username);
			Users user = usersService.login(users);
			if(user!=null){
				res.sendRedirect("main.jsp");
			}else{
				req.setAttribute("error", "用户名密码不正确");
				req.getRequestDispatcher("index.jsp").forward(req, res);
			}
		}else{
			req.setAttribute("error", "验证码不正确");
			req.getRequestDispatcher("index.jsp").forward(req, res);
		}
		
	}
}
