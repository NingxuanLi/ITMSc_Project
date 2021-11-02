package pers.hspt.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	public static Connection conn=null;
	public static Connection getConnection(){
		if(conn==null){
			init();
			
		}
		
		return conn;
	}
	
	public static void init(){
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/itmsc_project?useUnicode=true&characterEncoding=UTF-8";
			conn=DriverManager.getConnection(url,"root","root");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	public static void close(ResultSet rs, Statement stmt,PreparedStatement pstmt){
		try {
			if (rs!=null){
				rs.close();
			}
			if (stmt!=null){
				stmt.close();
			}
			if (pstmt!=null){
				pstmt.close();
			}
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
	


}
