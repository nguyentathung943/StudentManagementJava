package menu.TeacherInterface;

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

import menu.Server;

import java.sql.*;


class TeacherInfo extends Container {
	private String dates[] 
	        = { "01", "02", "03", "04", "05", 
	            "06", "07", "08", "09", "10", 
	            "11", "12", "13", "14", "15", 
	            "16", "17", "18", "19", "20", 
	            "21", "22", "23", "24", "25", 
	            "26", "27", "28", "29", "30", 
	            "31" }; 
	    private String months[] 
	        = { "Jan", "feb", "Mar", "Apr", 
	            "May", "Jun", "July", "Aug", 
	            "Sup", "Oct", "Nov", "Dec" };
	    
	    private JTextField emailText;
	    private JTextField textYear;
	public TeacherInfo(ResultSet Client, Server ServerConnection) throws SQLException {
		Container c = this;
		setSize(1200,650);
		JLabel title = new JLabel("Teacher Information"); 
        title.setFont(new Font("Arial", Font.BOLD, 30)); 
        title.setSize(300, 30); 
        title.setLocation(10, 10); 
        c.add(title); 
  
        JLabel name = new JLabel("Name"); 
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 30);
        name.setLocation(10, 98); 
        c.add(name); 
  
        JTextField tname = new JTextField();
        tname.setText(Client.getString("name"));
        tname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tname.setSize(190, 30); 
        tname.setLocation(120, 100); 
        c.add(tname); 
  
        JLabel mno = new JLabel("Mobile"); 
        mno.setFont(new Font("Arial", Font.PLAIN, 20)); 
        mno.setSize(100, 30); 
        mno.setLocation(10, 138); 
        c.add(mno); 
  
        JTextField tmno = new JTextField(Client.getString("phoneNumber")); 
        tmno.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tmno.setSize(190, 30); 
        tmno.setLocation(120, 140); 
        c.add(tmno);
  
        ButtonGroup gengp = new ButtonGroup();
        
        String day = Client.getString("dob");
        String[] DOB= day.split("-");
        JLabel dob = new JLabel("DOB"); 
        dob.setFont(new Font("Arial", Font.PLAIN, 20)); 
        dob.setSize(100, 30); 
        dob.setLocation(10, 178); 
        c.add(dob); 
  
        JComboBox date = new JComboBox(dates); 
        date.setFont(new Font("Arial", Font.PLAIN, 15));
        date.setSelectedItem(DOB[2]);
        date.setSize(50, 20); 
        date.setLocation(120, 185);
        c.add(date);
  
        JComboBox month = new JComboBox(months);
        month.setFont(new Font("Arial", Font.PLAIN, 15));
        month.setSelectedItem(Integer.parseInt(DOB[1])-1);
        month.setSize(60, 20);
        month.setLocation(166, 185); 
        c.add(month);
  
        JLabel email = new JLabel("Email"); 
        email.setFont(new Font("Arial", Font.PLAIN, 20)); 
        email.setSize(100, 30); 
        email.setLocation(10, 218); 
        c.add(email);
  
        JButton sub = new JButton("Save"); 
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(155, 30); 
        sub.setLocation(10, 290);
        c.add(sub);
        
        emailText = new JTextField(Client.getString("email"));
        emailText.setFont(new Font("Arial", Font.PLAIN, 15));
        emailText.setBounds(120, 220, 190, 30);
        c.add(emailText);
        
        JLabel IDLabel = new JLabel("ID");
        IDLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        IDLabel.setBounds(10, 69, 100, 30);
        c.add(IDLabel);
        
        JLabel idText = new JLabel((String) Client.getString("id"));
        idText.setFont(new Font("Arial", Font.PLAIN, 15));
        idText.setBounds(120, 71, 190, 30);
        c.add(idText);
        
        textYear = new JTextField();
        textYear.setFont(new Font("Arial", Font.PLAIN, 15));
        textYear.setBounds(224, 185, 60, 20);
        textYear.setText(DOB[0]);
        c.add(textYear);
        textYear.setColumns(10);
	}
}
