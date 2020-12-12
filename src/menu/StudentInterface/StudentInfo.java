package menu.StudentInterface;

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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import menu.Server;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


class StudentInfo extends Container {
	
    private JTextField emailText;
    private JTextField textPhoneNum;
    private JDateChooser dateChooser;
	public StudentInfo(String ClientID,  Server ServerConnection) throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Container c = this;
		setSize(1200,650);
		JLabel title = new JLabel("Student Information"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 30); 
        title.setLocation(300, 10); 
        c.add(title); 
  
        JLabel name = new JLabel("Name"); 
        name.setFont(new Font("Arial", Font.PLAIN, 20));
        name.setSize(100, 30);
        name.setLocation(300, 98); 
        c.add(name); 
        
        ResultSet Client = ServerConnection.ExecuteQuery("select * from student where id='"+ClientID+"'");
        Client.next();
  
        JTextField tname = new JTextField();
        tname.setText(Client.getString("name"));
        tname.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tname.setSize(190, 30); 
        tname.setLocation(400, 100); 
        c.add(tname);
  
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
        
        JLabel dob = new JLabel("DOB"); 
        dob.setFont(new Font("Arial", Font.PLAIN, 20)); 
        dob.setSize(100, 30); 
        dob.setLocation(300, 243); 
        c.add(dob);
  
        JLabel email = new JLabel("Email"); 
        email.setFont(new Font("Arial", Font.PLAIN, 20)); 
        email.setSize(100, 30); 
        email.setLocation(300, 302); 
        c.add(email);
        
        JLabel Class = new JLabel("Class");
        Class.setFont(new Font("Arial", Font.PLAIN, 20));
        Class.setBounds(300, 145, 100, 30);
        add(Class);
        
        emailText = new JTextField(Client.getString("email"));
        emailText.setFont(new Font("Arial", Font.PLAIN, 15));
        emailText.setBounds(400, 302, 190, 30);
        add(emailText);
        
        JLabel classText = new JLabel(Client.getString("MainClass"));
        classText.setFont(new Font("Arial", Font.PLAIN, 15));
        classText.setBounds(400, 145, 190, 30);
        add(classText);
        
        JLabel IDLabel = new JLabel("ID");
        IDLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        IDLabel.setBounds(300, 50, 100, 30);
        add(IDLabel);
        
        JLabel idText = new JLabel((String) Client.getString("id"));
        idText.setFont(new Font("Arial", Font.PLAIN, 15));
        idText.setBounds(400, 50, 190, 30);
        add(idText);
        
        JLabel lb_phone = new JLabel("Mobile");
        lb_phone.setFont(new Font("Arial", Font.PLAIN, 20));
        lb_phone.setBounds(300, 354, 100, 30);
        add(lb_phone);
        
        textPhoneNum = new JTextField(Client.getString("phoneNumber"));
        textPhoneNum.setFont(new Font("Arial", Font.PLAIN, 15));
        textPhoneNum.setBounds(400, 356, 190, 30);
        add(textPhoneNum);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(400, 243, 190, 30);
        add(dateChooser);
        dateChooser.setDateFormatString("yyyy-MM-dd");
        String dd = Client.getString("dob");
		Date date;
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
			dateChooser.setDate(date);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
        JButton sub = new JButton("Save"); 

        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(155, 30); 
        sub.setLocation(300, 417);
        c.add(sub); 
        
        JLabel lblNotify = new JLabel("");
        lblNotify.setFont(new Font("Arial", Font.BOLD, 14));
        lblNotify.setBounds(300, 394, 226, 19);
        add(lblNotify);
        
        sub.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
            		String dob = df.format(dateChooser.getDate());
            		String genderType = "";
            		if(male.isSelected())
            			genderType = "Male";
            		else if(female.isSelected())
            			genderType = "Female";
        			ServerConnection.UpdateInforStudent(idText.getText(), tname.getText(), classText.getText(), emailText.getText(), genderType, dob, textPhoneNum.getText());
        			lblNotify.setForeground(Color.GREEN);
        			lblNotify.setText("Information saved");
        		} catch (SQLException e1) {
        			lblNotify.setForeground(Color.RED);
        			lblNotify.setText("Invalid information format");
				}
        	}
        });
	}
}
