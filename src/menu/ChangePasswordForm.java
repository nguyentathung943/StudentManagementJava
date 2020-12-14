package menu;

import java.sql.*;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class ChangePasswordForm extends Container{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Image pass = new ImageIcon(LoginForm.class.getResource("/icon/eyes.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private JPasswordField textOldPass;
	private JPasswordField textNewPass1;
	private JPasswordField textNewPass2;

	public ChangePasswordForm(String ClientID,Server ServerConnection) {
		Container c = this;
		setSize(1200,650);
		
		JLabel title = new JLabel("Change Password"); 
        title.setFont(new Font("Arial", Font.BOLD, 36));
        title.setForeground(new Color(197, 84, 84));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(400, 10, 400, 42);
        c.add(title); 
  
      
  
        JLabel oldPass = new JLabel("Old password :"); 
        oldPass.setFont(new Font("Arial", Font.BOLD, 24)); 
        oldPass.setSize(200, 53); 
        oldPass.setLocation(135, 130); 
        c.add(oldPass);
  
        JLabel newPass = new JLabel("New password :"); 
        newPass.setFont(new Font("Arial", Font.BOLD, 24)); 
        newPass.setSize(200, 53); 
        newPass.setLocation(135, 200); 
        c.add(newPass);
        newPass.setVisible(false);
        
        JLabel confirmPass = new JLabel("Confirm password :"); 
        confirmPass.setFont(new Font("Arial", Font.BOLD, 24)); 
        confirmPass.setSize(226, 53); 
        confirmPass.setLocation(135, 270); 
        c.add(confirmPass);
        confirmPass.setVisible(false);
  
        JButton btnSave = new JButton("Save");
        btnSave.setLocation(355, 359);
        btnSave.setVisible(false);
        btnSave.setBorder(null);
        btnSave.setSize(187, 39); 
        btnSave.setBackground(new Color(37,78,88));
        btnSave.setForeground(new Color(136,189,188));
        btnSave.setFont(new Font("Arial", Font.BOLD, 24));
        btnSave.setFocusable(false);
        btnSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnSave.setBackground(new Color(17,45,50));
				btnSave.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnSave.setBackground(new Color(37,78,88));
				btnSave.setForeground(new Color(136,189,188));
			}
		});
        c.add(btnSave);
        
        JButton btnCheck = new JButton("CHECK");
        btnCheck.setFont(new Font("Arial", Font.BOLD, 20));
        btnCheck.setBorder(null);
        btnCheck.setFocusable(false);
        btnCheck.setBounds(802, 130, 130, 53);
        btnCheck.setBackground(new Color(37,78,88));
        btnCheck.setForeground(new Color(136,189,188));
        btnCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCheck.setBackground(new Color(17,45,50));
				btnCheck.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCheck.setBackground(new Color(37,78,88));
				btnCheck.setForeground(new Color(136,189,188));
			}
		});
        add(btnCheck);
        
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);
        panel.setBounds(375, 130, 406, 53);
        add(panel);
        
        textOldPass = new JPasswordField();
        textOldPass.setFont(new Font("Arial", Font.PLAIN, 20));
        textOldPass.setBackground(new Color(255,255,255));
        textOldPass.setColumns(10);
        textOldPass.setBorder(null);
        textOldPass.setBounds(10, 0, 316, 53);
        panel.add(textOldPass);
        
        JLabel pwdIcon = new JLabel("");
        pwdIcon.setIcon(new ImageIcon(ChangePasswordForm.class.getResource("/icon/eyes.png")));
        pwdIcon.setHorizontalAlignment(SwingConstants.CENTER);
        pwdIcon.setBackground(Color.WHITE);
        pwdIcon.setBounds(359, 0, 47, 53);
        panel.add(pwdIcon);
        
        JPanel panel_1 = new JPanel();
        panel_1.setLayout(null);
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(375, 200, 406, 53);
        panel_1.setVisible(false);
        add(panel_1);
        
        textNewPass1 = new JPasswordField();
        textNewPass1.setFont(new Font("Arial", Font.PLAIN, 20));
        textNewPass1.setColumns(10);
        textNewPass1.setBorder(null);
        textNewPass1.setBounds(10, 0, 316, 53);
        panel_1.add(textNewPass1);
        
        JLabel pwdIcon1 = new JLabel("");
        pwdIcon1.setIcon(new ImageIcon(ChangePasswordForm.class.getResource("/icon/eyes.png")));
        pwdIcon1.setHorizontalAlignment(SwingConstants.CENTER);
        pwdIcon1.setBackground(Color.WHITE);
        pwdIcon1.setBounds(359, 0, 47, 53);
        panel_1.add(pwdIcon1);
        
        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBackground(Color.WHITE);
        panel_2.setBounds(375, 270, 406, 53);
        panel_2.setVisible(false);
        add(panel_2);
        
        textNewPass2 = new JPasswordField();
        textNewPass2.setFont(new Font("Arial", Font.PLAIN, 20));
        textNewPass2.setColumns(10);
        textNewPass2.setBorder(null);
        textNewPass2.setBounds(10, 0, 316, 53);
        panel_2.add(textNewPass2);
        
        JLabel pwdIcon2 = new JLabel("");
        pwdIcon2.setIcon(new ImageIcon(ChangePasswordForm.class.getResource("/icon/eyes.png")));
        pwdIcon2.setHorizontalAlignment(SwingConstants.CENTER);
        pwdIcon2.setBackground(Color.WHITE);
        pwdIcon2.setBounds(359, 0, 47, 53);
        panel_2.add(pwdIcon2);
        
        JLabel lblNotifiCheck = new JLabel("");
        lblNotifiCheck.setBounds(345, 195, 406, 29);
        add(lblNotifiCheck);
        lblNotifiCheck.setForeground(Color.RED);
        lblNotifiCheck.setFont(new Font("Arial", Font.BOLD, 20));
        
        JLabel lblNotifiSave = new JLabel("");
        lblNotifiSave.setForeground(Color.RED);
        lblNotifiSave.setFont(new Font("Arial", Font.BOLD, 20));
        lblNotifiSave.setBounds(552, 369, 517, 29);
        add(lblNotifiSave);
        btnCheck.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String oldPass = textOldPass.getText();
        		if(oldPass.equals("")){
        			lblNotifiCheck.setText("This field must me filled");
        		}
        		else {
        			lblNotifiCheck.setText("");
        			try {
						ResultSet client = ServerConnection.ExecuteQuery("select * from credential where username='"+ClientID+"' and password='"+oldPass+"'");
						if(!client.next()) {
							lblNotifiCheck.setText("Wrong password, please try again!");
						}
						else {
							lblNotifiCheck.setText("");
							textOldPass.setEditable(false);
							newPass.setVisible(true);
							confirmPass.setVisible(true);
							panel_1.setVisible(true);
							panel_2.setVisible(true);
							btnSave.setVisible(true);
							btnCheck.setVisible(false);
							lblNotifiSave.setForeground(Color.red);
						}
						
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        	}
        });
        pwdIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((!textOldPass.getText().equals(""))) {
					textOldPass.setText(textOldPass.getText());
					textOldPass.setEchoChar((char) 0);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if ((!textOldPass.getText().equals(""))) {
					textOldPass.setEchoChar('●');
					textOldPass.setFont(new Font("Arial", Font.PLAIN, 20));
					textOldPass.setText(textOldPass.getText());
				}
			}
		});
        pwdIcon1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((!textNewPass1.getText().equals(""))) {
					textNewPass1.setText(textNewPass1.getText());
					textNewPass1.setEchoChar((char) 0);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if ((!textNewPass1.getText().equals(""))) {
					textNewPass1.setEchoChar('●');
					textNewPass1.setFont(new Font("Arial", Font.PLAIN, 20));
					textNewPass1.setText(textNewPass1.getText());
				}
			}
		});
        pwdIcon2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((!textNewPass2.getText().equals(""))) {
					textNewPass2.setText(textNewPass2.getText());
					textNewPass2.setEchoChar((char) 0);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if ((!textNewPass2.getText().equals(""))) {
					textNewPass2.setEchoChar('●');
					textNewPass2.setFont(new Font("Arial", Font.PLAIN, 20));
					textNewPass2.setText(textNewPass2.getText());
				}
			}
		});
        btnSave.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		String pass = textNewPass1.getText();
        		String confirm = textNewPass2.getText();
        		if(pass.equals("") || confirm.equals("")){
        			lblNotifiSave.setText("This field must me filled");
        		}
        		else if(pass.length()<5 || confirm.length()<5) {
        			lblNotifiSave.setText("New password must be more than 5 characters");
					textNewPass1.setText("");
					textNewPass2.setText("");
        		}
        		else {
        			lblNotifiSave.setText("");
        			try {
						ResultSet client = ServerConnection.ExecuteQuery("select * from credential where username='"+ClientID+"' and password='"+oldPass+"'");
						if(!pass.equals(confirm)) {
							lblNotifiSave.setText("Invalid confirm password");
							textNewPass1.setText("");
							textNewPass2.setText("");
						}
						else {
							ServerConnection.ExcecuteQueryUpdate("update credential set password='"+pass+"' where username='"+ClientID+"'");
							textNewPass1.setText("");
							textNewPass2.setText("");
							lblNotifiSave.setForeground(Color.green);
							lblNotifiSave.setText("Password changed successfully!");
						}											
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        	}
        });
	}
}


