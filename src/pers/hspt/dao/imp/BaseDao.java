package pers.hspt.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class BaseDao {
	protected Connection conn = null;
	protected Statement stmt = null;
	protected PreparedStatement pstmt = null;
	protected ResultSet rs = null;
}
