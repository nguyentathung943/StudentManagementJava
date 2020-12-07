package menu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class ChangePasswordForm extends Container{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChangePasswordForm() {
		Container c = this;
		setSize(800,600);
		JLabel title = new JLabel("Change password"); 
        title.setFont(new Font("Arial", Font.PLAIN, 30)); 
        title.setSize(300, 30); 
        title.setLocation(500, 30); 
        c.add(title); 
  
      
  
        JLabel oldPass = new JLabel("Old password :"); 
        oldPass.setFont(new Font("Arial", Font.PLAIN, 20)); 
        oldPass.setSize(200, 20); 
        oldPass.setLocation(300, 100); 
        c.add(oldPass); 
  
        JPasswordField toldPass = new JPasswordField(); 
        toldPass.setFont(new Font("Arial", Font.PLAIN, 15)); 
        toldPass.setSize(190, 30);
        toldPass.setLocation(500, 100);
        toldPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(toldPass.getText().equals("Password")) {
					toldPass.setEchoChar('●');
					toldPass.setText("");
				}
				else {
					toldPass.selectAll();
				}
			}
		});
        c.add(toldPass); 
  
        JLabel newPass = new JLabel("New password :"); 
        newPass.setFont(new Font("Arial", Font.PLAIN, 20)); 
        newPass.setSize(200, 20); 
        newPass.setLocation(300, 150); 
        c.add(newPass); 
  
        JPasswordField tnewPass = new JPasswordField();
        tnewPass.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tnewPass.setSize(190, 30); 
        tnewPass.setLocation(500, 150);
        tnewPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tnewPass.getText().equals("Password")) {
					tnewPass.setEchoChar('●');
					tnewPass.setText("");
				}
				else {
					tnewPass.selectAll();
				}
			}
			
		});
        c.add(tnewPass); 
        
        JLabel confirmPass = new JLabel("Confirm password :"); 
        confirmPass.setFont(new Font("Arial", Font.PLAIN, 20)); 
        confirmPass.setSize(200, 20); 
        confirmPass.setLocation(300, 200); 
        c.add(confirmPass); 
  
        JPasswordField tconfirm = new JPasswordField(); 
        tconfirm.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tconfirm.setSize(190, 30); 
        tconfirm.setLocation(500, 200);
        tconfirm.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if(tconfirm.getText().equals("Password")) {
					tconfirm.setEchoChar('●');
					tconfirm.setText("");
				}
				else {
					tconfirm.selectAll();
				}
			}
			
		});
        c.add(tconfirm); 
 
  
        JButton sub = new JButton("Save"); 
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(100, 20); 
        sub.setLocation(500, 250);
        c.add(sub); 
		
	}

}


