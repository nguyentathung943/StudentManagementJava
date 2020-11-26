package menu;

import java.sql.*;

public class Server{
	///Get connection ("link url","username","password")
	Connection Connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","admin","admin");
	///Create statement
	Statement statement = Connect.createStatement();
	Server() throws SQLException{
		
	}
	ResultSet ExecuteQuery(String query) throws SQLException {
		ResultSet Result = statement.executeQuery(query);
		return Result;
	}
	
	
	public static void main(String args[]) throws SQLException {
		Server a = new Server();
		ResultSet Result = a.ExecuteQuery("select * from student");
		///Process the result set
		while(Result.next()) {
			System.out.println(Result.getString("id")+ " " + Result.getString("email"));
		}
	}
}
