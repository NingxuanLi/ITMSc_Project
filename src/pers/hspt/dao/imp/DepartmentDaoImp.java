package pers.hspt.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pers.hspt.entity.Department;
import pers.hspt.util.DBConnection;

import pers.hspt.dao.DepartmentDao;
import pers.hspt.entity.Department;
import pers.hspt.util.PageData;

public class DepartmentDaoImp extends BaseDao implements DepartmentDao{

	//添加
	public boolean add(Department department) {
		boolean b=true;
		conn=DBConnection.getConnection();
		try {
			String sql="insert into department values(?,?)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, department.getDepId());
			pstmt.setString(2, department.getDepName());		
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			b=false;
		}finally{		
			DBConnection.close(rs, stmt, pstmt);
		}
		return b;
	}

	//查询科室
		public List<Department> getList(String depName,PageData pageData) {
			
		
			List<Department> list=new ArrayList<Department>();
			
			conn=DBConnection.getConnection();
	        try {
				stmt=conn.createStatement();
				String sql="";
				if(depName==null||depName.equals("")){				
					//查询所有的
					if(pageData==null){
						
						sql="select * from department";
					}else{
						sql="select * from department limit "+(pageData.getCurrentPage()-1)*pageData.getPageRows()+","+pageData.getPageRows()+" ";	
					}				
				}else{
					//查询单条
					sql="select * from department where dep_name like '%"+depName+"%' limit "+(pageData.getCurrentPage()-1)*pageData.getPageRows()+","+pageData.getPageRows()+" ";
				}
				
				rs=stmt.executeQuery(sql);
				Department department=null;
				while(rs.next()){
					department=new Department();
					department.setDepId(rs.getInt(1));
					department.setDepName(rs.getString(2));
					list.add(department);
				}
			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			
			return list;
		}

		//分页时需要查询总行数
		public int getRowsCount(String name){
			int rowsCount=0;
			try {
				conn=DBConnection.getConnection();
				stmt=conn.createStatement();
				String sql="";
				if(name==null){
					sql="select count(*) from department ";
				}else{
					sql="select count(*) from department where dep_name like '%"+name+"%' ";
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
		public boolean delete(int depId) {
			boolean b=true;
			conn=DBConnection.getConnection();
			try {
				String sql="delete from department where dep_id="+depId;
				pstmt=conn.prepareStatement(sql);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				b=false;
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			
			return b;

		}
		
		public Department get(int depId) {
			
			Department department=null;
			
			conn=DBConnection.getConnection();
	        try {
				stmt=conn.createStatement();
				String sql="select * from Department where dep_id="+depId;
				
				rs=stmt.executeQuery(sql);
			
				if(rs.next()){
					department=new Department();
					department.setDepId(rs.getInt(1));
					department.setDepName(rs.getString(2));
				}

			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			
			return department;
		}
		
        public Department get(String name) {
			
			Department department=null;
			
			conn=DBConnection.getConnection();
	        try {
				stmt=conn.createStatement();
				String sql="select * from Department where dep_name='"+ name + "'";
				
				rs=stmt.executeQuery(sql);
			
				if(rs.next()){
					department=new Department();
					department.setDepId(rs.getInt(1));
					department.setDepName(rs.getString(2));
				}

			} catch (SQLException e) {
				
				e.printStackTrace();
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			
			return department;
		}
		
		//修改
		public boolean modify(Department department,int depId) {
			boolean b=true;
			conn=DBConnection.getConnection();
			try {
				String sql="update department set dep_id=?,dep_name=? where dep_id="+depId;
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, department.getDepId());
				pstmt.setString(2, department.getDepName());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				b=false;
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			
			return b;
		}

}
