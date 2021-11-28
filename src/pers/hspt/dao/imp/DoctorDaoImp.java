package pers.hspt.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pers.hspt.util.PageData;

import pers.hspt.util.DateUtil;

import pers.hspt.dao.DoctorDao;
import pers.hspt.entity.Doctor;
import pers.hspt.util.DBConnection;

public class DoctorDaoImp extends BaseDao implements DoctorDao{
	

		public List<Doctor> getByDepId(int depId, PageData pageData){
			List<Doctor> list=new ArrayList<Doctor>();
			
			conn=DBConnection.getConnection();
	        try {
				stmt=conn.createStatement();
				String sql = "";
				if(pageData == null) {
					sql="select * from doctor where dep_id="+depId;
				}else {
					sql="select * from doctor where dep_id= "+depId + " limit " + (pageData.getCurrentPage()-1)*pageData.getPageRows()+","+pageData.getPageRows();
				}

				rs=stmt.executeQuery(sql);
				Doctor doc=null;
				while(rs.next()){
					doc=new Doctor();
					doc.setDocId(rs.getInt(1));
					doc.setDocName(rs.getString(2));
					doc.setDocPassword(rs.getString(3));
					doc.setMoney(rs.getInt(4));
					doc.setDocTime(rs.getDate(5));
					doc.setDocStatus(rs.getString(6));
					doc.setDepId(rs.getInt(7));
				
					list.add(doc);
				}
			} catch (SQLException e) {		
				e.printStackTrace();			
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			return list;
		}
		
		public void add(Doctor doctor) {
			
			conn=DBConnection.getConnection();
			try {
				String sql="insert into doctor values(default,?,?,?,?,?,?)";
				
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,doctor.getDocName());
				pstmt.setString(2,doctor.getDocPassword());
				pstmt.setInt(3, doctor.getMoney());
				pstmt.setDate(4, DateUtil.toSqlDate(doctor.getDocTime()));
				pstmt.setString(5, doctor.getDocStatus());
				pstmt.setInt(6, doctor.getDepId());
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				
				e.printStackTrace();

			}finally{		
				DBConnection.close(rs, stmt, pstmt);
			}

		}
		

		public int getRowsCount(String name){
			int rowsCount=0;
			try {
				conn=DBConnection.getConnection();
				stmt=conn.createStatement();
				String sql="";
				if(name==null){
					sql="select count(*) from doctor ";
				}else{
					sql="select count(*) from doctor where doc_name like '%"+name+"%' ";
				}
				rs=stmt.executeQuery(sql);
				
				if(rs.next()){	
					rowsCount=rs.getInt(1);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			
			return rowsCount;
			
		}
		

		public List<Doctor> getList(String docName,PageData pageData){

			List<Doctor> list=new ArrayList<Doctor>();
			
			conn=DBConnection.getConnection();
	        try {
				stmt=conn.createStatement();
				String sql="";
				if(docName==null||docName.equals("")){				

					if(pageData==null){
						sql="select * from doctor order by doc_id";
					}else{
						sql="select * from doctor limit "+(pageData.getCurrentPage()-1)*pageData.getPageRows()+","+pageData.getPageRows();
					}
				}else{

					sql="select * from doctor where doc_name like '%"+docName+"%' limit "+(pageData.getCurrentPage()-1)*pageData.getPageRows()+","+pageData.getPageRows();
				}
				
				rs=stmt.executeQuery(sql);
				Doctor doc=null;
				while(rs.next()){
					doc=new Doctor();
					doc.setDocId(rs.getInt(1));
					doc.setDocName(rs.getString(2));
					doc.setDocPassword(rs.getString(3));
					doc.setMoney(rs.getInt(4));
					doc.setDocTime(rs.getDate(5));
					doc.setDocStatus(rs.getString(6));
					doc.setDepId(rs.getInt(7));
				
					list.add(doc);
				}

			} catch (SQLException e) {			
				e.printStackTrace();		
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			
			return list;
		}
		
		public Doctor get(int docId){
			Doctor doc=null;
			conn=DBConnection.getConnection();
	        try {
				stmt=conn.createStatement();

				String	sql="select * from doctor  where doc_id ="+docId;
				
				rs=stmt.executeQuery(sql);
				
				if(rs.next()){
					doc=new Doctor();
					doc.setDocId(rs.getInt(1));
					doc.setDocName(rs.getString(2));
					doc.setDocPassword(rs.getString(3));
					doc.setMoney(rs.getInt(4));
					doc.setDocTime(rs.getDate(5));
					doc.setDocStatus(rs.getString(6));
					doc.setDepId(rs.getInt(7));
							
				}

			} catch (SQLException e) {	
				e.printStackTrace();	
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			return doc;
		}
		
		public Doctor get(String name){
			
			Doctor doc=null;
			conn=DBConnection.getConnection();
	        try {
				stmt=conn.createStatement();

				String	sql="select * from doctor  where doc_name = '"+ name + "'";
				
				rs=stmt.executeQuery(sql);
				
				if(rs.next()){
					doc=new Doctor();
					doc.setDocId(rs.getInt(1));
					doc.setDocName(rs.getString(2));
					doc.setDocPassword(rs.getString(3));
					doc.setMoney(rs.getInt(4));
					doc.setDocTime(rs.getDate(5));
					doc.setDocStatus(rs.getString(6));
					doc.setDepId(rs.getInt(7));
								
				}

			} catch (SQLException e) {			
				e.printStackTrace();			
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}	
			return doc;
		}
		
		public void delete(int docId) {
			conn=DBConnection.getConnection();
			try {
				String sql="delete from doctor where doc_id="+docId;
				pstmt=conn.prepareStatement(sql);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}	
		}
		
		
		public void modify(Doctor doctor) {
			conn=DBConnection.getConnection();
			try {
				String sql="update doctor set doc_name=?,doc_password=?,doc_money=?,doc_time=?,dep_id=? where doc_id="+doctor.getDocId();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,doctor.getDocName());
				pstmt.setString(2, doctor.getDocPassword());
				pstmt.setInt(3, doctor.getMoney());
				pstmt.setDate(4, DateUtil.toSqlDate(doctor.getDocTime()));
				pstmt.setInt(5, doctor.getDepId());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
		}

		@Override
		public int getRowsCountForOneDep(int depId) {
			int rowsCount=0;
			try {
				conn=DBConnection.getConnection();
				stmt=conn.createStatement();
				String sql="";
				
				sql="select count(*) from doctor where dep_id like '%"+depId+"%' ";
				
				rs=stmt.executeQuery(sql);
				
				if(rs.next()){
					
					rowsCount=rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			return rowsCount;
		}
		

}
