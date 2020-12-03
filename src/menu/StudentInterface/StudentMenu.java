package menu.StudentInterface;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import menu.LoginForm;
import menu.Server;

import java.sql.*;

public class StudentMenu extends JFrame implements ActionListener{
	JButton info;
	JButton pass;
	JPanel contentPane;
	JPanel container;
	private final JLabel lblX = new JLabel("X");
	private final JButton lblBack = new JButton("HOME");
	private final JButton logoutBtn = new JButton("LOG OUT");
	private Image homeIcon = new ImageIcon(LoginForm.class.getResource("/icon/home.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	private Image logoutIcon = new ImageIcon(LoginForm.class.getResource("/icon/logout.png")).getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentMenu frame = new StudentMenu(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public StudentMenu(ResultSet Client, Server ServerConnection) throws SQLException {
		setUndecorated(true);
		setResizable(false);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		setLocationRelativeTo(null);
		
		lblBack.setHorizontalAlignment(SwingConstants.LEFT);	
		lblBack.setBackground(new Color(51, 102, 153));
		lblBack.setForeground(new Color(255, 255, 255));
		lblBack.setFont(new Font("Arial", Font.BOLD, 13));
		lblBack.setIcon(new ImageIcon(homeIcon));
		lblBack.setFocusable(false);
		lblBack.setBounds(0, 0, 100, 45);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBack.setBackground(new Color(0, 51, 102));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblBack.setBackground(new Color(51, 102, 153));
			}
		});
		
		logoutBtn.setHorizontalAlignment(SwingConstants.LEFT);	
		logoutBtn.setBackground(new Color(51, 102, 153));
		logoutBtn.setForeground(new Color(255, 255, 255));
		logoutBtn.setFont(new Font("Arial", Font.BOLD, 13));
		logoutBtn.setIcon(new ImageIcon(logoutIcon));
		logoutBtn.setFocusable(false);
		logoutBtn.setBounds(100, 0, 120, 45);
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to log out?","LOG OUT",JOptionPane.YES_NO_OPTION)==0) {
					StudentMenu.this.dispose();
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								LoginForm frame = new LoginForm();
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				logoutBtn.setBackground(new Color(0, 51, 102));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				logoutBtn.setBackground(new Color(51, 102, 153));
			}
		});
		
		lblX.setForeground(new Color(240, 248, 255));
		lblX.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(1150, 0, 55, 53);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to close this application?","CLOSE THE PROGRAM",JOptionPane.YES_NO_OPTION)==0) {
					StudentMenu.this.dispose();
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblX.setForeground(Color.red);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				lblX.setForeground(Color.white);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblX.setForeground(Color.white);
			}
		});
		contentPane.add(lblX);
		contentPane.add(lblBack);
		contentPane.add(logoutBtn);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0,0,1200,150);
		panel.setLayout(new BorderLayout());
		
		container = new JPanel(new CardLayout());
		container.setBackground(SystemColor.inactiveCaption);
		container.setBounds(0,150,1200,800-150);
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(2,2,10,10));
		content.setBackground(SystemColor.inactiveCaption);
		content.setBounds(400,200,400,400);
		
		container.add("MainMenu", content);
		contentPane.add(panel);
		contentPane.add(container);
		
		
		
		
		info = new JButton("Edit Information");
		info.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		info.setIcon(new ImageIcon(StudentMenu.class.getResource("/icon/folder.png")));
		info.setHorizontalTextPosition(JLabel.CENTER);
		info.setVerticalTextPosition(JLabel.BOTTOM);
		info.setVerticalAlignment(JLabel.CENTER);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setBounds(450,100,150,100);
		info.setBackground(new Color(191,205,219));
		info.setFocusable(false);
		info.setBorder(null);
		info.addActionListener(this);
		
		JButton register = new JButton("Register courses");
		register.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		register.setIcon(new ImageIcon(StudentMenu.class.getResource("/icon/tick.png")));
		register.setHorizontalTextPosition(JLabel.CENTER);
		register.setVerticalTextPosition(JLabel.BOTTOM);
		register.setVerticalAlignment(JLabel.CENTER);
		register.setHorizontalAlignment(JLabel.CENTER);
		register.setBounds(600,100,150,100);
		register.setBackground(new Color(191,205,219));
		register.setFocusable(false);
		register.setBorder(null);
		register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl1 = (CardLayout)(container.getLayout());
				cl1.show(container, "Register");
				lblBack.setVisible(true);
			}
		});
		
		JButton schedule = new JButton("Show schedule");
		schedule.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		schedule.setIcon(new ImageIcon(StudentMenu.class.getResource("/icon/calendar.png")));
		schedule.setHorizontalTextPosition(JLabel.CENTER);
		schedule.setVerticalTextPosition(JLabel.BOTTOM);
		schedule.setVerticalAlignment(JLabel.CENTER);
		schedule.setHorizontalAlignment(JLabel.CENTER);
		schedule.setBounds(450,200,150,100);
		schedule.setBackground(new Color(191,205,219));
		schedule.setFocusable(false);
		schedule.setBorder(null);
		schedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl1 = (CardLayout)(container.getLayout());
				cl1.show(container, "Schedule");
			}
		});
		pass = new JButton("Change password");
		pass.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		pass.setIcon(new ImageIcon(StudentMenu.class.getResource("/icon/cogwheel.png")));
		pass.setHorizontalTextPosition(JLabel.CENTER);
		pass.setVerticalTextPosition(JLabel.BOTTOM);
		pass.setVerticalAlignment(JLabel.CENTER);
		pass.setHorizontalAlignment(JLabel.CENTER);
		pass.setBounds(650,200,150,100);
		pass.setBackground(new Color(191,205,219));
		pass.setFocusable(false);
		pass.setBorder(null);
		pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl1 = (CardLayout)(container.getLayout());
				cl1.show(container, "Pass");
			}
		});
		
		lblBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl1 = (CardLayout)(container.getLayout());
				cl1.show(container, "MainMenu");
				
			}
			
		});
		content.add(info);
		content.add(register);
		content.add(schedule);
		content.add(pass);
		
		String ClientID = Client.getString("id");
		Container InfoForm = new StudentInfo(Client);
		Container RegisterUI = new StudentRegisterCourse();
		Container Schedule = new StudentShowSchedule(ClientID, ServerConnection);
		//Container changePassword = new ChangePasswordForm();
		//container.add("Pass",changePassword);
        container.add("Info",InfoForm);
        container.add("Register",RegisterUI);
        container.add("Schedule",Schedule);
        
        
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == info) {
			CardLayout cl = (CardLayout)(container.getLayout());
			cl.show(container, "Info");
			lblBack.setVisible(true);
		}
	}
}
