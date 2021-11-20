package pers.hspt.dao.imp;

import java.sql.SQLException;

import pers.hspt.entity.Appointment;
import pers.hspt.util.DBConnection;
import pers.hspt.util.DateUtil;
import pers.hspt.dao.AppointmentDao;

public class AppointmentDaoImp extends BaseDao implements AppointmentDao{

	@Override
	public Appointment getByDocId(int docId){
		// ��ʾ�б�ʱ��ѯ���еģ��޸ĳ�ʼ��ʱ��ѯ������¼
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

}
