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
		}else if ("doctorLogin".equals(method)) {
//			doctorLogin(request,response);
		}else if("logout".equals(method)) {
//			loginOut(request,response);
		}else if(method.equals("appointmentMake")) {
			appointmentMake(request,response);
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
		System.out.println(docId);
		
		String docName = request.getParameter("docName");
		int money = Integer.valueOf(request.getParameter("money"));
		String docTime = request.getParameter("docTime"); //��Ҫת����sql���������
		int depId = Integer.valueOf(request.getParameter("depId"));
		Date timeDate = DateUtil.getBirthDate(docTime);
		
		doctor.setDocName(docName);
		doctor.setMoney(money);
		doctor.setDepId(depId);
		doctor.setDocTime(timeDate);
		
		boolean b = doctorService.modify(doctor);
		if(b) {
			//�޸ĳɹ�����ת��showList
			System.out.println("modification succeeded");
			showList(request, response);
		}else {
			//�޸�ʧ��
			System.out.println("modification failed");
			request.setAttribute("error", "modification failed");
			request.getRequestDispatcher("/admin/doctorList.jsp").forward(request, response);
		}
	}


	private void gotoModify(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Department> depList = departmentService.getList(null, null);
		request.setAttribute("depList", depList);
		String docId = request.getParameter("docId");
		Doctor doc = doctorService.get(Integer.valueOf(docId));
		Department department = departmentService.get(doc.getDepId());
		//dto��װ
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
				JOptionPane.showMessageDialog(null, "You haven't select a doctor");// ����ȥ
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
			int money = Integer.valueOf(request.getParameter("money"));
			int depId = Integer.valueOf(request.getParameter("depId"));
			String docTime = request.getParameter("docTime");
			Date timeDate = DateUtil.getBirthDate(docTime);
			
			Doctor doctor = new Doctor(docName, money, timeDate, "normal", depId);
			
			boolean b = doctorService.add(doctor);
			//��ӳɹ�
			if(b) {
				request.setAttribute("message", "successfully added, the default password is 123");
				showList(request, response);
			//���ʧ��	
			}else {
				request.setAttribute("error", "add failed");
				request.getRequestDispatcher("/admin/addDoctor.jsp").forward(request, response);
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

}
