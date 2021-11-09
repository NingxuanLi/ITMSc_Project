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

			// ����Ա��¼
			adminLogin(request, response);
		} 

	}
	
	public void adminLogin(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("password");
		String imgTxt = request.getParameter("code");// ƥ����֤��
		if (adminName == null || adminName.trim().equals("")) {
			request.setAttribute("error", "������������");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
			return;
		}
		if (password == null || password.equals("")) {
			request.setAttribute("error", "������������");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
			return;
		}
		if (imgTxt == null || imgTxt.trim().equals("")) {
			request.setAttribute("error", "��֤�벻�ܿ�");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
			return;
		}
		// �������ݿ�,ƥ�����Ա
		Admin admin = adminService.getAdmin(adminName);
		if (admin != null) {
			// ���ҵ��иù���Ա
			if (!password.trim().equals(admin.getAdminPassword())) {
				request.setAttribute("error", "�������");
				request.getRequestDispatcher("/admin_login.jsp").forward(
						request, response);
			} else {
				// ƥ����֤��
				String str = (String) request.getSession().getAttribute("str");
			
				if (imgTxt.equals(str)) {
					// ��¼�ɹ���������̨����ҳ��
					// ������Ա���浽��session��
					request.getSession().setAttribute("admin", admin);
					request.getRequestDispatcher("/admin/index.jsp").forward(
							request, response);
				} else {
					
					request.setAttribute("error", "��֤�����");
					request.getRequestDispatcher("/admin_login.jsp").forward(
							request, response);
				}
			}
		} else {
			// û�д˹���Ա
			request.setAttribute("error", "û�д˹���Ա");
			request.getRequestDispatcher("/admin_login.jsp").forward(request,
					response);
		}
	}

	
	


}
