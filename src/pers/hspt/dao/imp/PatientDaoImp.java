package pers.hspt.dao.imp;

import java.sql.SQLException;

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
				p.setSex(rs.getString(5));
				p.setTel(rs.getString(6));
				p.setBrp(rs.getString(7));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return p;
	}
	
	

	
	

}
