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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


class TeacherInfo extends Container {
	
	private JTextField emailText;
	private JDateChooser dateChooser;
	private JLabel idText;
	public TeacherInfo(String ClientID, Server ServerConnection) throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
        
        ResultSet Client = ServerConnection.ExecuteQuery("select * from teacher where id='"+ClientID+"'");
        Client.next();
        
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
  

        JLabel dob = new JLabel("DOB"); 
        dob.setFont(new Font("Arial", Font.PLAIN, 20)); 
        dob.setSize(100, 30); 
        dob.setLocation(10, 178); 
        c.add(dob);
  
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
        
        idText = new JLabel((String) Client.getString("id"));
        idText.setFont(new Font("Arial", Font.PLAIN, 15));
        idText.setBounds(120, 71, 190, 30);
        c.add(idText);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(120, 180, 190, 30);
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
		JLabel lblNotify = new JLabel("");
		lblNotify.setForeground(Color.GREEN);
		lblNotify.setFont(new Font("Arial", Font.BOLD, 14));
		lblNotify.setBounds(10, 261, 190, 19);
		add(lblNotify);
        sub.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {    		
        		try {
        			String dob = df.format(dateChooser.getDate());
        			ServerConnection.UpdateInforTeacher(idText.getText(), tname.getText(), tmno.getText(), emailText.getText(), dob);
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
