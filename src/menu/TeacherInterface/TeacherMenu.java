package menu.TeacherInterface;

import java.awt.BorderLayout;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import menu.ChangePasswordForm;
import menu.LoginForm;
import menu.Server;
import menu.StudentInterface.StudentMenu;

import java.sql.*;

public class TeacherMenu extends JFrame implements ActionListener{
	JButton info;
	JButton ShowCourses;
	JButton pass;
	JButton schedule;
	JPanel contentPane;
	JPanel container;
	Container mainUI;
	private final JLabel lblX = new JLabel("X");
	private final JButton lblBack = new JButton("HOME");
	private final JButton logoutBtn = new JButton("LOG OUT");
	private Image homeIcon = new ImageIcon(LoginForm.class.getResource("/icon/home.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image logoutIcon = new ImageIcon(LoginForm.class.getResource("/icon/logout.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeacherMenu frame = new TeacherMenu(null,null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public TeacherMenu(ResultSet Client, Server ServerConnection) throws SQLException {
		String ClientID = Client.getString("id");
		setUndecorated(true);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setLayout(new CardLayout());
		setContentPane(contentPane);
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		setLocationRelativeTo(null);
		mainUI = new Container();
		mainUI.setLayout(null);
		contentPane.add("MainUI", mainUI);
		
		lblBack.setBackground(new Color(37,78,88));
		lblBack.setForeground(new Color(255, 255, 255));
		lblBack.setFont(new Font("Arial", Font.BOLD, 13));
		lblBack.setIcon(new ImageIcon(homeIcon));
		lblBack.setFocusable(false);
		lblBack.setBorder(null);
		lblBack.setBounds(448, 0, 100, 100);
		lblBack.setHorizontalTextPosition(JLabel.CENTER);
		lblBack.setVerticalTextPosition(JLabel.BOTTOM);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBack.setBackground(new Color(17,45,50));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblBack.setBackground(new Color(37,78,88));
			}
		});
		
		logoutBtn.setBackground(new Color(37,78,88));
		logoutBtn.setForeground(new Color(255, 255, 255));
		logoutBtn.setFont(new Font("Arial", Font.BOLD, 13));
		logoutBtn.setIcon(new ImageIcon(logoutIcon));
		logoutBtn.setHorizontalTextPosition(JLabel.CENTER);
		logoutBtn.setVerticalTextPosition(JLabel.BOTTOM);
		logoutBtn.setFocusable(false);
		logoutBtn.setBorder(null);
		logoutBtn.setBounds(624, 0, 100, 100);
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to log out?","LOG OUT",JOptionPane.YES_NO_OPTION)==0) {
					TeacherMenu.this.dispose();
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
				logoutBtn.setBackground(new Color(17,45,50));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				logoutBtn.setBackground(new Color(37,78,88));
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
					TeacherMenu.this.dispose();
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
		mainUI.add(lblX);
		mainUI.add(lblBack);
		mainUI.add(logoutBtn);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(37,78,88));
		panel.setBounds(0,0,1200,150);
		
		container = new JPanel(new CardLayout());
		container.setBackground(new Color(222,242,241));
		container.setBounds(0,150,1200,800-150);
		
		JPanel content = new JPanel();
		content.setBackground(new Color(222,242,241));
		content.setBounds(400,200,400,400);
		
