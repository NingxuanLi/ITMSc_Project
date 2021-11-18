package pers.hspt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


import pers.hspt.entity.Doctor;
import pers.hspt.entity.Department;
import pers.hspt.service.DoctorService;
import pers.hspt.service.DepartmentService;
import pers.hspt.service.imp.DoctorServiceImp;
import pers.hspt.service.imp.DepartmentServiceImp;
import pers.hspt.util.PageData;



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
		// �õ�ֵ
		String method = request.getParameter("method");
		if (method.equals("frontShowList")) {
			// ��ʾ�����б�
			frontShowList(request, response);

		} else if (method.equals("showList")) {
			// ��ʾ�����б�
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
		// TODO Auto-generated method stub
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
		int rowsCount = departmentService.getRowsCount(null);

		pageData.setRowsCount(rowsCount);

		// ������ҳ��
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
				dep.setColor("red");
			} else {
				dep.setColor("black");
			}
		}
		request.setAttribute("depList", depList);
		request.setAttribute("page", pageData);
		
		request.getRequestDispatcher("/frontDepList.jsp").forward(request, response);
		
		
		
	}

	// ���
	public void add(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String depId = request.getParameter("depId");
		String depName = request.getParameter("depName");
		// �õ�ֵ

		System.out.println(depId);
		System.out.println(depName);
		// �ж���ǰ̨ҳ���ж�

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
		// �������ݿ�
		Department department = new Department(Integer.valueOf(depId), depName);
		boolean b = departmentService.add(department);
		if (b) {
			// �ɹ�
			// ��ת���б�Ҫ����
			showList(request, response);
		} else {
			// ����ʧ��
			request.setAttribute("error", "add failed");
			request.getRequestDispatcher("admin/addDep.jsp").forward(
					request, response);
		}

	}	
	
	// ��ʾ�����б�
	public void showList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ģ����ѯ
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
		int rowsCount = departmentService.getRowsCount(checkName);
		pageData.setRowsCount(rowsCount);
		// ������ҳ��
		int pageCount = 0;
		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		// �Ȳ�ѯ���ݿ�
		List<Department> depList = departmentService.getList(checkName, pageData); // �õ����е�
		// �鿴�Ƿ���ҽ��,��ҽ����ʾ��ɫ��
		for (Department dep : depList) {
			boolean isDoc = isHasDoc(dep.getDepId(), request, response);
			if (isDoc) {
				dep.setColor("red");
			} else {
				dep.setColor("black");
			}
		}
		// ����ҳ�棬����ֵ����ȥ
		request.setAttribute("depList", depList);
		request.setAttribute("page", pageData); // ��page����ȥ
		request.setAttribute("checkName", checkName);// �����������
		// ������̨
		request.getRequestDispatcher("/admin/departmentList.jsp").forward(request,
				response);
	}
	
	// ��ʾ�����б�ʱ����ѯ�������Ƿ���ҽ������ҽ������ʾΪ��ɫ
		public boolean isHasDoc(int depId, HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			boolean b = false; // Ĭ��û��ԤԼ

			List<Doctor> rList = doctorService.getByDepId(depId);

			if (rList.size() > 0) {
				b = true;// ��ԤԼ
			} else {
				b = false;// û��ԤԼ
			}
			return b;

		}
		
		// ɾ��
		public void delete(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {

			// ����ɾ��
			String depId = request.getParameter("depId");
			// ���ɾ��
			String strId = request.getParameter("strId");

			boolean b = false;
			boolean isHasDoc = false;
			if (strId != null && !strId.trim().equals("")) {
				String arrId[] = strId.split(","); // ���ַ����ָ������,�õ����и�ѡ���value��������Աid
				for (int i = 0; i < arrId.length; i++) {
					isHasDoc = isHasDoc(Integer.valueOf(arrId[i]), request,
							response);
					if (!isHasDoc) {
						b = departmentService.delete(Integer.valueOf(arrId[i]));
					}
				}
			} else {

				if (depId != null) {
					isHasDoc = isHasDoc(Integer.valueOf(depId), request, response);
					if (!isHasDoc) {
						b = departmentService.delete(Integer.valueOf(depId));
					}
				} else {
					// ��Ϊ�յ�ʱ��
					JOptionPane.showMessageDialog(null, "haven't selected a department");// ����ȥ
					showList(request, response);
					return;
				}
			}

			if (!b) {
				JOptionPane.showMessageDialog(null, "can't delete a department havig doctors");
				request.setAttribute("error", "delete failed");
			}

			showList(request, response);

		}
		
		// �����޸�ҳ��
		public void gotoModify(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
			// �õ�ҳ���offId����ѯ���ݿ⣬�õ����󣬳�ʼ��ҳ��
			String depId = request.getParameter("depId");

			// System.out.println(list.size());
			Department department = departmentService.get(Integer.valueOf(depId));
			// ����ֵ������ҳ��
			request.setAttribute("department", department);
			request.getRequestDispatcher("admin/updateDep.jsp").forward(request,
					response);

		}
		
		public void modify(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// �õ�ҳ��ֵ
			int oldDepId = Integer.valueOf(request.getParameter("oldDepId"));// �ɵ�id
			int newDepId = Integer.valueOf(request.getParameter("depId"));
			String depName = request.getParameter("depName");

			// ���ݿ��޸�
			Department department = new Department(newDepId, depName);
			boolean b = departmentService.modify(department, oldDepId);
			if (b) {
				// �ɹ�
				showList(request, response);
			} else {
				// �޸�ʧ��
				request.setAttribute("error", "edit failed");
				request.getRequestDispatcher("/admin/departmentList.jsp").forward(request,
						response);
			}

		}
	

}
