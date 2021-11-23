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
		super.destroy(); 
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
		Admin oldAdmin = adminService.getAdmin(Integer.valueOf(adminId));
		String adminName = request.getParameter("adminName");
		String adminPassword = request.getParameter("adminPassword");
		
		if(adminName == null || adminName.trim().equals("")) {
			request.setAttribute("error", "Admin name can't be null");
			gotoModify(request, response);
			return;
		}
		
		if(adminPassword == null || adminPassword.trim().equals("")) {
			request.setAttribute("error", "Admin password can't be null");
			gotoModify(request, response);
			return;
		}		
		Admin admin = new Admin(adminName, adminPassword);
		admin.setAdminId(Integer.valueOf(adminId));
		if(oldAdmin.getAdminName().equals(adminName)) {
			adminService.modify(admin);
			showList(request, response);
			return;
		}else {
			boolean isNameRepeated = isNameRepeated(adminName, request, response);
			if(isNameRepeated) {
				request.setAttribute("error", "name repeats");
				gotoModify(request, response);
				return;
			}else {
				adminService.modify(admin);
				showList(request, response);
				return;
			}
		}
			
	}

	private void gotoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String adminId = request.getParameter("adminId");
		Admin admin = adminService.getAdmin(Integer.valueOf(adminId));
		request.setAttribute("admin", admin);
		request.getRequestDispatcher("/admin/updateAdmin.jsp").forward(request, response);
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//delete single
    	String adminId = request.getParameter("adminId");
		//delete mutiple
    	String str = request.getParameter("adminIdArr");
    	
    	if(str != null && !str.trim().equals("")) {
    		String[] arrId = str.split(",");
    		for (int i = 0;  i < arrId.length; i++) {
				adminService.delete(Integer.valueOf(arrId[i]));				
			}
    		request.setAttribute("message", "delete succeeds");
    		showList(request, response);
    		return;
    	}else {
    		if(adminId != null) {
    			adminService.delete(Integer.valueOf(adminId));
    			request.setAttribute("message", "delete succeeds");
        		showList(request, response);
        		return;
    		}else {
    			//no select
    			request.setAttribute("message", "please select an admin");
				showList(request, response);
				return;
    		}
    	}

	}

	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	String checkName = request.getParameter("checkName"); // get name for search function
		// paging
		PageData pageData = new PageData();
		// get the current page
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null) {
			pageData.setCurrentPage(Integer.valueOf(currentPage));
		}
		// get rows count per page
		String pageRows = request.getParameter("pageRows");
		if (pageRows != null) {
			pageData.setPageRows(Integer.valueOf(pageRows));
		}
		// get quantity of all data in the database
		int rowsCount = adminService.getRowsCount(checkName);
		pageData.setRowsCount(rowsCount);
		// calculate the total number of pages
		int pageCount = 0;
		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		
		List<Admin> adminList = adminService.getAdminList(checkName, pageData);
		
		request.setAttribute("adminList", adminList);
		request.setAttribute("page", pageData);
		request.setAttribute("checkName", checkName);
		request.getRequestDispatcher("/admin/adminList.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String adminName = request.getParameter("adminName");
		String password = request.getParameter("adminPassword");
		//is null judgement
		if(adminName == null || adminName.trim().equals("")) {
			request.setAttribute("error", "Admin name can't be null");
			request.getRequestDispatcher("/admin/addAdmin.jsp").forward(request, response);
		}
		if(password == null || password.trim().equals("")) {
			request.setAttribute("error", "password can't be null");
			request.getRequestDispatcher("/admin/addAdmin.jsp").forward(request, response);
		}
		
		//can't add a new Admin with name which is the same of another admin in the databse
		boolean isNameRepeated = isNameRepeated(adminName, request, response);
		Admin admin= new Admin(adminName, password);
		
		if(!isNameRepeated) {
			adminService.addAdmin(admin);
			request.setAttribute("message", "add succeeds");
			showList(request, response);
		}else {
			request.setAttribute("error", "there exists a admin with same name");
			request.getRequestDispatcher("/admin/addAdmin.jsp").forward(request, response);
			return;
		}		
				
	}
    
    //judge if there is a admin with the same name
	public boolean isNameRepeated(String adminName, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isRepeated = false;

		List<Admin> list = adminService.getAdminList(null, null); // get all admins
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
