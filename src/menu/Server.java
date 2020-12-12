package menu;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Server {
	/// Get connection ("link url","username","password")
	public Connection Connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement", "root",
			"admin");
	/// Create statement
	public Statement statement = Connect.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 0);

	public Server() throws SQLException {

	}

	public ResultSet ExecuteQuery(String query) throws SQLException { // fetch data
		ResultSet Result = statement.executeQuery(query);
		return Result;
	}

	// public void InsertCourse(String id, String name, String head,Date SDate, Date
	// EDate,String time)throws SQLException {
	// String query = " insert into course (courseID, name,
	// headTeacher,startDate,endDate,time)"
	// + " values (?, ?, ?,?,?,?)";
	// // create the mysql insert preparedstatement
	// PreparedStatement preparedStmt = Connect.prepareStatement(query);
	// preparedStmt.setString (1, id);
	// preparedStmt.setString (2, name);
	// preparedStmt.setString (3, head);
	// preparedStmt.setDate (4, SDate);
	// preparedStmt.setDate (5, EDate);
	// preparedStmt.setString (6, time);
	// // execute the preparedstatement
	// preparedStmt.execute();
	// }
	// public void InsertCourse(String id, String name, String head,Date SDate, Date
	// EDate,String time)throws SQLException {
	// String query = " insert into course (courseID, name,
	// headTeacher,startDate,endDate,time)"
	// + " values (?, ?, ?,?,?,?)";
	// // create the mysql insert preparedstatement
	// PreparedStatement preparedStmt = Connect.prepareStatement(query);
	// preparedStmt.setString (1, id);
	// preparedStmt.setString (2, name);
	// preparedStmt.setString (3, head);
	// preparedStmt.setDate (4, SDate);
	// preparedStmt.setDate (5, EDate);
	// preparedStmt.setString (6, time);
	// // execute the preparedstatement
	// preparedStmt.execute();
	// }
	
	public void AddStudenttoCourse(String id, String courseID) throws SQLException {
		String query = " insert into course_attend (courseID,StudentID,practice_point,theory_point,overall,pass_status)"
				+ " values (?, ?, ?,?,?,?)";
		// create the mysql insert preparedstatement
		PreparedStatement preparedStmt = Connect.prepareStatement(query);
		preparedStmt.setString(1, courseID);
		preparedStmt.setString(2, id);
		preparedStmt.setString(3, null);
		preparedStmt.setDate(4, null);
		preparedStmt.setDate(5, null);
		preparedStmt.setString(6, null);
		preparedStmt.execute();
	}

	public void DeleteCourse(String id, String headTeacher) throws SQLException {
		String query1 = "delete from course_attend where courseID= ?";
		String query2 = "delete from course where courseID=? and headteacher = ?";
		PreparedStatement preparedStmt = Connect.prepareStatement(query1);
		preparedStmt.setString(1, id);
		// execute the preparedstatement
		preparedStmt.execute();
		preparedStmt = Connect.prepareStatement(query2);
		preparedStmt.setString(1, id);
		preparedStmt.setString(2, headTeacher);
		// execute the preparedstatement
		preparedStmt.execute();
	}

	public void UpdateCourse(String o_id, String id, String name, String NStart, String NEnd, String Ntime)
			throws SQLException {
		String query = "update course set courseID=?,name=?,startDate=?, endDate=?,time=? where courseID=?";
		PreparedStatement preparedStmt = Connect.prepareStatement(query);
		preparedStmt.setString(1, id);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, NStart);
		preparedStmt.setString(4, NEnd);
		preparedStmt.setString(5, Ntime);
		preparedStmt.setString(6, o_id);
		// execute the preparedstatement
		preparedStmt.execute();
	}

	public void UpdateStudentCourse(String StudentID, String CourseID, Float PracP, Float TheoP, Float overall,
			String sta) throws SQLException {
		String query = " update course_attend set practice_point=?,theory_point=?,overall=?,pass_status=? where courseID=? and StudentID=? ";
		PreparedStatement preparedStmt = Connect.prepareStatement(query);
		if (PracP == null) {
			preparedStmt.setString(1, null);
		} else {
			preparedStmt.setFloat(1, PracP);
		}
		if (TheoP == null) {
			preparedStmt.setString(2, null);
		} else {
			preparedStmt.setFloat(2, TheoP);
		}
		if (overall == null) {
			preparedStmt.setString(3, null);
		} else {
			preparedStmt.setFloat(3, overall);
		}
		preparedStmt.setString(4, sta);
		preparedStmt.setString(5, CourseID);
		preparedStmt.setString(6, StudentID);
		preparedStmt.execute();
	}

	public void DeleteStudentCourse(String StudentID, String CourseID) throws SQLException {
		String query = "delete from course_attend where courseID=? and StudentID=?";
		PreparedStatement preparedStmt = Connect.prepareStatement(query);
		preparedStmt.setString(1, CourseID);
		preparedStmt.setString(2, StudentID);
		preparedStmt.execute();
	}

	public void UpdateInforTeacher(String id, String name, String phoneNumber, String email, String dob)
			throws SQLException {
		String query = "update teacher set name=?,phoneNumber=?,email=?,dob=? where id=?";

		PreparedStatement preparedStmt = Connect.prepareStatement(query);
		preparedStmt.setString(1, name);
		preparedStmt.setString(2, phoneNumber);
		preparedStmt.setString(3, email);
		preparedStmt.setString(4, dob);
		preparedStmt.setString(5, id);
		preparedStmt.execute();
	}

	public void UpdateInforStudent(String id, String name, String MainClass, String email, String gender, String dob, String phoneNumber)
			throws SQLException {
		String query = "update student set name=?,MainClass=?,email=?,gender=?,dob=?,phoneNumber=? where id=?";

		PreparedStatement preparedStmt = Connect.prepareStatement(query);
		preparedStmt.setString(1, name);
		preparedStmt.setString(2, MainClass);
		preparedStmt.setString(3, email);
		preparedStmt.setString(4, gender);
		preparedStmt.setString(5, dob);
		preparedStmt.setString(6, phoneNumber);
		preparedStmt.setString(7, id);
		preparedStmt.execute();
	}
	
	// admin
	public void UpdateInforAdministrator(String id, String email, String name, String dob, String phoneNumber)
			throws SQLException {
		String query = "update administrator set email=?,name=?, dob=?, phoneNumber=? where id=?";

		PreparedStatement preparedStmt = Connect.prepareStatement(query);
		preparedStmt.setString(1, email);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, dob);
		preparedStmt.setString(4, phoneNumber);
		preparedStmt.setString(5, id);
		preparedStmt.execute();
	}

	public void InsertStudent(String id, String name, String Class, String email, String gender, String dob,
			String phoneNum) throws SQLException {
		PreparedStatement preparedStmt = Connect
				.prepareStatement("insert into credential (username, role)" + " values (?,?)");
		preparedStmt.setString(1, id);
		preparedStmt.setString(2, "Student");
		preparedStmt.execute();
		String query = " insert into student (id, name, MainClass, email, gender, dob, phoneNumber)"
				+ " values (?,?,?,?,?,?,?)";
		preparedStmt = Connect.prepareStatement(query);
		preparedStmt.setString(1, id);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, Class);
		preparedStmt.setString(4, email);
		preparedStmt.setString(5, gender);
		preparedStmt.setString(6, dob);
		preparedStmt.setString(7, phoneNum);
		preparedStmt.execute();
	}

	public void UpdateStudent(Server ServerConnection, String o_id, String id, String name, String Class, String email,
			String gender, String dob, String phoneNum) throws SQLException {
		if (!o_id.equals(id)) {
			ResultSet data = ServerConnection.ExecuteQuery("select * from credential where username=" + o_id);
			data.next();
			String password = data.getString("password");
			String role = data.getString("role");

			String query1 = "insert into credential (username, password, role)" + " values (?,?,?)";
			String query2 = "insert into student (id, name, MainClass, email, gender, dob, phoneNumber)"
					+ " values (?,?,?,?,?,?,?)";
			String query3 = "update course_attend set StudentID=? where StudentID=?";
			String query4 = "update registered_course set studentID=? where studentID=?";
			String query5 = "delete from student where id=?";
			String query6 = "delete from credential where username=?";
			PreparedStatement preparedStmt;

			preparedStmt = Connect.prepareStatement(query1);
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, role);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query2);
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, Class);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, gender);
			preparedStmt.setString(6, dob);
			preparedStmt.setString(7, phoneNum);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query3);
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, o_id);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query4);
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, o_id);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query5);
			preparedStmt.setString(1, o_id);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query6);
			preparedStmt.setString(1, o_id);
			preparedStmt.execute();
		} else {
			String query = "update student set name=?, MainClass=?, email=?, gender=?, dob=?, phoneNumber=? where id=?";
			PreparedStatement preparedStmt = Connect.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, Class);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, gender);
			preparedStmt.setString(5, dob);
			preparedStmt.setString(6, phoneNum);
			preparedStmt.setString(7, id);
			preparedStmt.execute();
		}

	}

	public void DeleteStudent(String id) throws SQLException {
		String query1 = "delete from course_attend where StudentID=?";
		String query2 = "delete from registered_course where studentID=?";
		String query3 = "delete from student where id= ?";
		String query4 = "delete from credential where username=?";

		PreparedStatement preparedStmt = Connect.prepareStatement(query1);
		preparedStmt.setString(1, id);
		preparedStmt.execute();

		preparedStmt = Connect.prepareStatement(query2);
		preparedStmt.setString(1, id);
		preparedStmt.execute();

		preparedStmt = Connect.prepareStatement(query3);
		preparedStmt.setString(1, id);
		preparedStmt.execute();

		preparedStmt = Connect.prepareStatement(query4);
		preparedStmt.setString(1, id);
		preparedStmt.execute();
	}

	public void InsertTeacher(String id, String name, String phoneNumber, String email, String dob)
			throws SQLException {
		PreparedStatement preparedStmt = Connect
				.prepareStatement("insert into credential (username, role)" + " values (?,?)");
		preparedStmt.setString(1, id);
		preparedStmt.setString(2, "Teacher");
		preparedStmt.execute();
		String query = "insert into teacher (id, name, phoneNumber, email, dob)" + " values (?,?,?,?,?)";
		preparedStmt = Connect.prepareStatement(query);
		preparedStmt.setString(1, id);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, phoneNumber);
		preparedStmt.setString(4, email);
		preparedStmt.setString(5, dob);
		preparedStmt.execute();
	}

	public void UpdateTeacher(Server ServerConnection, String o_id, String id, String name, String phoneNumber,
			String email, String dob) throws SQLException {
		if (!o_id.equals(id)) {
			ResultSet data = ServerConnection.ExecuteQuery("select * from credential where username='" + o_id + "'");
			data.next();
			String password = data.getString("password");
			String role = data.getString("role");
			String query1 = "insert into credential (username, password, role)" + " values (?,?,?)";
			String query2 = "insert into teacher (id, name, phoneNumber, email, dob)" + " values (?,?,?,?,?)";
			String query3 = "update course set headTeacher=? where headTeacher= ?";
			String query4 = "delete from teacher where id='?'";
			String query5 = "delete from credential where username='?'";
			PreparedStatement preparedStmt;

			preparedStmt = Connect.prepareStatement(query1);
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, password);
			preparedStmt.setString(3, role);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query2);
			preparedStmt.setString(1, id);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, phoneNumber);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, dob);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query3);
			preparedStmt.setString(1, "'" + id + "'");
			preparedStmt.setString(2, "'" + o_id + "'");
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query4);
			preparedStmt.setString(1, o_id);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query5);
			preparedStmt.setString(1, o_id);
			preparedStmt.execute();
		} else {
			String query = "update teacher set name=?, phoneNumber=?, email=?, dob=? where id=?";
			PreparedStatement preparedStmt = Connect.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, phoneNumber);
			preparedStmt.setString(3, email);
			preparedStmt.setString(4, dob);
			preparedStmt.setString(5, id);
			preparedStmt.execute();
		}

	}

	public void DeleteTeacher(String id) throws SQLException {
		String query1 = "delete from course where headTeacher=?";
		String query2 = "delete from teacher where id= ?";
		String query3 = "delete from credential where username=?";

		PreparedStatement preparedStmt = Connect.prepareStatement(query1);
		preparedStmt.setString(1, id);
		preparedStmt.execute();

		preparedStmt = Connect.prepareStatement(query2);
		preparedStmt.setString(1, id);
		preparedStmt.execute();

		preparedStmt = Connect.prepareStatement(query3);
		preparedStmt.setString(1, id);
		preparedStmt.execute();
	}

	public void InsertCourse(String courseID, String name, String headTeacher, String startDate, String endDate,
			String time) throws SQLException {
		String query = "insert into course (courseID, name, headTeacher, startDate, endDate, time)"
				+ " values (?,?,?,?,?,?)";
		PreparedStatement preparedStmt = Connect.prepareStatement(query);
		preparedStmt = Connect.prepareStatement(query);
		preparedStmt.setString(1, courseID);
		preparedStmt.setString(2, name);
		preparedStmt.setString(3, headTeacher);
		preparedStmt.setString(4, startDate);
		preparedStmt.setString(5, endDate);
		preparedStmt.setString(6, time);
		preparedStmt.execute();
	}

	public void UpdateCourseAdmin(Server ServerConnection, String o_id, String courseID, String name,
			String headTeacher, String startDate, String endDate, String time) throws SQLException {
		if (!o_id.equals(courseID)) {
			String query1 = "insert into course (courseID, name, headTeacher, startDate, endDate, time)"
					+ " values (?,?,?,?,?,?)";
			String query2 = "update course_attend set courseID=? where courseID=?";
			String query3 = "update registered_course set courseID=? where courseID=?";
			String query4 = "delete from course where courseID=?";
			PreparedStatement preparedStmt;

			preparedStmt = Connect.prepareStatement(query1);
			preparedStmt.setString(1, courseID);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, headTeacher);
			preparedStmt.setString(4, startDate);
			preparedStmt.setString(5, endDate);
			preparedStmt.setString(6, time);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query2);
			preparedStmt.setString(1, courseID);
			preparedStmt.setString(2, o_id);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query3);
			preparedStmt.setString(1, courseID);
			preparedStmt.setString(2, o_id);
			preparedStmt.execute();

			preparedStmt = Connect.prepareStatement(query4);
			preparedStmt.setString(1, o_id);
			preparedStmt.execute();

		} else {
			String query = "update course set name=?, headTeacher=?, startDate=?, endDate=?, time=? where courseID=?";
			PreparedStatement preparedStmt = Connect.prepareStatement(query);
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, headTeacher);
			preparedStmt.setString(3, startDate);
			preparedStmt.setString(4, endDate);
			preparedStmt.setString(5, time);
			preparedStmt.setString(6, courseID);
			preparedStmt.execute();
		}

	}

	public void DeleteCourse(String id) throws SQLException {
		String query1 = "delete from course_attend where courseID=?";
		String query2 = "delete from registered_course where courseID=?";
		String query3 = "delete from course where courseID=?";

		PreparedStatement preparedStmt = Connect.prepareStatement(query1);
		preparedStmt.setString(1, id);
		preparedStmt.execute();

		preparedStmt = Connect.prepareStatement(query2);
		preparedStmt.setString(1, id);
		preparedStmt.execute();

		preparedStmt = Connect.prepareStatement(query3);
		preparedStmt.setString(1, id);
		preparedStmt.execute();
	}

	public int ExcecuteQueryUpdate(String query) throws SQLException { // modify data
		int val = statement.executeUpdate(query);
		return val;
	}

	public void CloseConnection() throws SQLException {
		this.Connect.close();
	}

	public static void main(String args[]) throws SQLException {
		Server a = new Server();
		ResultSet Result = a.ExecuteQuery("select * from student");
		/// Process the result set
		while (Result.next()) {
			System.out.println(Result.getString("id") + " " + Result.getString("email"));
		}
	}
}
