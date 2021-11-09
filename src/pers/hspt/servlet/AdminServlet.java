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

			// administrator login
			adminLogin(request, response);
		} 

	}
	
	public void adminLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		String imgTxt = request.getParameter("code");// get the captcha code string
		if (adminName == null || adminName.trim().equals("")) {
			request.setAttribute("error", "please enter your name");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
			return;
		}
		if (password == null || password.equals("")) {
			request.setAttribute("error", "please enter your password");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
			return;
		}
		if (imgTxt == null || imgTxt.trim().equals("")) {
			request.setAttribute("error", "please enter the captcha code");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
			return;
		}
		// database searching
		Admin admin = adminService.getAdmin(adminName);
		if (admin != null) {
			// find the admin
			if (!password.trim().equals(admin.getAdminPassword())) {
				request.setAttribute("error", "wrong password!");
				request.getRequestDispatcher("/admin_login.jsp").forward(
						request, response);
			} else {
				// check if captcha code is correct
				String str = (String) request.getSession().getAttribute("str");
			
				if (imgTxt.equals(str)) {
					// successfully login, jump to index page of admin
					// save the admin in session
					request.getSession().setAttribute("admin", admin);
					request.getRequestDispatcher("/admin/index.jsp").forward(
							request, response);
				} else {
					
					request.setAttribute("error", "wrong captcha");
					request.getRequestDispatcher("/admin_login.jsp").forward(
							request, response);
				}
			}
		} else {
			// don't have this admin in the database, don't jump
			request.setAttribute("error", "don't have this admin!");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
		}
	}

	
	


}
