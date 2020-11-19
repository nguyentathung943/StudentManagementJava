package menu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
public class ReadJsonFile {

	public static void main(String[] args) throws IOException, ParseException{
		JSONParser parser = new JSONParser();
		Reader reader = new FileReader("C:\\Users\\tathu\\Desktop\\SWING\\App\\src\\file\\Student.json");
		Object obj = parser.parse(reader);
		JSONArray list = (JSONArray) obj;
		
		
		//System.out.print(list);
		list.forEach(element -> printStudentInfo((JSONObject)element));
	}
	static void printStudentInfo(JSONObject a) {
		String id = (String)a.get("id");
		String name = (String)a.get("name");
		String email = (String)a.get("email");
		String gender = (String)a.get("gender");
		JSONObject dob = (JSONObject)a.get("dob");

		String MainClass = (String)a.get("MainClass");
		//JSONArray SubjectClass = (JSONArray)a.get("SubjectClass");
		ArrayList<String> SubjectClass = (ArrayList<String>) a.get("SubjectClass");
		String password = (String)a.get("password");
		
		DOB temp = new DOB((String)dob.get("day"),(String)dob.get("month"),(String)dob.get("year"));
		Student stu = new Student(id,name,email,gender,temp,MainClass,SubjectClass,password);
		System.out.println(stu.toString());
		System.out.println(SubjectClass);
	}

}
