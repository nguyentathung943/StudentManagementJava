package menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.sql.*;
public class Info extends Container {
	private String dates[] 
	        = { "1", "2", "3", "4", "5", 
	            "6", "7", "8", "9", "10", 
	            "11", "12", "13", "14", "15", 
	            "16", "17", "18", "19", "20", 
	            "21", "22", "23", "24", "25", 
	            "26", "27", "28", "29", "30", 
	            "31" }; 
	    private String months[] 
	        = { "Jan", "feb", "Mar", "Apr", 
	            "May", "Jun", "July", "Aug", 
	            "Sup", "Oct", "Nov", "Dec" }; 
	    private String years[] 
	        = { "1995", "1996", "1997", "1998", 
	            "1999", "2000", "2001", "2002", 
	            "2003", "2004", "2005", "2006", 
	            "2007", "2008", "2009", "2010", 
	            "2011", "2012", "2013", "2014", 
	            "2015", "2016", "2017", "2018", 
	            "2019" }; 
	    private JTextField emailText;
	public Info(ResultSet Client) throws SQLException {
		Container c = this;
		JLabel title = new JLabel("Information"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 30); 
        title.setLocation(500, 30); 
        c.add(title); 
  
        JLabel name = new JLabel("Name"); 
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 30);
        name.setLocation(300, 100); 
        c.add(name); 
  
        JTextField tname = new JTextField();
        tname.setText(Client.getString("name"));
        tname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tname.setSize(190, 30); 
        tname.setLocation(400, 100); 
        c.add(tname); 
  
        JLabel mno = new JLabel("Mobile"); 
        mno.setFont(new Font("Arial", Font.PLAIN, 20)); 
        mno.setSize(100, 30); 
        mno.setLocation(300, 150); 
        c.add(mno); 
  
        JTextField tmno = new JTextField(); 
        tmno.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tmno.setSize(190, 30); 
        tmno.setLocation(400, 150); 
        c.add(tmno); 
  
        JLabel gender = new JLabel("Gender"); 
        gender.setFont(new Font("Arial", Font.PLAIN, 20)); 
        gender.setSize(100, 30); 
        gender.setLocation(300, 193); 
        c.add(gender); 
  
        JRadioButton male = new JRadioButton("Male"); 
        male.setFont(new Font("Arial", Font.PLAIN, 15)); 
        male.setBackground(new Color(191,205,219));
        male.setSelected(true); 
        male.setSize(75, 20); 
        male.setLocation(400, 200); 
        c.add(male); 
  
        JRadioButton female = new JRadioButton("Female"); 
        female.setFont(new Font("Arial", Font.PLAIN, 15)); 
        female.setBackground(new Color(191,205,219));
        female.setSelected(false); 
        female.setSize(80, 20); 
        female.setLocation(475, 200); 
        c.add(female); 
  
        ButtonGroup gengp = new ButtonGroup(); 
        gengp.add(male); 
        gengp.add(female);
        
        String day = Client.getString("dob");
        String[] DOB= day.split("-");
        JLabel dob = new JLabel("DOB"); 
        dob.setFont(new Font("Arial", Font.PLAIN, 20)); 
        dob.setSize(100, 30); 
        dob.setLocation(300, 243); 
        c.add(dob); 
  
        JComboBox date = new JComboBox(dates); 
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSelectedItem(DOB[2]);
        date.setSize(50, 20); 
        date.setLocation(400, 250);
        c.add(date);
  
        JComboBox month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSelectedItem(DOB[1]);
        month.setSize(60, 20); 
        month.setLocation(450, 250); 
        c.add(month); 
  
        JComboBox year = new JComboBox(years); 
        year.setFont(new Font("Arial", Font.PLAIN, 15)); 
        year.setSelectedItem(DOB[0]);
        year.setSize(60, 20); 
        year.setLocation(520, 250); 
        c.add(year); 
  
        JLabel email = new JLabel("Email"); 
        email.setFont(new Font("Arial", Font.PLAIN, 20)); 
        email.setSize(100, 30); 
        email.setLocation(300, 302); 
        c.add(email);
  
        JButton sub = new JButton("Save"); 
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(155, 30); 
        sub.setLocation(300, 437);
        c.add(sub); 
        
        JLabel Class = new JLabel("Class");
        Class.setFont(new Font("Arial", Font.PLAIN, 20));
        Class.setBounds(300, 354, 100, 30);
        add(Class);
        
        emailText = new JTextField(Client.getString("email"));
        emailText.setFont(new Font("Arial", Font.PLAIN, 15));
        emailText.setBounds(400, 304, 190, 30);
        add(emailText);
        
        JLabel classText = new JLabel(Client.getString("MainClass"));
        classText.setBounds(400, 357, 190, 30);
        add(classText);
	}
}
