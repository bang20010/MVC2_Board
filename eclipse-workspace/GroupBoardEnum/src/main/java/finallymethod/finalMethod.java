package finallymethod;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface finalMethod {
	void pstmtMethod(Connection conn, PreparedStatement pstmt);
	void rsMethod(Connection conn, PreparedStatement pstmt, ResultSet rs);
}
