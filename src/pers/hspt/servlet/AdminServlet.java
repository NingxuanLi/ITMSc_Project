package pers.hspt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import pers.hspt.entity.Admin;

import pers.hspt.service.AdminService;
import pers.hspt.service.imp.AdminServiceImp;
import pers.hspt.util.PageData;

public class AdminServlet extends HttpServlet{
	
	private AdminService adminService = new AdminServiceImp();
	
	public AdminServlet() {
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
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");

		if (method.equals("adminLogin")) {

			// 管理员登录
			adminLogin(request, response);
		} 

	}
	
	public void adminLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		String imgTxt = request.getParameter("code");// 匹配验证码
		if (adminName == null || adminName.trim().equals("")) {
			request.setAttribute("error", "请您输入姓名");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
			return;
		}
		if (password == null || password.equals("")) {
			request.setAttribute("error", "请您输入密码");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
			return;
		}
		if (imgTxt == null || imgTxt.trim().equals("")) {
			request.setAttribute("error", "验证码不能空");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
			return;
		}
		// 查找数据库,匹配管理员
		Admin admin = adminService.getAdmin(adminName);
		if (admin != null) {
			// 查找到有该管理员
			if (!password.trim().equals(admin.getAdminPassword())) {
				request.setAttribute("error", "密码错误");
				request.getRequestDispatcher("/admin_login.jsp").forward(
						request, response);
			} else {
				// 匹配验证码
				String str = (String) request.getSession().getAttribute("str");
			
				if (imgTxt.equals(str)) {
					// 登录成功，跳到后台管理页面
					// 将管理员保存到到session中
					request.getSession().setAttribute("admin", admin);
					request.getRequestDispatcher("/admin/index.jsp").forward(
							request, response);
				} else {
					
					request.setAttribute("error", "验证码错误");
					request.getRequestDispatcher("/admin_login.jsp").forward(
							request, response);
				}
			}
		} else {
			// 没有此管理员
			request.setAttribute("error", "没有此管理员");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
		}
	}

	
	


}
