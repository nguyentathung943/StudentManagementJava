package menu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
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
  
        JTextField toldPass = new JTextField(); 
        toldPass.setFont(new Font("Arial", Font.PLAIN, 15)); 
        toldPass.setSize(190, 20); 
        toldPass.setLocation(500, 100); 
        c.add(toldPass); 
  
        JLabel newPass = new JLabel("New password :"); 
        newPass.setFont(new Font("Arial", Font.PLAIN, 20)); 
        newPass.setSize(200, 20); 
        newPass.setLocation(300, 150); 
        c.add(newPass); 
  
        JTextField tnewPass = new JTextField(); 
        tnewPass.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tnewPass.setSize(190, 20); 
        tnewPass.setLocation(500, 150); 
        c.add(tnewPass); 
        
        JLabel confirmPass = new JLabel("Confirm password :"); 
        confirmPass.setFont(new Font("Arial", Font.PLAIN, 20)); 
        confirmPass.setSize(200, 20); 
        confirmPass.setLocation(300, 200); 
        c.add(confirmPass); 
  
        JTextField tconfirm = new JTextField(); 
        tconfirm.setFont(new Font("Arial", Font.PLAIN, 15)); 
        tconfirm.setSize(190, 20); 
        tconfirm.setLocation(500, 200); 
        c.add(tconfirm); 
 
  
        JButton sub = new JButton("Save"); 
        sub.setFont(new Font("Arial", Font.PLAIN, 15)); 
        sub.setSize(100, 20); 
        sub.setLocation(400, 250);
        c.add(sub); 
		
	}

}


