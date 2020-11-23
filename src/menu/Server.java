package menu;

import java.sql.*;

public class Server{
	public static void main(String args[]) throws SQLException {
		///Get connection ("link url","username","password")
		Connection Connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","admin","admin");
		///Create statement
		Statement statement = Connect.createStatement();
		///Execute SQL query
		ResultSet Result = statement.executeQuery(" select * from student where student.MainClass='18clc4' ");
		///Process the result set
		while(Result.next()) {
			System.out.println(Result.getString("id"));
		}
	}
	
	
}
