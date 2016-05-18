package DatabaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection 
{
	public Connection connect() throws Exception
	{
		Connection con = null;
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/cybagenet","root","root");
	
		return con;
	}
	public Connection close(Connection c) throws SQLException 
	{
		c.close();	
		return c;
	}
}
