package pers.hspt.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pers.hspt.dao.AdminDao;
import pers.hspt.entity.Admin;
import pers.hspt.util.DBConnection;
import pers.hspt.util.PageData;

public class AdminDaoImp extends BaseDao implements AdminDao{
	public Admin getAdmin(String adminName){
		Admin admin=null;
		
		try {
			conn=DBConnection.getConnection();
			stmt=conn.createStatement();
			
			String	sql="select * from admin where admin_name='"+adminName+"'";
			rs=stmt.executeQuery(sql);
			
			if(rs.next()){
				admin=new Admin();
				admin.setAdminId(rs.getInt(1));
				admin.setAdminName(rs.getString(2));
				admin.setAdminPassword(rs.getString(3));
		     
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}finally{
			DBConnection.close(rs, stmt, pstmt);
		}
		
		return admin;
	}
	

}
