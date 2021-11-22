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
	
	//得到管理员列表
		public List<Admin> getAdminList(String adminName,PageData pageData) {
			
			List<Admin> list=new ArrayList<Admin>();
			
			try {
				conn=DBConnection.getConnection();
				stmt=conn.createStatement();
				String sql;
				if(adminName==null||adminName.equals("")){				
					//查询所有的
					if(pageData==null){
						sql="select * from admin order by admin_id";
					}else{
						sql="select * from admin limit "+(pageData.getCurrentPage()-1)*pageData.getPageRows()+","+pageData.getPageRows();
					}
					
				}else{
					//查询单条
					sql="select * from admin where admin_name like '%"+adminName+"%' limit "+(pageData.getCurrentPage()-1)*pageData.getPageRows()+","+pageData.getPageRows();
				}
				
				rs=stmt.executeQuery(sql);
				Admin admin;
				while(rs.next()){
					admin=new Admin();
					admin.setAdminId(rs.getInt(1));
					admin.setAdminName(rs.getString(2));
					admin.setAdminPassword(rs.getString(3));
			       list.add(admin);
					
				}
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			
			return list;
			
		}
		
		public void addAdmin(Admin admin) {
			try {
				conn=DBConnection.getConnection();
				String sql="insert into admin values(default,?,?)";
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, admin.getAdminName());
				pstmt.setString(2, admin.getAdminPassword());
			
				pstmt.executeUpdate();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
		}
		
		//分页时需要查询总行数
		public int getRowsCount(String name){
			int rowsCount=0;
			try {
				conn=DBConnection.getConnection();
				stmt=conn.createStatement();
				String sql="";
				if(name==null){
					sql="select count(*) from admin ";
				}else{
					sql="select count(*) from admin where admin_name like '%"+name+"%' ";
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
		
		//删除
		public void delete(int adminId) {
			try {
				conn=DBConnection.getConnection();
				stmt=conn.createStatement();
				String sql="delete from admin where admin_id="+adminId;
				stmt.executeUpdate(sql);
			} catch (SQLException e) {	
				e.printStackTrace();
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			
		}
		
		public void modify(Admin admin) {
			
			try {
				conn=DBConnection.getConnection();
				String sql="update  admin set admin_name=?,admin_password=? where admin_id="+admin.getAdminId();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, admin.getAdminName());
				pstmt.setString(2, admin.getAdminPassword());
				
				pstmt.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			
			

		}
		
		public Admin getAdmin(int adminId) {
			Admin admin=null;
			
			try {
				conn=DBConnection.getConnection();
				stmt=conn.createStatement();	
				 String	sql="select * from admin where admin_id="+adminId ;
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
