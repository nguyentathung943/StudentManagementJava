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
import javax.swing.SwingConstants;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

import menu.Server;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;


class StudentInfo extends Container {
	
    private JTextField emailText;
    private JTextField textPhoneNum;
    private JDateChooser dateChooser;
	public StudentInfo(String ClientID,  Server ServerConnection) throws SQLException {
		JLabel lblInfo = new JLabel("Student Infomation");
		lblInfo.setForeground(new Color(197, 84, 84));
		lblInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lblInfo.setFont(new Font("Arial", Font.BOLD, 36));
		lblInfo.setBounds(400, 10, 400, 35);
        add(lblInfo);
        
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Container c = this;
		setSize(1200,650);
  
        JLabel name = new JLabel("Name"); 
        name.setFont(new Font("Arial", Font.BOLD, 24));
        name.setSize(100, 30);
        name.setLocation(370, 100); 
        c.add(name); 
        
        ResultSet Client = ServerConnection.ExecuteQuery("select * from student where id='"+ClientID+"'");
        Client.next();
  
        JTextField tname = new JTextField();
        tname.setText(Client.getString("name"));
        tname.setFont(new Font("Arial", Font.PLAIN, 18)); 
        tname.setSize(190, 30); 
        tname.setLocation(590, 100); 
        c.add(tname);
  
        JLabel gender = new JLabel("Gender"); 
        gender.setFont(new Font("Arial", Font.BOLD, 24)); 
        gender.setSize(100, 30); 
        gender.setLocation(370, 200); 
        c.add(gender); 
  
        JRadioButton male = new JRadioButton("Male"); 
        male.setFont(new Font("Arial", Font.PLAIN, 18)); 
        male.setBackground(new Color(222,242,241));
        male.setSelected(true);
        male.setSize(75, 20);
        male.setLocation(590, 200); 
        c.add(male); 
  
        JRadioButton female = new JRadioButton("Female"); 
        female.setFont(new Font("Arial", Font.PLAIN, 18)); 
        female.setBackground(new Color(222,242,241));
        female.setSelected(false); 
        female.setSize(100, 20); 
        female.setLocation(670, 200); 
        c.add(female);
  
        ButtonGroup gengp = new ButtonGroup(); 
        gengp.add(male); 
        gengp.add(female);
        
        JLabel dob = new JLabel("DOB"); 
        dob.setFont(new Font("Arial", Font.BOLD, 24)); 
        dob.setSize(100, 30); 
        dob.setLocation(370, 250); 
        c.add(dob);
  
        JLabel email = new JLabel("Email"); 
        email.setFont(new Font("Arial", Font.BOLD, 24)); 
        email.setSize(100, 30); 
        email.setLocation(370, 300); 
        c.add(email);
        
        JLabel Class = new JLabel("Class");
        Class.setFont(new Font("Arial", Font.BOLD, 24));
        Class.setBounds(370, 150, 100, 30);
        add(Class);
        
        emailText = new JTextField(Client.getString("email"));
        emailText.setFont(new Font("Arial", Font.PLAIN, 18));
        emailText.setBounds(590, 300, 190, 30);
        add(emailText);
        
        JLabel classText = new JLabel(Client.getString("MainClass"));
        classText.setFont(new Font("Arial", Font.PLAIN, 18));
        classText.setBounds(590, 150, 190, 30);
        add(classText);
        
        JLabel IDLabel = new JLabel("ID");
        IDLabel.setFont(new Font("Arial", Font.BOLD, 24));
        IDLabel.setBounds(370, 50, 100, 30);
        add(IDLabel);
        
        JLabel idText = new JLabel((String) Client.getString("id"));
        idText.setFont(new Font("Arial", Font.PLAIN, 18));
        idText.setBounds(590, 50, 190, 30);
        add(idText);
        
        JLabel lb_phone = new JLabel("Mobile");
        lb_phone.setFont(new Font("Arial", Font.BOLD, 24));
        lb_phone.setBounds(370, 350, 100, 30);
        add(lb_phone);
        
        textPhoneNum = new JTextField(Client.getString("phoneNumber"));
        textPhoneNum.setFont(new Font("Arial", Font.PLAIN, 18));
        textPhoneNum.setBounds(590, 350, 190, 30);
        add(textPhoneNum);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(590, 250, 190, 30);
        Font font = new Font("Arial", Font.PLAIN,18);
        dateChooser.setFont(font);
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
        sub.setBorder(null);
        sub.setBackground(new Color(37,78,88));
        sub.setForeground(new Color(136,189,188));
        sub.setFont(new Font("Arial", Font.BOLD, 24)); 
        sub.setSize(190, 39); 
        sub.setLocation(590, 412);
        sub.setFocusable(false);
        c.add(sub); 
        
        JLabel lblNotify = new JLabel("");
        lblNotify.setFont(new Font("Arial", Font.BOLD, 18));
        lblNotify.setBounds(590, 379, 274, 23);
        add(lblNotify);
        
        sub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				sub.setBackground(new Color(17,45,50));
				sub.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				sub.setBackground(new Color(37,78,88));
		        sub.setForeground(new Color(136,189,188));
			}
		});
        sub.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		try {
        			String dob=null;
        			try {
        				dob = df.format(dateChooser.getDate());
        			}catch(Exception exc) {
        				
        			}
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
