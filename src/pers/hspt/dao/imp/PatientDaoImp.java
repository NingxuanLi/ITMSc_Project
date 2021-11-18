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

	
	

}
