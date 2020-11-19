package menu;
import java.util.*;



import java.io.FileWriter;
import java.io.IOException;

class DOB{
	String day;
	String month;
	String year;
	DOB(String day, String month, String year){
		this.day = day;
		this.month = month;
		this.year = year;
	}
	public String toString() {
		return day+"/"+month+"/"+year;
	}
}
class Student{
	String id;
	String name;
	String email;
	String gender;
	DOB dob;
	String MainClass;
	ArrayList<String> SubjectClass;
	String password;
	Student(String id, String name,String email,String gender, DOB dob, String MainClass, ArrayList<String> SubjectClass, String Password){
		this.id = id;
		this.name = name;
		this.email=email;
		this.gender = gender;
		this.dob=dob;
		this.MainClass=MainClass;
		this.SubjectClass=SubjectClass;
		this.password=Password;
	}
	public String toString() {
		return "ID: " + this.id + " Name: " + this.name + " Email: " + this.email + " Gender: " + this.gender + " DOB: " + this.dob.toString() + " Class: " + this.MainClass;
	}
}