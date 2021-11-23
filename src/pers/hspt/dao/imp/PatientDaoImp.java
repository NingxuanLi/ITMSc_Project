package pers.hspt.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pers.hspt.util.PageData;

import pers.hspt.entity.Patient;
import pers.hspt.util.DBConnection;

import pers.hspt.dao.PatientDao;

public class PatientDaoImp extends BaseDao implements PatientDao{
	
	public boolean add(Patient patient) {		
		boolean b=true;
		conn=DBConnection.getConnection();
		try {
			String sql="insert into patient values(default,?,?,?,?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, patient.getName());
			pstmt.setString(2, patient.getPassword());
			pstmt.setString(3, patient.getRealName());
			pstmt.setString(4, patient.getGender());
			pstmt.setString(5, patient.getTel());
			pstmt.setString(6, patient.getBrp());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			b=false;
		}finally{		
			DBConnection.close(rs, stmt, pstmt);
		}
		return b;

	}


	public Patient get(String name) {
		Patient p=null;
		conn=DBConnection.getConnection();
		try {
			stmt=conn.createStatement();
			String sql="select * from patient where p_name='"+name+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				p=new Patient();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPassword(rs.getString(3));
				p.setRealName(rs.getString(4));
				p.setGender(rs.getString(5));
				p.setTel(rs.getString(6));
				p.setBrp(rs.getString(7));
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	public int getRowsCount(String name){
		int rowsCount=0;
		try {
			conn=DBConnection.getConnection();
			stmt=conn.createStatement();
			String sql="";
			if(name==null||name.equals("")){
				sql="select count(*) from patient ";
			}else{
				System.out.println(name);
				sql="select count(*) from patient where p_name like '"+name+"%' ";
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
	
	public List<Patient> getList(String name,PageData pageData) {
		List<Patient> list=new ArrayList<Patient>();	
		conn=DBConnection.getConnection();
        try {
			stmt=conn.createStatement();
			String sql="";
			if(name==null||name.equals("")){				
				
				sql="select * from  patient limit "+(pageData.getCurrentPage()-1)*pageData.getPageRows()+","+pageData.getPageRows();
				
			}else{
						
				sql="select * from  patient where p_name like '%"+name+"%' limit "+(pageData.getCurrentPage()-1)*pageData.getPageRows()+","+pageData.getPageRows();
			}		
			rs=stmt.executeQuery(sql);
			Patient p=null;
			while(rs.next()){
				p=new Patient();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPassword(rs.getString(3));
				p.setRealName(rs.getString(4));
				p.setGender(rs.getString(5));
				p.setTel(rs.getString(6));
				p.setBrp(rs.getString(7));
				list.add(p);
			}
		} catch (SQLException e) {			
			e.printStackTrace();		
		}finally{
			DBConnection.close(rs, stmt, pstmt);
		}		
		return list;
	}
	
	public void delete(int id) {

		conn=DBConnection.getConnection();
		try {
			String sql="delete from patient where p_id="+id;
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBConnection.close(rs, stmt, pstmt);
		}
	}


	@Override
	public Patient get(int id) {
		Patient p=null;
		conn=DBConnection.getConnection();
		try {
			stmt=conn.createStatement();
			String sql="select * from patient where p_id='"+id+"'";
			rs=stmt.executeQuery(sql);
			if(rs.next()){
				p=new Patient();
				p.setId(rs.getInt(1));
				p.setName(rs.getString(2));
				p.setPassword(rs.getString(3));
				p.setRealName(rs.getString(4));
				p.setGender(rs.getString(5));
				p.setTel(rs.getString(6));
				p.setBrp(rs.getString(7));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return p;
	}
	
	

	
	

}
