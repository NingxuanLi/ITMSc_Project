package pers.hspt.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import pers.hspt.util.PageData;
import pers.hspt.entity.Appointment;
import pers.hspt.entity.Patient;
import pers.hspt.service.AppointmentService;
import pers.hspt.service.PatientService;
import pers.hspt.service.imp.AppointmentServiceImp;
import pers.hspt.service.imp.PatientServiceImp;

public class PatientServlet extends HttpServlet{
	private PatientService patientService = new PatientServiceImp();
	private AppointmentService appointmentService = new AppointmentServiceImp();
	
	public PatientServlet() {
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
		// 得到值
		String method = request.getParameter("method");
		if (method.equals("add")) {
			add(request, response);
		}else if(method.equals("showList")) {
			showList(request, response);
		}else if(method.equals("delete")) {
			delete(request, response);
		}
		
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//删除单个
		String pId = request.getParameter("pId");
		//删除多个
		String strId = request.getParameter("strId");
		
		boolean hasApp = false; //判断这个patient有没有预约在数据库
		if(strId != null && !strId.trim().equals("")) { //多个一起删除
			String[] arrId = strId.split(",");
			for(String id : arrId) {
				hasApp = hasApp(Integer.valueOf(id), request, response);
				if(!hasApp) { //没有预约，可以删除
					patientService.delete(Integer.valueOf(id));
				}
			}
			showList(request, response);
			return;
		}
		if(pId != null) {
			hasApp = hasApp(Integer.valueOf(pId), request, response);
			if(!hasApp) {
				patientService.delete(Integer.valueOf(pId));
				request.setAttribute("message", "delete succeeds");
				showList(request, response);
				return;
			}
			if(hasApp){
				//TODO 显示不了这个message
//				JOptionPane.showMessageDialog(null, "can't delete a patient already making an appointment");
				request.setAttribute("message", "can't delete a patient already making an appointment");
				showList(request, response);
				return;
			}
		}
		request.setAttribute("message", "please select a patient");
		showList(request, response);
	}

	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// 得到名字，根据姓名查找时用
		String checkName = request.getParameter("checkName");
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
		int rowsCount = patientService.getRowsCount(checkName);	
		pageData.setRowsCount(rowsCount);	
		// 计算总页数
		int pageCount = 0;

		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		
		List<Patient> patientList = patientService.getList(checkName, pageData);
		//查看每个病人是否有预约，如果有就显示红色，没有就显示黑色
		for(Patient p : patientList) {
			boolean hasApp = hasApp(p.getId(), request, response);
			if(hasApp) {  //有预约显示红色
				p.setColor("red");
			}else {  //没有预约显示黑色
				p.setColor("black");
			}
		}
		//跳转
		request.setAttribute("patientList", patientList);
		request.setAttribute("page", pageData);
		request.setAttribute("checkName", checkName);
		
		request.getRequestDispatcher("/admin/patientList.jsp").forward(request, response);
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String realName = request.getParameter("realName");
		String gender = request.getParameter("gender");
		String telNumber = request.getParameter("telNumber");
		String brpNumber = request.getParameter("brpNumber");
		//condition judgement
		if(name == null || name.trim().equals("")) {
			request.setAttribute("error", "name can't be null");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
			return;
		}
		if(password == null || password.trim().equals("")) {
			request.setAttribute("error", "password can't be null");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
			return;
		}
		if(confirmPassword == null || confirmPassword.trim().equals("")) {
			request.setAttribute("error", "confirm password can't be null");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
			return;
		}
		if(!confirmPassword.equals(password)) {
			request.setAttribute("error", "different password");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
			return;
		}
		if(realName == null || realName.trim().equals("")) {
			request.setAttribute("error", "real name can't be null");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
			return;
		}
		if(gender == null || gender.trim().equals("")) {
			request.setAttribute("error", "must choose a gender");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
			return;
		}
		if(telNumber == null || telNumber.trim().equals("")) {
			request.setAttribute("error", "tel number can't be null");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
			return;
		}
		if(brpNumber == null || brpNumber.trim().equals("")) {
			request.setAttribute("error", "BRP number can't be null");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
			return;
		}
		
		Patient patient = patientService.get(name); //不能添加相同名字的用户
		if(patient != null) {
			request.setAttribute("error", "The name is already in use, please enter a different name");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
			return;
		}else {
			patient = new Patient(name, password, realName, gender, telNumber, brpNumber);					
		}
		//Database insertion
		boolean b = patientService.add(patient);
		if(b) {
			request.setAttribute("message", "registration successful");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
		}else {
			request.setAttribute("error", "registration failed");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
		}
		
	}
	
	//判断一个病人有没有预约过
	public boolean hasApp(int id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean b = false;
		Appointment appointment = appointmentService.getByPatientId(id);
		if(appointment == null) {   //只要查到有一条记录，说明这个病人已经预约过了
			b = false;
		}else {  //没有查到app记录，说明病人还没有预约过
			b = true;
		}
		return b;
	}
	

}
