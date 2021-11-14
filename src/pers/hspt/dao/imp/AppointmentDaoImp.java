package pers.hspt.dao.imp;

import java.sql.SQLException;

import pers.hspt.entity.Appointment;
import pers.hspt.util.DBConnection;

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
				app.setCreateTime(rs.getDate(5));
				app.setAppTime(rs.getDate(6));
			}

		} catch (SQLException e) {
			
			e.printStackTrace();
			
		}finally{
			DBConnection.close(rs, stmt, pstmt);
		}
		
		return app;
	}

}
