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
		// 得到值
		String method = request.getParameter("method");
		if (method.equals("frontShowList")) {
			// 显示诊室列表
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
		// 分页
		PageData pageData = new PageData();
		// 得到当前页
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null) {
			pageData.setCurrentPage(Integer.valueOf(currentPage));
		}
		// 得到每页行数
		String pageRows = request.getParameter("pageRows");
		if (pageRows != null) {
			pageData.setPageRows(Integer.valueOf(pageRows));
		}

		// 得到总行数
		int rowsCount = departmentService.getRowsCount(null);

		pageData.setRowsCount(rowsCount);

		// 计算总页数
		int pageCount = 0;

		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		
		List<Department> depList = departmentService.getList(null, pageData);
		for (Department dep : depList) {
			boolean isDoc = isHasDoc(dep.getDepId(), request, response);
			if (isDoc) {
				dep.setColor("black");
			} else {
				dep.setColor("black");
			}
		}
		request.setAttribute("depList", depList);
		request.setAttribute("page", pageData);
		
		request.getRequestDispatcher("/frontDepList.jsp").forward(request, response);
		
		
		
	}

	// 添加
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String depId = request.getParameter("depId");
		String depName = request.getParameter("depName");
		// 得到值
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
		// 插入数据库
		Department department = new Department(Integer.valueOf(depId), depName);	
		departmentService.add(department);
		request.setAttribute("message", "add succeeds");
		showList(request, response);			
	}	
	
	// 显示科室列表
	public void showList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 模糊查询
		String checkName = request.getParameter("checkName"); // 得到名字，根据姓名查找时用
		// 分页
		PageData pageData = new PageData();
		// 得到当前页
		String currentPage = request.getParameter("currentPage");
		if (currentPage != null) {
			pageData.setCurrentPage(Integer.valueOf(currentPage));
		}
		// 得到每页行数
		String pageRows = request.getParameter("pageRows");
		if (pageRows != null) {
			pageData.setPageRows(Integer.valueOf(pageRows));
		}
		// 得到总行数
		int rowsCount = departmentService.getRowsCount(checkName);
		pageData.setRowsCount(rowsCount);
		// 计算总页数
		int pageCount = 0;
		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		// 先查询数据库
		List<Department> depList = departmentService.getList(checkName, pageData); // 得到所有的
		// 查看是否有医生,有医生显示红色，
		for (Department dep : depList) {
			boolean isDoc = isHasDoc(dep.getDepId(), request, response);
			if (isDoc) {
				dep.setColor("red");
			} else {
				dep.setColor("black");
			}
		}
		// 跳到页面，并将值传过去
		
		request.setAttribute("depList", depList);
		request.setAttribute("page", pageData); // 将page传过去
		request.setAttribute("checkName", checkName);// 不让名字清空
		// 跳到后台
		request.getRequestDispatcher("/admin/departmentList.jsp").forward(request,
				response);
	}
	
	// 显示科室列表时，查询看下面是否有医生，有医生的显示为红色
		public boolean isHasDoc(int depId, HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			boolean b = false; // 默认没有预约

			List<Doctor> rList = doctorService.getByDepId(depId);

			if (rList.size() > 0) {
				b = true;// 有预约
			} else {
				b = false;// 没有预约
			}
			return b;

		}
		
		// 删除
		public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// 单个删除
			String depId = request.getParameter("depId");
			// 多个删除
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
		
		// 进入修改页面
		public void gotoModify(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// 得到页面的offId，查询数据库，得到对象，初始化页面
			String depId = request.getParameter("depId");

			Department department = departmentService.get(Integer.valueOf(depId));
			if(isHasDoc(Integer.valueOf(depId), request, response)) {
//				TODO
//				request.setAttribute("message", "can't update department already having a doctor");
				JOptionPane.showMessageDialog(null, "can't update a department already having a appointment");
				showList(request, response);
				return;
			}
			// 保存值，传到页面
			request.setAttribute("department", department);
			request.getRequestDispatcher("admin/updateDep.jsp").forward(request,
					response);

		}
		
		public void modify(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// 得到页面值
			int oldDepId = Integer.valueOf(request.getParameter("oldDepId"));// 旧的id
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
			
			
			
			if(depName.equals(department.getDepName()) && Integer.valueOf(newDepIdStr) == department.getDepId()) {
				
			}
			
			boolean isIdRepeated = isIdRepeated(Integer.valueOf(newDepIdStr), request, response);
			boolean isNameRepeated = isNameRepeated(depName, request, response);
			
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
		
		public boolean isNameRepeated(String depName, HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			boolean isRepeated = false;
			// 判断不能重名
			List<Department> list = departmentService.getList(null, null); // 得到列表,查询所有的
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
		public boolean isIdRepeated(int depId, HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			boolean isRepeated = false;
			// 判断不能重名
			List<Department> list = departmentService.getList(null, null); // 得到列表,查询所有的
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
