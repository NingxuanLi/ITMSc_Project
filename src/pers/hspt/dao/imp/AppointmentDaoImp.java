package pers.hspt.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import pers.hspt.entity.Appointment;
import pers.hspt.util.DBConnection;
import pers.hspt.util.DateUtil;
import pers.hspt.util.PageData;
import pers.hspt.dao.AppointmentDao;

public class AppointmentDaoImp extends BaseDao implements AppointmentDao{

	@Override
	public Appointment getByDocId(int docId){
		// 显示列表时查询所有的，修改初始化时查询单条记录
		Appointment app=null;
		conn=DBConnection.getConnection();
        try {
			stmt=conn.createStatement();
			String sql="select * from appointment where doc_id="+docId;

			rs=stmt.executeQuery(sql);
			
			if(rs.next()){
				app=new Appointment();
				app.setAppId(rs.getInt(1));
				app.setAppNum(rs.getString(2));
				app.setpId(rs.getInt(3));
				app.setDocId(rs.getInt(4));
				app.setAppTime(rs.getDate(5));
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally{
			DBConnection.close(rs, stmt, pstmt);
		}
		
		return app;
	}

	@Override
	public boolean add(Appointment appointment) {
		boolean b = true;
		conn=DBConnection.getConnection();
		try {
			String sql = "insert into appointment values(default,?,?,?,?,default)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, appointment.getAppNum());
			pstmt.setInt(2, appointment.getpId());
			pstmt.setInt(3, appointment.getDocId());
			pstmt.setDate(4, DateUtil.toSqlDate(appointment.getAppTime()));
			
			pstmt.executeUpdate();
		
		}catch(SQLException e) {
			e.printStackTrace();
			b = false;
		}finally {
			DBConnection.close(rs, stmt, pstmt);
		}
		return b;
	}

	@Override
	public Appointment getByPatientId(int id) {
		Appointment app = null;
		conn=DBConnection.getConnection();
		try {
			stmt=conn.createStatement();
			String sql="select * from appointment where p_id="+id;
			rs=stmt.executeQuery(sql);
			
			if(rs.next()) {
				app = new Appointment();
				app.setAppId(rs.getInt(1));
				app.setAppNum(rs.getString(2));
				app.setpId(rs.getInt(3));
				app.setDocId(rs.getInt(4));
				app.setAppTime(rs.getDate(5));
				app.setAppState(rs.getString(6));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBConnection.close(rs, stmt, pstmt);
		}
		return app;
	}

	@Override
	public int getRowsCount() {
		int rowsCount = 0;
		try {
			conn=DBConnection.getConnection();
			stmt=conn.createStatement();
			String sql="";
			sql="select count(*) from appointment ";
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

	@Override
	public List<Appointment> getList(PageData pageData) {
		List<Appointment> list = new ArrayList<>();
		conn=DBConnection.getConnection();
        try {
			stmt=conn.createStatement();
			String sql="";
			sql="select * from appointment where app_state = 0 limit "+(pageData.getCurrentPage()-1)*pageData.getPageRows()+","+pageData.getPageRows();
			rs=stmt.executeQuery(sql);
			Appointment app=null;
			while(rs.next()){
				app=new Appointment();
				app.setAppId(rs.getInt(1));
				app.setAppNum(rs.getString(2));
				app.setpId(rs.getInt(3));
				app.setDocId(rs.getInt(4));
				app.setAppTime(rs.getDate(5));
				list.add(app);
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally{
			DBConnection.close(rs, stmt, pstmt);
		}
		
		return list;
	}

	@Override
	public void approve(int appId) {
		conn=DBConnection.getConnection();
		try {
			String sql="update appointment set app_state=1 where app_id="+appId;
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(rs, stmt, pstmt);
		}
		
	}

	@Override
	public void disapprove(int appId) {
		conn=DBConnection.getConnection();
		try {
			String sql="update appointment set app_state=2 where app_id="+appId;
			pstmt=conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBConnection.close(rs, stmt, pstmt);
		}
	}
}
