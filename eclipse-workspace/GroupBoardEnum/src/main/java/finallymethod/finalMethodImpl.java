package finallymethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class finalMethodImpl implements finalMethod{

	@Override
	public void pstmtMethod(Connection conn, PreparedStatement pstmt) {
		if(conn != null) try {conn.close();	} catch (Exception e) {}
		if (pstmt != null)try {pstmt.clearParameters();} catch (Exception e) {}
	}

	@Override
	public void rsMethod(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null)try {rs.close();	} catch (Exception e) {}
		if (pstmt != null)try {pstmt.clearParameters();} catch (Exception e) {}
		if (conn != null)try {conn.close();	} catch (Exception e) {}
		
	}
	
}
