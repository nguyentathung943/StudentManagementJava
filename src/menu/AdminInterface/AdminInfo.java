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
import javax.swing.SwingConstants;

class AdminInfo extends Container {

    private JTextField emailText;
    private JDateChooser dateChooser;
	public AdminInfo(String ClientID, Server ServerConnection) throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Container c = this;
		setSize(1200,650);
		JLabel title = new JLabel("Administrator Information"); 
		title.setForeground(new Color(197, 84, 84));
		title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 36)); 
        title.setSize(500, 35); 
        title.setLocation(400, 10); 
        c.add(title); 
        JLabel name = new JLabel("Name"); 
        name.setFont(new Font("Arial", Font.BOLD, 24));
        name.setSize(100, 30);
        name.setLocation(370, 100); 
        c.add(name); 
  
        ResultSet Client = ServerConnection.ExecuteQuery("select * from administrator where id='"+ClientID+"'");
        Client.next();
        JTextField tname = new JTextField();
        tname.setText(Client.getString("name"));
        tname.setFont(new Font("Arial", Font.PLAIN, 18)); 
        tname.setSize(190, 30); 
        tname.setLocation(590, 100); 
        c.add(tname); 
  
        JLabel mno = new JLabel("Mobile"); 
        mno.setFont(new Font("Arial", Font.BOLD, 24)); 
        mno.setSize(100, 30); 
        mno.setLocation(370, 150); 
        c.add(mno); 
  
        JTextField tmno = new JTextField(Client.getString("phoneNumber")); 
        tmno.setFont(new Font("Arial", Font.PLAIN, 18)); 
        tmno.setSize(190, 30); 
        tmno.setLocation(590, 150); 
        c.add(tmno);
  
        ButtonGroup gengp = new ButtonGroup();
        
        JLabel dob = new JLabel("DOB"); 
        dob.setFont(new Font("Arial", Font.BOLD, 24)); 
        dob.setSize(100, 30); 
        dob.setLocation(370, 200); 
        c.add(dob);
  
        JLabel email = new JLabel("Email"); 
        email.setFont(new Font("Arial", Font.BOLD, 24)); 
        email.setSize(100, 30); 
        email.setLocation(370, 250); 
        c.add(email);
  
        
        
        emailText = new JTextField(Client.getString("email"));
        emailText.setFont(new Font("Arial", Font.PLAIN, 18));
        emailText.setBounds(590, 250, 190, 30);
        add(emailText);
        
        JLabel IDLabel = new JLabel("ID");
        IDLabel.setFont(new Font("Arial", Font.BOLD, 24));
        IDLabel.setBounds(370, 50, 100, 30);
        add(IDLabel);
        
        JLabel idText = new JLabel((String) Client.getString("id"));
        idText.setFont(new Font("Arial", Font.PLAIN, 18));
        idText.setBounds(590, 50, 190, 30);
        add(idText);
        
        JButton sub = new JButton("Save"); 
        sub.setBorder(null);
        sub.setBackground(new Color(37,78,88));
        sub.setForeground(new Color(136,189,188));
        sub.setFont(new Font("Arial", Font.BOLD, 24)); 
        sub.setSize(187, 39); 
        sub.setFocusable(false);
        sub.setLocation(423, 312);
        c.add(sub);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(590, 200, 190, 30);
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
		lblNotify.setFont(new Font("Arial", Font.BOLD, 18));
		lblNotify.setBounds(620, 312, 314, 39);
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
        			String dob = df.format(dateChooser.getDate());
        			ServerConnection.UpdateInforAdministrator(idText.getText(), emailText.getText() , tname.getText(), dob, tmno.getText());
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
