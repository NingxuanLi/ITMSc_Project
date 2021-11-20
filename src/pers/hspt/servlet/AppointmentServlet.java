package pers.hspt.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pers.hspt.util.PageData;
import pers.hspt.dto.AppointmentDto;
import pers.hspt.entity.Appointment;
import pers.hspt.entity.Department;
import pers.hspt.entity.Doctor;
import pers.hspt.entity.Patient;
import pers.hspt.service.AppointmentService;
import pers.hspt.service.DepartmentService;
import pers.hspt.service.DoctorService;
import pers.hspt.service.PatientService;
import pers.hspt.service.imp.AppointmentServiceImp;
import pers.hspt.service.imp.DepartmentServiceImp;
import pers.hspt.service.imp.DoctorServiceImp;
import pers.hspt.service.imp.PatientServiceImp;
import pers.hspt.util.DateUtil;

public class AppointmentServlet extends HttpServlet{
	PatientService patientService = new PatientServiceImp();
	DoctorService doctorService = new DoctorServiceImp();
	AppointmentService appointmentService = new AppointmentServiceImp();
	DepartmentService departmentService = new DepartmentServiceImp();
	
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
		}else if(method.equals("showList")) {
			showList(request, response);
		}else if(method.equals("approve")) {
			approve(request, response);
		}else if(method.equals("disapprove")) {
			disapprove(request, response);
		}
	}

	private void disapprove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String appId = request.getParameter("appId");
		String strId = request.getParameter("strId");
		
		if(strId != null && !strId.trim().equals("")) {
			String[] arrId = strId.split(",");
			for(String id : arrId) {
				appointmentService.disapprove(Integer.valueOf(id));
			}
			showList(request,response);
			return;
		}
		if(appId != null) {
			appointmentService.disapprove(Integer.valueOf(appId));
			showList(request,response);
			return;
		}
		
		//���ǿյģ���û��ѡ��
		request.setAttribute("message", "please select");
		showList(request,response);
		
	}

	private void approve(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String appId = request.getParameter("appId");
		String strId = request.getParameter("strId");
		
		if(strId != null && !strId.trim().equals("")) {
			String[] arrId = strId.split(",");
			for(String id : arrId) {
				appointmentService.approve(Integer.valueOf(id));
			}
			showList(request,response);
			return;
		}
		if(appId != null) {
			appointmentService.approve(Integer.valueOf(appId));
			showList(request,response);
			return;
		}
		
		//���ǿյģ���û��ѡ��
		request.setAttribute("message", "please select");
		showList(request,response);
		
		
	}

	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		PageData pageData = new PageData();// ��ҳ
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
		int rowsCount = appointmentService.getRowsCount();
		pageData.setRowsCount(rowsCount);
		// ������ҳ��
		int pageCount = 0;
		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		
		//��ѯ���ݿ�
		List<Appointment> list = appointmentService.getList(pageData);

		List<AppointmentDto> appList = new ArrayList<>();
		AppointmentDto dto = null;
		for(Appointment app : list) {
			//���patient��doctor��department
			
			Patient patient = patientService.get(app.getpId());
			Doctor doctor = doctorService.get(app.getDocId());
			Department department = departmentService.get(doctor.getDepId());
			
			dto = new AppointmentDto();
			dto.setAppId(app.getAppId());
			dto.setAppNum(app.getAppNum());
			dto.setpId(patient.getId());
			dto.setpName(patient.getName());
			dto.setDepName(department.getDepName());
			dto.setDocId(doctor.getDocId());
			dto.setDocName(doctor.getDocName());
			dto.setAppTime(app.getAppTime());
			
			appList.add(dto);
		}
		//��ת
		request.setAttribute("appList", appList);
		request.setAttribute("page", pageData);
		request.getRequestDispatcher("/admin/appointmentList.jsp").forward(request, response);
		
	}

	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		//�ж��ǲ��ǿ�
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
		//����Ϊ�գ��ж���û���������
		Patient patient = patientService.get(name);
		if(patient != null) {
			//��������ˣ��ж������Ƿ���ȷ
			if(patient.getPassword().equals(password)) {
				//������ȷ����docId������
				String docId = request.getParameter("docId");
				Doctor doctor = doctorService.get(Integer.valueOf(docId));
				String appNum = DateUtil.getDateSampleString(doctor.getDocTime()) + "-" + String.valueOf(doctor.getDocId());
				Appointment app = new Appointment(appNum, patient.getId(), doctor.getDocId(), doctor.getDocTime());
				//��ӽ����ݿ�
				boolean b = appointmentService.add(app);
				if(b) {
					//��ӳɹ�
					request.setAttribute("message", "successfully made a appointment");
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				}else {
					//���ʧ��
					request.setAttribute("error", "failed");
					request.getRequestDispatcher("/appointmentMake.jsp").forward(request, response);
				}
			}else {
				//�������
				request.setAttribute("error", "wrong password, please re-enter");
				request.getRequestDispatcher("/appointmentMake.jsp").forward(request, response);
			}
		}else {
			//û���������
			request.setAttribute("error", "patient not found");
			request.getRequestDispatcher("/appointmentMake.jsp").forward(request, response);
		}	
	}
	

	
	

}
