package pers.hspt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.hspt.entity.Patient;
import pers.hspt.service.PatientService;
import pers.hspt.service.imp.PatientServiceImp;

public class PatientServlet extends HttpServlet{
	private PatientService patientService = new PatientServiceImp();
	
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

		} 
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
		
		//Database insertion
		Patient patient = new Patient(name, password, realName, gender, telNumber, brpNumber);
		boolean b = patientService.add(patient);
		
		if(b) {
			//TODO 在jsp页面用alert说明注册成功
			request.setAttribute("error", "registration successful");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
		}else {
			request.setAttribute("error", "registration failed");
			request.getRequestDispatcher("/patient_registration.jsp").forward(request, response);
		}
		
	}
	

}
