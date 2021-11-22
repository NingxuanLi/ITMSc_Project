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
import pers.hspt.entity.Admin;
import pers.hspt.entity.Appointment;
import pers.hspt.service.DoctorService;
import pers.hspt.service.DepartmentService;
import pers.hspt.service.AppointmentService;
import pers.hspt.service.imp.DoctorServiceImp;
import pers.hspt.service.imp.DepartmentServiceImp;
import pers.hspt.service.imp.AppointmentServiceImp;
import pers.hspt.util.DateUtil;
import pers.hspt.util.PageData;
import pers.hspt.util.StringUtil;

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
		// �õ�ֵ
		String method = request.getParameter("method");
		if (method.equals("frontShowList")) {
			// ǰ̨�Һ�ʱ��Ҫ
			frontShowList(request, response);
		} else if (method.equals("docListInOneDep")) {
			// ǰ̨��ѯҽ���б�
			docListInOneDep(request, response);
		} else if (method.equals("showList")) {
			// ��ʾ�����б�
			showList(request, response);
		} else if (method.equals("gotoAdd")) {
			// ���ʱ�ȳ�ʼ�������б��
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
		}else if(method.equals("logout")) {
			logout(request,response);
		}else if(method.equals("appointmentMake")) {
			appointmentMake(request,response);
		}

	}


	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getSession().invalidate();
		request.getRequestDispatcher("/doctor_login.jsp").forward(request, response);
		
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
					request.setAttribute("error", "wrong captcha");
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
		int rowsCount = doctorService.getRowsCountForOneDep(Integer.valueOf(depId));

		pageData.setRowsCount(rowsCount);

		// ������ҳ��
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
		int rowsCount = doctorService.getRowsCount(null);

		pageData.setRowsCount(rowsCount);

		// ������ҳ��
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
		
		String docName = request.getParameter("docName");
		String docPassword = request.getParameter("docPassword");
		String moneyStr = request.getParameter("money");
		if (moneyStr == null || moneyStr.trim().equals("")) {
			request.setAttribute("error", "please enter the appointment fee");
			gotoModify(request, response);
			return;
		}
		if(!StringUtil.isInteger(moneyStr)) {
			request.setAttribute("error", "the appointment fee must be an integer");
			gotoModify(request, response);
			return;
		}
		int money = Integer.valueOf(moneyStr);
		String docTime = request.getParameter("docTime"); //��Ҫת����sql���������
		int depId = Integer.valueOf(request.getParameter("depId"));
		Date timeDate = DateUtil.getBirthDate(docTime);
		
		if(docName == null || docName.trim().equals("")) {
			request.setAttribute("error", "doc name can't be null");
			gotoModify(request, response);
			return;
		}
		if(docPassword == null || docPassword.trim().equals("")) {
			request.setAttribute("error", "doc name can't be null");
			gotoModify(request, response);
			return;
		}
		if(docTime == null || docTime.trim().equals("")) {
			request.setAttribute("error", "appointment date can't be null");
			gotoModify(request, response);
			return;
		}
		
		if(doctor.getDocName().equals(docName)) {
			doctor.setDocName(docName);
			doctor.setDocPassword(docPassword);
			doctor.setMoney(money);
			doctor.setDepId(depId);
			doctor.setDocTime(timeDate);			
			doctorService.modify(doctor);
			request.setAttribute("message", "update succeeds");
			showList(request, response);
		}else {
			boolean isNameRepeated = isNameRepeated(docName, request, response);
			if(isNameRepeated) {
				request.setAttribute("error", "doctor name repeats");
				gotoModify(request, response);
				return;
			}else {
				doctor.setDocName(docName);
				doctor.setDocPassword(docPassword);
				doctor.setMoney(money);
				doctor.setDepId(depId);
				doctor.setDocTime(timeDate);			
				doctorService.modify(doctor);
				request.setAttribute("message", "update succeeds");
				showList(request, response);
			}
		}	
	}


	private void gotoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String docId = request.getParameter("docId");
		boolean hasApp = isHasApp(Integer.valueOf(docId), request, response);
		if(hasApp) {
//			TODO
//			request.setAttribute("message", "can't update a doctor already having an appointment");
			JOptionPane.showMessageDialog(null, "can't update a doctor already having a appointment");
			showList(request, response);
			return;
		}
		List<Department> depList = departmentService.getList(null, null);
		request.setAttribute("depList", depList);
		Doctor doc = doctorService.get(Integer.valueOf(docId));
		Department department = departmentService.get(doc.getDepId());
		//dto��װ
		DoctorDto dto = new DoctorDto();
		dto.setDocId(doc.getDocId());
		dto.setDocName(doc.getDocName());
		dto.setDocPassword(doc.getDocPassword());
		dto.setDepName(department.getDepName());
		dto.setDocStatus(doc.getDocStatus());
		dto.setDocTime(doc.getDocTime());
		dto.setMoney(doc.getMoney());
		request.setAttribute("doctorDto", dto);
		request.getRequestDispatcher("/admin/updateDoctor.jsp").forward(request, response);
		
	}


	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//ɾ������
		String  docId = request.getParameter("docId");
		//ɾ�����
		String strId = request.getParameter("strId");
		
		boolean b = false;
		boolean isApp = false;
		if(strId != null && !strId.trim().equals("")) {
			String arrId[] = strId.split(",");
			for (int i = 0; i < arrId.length; i++) {
				isApp = isHasApp(Integer.valueOf(arrId[i]), request, response);
				//û��ԤԼ
				if(!isApp) {
					doctorService.delete(Integer.valueOf(arrId[i]));
				}
			}
			request.setAttribute("message", "delete succeeds");
			showList(request, response);
			return;
		}else {
			if(docId != null) {
				isApp = isHasApp(Integer.valueOf(docId), request, response);
				if(!isApp) {
					doctorService.delete(Integer.valueOf(docId));
					request.setAttribute("message", "delete succeeds");
					showList(request, response);
					return;
				}else {
//					TODO
//					request.setAttribute("message", "can't delete a doctor already having a appointment"); 
					JOptionPane.showMessageDialog(null, "can't delete a doctor already having a appointment");
					showList(request, response);
					return;
				}
			}else {
				request.setAttribute("message", "please select a doctor");
				showList(request, response);
				return;
			}
		}
	
	}


	private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
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
		int rowsCount = doctorService.getRowsCount(checkName);
		pageData.setRowsCount(rowsCount);
		// ������ҳ��
		int pageCount = 0;
		if (rowsCount % pageData.getPageRows() == 0) {
			pageCount = rowsCount / pageData.getPageRows();
		} else {
			pageCount = rowsCount / pageData.getPageRows() + 1;
		}
		pageData.setPageCount(pageCount);
		// �Ȳ�ѯ���ݿ�,�õ�ҽ���б�
		List<Doctor> docList = doctorService.getList(checkName, pageData);// ��ѯ���м�¼
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
			//�鿴�Ƿ���ԤԼ�������������ʾ��ɫ��û��ԤԼ��ʾ��ɫ��ɾ��ʱֻ��ɾ����ɫ
			boolean isApp = isHasApp(doc.getDocId(), request, response);
			if(isApp) {
				dto.setColor("red");
			}else {
				dto.setColor("black");
			}
			docDtoList.add(dto);
		}
		//��ת��ҳ�沢��ֵ����ȥ
		request.setAttribute("docDtoList", docDtoList);
		request.setAttribute("page", pageData); // ��page����ȥ
		request.setAttribute("checkName", checkName);// �����������
		String appDocId = request.getParameter("appDocId");
		if (appDocId != null) {
			// �õ�����ҽ����Ϣ
			Doctor doc = doctorService.get(Integer.valueOf(appDocId));
			// �õ�������
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
		boolean b = false; // Ĭ��û��ԤԼ
		Appointment appointment = appointmentService.getByDocId(docId);
		if (appointment == null) {
			b = false;// û��ԤԼ
		} else {
			b = true;// ��ԤԼ
		}
		return b;

	}


	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try{
			String docName = request.getParameter("docName");
			String docPassword = request.getParameter("docPassword");
			String moneyStr = request.getParameter("money");
			if (moneyStr == null || moneyStr.trim().equals("")) {
				request.setAttribute("error", "please enter the appointment fee");
				gotoAdd(request, response);
				return;
			}
			if(!StringUtil.isInteger(moneyStr)) {
				request.setAttribute("error", "the appointment fee must be an integer");
				gotoAdd(request, response);
				return;
			}
			int money = Integer.valueOf(moneyStr);
			int depId = Integer.valueOf(request.getParameter("depId"));
			String docTime = request.getParameter("docTime");
			Date timeDate = DateUtil.getBirthDate(docTime);
			if (docName == null || docName.trim().equals("")) {
				request.setAttribute("error", "please enter doctor name");
				gotoAdd(request, response);
				return;
			}
			if (docPassword == null || docPassword.trim().equals("")) {
				request.setAttribute("error", "please enter doctor password");
				gotoAdd(request, response);
				return;
			}
								
			Doctor doctor = doctorService.get(docName);
			if(doctor != null) {
				request.setAttribute("error", "there exist a doctor with the same name, please enter a different name");
				gotoAdd(request, response);
				return;
			}else {
				doctor = new Doctor(docName, docPassword,money, timeDate, "normal", depId);
				doctorService.add(doctor);
				request.setAttribute("message", "add succeeds");
				showList(request, response);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}		
	}


	private void gotoAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		//��ѯ���п���
		List<Department> depList = departmentService.getList(null, null);
		//����depList����ת��jsp
		request.setAttribute("depList", depList);
		request.getRequestDispatcher("/admin/addDoctor.jsp").forward(request, response);
			
	}
	
	public boolean isNameRepeated(String docName, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		boolean isRepeated = false;
		// �жϲ�������
		List<Doctor> list = doctorService.getList(null, null); // �õ��б�,��ѯ���е�
		if (list != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getDocName().equals(docName)) {
					isRepeated = true;
					break;
				}
			}
		}
		return isRepeated;
	}
	
	

}