		container.add("MainMenu", content);
		mainUI.add(panel);
		mainUI.add(container);
		
		
		info = new JButton("Edit Information");
		info.setFont(new Font("Arial", Font.BOLD, 18));
		info.setIcon(new ImageIcon(TeacherMenu.class.getResource("/icon/folder.png")));
		info.setHorizontalTextPosition(JLabel.CENTER);
		info.setVerticalTextPosition(JLabel.BOTTOM);
		info.setVerticalAlignment(JLabel.CENTER);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setBounds(345,32,185,185);
		info.setBackground(new Color(222,242,241));
		info.setFocusable(false);
		info.setBorder(null);
		info.setForeground(new Color(37,78,88));
		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {							
				Container InfoForm;
				try {
					InfoForm = new TeacherInfo(ClientID,ServerConnection);
					container.add("Info",InfoForm);
					CardLayout cl1 = (CardLayout)(container.getLayout());
					cl1.show(container, "Info");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		info.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				info.setBackground(new Color(17,45,50));
				info.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				info.setBackground(new Color(222,242,241));
				info.setForeground(new Color(37,78,88));
			}
		});
		
		ShowCourses = new JButton("Courses Management");
		ShowCourses.setFont(new Font("Arial", Font.BOLD, 18));
		ShowCourses.setIcon(new ImageIcon(TeacherMenu.class.getResource("/icon/tick.png")));
		ShowCourses.setHorizontalTextPosition(JLabel.CENTER);
		ShowCourses.setVerticalTextPosition(JLabel.BOTTOM);
		ShowCourses.setVerticalAlignment(JLabel.CENTER);
		ShowCourses.setHorizontalAlignment(JLabel.CENTER);
		ShowCourses.setBounds(660,32,185,185);
		ShowCourses.setBackground(new Color(222,242,241));
		ShowCourses.setFocusable(false);
		ShowCourses.setBorder(null);
		ShowCourses.setForeground(new Color(37,78,88));
		ShowCourses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				Container CoursesForm;
				try {
					CoursesForm = new ShowCourses(ClientID,ServerConnection);
					container.add("Courses",CoursesForm);
					CardLayout cl1 = (CardLayout)(container.getLayout());
					cl1.show(container, "Courses");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		ShowCourses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ShowCourses.setBackground(new Color(17,45,50));
				ShowCourses.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ShowCourses.setBackground(new Color(222,242,241));
				ShowCourses.setForeground(new Color(37,78,88));
			}
		});
		
		schedule = new JButton("Show Schedule");
		schedule.setFont(new Font("Arial", Font.BOLD, 18));
		schedule.setIcon(new ImageIcon(StudentMenu.class.getResource("/icon/calendar.png")));
		schedule.setHorizontalTextPosition(JLabel.CENTER);
		schedule.setVerticalTextPosition(JLabel.BOTTOM);
		schedule.setVerticalAlignment(JLabel.CENTER);
		schedule.setHorizontalAlignment(JLabel.CENTER);
		schedule.setBounds(345,276,185,185);
		schedule.setForeground(new Color(37,78,88));
		schedule.setBackground(new Color(222,242,241));
		schedule.setFocusable(false);
		schedule.setBorder(null);
		schedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {						
				Container Schedule;
				try {
					Schedule = new ShowTeachingSchedule(ClientID,contentPane,ServerConnection);
					container.add("Schedule",Schedule);
					CardLayout cl1 = (CardLayout)(container.getLayout());
					cl1.show(container, "Schedule");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		schedule.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				schedule.setBackground(new Color(17,45,50));
				schedule.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				schedule.setBackground(new Color(222,242,241));
				schedule.setForeground(new Color(37,78,88));
			}
		});
		
		pass = new JButton("Change password");
		pass.setFont(new Font("Arial", Font.BOLD, 18));
		pass.setIcon(new ImageIcon(StudentMenu.class.getResource("/icon/cogwheel.png")));
		pass.setHorizontalTextPosition(JLabel.CENTER);
		pass.setVerticalTextPosition(JLabel.BOTTOM);
		pass.setVerticalAlignment(JLabel.CENTER);
		pass.setHorizontalAlignment(JLabel.CENTER);
		pass.setBounds(660,276,185,185);
		pass.setBackground(new Color(222,242,241));
		pass.setForeground(new Color(37,78,88));
		pass.setFocusable(false);
		pass.setBorder(null);
		pass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				Container changePassword = new ChangePasswordForm(ClientID, ServerConnection);
				container.add("Pass",changePassword);
				CardLayout cl1 = (CardLayout)(container.getLayout());
				cl1.show(container, "Pass");
			}
		});
		pass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				pass.setBackground(new Color(17,45,50));
				pass.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				pass.setBackground(new Color(222,242,241));
				pass.setForeground(new Color(37,78,88));
			}
		});
		
		lblBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl1 = (CardLayout)(container.getLayout());
				cl1.show(container, "MainMenu");
			}
		});
		content.setLayout(null);
		content.add(info);
		content.add(ShowCourses);
		content.add(schedule);
		content.add(pass);    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
