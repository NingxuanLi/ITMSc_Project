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
		//single delete
		String pId = request.getParameter("pId");
		//mutiple delete
		String strId = request.getParameter("strId");
		
		boolean hasApp = false; 
		if(strId != null && !strId.trim().equals("")) {
			String[] arrId = strId.split(",");
			for(String id : arrId) {
				hasApp = hasApp(Integer.valueOf(id), request, response);
				if(!hasApp) { 
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
				//TODO 
				JOptionPane.showMessageDialog(null, "can't delete a patient already making an appointment");
//				request.setAttribute("message", "can't delete a patient already making an appointment");
				showList(request, response);
				return;
			}
		}
		request.setAttribute("message", "please select a patient");
		showList(request, response);
	}

	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// get name for search function
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
		int rowsCount = patientService.getRowsCount(checkName);	
		pageData.setRowsCount(rowsCount);	
		// calculate toal number of pages
		int pageCount = 0;

		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		
		List<Patient> patientList = patientService.getList(checkName, pageData);
		//if a patient has appointments, set his color to red(can't be deleted)
		for(Patient p : patientList) {
			boolean hasApp = hasApp(p.getId(), request, response);
			if(hasApp) {  
				p.setColor("red");
			}else {  
				p.setColor("black");
			}
		}
		//jump
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
		
		Patient patient = patientService.get(name); //patient name must be unique
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
	
	//judge whether a patient already has appointments
	public boolean hasApp(int id, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean b = false;
		Appointment appointment = appointmentService.getByPatientId(id);
		if(appointment == null) {   
			b = false;
		}else { 
			b = true;
		}
		return b;
	}
	

}
