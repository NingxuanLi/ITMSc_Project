package pers.hspt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


import pers.hspt.entity.Doctor;
import pers.hspt.entity.Admin;
import pers.hspt.entity.Department;
import pers.hspt.service.DoctorService;
import pers.hspt.service.DepartmentService;
import pers.hspt.service.imp.DoctorServiceImp;
import pers.hspt.service.imp.DepartmentServiceImp;
import pers.hspt.util.PageData;
import pers.hspt.util.StringUtil;



public class DepartmentServlet extends HttpServlet{
	
	private DepartmentService departmentService = new DepartmentServiceImp();
	private DoctorService doctorService = new DoctorServiceImp();
	
	public DepartmentServlet() {
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
		if (method.equals("frontShowList")) {
			frontShowList(request, response);
		} else if (method.equals("showList")) {
			showList(request, response);
		} else if (method.equals("add")) {
			add(request, response);
		} else if (method.equals("modify")) {
			modify(request, response);
		} else if (method.equals("gotoModify")) {
			gotoModify(request, response);
		} else if (method.equals("delete")) {
			delete(request, response);
		}

	}
	
	private void frontShowList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String checkName = request.getParameter("checkName");  //search function
		System.out.println(checkName);
		// paging
		PageData pageData = new PageData();
		// get current page
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null) {
			pageData.setCurrentPage(Integer.valueOf(currentPage));
		}
		// get number of rows per page
		String pageRows = request.getParameter("pageRows");
		if (pageRows != null) {
			pageData.setPageRows(Integer.valueOf(pageRows));
		}

		// get total number of rows
		int rowsCount = departmentService.getRowsCount(null);

		pageData.setRowsCount(rowsCount);

		// calculate total number of pages
		int pageCount = 0;

		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		
		List<Department> depList = departmentService.getList(checkName, pageData);
		for (Department dep : depList) {
			//if a department already has a doctor, set its color to red(can't be updated or deleted)
			boolean isDoc = isHasDoc(dep.getDepId(), request, response);
			if (isDoc) {
				dep.setColor("black");
			} else {
				dep.setColor("black");
			}
		}
		request.setAttribute("checkName", checkName);
		request.setAttribute("depList", depList);
		request.setAttribute("page", pageData);	
		request.getRequestDispatcher("/frontDepList.jsp").forward(request, response);	
	}


	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String depId = request.getParameter("depId");
		String depName = request.getParameter("depName");

		if (depId == null || depId.trim().equals("")) {
			request.setAttribute("error", "id can't be null");
			request.getRequestDispatcher("/admin/addDep.jsp").forward(
					request, response);
			return;
		}
		if (depName == null || depName.trim().equals("")) {
			request.setAttribute("error", "name can't be null");
			request.getRequestDispatcher("/admin/addDep.jsp").forward(
					request, response);
			return;
		}
		
		if(isNameRepeated(depName, request, response)) {
			request.setAttribute("error", "there exists a dep with same name, please use another name");
			request.getRequestDispatcher("/admin/addDep.jsp").forward(request, response);
			return;
		}
		if(isIdRepeated(Integer.valueOf(depId), request, response)) {
			request.setAttribute("error", "there exists a dep with same id, please use another id");
			request.getRequestDispatcher("/admin/addDep.jsp").forward(request, response);
			return;
		}

		Department department = new Department(Integer.valueOf(depId), depName);	
		departmentService.add(department);
		request.setAttribute("message", "add succeeds");
		showList(request, response);			
	}	
	
	public void showList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// get name of search function 
		String checkName = request.getParameter("checkName"); 
		// paging 
		PageData pageData = new PageData();
		// get current page
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null) {
			pageData.setCurrentPage(Integer.valueOf(currentPage));
		}
		// get number of rows per page
		String pageRows = request.getParameter("pageRows");
		if (pageRows != null) {
			pageData.setPageRows(Integer.valueOf(pageRows));
		}
		// get total number of rows
		int rowsCount = departmentService.getRowsCount(checkName);
		pageData.setRowsCount(rowsCount);
		// calculate total number of pages
		int pageCount = 0;
		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		// database search
		List<Department> depList = departmentService.getList(checkName, pageData); 
		for (Department dep : depList) {
			boolean isDoc = isHasDoc(dep.getDepId(), request, response);
			//if there is already a doctor in a department, set its color to red(can't be modified or deleted)
			if (isDoc) {
				dep.setColor("red");
			} else {
				dep.setColor("black");
			}
		}
		//jump
		request.setAttribute("depList", depList);
		request.setAttribute("page", pageData); 
		request.setAttribute("checkName", checkName);
		request.getRequestDispatcher("/admin/departmentList.jsp").forward(request, response);
	}
	
	// judge whether there is already a doctor in a department
		public boolean isHasDoc(int depId, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			boolean b = false; // default no

			List<Doctor> rList = doctorService.getByDepId(depId, null);
			if (rList.size() > 0) {
				b = true;
			} else {
				b = false;
			}
			return b;
		}
		
		
		public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// single delete
			String depId = request.getParameter("depId");
			// mutiple delete
			String strId = request.getParameter("strId");
		
			boolean isHasDoc = false;
			if (strId != null && !strId.trim().equals("")) {
				String arrId[] = strId.split(","); 
				for (int i = 0; i < arrId.length; i++) {
					isHasDoc = isHasDoc(Integer.valueOf(arrId[i]), request, response);
					if (!isHasDoc) {
						departmentService.delete(Integer.valueOf(arrId[i]));
					}
				}
				request.setAttribute("message", "delete succeeds");
				showList(request, response);
				return;
			} else {
				if (depId != null) {
					isHasDoc = isHasDoc(Integer.valueOf(depId), request, response);
					if (!isHasDoc) {
						departmentService.delete(Integer.valueOf(depId));
						request.setAttribute("message", "delete succeeds");
						showList(request, response);
						return;
					}else {						
						//TODO
//						request.setAttribute("message", "can't delete a department already having a doctor");
						JOptionPane.showMessageDialog(null, "can't delete a department already having a appointment");
						showList(request, response);
						return;
					}
				} else {
					request.setAttribute("message", "please select a department");
					showList(request, response);
					return;
				}
			}

		}
		
		
		public void gotoModify(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// get department Id
			String depId = request.getParameter("depId");

			Department department = departmentService.get(Integer.valueOf(depId));
			if(isHasDoc(Integer.valueOf(depId), request, response)) {
//				TODO
//				request.setAttribute("message", "can't update department already having a doctor");
				JOptionPane.showMessageDialog(null, "can't update a department already having a appointment");
				showList(request, response);
				return;
			}
			// jump
			request.setAttribute("department", department);
			request.getRequestDispatcher("admin/updateDep.jsp").forward(request,
					response);

		}
		
		public void modify(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			int oldDepId = Integer.valueOf(request.getParameter("oldDepId"));// get the old department id
			String newDepIdStr = request.getParameter("depId");
			String depName = request.getParameter("depName");
			Department department = departmentService.get(oldDepId);
			
			if (newDepIdStr == null || newDepIdStr.trim().equals("")) {
				request.setAttribute("error", "please enter the department id");
				request.setAttribute("department", department);
				request.getRequestDispatcher("/admin/updateDep.jsp").forward(request, response);
				return;
			}
			if(!StringUtil.isInteger(newDepIdStr)) {
				request.setAttribute("error", "the department id must be an integer");
				request.setAttribute("department", department);
				request.getRequestDispatcher("/admin/updateDep.jsp").forward(request, response);
				return;
			}
			
			boolean isIdRepeated = isIdRepeated(Integer.valueOf(newDepIdStr), request, response);
			boolean isNameRepeated = isNameRepeated(depName, request, response);
			//if the id or name of department is repeated, update will fail
			if(isIdRepeated && isNameRepeated) {
				request.setAttribute("error", "department name or id repeated");
				request.setAttribute("department", department);
				request.getRequestDispatcher("/admin/updateDep.jsp").forward(request, response);
				return;
			}else {
				department = new Department(Integer.valueOf(newDepIdStr), depName);
				departmentService.modify(department, oldDepId);
				request.setAttribute("message", "update succeeds");
				showList(request, response);
			}
		}
		
		//judge whether the name of department is repeated
		public boolean isNameRepeated(String depName, HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			boolean isRepeated = false;

			List<Department> list = departmentService.getList(null, null); // get all departments
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getDepName().equals(depName)) {
						isRepeated = true;
						break;
					}
				}
			}
			return isRepeated;
		}		
		
		//judge whether the id of department is repeated
		public boolean isIdRepeated(int depId, HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			boolean isRepeated = false;

			List<Department> list = departmentService.getList(null, null); // get all departments
			if (list != null) {
				for (int i = 0; i < list.size(); i++) {
					if (list.get(i).getDepId() == depId) {
						isRepeated = true;
						break;
					}
				}
			}
			return isRepeated;
		}
	

}
