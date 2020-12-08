package menu.AdminInterface;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import menu.Server;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.toedter.calendar.JDateChooser;

class AdminInfo extends Container {

    private JTextField emailText;
    private JDateChooser dateChooser;
	public AdminInfo(ResultSet Client, Server ServerConnection) throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Container c = this;
		setSize(1200,557);
		JLabel title = new JLabel("Administrator Information"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(376, 30); 
        title.setLocation(300, 10); 
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
        mno.setLocation(300, 148); 
        c.add(mno); 
  
        JTextField tmno = new JTextField(Client.getString("phoneNumber")); 
        tmno.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tmno.setSize(190, 30); 
        tmno.setLocation(400, 150); 
        c.add(tmno);
  
        ButtonGroup gengp = new ButtonGroup();
        
        JLabel dob = new JLabel("DOB"); 
        dob.setFont(new Font("Arial", Font.PLAIN, 20)); 
        dob.setSize(100, 30); 
        dob.setLocation(300, 205); 
        c.add(dob);
  
        JLabel email = new JLabel("Email"); 
        email.setFont(new Font("Arial", Font.PLAIN, 20)); 
        email.setSize(100, 30); 
        email.setLocation(300, 254); 
        c.add(email);
  
        
        
        emailText = new JTextField(Client.getString("email"));
        emailText.setFont(new Font("Arial", Font.PLAIN, 15));
        emailText.setBounds(400, 256, 190, 30);
        add(emailText);
        
        JLabel IDLabel = new JLabel("ID");
        IDLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        IDLabel.setBounds(300, 50, 100, 30);
        add(IDLabel);
        
        JLabel idText = new JLabel((String) Client.getString("id"));
        idText.setFont(new Font("Arial", Font.PLAIN, 15));
        idText.setBounds(400, 50, 190, 30);
        add(idText);
        
        JButton sub = new JButton("Save"); 
        sub.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		sub.setBackground(new Color(0,129,129));
        	}
        });
        sub.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String dob = df.format(dateChooser.getDate());
        		try {
        			ServerConnection.UpdateInforAdministrator(idText.getText(), emailText.getText() , tname.getText(), dob, tmno.getText());
        		} catch (SQLException e1) {
					e1.printStackTrace();
				}
        	}
        });
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(155, 30); 
        sub.setLocation(300, 316);
        c.add(sub);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(400, 205, 190, 30);
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
	}
}
