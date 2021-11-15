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
		} else if(method.equals("logout")) {
			logout(request, response);
		} else if(method.equals("add")) {
			add(request, response);
		} else if(method.equals("showList")) {
			showList(request, response);
		} else if(method.equals("delete")) {
			delete(request, response);
		} else if(method.equals("gotoModify")) {
			gotoModify(request, response);
		} else if(method.equals("modify")) {
			modify(request, response);
		}

	}
	
    private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String adminId = request.getParameter("adminId");
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		
		Admin admin = new Admin(adminName, adminPassword);
		admin.setAdminId(Integer.valueOf(adminId));
		boolean b = adminService.modify(admin);
		if(b) {
			showList(request, response);
		}else {
			request.setAttribute("error", "edit failed");
			request.getRequestDispatcher("/admin/updateAdmin.jsp").forward(request, response);			
		}
	}

	private void gotoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String adminId = request.getParameter("adminId");
		Admin admin = adminService.getAdmin(Integer.valueOf(adminId));
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("/admin/updateAdmin.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//ɾ������
    	String adminId = request.getParameter("adminId");
		//ɾ�����
    	String str = request.getParameter("adminIdArr");
    	
    	boolean b = false;
    	if(str != null && !str.trim().equals("")) {
    		String[] arrId = str.split(",");
    		for (int i = 0;  i < arrId.length; i++) {
				System.out.println(arrId[i]);
				b = adminService.delete(Integer.valueOf(arrId[i]));
			}
    	}else {
    		if(adminId != null) {
    			b = adminService.delete(Integer.valueOf(adminId));
    		}else {
    			//��Ϊ��
    			JOptionPane.showMessageDialog(null, "haven't select an admin to delete");// ����ȥ
				showList(request, response);
				return;
    		}
    	}
    	if(!b) {
    		request.setAttribute("error", "delete failed");
    	}
    	showList(request, response);
	}

	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String checkName = request.getParameter("checkName"); // �õ����֣�������������ʱ��
		// ��ҳ
		PageData pageData = new PageData();
		// �õ���ǰҳ
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null) {
			pageData.setCurrentPage(Integer.valueOf(currentPage));
		}
		// �õ�ÿҳ����
		String pageRows = request.getParameter("pageRows");
		if (pageRows != null) {
			pageData.setPageRows(Integer.valueOf(pageRows));
		}
		// �õ�������
		int rowsCount = adminService.getRowsCount(checkName);
		pageData.setRowsCount(rowsCount);
		// ������ҳ��
		int pageCount = 0;
		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		
		List<Admin> adminList = adminService.getAdminList(checkName, pageData);
		//����
		request.setAttribute("adminList", adminList);
		request.setAttribute("page", pageData);
		request.setAttribute("checkName", checkName);
		request.getRequestDispatcher("/admin/adminList.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("adminPassword");
		//�ж������������Ƿ�Ϊ��
		if(adminName == null || adminName.trim().equals("")) {
			request.setAttribute("error", "Admin name can't be null");
			request.getRequestDispatcher("/admin/addAdmin.jsp").forward(request, response);
		}
		if(password == null || password.trim().equals("")) {
			request.setAttribute("error", "password can't be null");
			request.getRequestDispatcher("/admin/addAdmin.jsp").forward(request, response);
		}
		//�����ݿ�����ӹ���Ա
		//������ݿ����Ƿ��������ģ�����У��������
		boolean isNameRepeated = isNameRepeated(adminName, request, response);
		Admin admin= new Admin(adminName, password);
		boolean b = false;
		if(!isNameRepeated) {
			b = adminService.addAdmin(admin);
		}else {
			request.setAttribute("error", "there exists a admin with same name");
			request.getRequestDispatcher("/admin/addAdmin.jsp").forward(request, response);
			return;
		}
		
		if(b) {  //��ӳɹ�
			System.out.println("successfully added");
			showList(request, response);
		}else {  //���ʧ��
			request.setAttribute("error", "add failed");
			request.getRequestDispatcher("/admin/addAdmin.jsp").forward(request, response);
		}
		
	}
    
    //�ж����ݿ����Ƿ��������Ĺ���Ա
	public boolean isNameRepeated(String adminName, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isRepeated = false;
		// �жϲ�������
		List<Admin> list = adminService.getAdminList(null, null); // �õ��б�,��ѯ���е�
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getAdminName().equals(adminName)) {
					isRepeated = true;
					break;
				}
			}
		}

		return isRepeated;
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
	
	public void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// log out and destory session

		request.getSession().invalidate();
		request.getRequestDispatcher("/admin_login.jsp").forward(request,
				response);

	}

	
	


}
