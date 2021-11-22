package pers.hspt.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import pers.hspt.util.PageData;
import pers.hspt.dto.AppointmentDto;
import pers.hspt.dto.DoctorAppDto;
import pers.hspt.dto.PersonalAppDto;
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
		}else if(method.equals("query")) {
			query(request, response);
		}else if(method.equals("showDoctorList")) {
			showDoctorList(request, response);
		}else if(method.equals("showArchiveList")) {
			showArchiveList(request, response);
		}else if(method.equals("delete")) {
			delete(request, response);
		}
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// ����ɾ��
		String appId = request.getParameter("appId");
		// ���ɾ��
		String strId = request.getParameter("strId");
			
		if (strId != null && !strId.trim().equals("")) {
			String arrId[] = strId.split(","); 
			for (int i = 0; i < arrId.length; i++) {
				appointmentService.delete(Integer.valueOf(arrId[i]));				
			}
			request.setAttribute("message", "delete succeeds");
			showArchiveList(request, response);
			return;
		} else {
			if (appId != null) {			
				appointmentService.delete(Integer.valueOf(appId));
				request.setAttribute("message", "delete succeeds");
				showArchiveList(request, response);
				return;				
			} else {
				request.setAttribute("message", "please select a appointment");
				showArchiveList(request, response);
				return;
			}
		}
		
	}

	private void showArchiveList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
		int rowsCount = appointmentService.getArchiveRowsCount();
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
		List<Appointment> list = appointmentService.getArchiveList(pageData);

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
		request.getRequestDispatcher("/admin/appointmentArchiveList.jsp").forward(request, response);	
	}

	private void showDoctorList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Doctor doctor = (Doctor) request.getSession().getAttribute("doctor");
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
		int rowsCount = appointmentService.getDoctorRowsCount(doctor.getDocId());
		
		pageData.setRowsCount(rowsCount);

		// ������ҳ��
		int pageCount = 0;

		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
				
		List<Appointment> list = appointmentService.getDoctorList(doctor.getDocId(), pageData);
		System.out.println(list.size());
		List<DoctorAppDto> docAppList = new ArrayList<>();
		for(Appointment app : list) {
			Patient patient = patientService.get(app.getpId());
			DoctorAppDto dto = new DoctorAppDto();
			dto.setpName(patient.getName());
			dto.setpRealName(patient.getRealName());
			dto.setpGender(patient.getGender());
			dto.setpTelNum(patient.getTel());
			dto.setpBrpNum(patient.getBrp());
			dto.setAppTime(app.getAppTime());
			docAppList.add(dto);
		}
		request.setAttribute("page", pageData);
		request.setAttribute("docAppList", docAppList);
		request.getRequestDispatcher("/doctor/docAppList.jsp").forward(request, response);		
	}

	private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String imgTxt = request.getParameter("code");
		//�ǿ��ж�
		if(name == null || name.trim().equals("")) {
			request.setAttribute("error", "name can't be null");
			request.getRequestDispatcher("/appointment_query.jsp").forward(request, response);
			return;
		}
		if(password == null || password.trim().equals("")) {
			request.setAttribute("error", "password can't be null");
			request.getRequestDispatcher("/appointment_query.jsp").forward(request, response);
			return;
		}
		if(imgTxt == null || imgTxt.trim().equals("")) {
			request.setAttribute("error", "captcha can't be null");
			request.getRequestDispatcher("/appointment_query.jsp").forward(request, response);
			return;
		}
		//���ǿյģ����ݿ���û��������ˣ�û�оͷ���
		Patient patient  = patientService.get(name);
		if(patient != null) {
			//�����Ƿ���ȷ
			if(!password.trim().equals(patient.getPassword())) {
				request.setAttribute("error", "wrong password");
				request.getRequestDispatcher("/appointment_query.jsp").forward(request, response);
				return;
			}else {
				String str = (String) request.getSession().getAttribute("str");
				if(imgTxt.equals(str)) {									
					List<Appointment> list = appointmentService.getPatientList(patient.getId());
					List<PersonalAppDto> personalAppList = new ArrayList<>();
					for(Appointment app : list) {
						
						Doctor doctor = doctorService.get(app.getDocId());
						Department department = departmentService.get(doctor.getDepId());
						PersonalAppDto dto = new PersonalAppDto();
						dto.setRealName(patient.getRealName());
						dto.setDepName(department.getDepName());
						dto.setDocName(doctor.getDocName());
						dto.setMoney(doctor.getMoney());
						dto.setTelNum(patient.getTel());
						dto.setBrpNum(patient.getBrp());
						dto.setAppTime(app.getAppTime());
						if(app.getAppState().equals("0")) {
							dto.setAppState("haven't been processed yet");
						}else if(app.getAppState().equals("1")) {
							dto.setAppState("approved");
						}else {
							dto.setAppState("disapproved");
						}
						personalAppList.add(dto);
					}
					request.setAttribute("personalAppList", personalAppList);
					request.getRequestDispatcher("/personalAppList.jsp").forward(request, response);
					return;					
				}else {
					request.setAttribute("error", "wrong captcha");
					request.getRequestDispatcher("/appointment_query.jsp").forward(request, response);
					return;
				}
			}
		}else {
			request.setAttribute("error", "don't have this patient");
			request.getRequestDispatcher("/appointment_query.jsp").forward(request, response);
		}
		
		
	}

	private void disapprove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String appId = request.getParameter("appId");
		String strId = request.getParameter("strId");
		System.out.println(appId);
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
		System.out.println(appId);
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
		request.setAttribute("message", "please select a appointment");
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
