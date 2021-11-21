package pers.hspt.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.jspsmart.upload.SmartUpload;
import com.mysql.jdbc.DocsConnectionPropsHelper;

import pers.hspt.entity.Doctor;
import pers.hspt.entity.Department;
import pers.hspt.entity.Patient;
import pers.hspt.dto.DoctorDto;
import pers.hspt.entity.Appointment;
import pers.hspt.service.DoctorService;
import pers.hspt.service.DepartmentService;
import pers.hspt.service.AppointmentService;
import pers.hspt.service.imp.DoctorServiceImp;
import pers.hspt.service.imp.DepartmentServiceImp;
import pers.hspt.service.imp.AppointmentServiceImp;
import pers.hspt.util.DateUtil;
import pers.hspt.util.PageData;

public class DoctorServlet extends HttpServlet{
	
	private DepartmentService departmentService = new DepartmentServiceImp();
	private DoctorService doctorService = new DoctorServiceImp();
	private AppointmentService appointmentService = new AppointmentServiceImp();
	
	public DoctorServlet() {
		super();
	}
	
	
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code heref
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
			// 前台挂号时需要
			frontShowList(request, response);

		} else if (method.equals("docListInOneDep")) {
			// 前台查询医生列表
			docListInOneDep(request, response);

		} else if (method.equals("showList")) {
			// 显示诊室列表
			showList(request, response);

		} else if (method.equals("gotoAdd")) {
			// 添加时先初始化下拉列表框
			gotoAdd(request, response);

		} else if (method.equals("add")) {

			add(request, response);

		} else if (method.equals("modify")) {

			modify(request, response);

		} else if (method.equals("gotoModify")) {

			gotoModify(request, response);

		} else if (method.equals("delete")) {

			delete(request, response);
		}else if (method.equals("login")) {
			login(request,response);
		}else if("logout".equals(method)) {
//			loginOut(request,response);
		}else if(method.equals("appointmentMake")) {
			appointmentMake(request,response);
		}

	}


	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String imgTxt = request.getParameter("code");// get the captcha code string
		if (name == null || name.trim().equals("")) {
			request.setAttribute("error", "please enter your name");
			request.getRequestDispatcher("/doctor_login.jsp").forward(request, response);
			return;
		}
		if (password == null || password.equals("")) {
			request.setAttribute("error", "please enter your password");
			request.getRequestDispatcher("/doctor_login.jsp").forward(request, response);
			return;
		}
		if (imgTxt == null || imgTxt.trim().equals("")) {
			request.setAttribute("error", "please enter the captcha code");
			request.getRequestDispatcher("/doctor_login.jsp").forward(request, response);
			return;
		}
		Doctor doctor = doctorService.get(name);
		if(doctor != null) {
			if(!password.trim().equals(doctor.getDocPassword())) {
				request.setAttribute("error", "wrong password");
				request.getRequestDispatcher("/doctor_login.jsp").forward(request, response);
				return;
			}else {
				String str = (String) request.getSession().getAttribute("str");
				if (imgTxt.equals(str)) {
					request.getSession().setAttribute("doctor", doctor); //session
					request.getRequestDispatcher("/doctor/index.jsp").forward(request, response);
					return;
				}else {
					request.setAttribute("eror", "wrong captcha");
					request.getRequestDispatcher("/doctor_login.jsp").forward(request, response);
					return;
				} 				
			}
		}else {
			request.setAttribute("error", "don't have this doctor");
			request.getRequestDispatcher("/doctor_login.jsp").forward(request, response);
			return;
		}
		
	}


	private void appointmentMake(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String docId = request.getParameter("docId");
		Doctor doc = doctorService.get(Integer.valueOf(docId));
		DoctorDto dto = new DoctorDto();
		Department department = departmentService.get(doc.getDepId());
		dto.setDocId(doc.getDocId());
		dto.setDocName(doc.getDocName());
		dto.setDepName(department.getDepName());
		dto.setDocStatus(doc.getDocStatus());
		dto.setMoney(doc.getMoney());
		dto.setDocTime(doc.getDocTime());
		
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("/appointmentMake.jsp").forward(request, response);
		
	}


	private void docListInOneDep(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String depId = request.getParameter("depId");
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
		int rowsCount = doctorService.getRowsCountForOneDep(Integer.valueOf(depId));

		pageData.setRowsCount(rowsCount);

		// 计算总页数
		int pageCount = 0;

		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		
		
		List<Doctor> docList = doctorService.getByDepId(Integer.valueOf(depId));
		List<DoctorDto> docDtoList = new ArrayList<>();
		DoctorDto dto = null;
		for(Doctor doc : docList) {
			dto = new DoctorDto();
			Department department = departmentService.get(doc.getDepId());
			dto.setDocId(doc.getDocId());
			dto.setDocName(doc.getDocName());
			dto.setDepName(department.getDepName());
			dto.setDocStatus(doc.getDocStatus());
			dto.setMoney(doc.getMoney());
			dto.setDocTime(doc.getDocTime());
			docDtoList.add(dto);
		}
		request.setAttribute("docDtoList", docDtoList);
		request.setAttribute("page", pageData);
		request.getRequestDispatcher("/docListOneDep.jsp").forward(request, response);
		
		
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
		int rowsCount = doctorService.getRowsCount(null);

		pageData.setRowsCount(rowsCount);

		// 计算总页数
		int pageCount = 0;

		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		
		List<Doctor> doctorList = doctorService.getList(null, pageData);
		List<DoctorDto> docDtoList = new ArrayList<>();
		DoctorDto dto = null;
		for(Doctor doc : doctorList) {
			dto = new DoctorDto();
			Department department = departmentService.get(doc.getDepId());
			dto.setDocId(doc.getDocId());
			dto.setDocName(doc.getDocName());
			dto.setDepName(department.getDepName());
			dto.setDocStatus(doc.getDocStatus());
			dto.setMoney(doc.getMoney());
			dto.setDocTime(doc.getDocTime());
			docDtoList.add(dto);
		}
		request.setAttribute("docDtoList", docDtoList);
		request.setAttribute("page", pageData);
		request.getRequestDispatcher("/frontDocList.jsp").forward(request, response);				
	}


	private void modify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String docId = request.getParameter("docId");
		Doctor doctor = doctorService.get(Integer.valueOf(docId));
		System.out.println(docId);
		
		String docName = request.getParameter("docName");
		int money = Integer.valueOf(request.getParameter("money"));
		String docTime = request.getParameter("docTime"); //需要转化成sql里面的日期
		int depId = Integer.valueOf(request.getParameter("depId"));
		Date timeDate = DateUtil.getBirthDate(docTime);
		
		Doctor docRepeated = doctorService.get(docName);
		if(docRepeated != null) {
			request.setAttribute("error", "repeated doctor name, please use another name");
			request.getRequestDispatcher("/admin/updateDoctor.jsp").forward(request, response);
			return;
		}else {
			doctor.setDocName(docName);
			doctor.setMoney(money);
			doctor.setDepId(depId);
			doctor.setDocTime(timeDate);			
			boolean b = doctorService.modify(doctor);
			if(b) {
				//修改成功，跳转到showList
				System.out.println("modification succeeded");
				showList(request, response);
			}else {
				//修改失败
				System.out.println("modification failed");
				request.setAttribute("error", "modification failed");
				request.getRequestDispatcher("/admin/doctorList.jsp").forward(request, response);
			}
		}	
	}


	private void gotoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Department> depList = departmentService.getList(null, null);
		request.setAttribute("depList", depList);
		String docId = request.getParameter("docId");
		Doctor doc = doctorService.get(Integer.valueOf(docId));
		Department department = departmentService.get(doc.getDepId());
		//dto封装
		DoctorDto dto = new DoctorDto();
		dto.setDocId(doc.getDocId());
		dto.setDocName(doc.getDocName());
		dto.setDepName(department.getDepName());
		dto.setDocStatus(doc.getDocStatus());
		dto.setDocTime(doc.getDocTime());
		dto.setMoney(doc.getMoney());
		request.setAttribute("doctorDto", dto);
		request.getRequestDispatcher("/admin/updateDoctor.jsp").forward(request, response);
		
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//删除单个
		String  docId = request.getParameter("docId");
		//删除多个
		String strId = request.getParameter("strId");
		
		boolean b = false;
		boolean isApp = false;
		if(strId != null && !strId.trim().equals("")) {
			String arrId[] = strId.split(",");
			for (int i = 0; i < arrId.length; i++) {
				isApp = isHasApp(Integer.valueOf(arrId[i]), request, response);
				//没有预约
				if(!isApp) {
					b = doctorService.delete(Integer.valueOf(arrId[i]));
				}
			}
		}else {
			if(docId != null) {
				isApp = isHasApp(Integer.valueOf(docId), request, response);
				if(!isApp) {
					b = doctorService.delete(Integer.valueOf(docId));
				}
			}else {
				JOptionPane.showMessageDialog(null, "You haven't select a doctor");// 跳出去
				showList(request, response);
				return;
			}
		}
		if(!b) {
			JOptionPane.showMessageDialog(null, "can't delete a doctor with an appointment");
			request.setAttribute("error", "delete failed");
		}
		showList(request, response);
	}


	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
		int rowsCount = doctorService.getRowsCount(checkName);
		pageData.setRowsCount(rowsCount);
		// 计算总页数
		int pageCount = 0;
		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		// 先查询数据库,得到医生列表
		List<Doctor> docList = doctorService.getList(checkName, pageData);// 查询所有记录
		List<DoctorDto> docDtoList = new ArrayList<>();
		DoctorDto dto = null;
		for (Doctor doc : docList) {
			dto = new DoctorDto();
			Department department = departmentService.get(doc.getDepId());
			dto.setDocId(doc.getDocId());
			dto.setDocName(doc.getDocName());
			dto.setDepName(department.getDepName());
			dto.setDocStatus(doc.getDocStatus());
			dto.setDocTime(doc.getDocTime());
			dto.setMoney(doc.getMoney());
			//查看是否有预约，如果有名字显示红色，没有预约显示黑色，删除时只能删除黑色
			boolean isApp = isHasApp(doc.getDocId(), request, response);
			if(isApp) {
				dto.setColor("red");
			}else {
				dto.setColor("black");
			}
			docDtoList.add(dto);
		}
		//跳转到页面并将值传过去
		request.setAttribute("docDtoList", docDtoList);
		request.setAttribute("page", pageData); // 将page传过去
		request.setAttribute("checkName", checkName);// 不让名字清空
		String appDocId = request.getParameter("appDocId");
		if (appDocId != null) {
			// 得到单个医生信息
			Doctor doc = doctorService.get(Integer.valueOf(appDocId));
			// 得到科室名
			Department department = departmentService.get(doc.getDepId());
			request.setAttribute("doc", doc);
			request.setAttribute("dep", department);
			request.getRequestDispatcher("/admin/appShowDoctor.jsp").forward(
					request, response);
		} else {
			request.getRequestDispatcher("/admin/doctorList.jsp").forward(
					request, response);
		}
		
	}
	
	public boolean isHasApp(int docId, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean b = false; // 默认没有预约
		Appointment appointment = appointmentService.getByDocId(docId);
		if (appointment == null) {
			b = false;// 没有预约
		} else {
			b = true;// 有预约
		}
		return b;

	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			String docName = request.getParameter("docName");
			String docPassword = request.getParameter("docPassword");
			int money = Integer.valueOf(request.getParameter("money"));
			int depId = Integer.valueOf(request.getParameter("depId"));
			String docTime = request.getParameter("docTime");
			Date timeDate = DateUtil.getBirthDate(docTime);
						
			Doctor doctor = doctorService.get(docName);
			if(doctor != null) {
				request.setAttribute("error", "there exist a doctor with the same name, please enter a different name");
				request.getRequestDispatcher("/admin/addDoctor.jsp").forward(request, response);
				return;
			}else {
				doctor = new Doctor(docName, docPassword,money, timeDate, "normal", depId);
				boolean b = doctorService.add(doctor);
				//添加成功
				if(b) {
					request.setAttribute("message", "successfully added");
					showList(request, response);
				//添加失败	
				}else {
					request.setAttribute("error", "add failed");
					request.getRequestDispatcher("/admin/addDoctor.jsp").forward(request, response);
				}
			}
			boolean b = doctorService.add(doctor);
			//添加成功
			if(b) {
				request.setAttribute("message", "successfully added");
				showList(request, response);
			//添加失败	
			}else {
				request.setAttribute("error", "add failed");
				request.getRequestDispatcher("/admin/addDoctor.jsp").forward(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}


	private void gotoAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//查询所有科室
		List<Department> depList = departmentService.getList(null, null);
		//保存depList，跳转到jsp
		request.setAttribute("depList", depList);
		request.getRequestDispatcher("/admin/addDoctor.jsp").forward(request, response);
		
		
		
	}

}
