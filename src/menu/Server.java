package menu;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Server{
	///Get connection ("link url","username","password")
	public Connection Connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement","root","admin");
	///Create statement
	public Statement statement = Connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 0);
	public Server() throws SQLException{
		
	}
	public ResultSet ExecuteQuery(String query) throws SQLException { // fetch data
		ResultSet Result = statement.executeQuery(query);
		return Result;
	}
	public void InsertCourse(String id, String name, String head,Date SDate, Date EDate,String time)throws SQLException {
		String query = " insert into course (courseID, name, headTeacher,startDate,endDate,time)"
		        + " values (?, ?, ?,?,?,?)";
		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = Connect.prepareStatement(query);
		      preparedStmt.setString (1, id);
		      preparedStmt.setString (2, name);
		      preparedStmt.setString (3, head); 
		      preparedStmt.setDate (4, SDate);
		      preparedStmt.setDate (5, EDate);
		      preparedStmt.setString (6, time);
		      // execute the preparedstatement
		      preparedStmt.execute();
	}
	public void DeleteCourse(String id,String headTeacher) throws SQLException {
		String query1 = "delete from course_attend where courseID= ?";
		String query2 = "delete from course where courseID=? and headteacher = ?";
	      PreparedStatement preparedStmt = Connect.prepareStatement(query1);
	      preparedStmt.setString (1, id);
	      // execute the preparedstatement
	      preparedStmt.execute();
	      preparedStmt = Connect.prepareStatement(query2);
	      preparedStmt.setString (1, id);
	      preparedStmt.setString (2, headTeacher);
	      // execute the preparedstatement
	      preparedStmt.execute();
	}
	public void UpdateCourse(String o_id,String id, String name, Date NStart, Date NEnd, String Ntime) throws SQLException {
		String query = "update course set courseID=?,name=?,startDate=?, endDate=?,time=? where courseID=?";
	      PreparedStatement preparedStmt = Connect.prepareStatement(query);
	      preparedStmt.setString (1, id);
	      preparedStmt.setString (2, name);
	      preparedStmt.setDate (3, NStart);
	      preparedStmt.setDate (4, NEnd);
	      preparedStmt.setString (5, Ntime);
	      preparedStmt.setString (6, o_id);
	      // execute the preparedstatement
	      preparedStmt.execute();
	}
	public void InsertStudent(String id, String name, String Class, String email, String gender, String dob,String phoneNum ) throws SQLException, ParseException {
		String query = " insert into course (id, name, MainClass, email, gender, dob, phoneNumber)"
		        + " values (?,?,?,?,?,?,?)";
		      // create the mysql insert preparedstatement
			  Date date1=(Date) new SimpleDateFormat("yyyy-mm-dd").parse(dob);  
		      PreparedStatement preparedStmt = Connect.prepareStatement(query);
		      preparedStmt.setString (1, id);
		      preparedStmt.setString (2, name);
		      preparedStmt.setString (3, Class);
		      preparedStmt.setString (4,email);
		      preparedStmt.setString (5, gender);
		      preparedStmt.setDate (6, date1);
		      preparedStmt.setString (7, phoneNum);
		      // execute the preparedstatement
		      preparedStmt.execute();
	}
	public void UpdateStudentCourse(String StudentID, String CourseID,Float PracP, Float TheoP, Float overall, String sta  ) throws SQLException {
		String query = " update course_attend set practice_point=?,theory_point=?,overall=?,pass_status=? where courseID=? and StudentID=? " ;
		      PreparedStatement preparedStmt = Connect.prepareStatement(query);
		      if(PracP==null) {
		    	  preparedStmt.setString(1,null);
		      }
		      else {
		    	  preparedStmt.setFloat(1,PracP);
		      }
		      if(TheoP==null) {
		    	  preparedStmt.setString(2,null);
		      }
		      else {
		    	  preparedStmt.setFloat(2,TheoP);
		      }
		      if(overall == null) {
		    	  preparedStmt.setString (3, null);
		      }
		      else {
		    	  preparedStmt.setFloat (3, overall);
		      }
		      preparedStmt.setString (4,sta);
		      preparedStmt.setString (5, CourseID);
		      preparedStmt.setString (6, StudentID);
		      preparedStmt.execute();
	}
	public void DeleteStudentCourse(String StudentID, String CourseID) throws SQLException{
		String query = "delete from course_attend where courseID=? and StudentID=?";
	      PreparedStatement preparedStmt = Connect.prepareStatement(query);
	      preparedStmt.setString (1, CourseID);
	      preparedStmt.setString (2, StudentID);
	      preparedStmt.execute();
	}
	public int ExcecuteQueryUpdate(String query) throws SQLException{ // modify data
		int val = statement.executeUpdate(query);
		return val;
	}
	public void CloseConnection() throws SQLException {
		this.Connect.close();
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
