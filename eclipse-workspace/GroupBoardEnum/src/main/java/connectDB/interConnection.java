package connectDB;

import java.sql.Connection;

public interface interConnection {
	Connection getConnection() throws Exception;
}
