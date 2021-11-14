package pers.hspt.dao.imp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pers.hspt.dao.DoctorDao;
import pers.hspt.entity.Doctor;
import pers.hspt.util.DBConnection;

public class DoctorDaoImp extends BaseDao implements DoctorDao{
	
	//根据科室查询医生
		public List<Doctor> getByDepId(int depId){
			List<Doctor> list=new ArrayList<Doctor>();
			
			conn=DBConnection.getConnection();
	        try {
				stmt=conn.createStatement();
				String sql="select * from doctor where dep_id="+depId;

				rs=stmt.executeQuery(sql);
				Doctor doc=null;
				while(rs.next()){
					doc=new Doctor();
					doc.setDocId(rs.getInt(1));
					doc.setDocName(rs.getString(2));
					doc.setDocPassword(rs.getString(3));
					doc.setDocImg(rs.getString(4));
					doc.setMoney(rs.getInt(5));
					doc.setDocTime(rs.getDate(6));
					doc.setSumCount(rs.getInt(7));
					doc.setRemainCount(rs.getInt(8));
					doc.setDocStatus(rs.getString(9));
					doc.setDepId(rs.getInt(10));
				
					list.add(doc);
				}

			} catch (SQLException e) {
				
				e.printStackTrace();
				
			}finally{
				DBConnection.close(rs, stmt, pstmt);
			}
			return list;
		}

}
