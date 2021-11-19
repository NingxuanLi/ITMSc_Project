package pers.hspt.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.hspt.entity.Appointment;
import pers.hspt.entity.Doctor;
import pers.hspt.entity.Patient;
import pers.hspt.service.AppointmentService;
import pers.hspt.service.DoctorService;
import pers.hspt.service.PatientService;
import pers.hspt.service.imp.AppointmentServiceImp;
import pers.hspt.service.imp.DoctorServiceImp;
import pers.hspt.service.imp.PatientServiceImp;
import pers.hspt.util.DateUtil;

public class AppointmentServlet extends HttpServlet{
	PatientService patientService = new PatientServiceImp();
	DoctorService doctorService = new DoctorServiceImp();
	AppointmentService appointmentService = new AppointmentServiceImp();
	
	public AppointmentServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String method = request.getParameter("method");
		if(method.equals("add")) {
			add(request, response);
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		//判断是不是空
		if(name == null || name.trim().equals("")) {
			request.setAttribute("error", "patient name can't be null");
			request.getRequestDispatcher("/appointmentMake.jsp").forward(request, response);
			return;
		}
		if(password == null || password.trim().equals("")) {
			request.setAttribute("error", "password can't be null");
			request.getRequestDispatcher("/appointmentMake.jsp").forward(request, response);
			return;
		}
		//都不为空，判断有没有这个病人
		Patient patient = patientService.get(name);
		if(patient != null) {
			//有这个病人，判断密码是否正确
			if(patient.getPassword().equals(password)) {
				//密码正确，把docId传过来
				String docId = request.getParameter("docId");
				Doctor doctor = doctorService.get(Integer.valueOf(docId));
				String appNum = DateUtil.getDateSampleString(doctor.getDocTime()) + "-" + String.valueOf(doctor.getDocId());
				Appointment app = new Appointment(appNum, patient.getId(), doctor.getDocId(), doctor.getDocTime());
				//添加进数据库
				boolean b = appointmentService.add(app);
				if(b) {
					//添加成功
					request.setAttribute("message", "successfully made a appointment");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}else {
					//添加失败
					request.setAttribute("error", "failed");
					request.getRequestDispatcher("/appointmentMake.jsp").forward(request, response);
				}
			}else {
				//密码错误
				request.setAttribute("error", "wrong password, please re-enter");
				request.getRequestDispatcher("/appointmentMake.jsp").forward(request, response);
			}
		}else {
			//没有这个病人
			request.setAttribute("error", "patient not found");
			request.getRequestDispatcher("/appointmentMake.jsp").forward(request, response);
		}	
	}
	

	
	

}
