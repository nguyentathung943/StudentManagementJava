package menu.AdminInterface;


import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
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
import java.sql.*;

import menu.ChangePasswordForm;
import menu.LoginForm;
import menu.Server;
import menu.StudentInterface.StudentMenu;
public class AdminMenu extends JFrame implements ActionListener{
	JButton info;
	JButton humanManage;
	JButton courseManage;
	JButton pass;
	JPanel contentPane;
	JPanel container;
	private final JLabel lblX = new JLabel("X");
	private final JButton lblBack = new JButton("HOME");
	private final JButton logoutBtn = new JButton("LOG OUT");
	private Image homeIcon = new ImageIcon(LoginForm.class.getResource("/icon/home.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image logoutIcon = new ImageIcon(LoginForm.class.getResource("/icon/logout.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	  
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminMenu frame = new AdminMenu(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AdminMenu(ResultSet Client, Server ServerConnection) throws SQLException {
		setUndecorated(true);
		setResizable(false);
		String ClientID = Client.getString("id");
		contentPane = new JPanel();
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		setLocationRelativeTo(null);
		
		lblX.setForeground(new Color(240, 248, 255));
		lblX.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(1150, 0, 55, 53);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to close this application?","CLOSE THE PROGRAM",JOptionPane.YES_NO_OPTION)==0) {
					AdminMenu.this.dispose();
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
		contentPane.add(panel);
		panel.setLayout(null);
		
		logoutBtn.setBounds(624, 0, 100, 100);
		panel.add(logoutBtn);
		logoutBtn.setBackground(new Color(37,78,88));
		logoutBtn.setForeground(new Color(255, 255, 255));
		logoutBtn.setFont(new Font("Arial", Font.BOLD, 13));
		logoutBtn.setIcon(new ImageIcon(logoutIcon));
		logoutBtn.setHorizontalTextPosition(JLabel.CENTER);
		logoutBtn.setVerticalTextPosition(JLabel.BOTTOM);
		logoutBtn.setFocusable(false);
		logoutBtn.setBorder(null);
		lblBack.setBounds(448, 0, 100, 100);
		panel.add(lblBack);
		lblBack.setBackground(new Color(37,78,88));
		lblBack.setForeground(new Color(255, 255, 255));
		lblBack.setFont(new Font("Arial", Font.BOLD, 13));
		lblBack.setIcon(new ImageIcon(homeIcon));
		lblBack.setFocusable(false);
		lblBack.setBorder(null);
		lblBack.setHorizontalTextPosition(JLabel.CENTER);
		lblBack.setVerticalTextPosition(JLabel.BOTTOM);
		lblBack.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CardLayout cl1 = (CardLayout)(container.getLayout());
				cl1.show(container, "MainMenu");
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				lblBack.setBackground(new Color(17,45,50));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblBack.setBackground(new Color(37,78,88));
			}
		});
		logoutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(JOptionPane.showConfirmDialog(null, "Do you want to log out?","LOG OUT",JOptionPane.YES_NO_OPTION)==0) {
					AdminMenu.this.dispose();
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
		contentPane.add(container);
		
		info = new JButton("Edit Information");
		info.setFont(new Font("Arial", Font.BOLD, 18));
		info.setIcon(new ImageIcon(StudentMenu.class.getResource("/icon/folder.png")));
		info.setHorizontalTextPosition(JLabel.CENTER);
		info.setVerticalTextPosition(JLabel.BOTTOM);
		info.setVerticalAlignment(JLabel.CENTER);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setBounds(345,32,185,185);
		info.setBackground(new Color(222,242,241));
		info.setForeground(new Color(37,78,88));
		info.setFocusable(false);
		info.setBorder(null);
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
		info.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container InfoForm;
				try {
					InfoForm = new AdminInfo(ClientID, ServerConnection);
					container.add("Info",InfoForm);
					CardLayout cl1 = (CardLayout)(container.getLayout());
					cl1.show(container, "Info");
					lblBack.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		
		courseManage = new JButton("Courses Management");
		courseManage.setFont(new Font("Arial", Font.BOLD, 18));
		courseManage.setIcon(new ImageIcon(StudentMenu.class.getResource("/icon/tick.png")));
		courseManage.setHorizontalTextPosition(JLabel.CENTER);
		courseManage.setVerticalTextPosition(JLabel.BOTTOM);
		courseManage.setVerticalAlignment(JLabel.CENTER);
		courseManage.setHorizontalAlignment(JLabel.CENTER);
		courseManage.setForeground(new Color(37,78,88));
		courseManage.setBounds(660,32,185,185);
		courseManage.setBackground(new Color(222,242,241));
		courseManage.setFocusable(false);
		courseManage.setBorder(null);
		courseManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container CourseForm;
				try {
					CourseForm = new CoursesManagement(ServerConnection);
					container.add("Course", CourseForm);
					CardLayout cl1 = (CardLayout)(container.getLayout());
					cl1.show(container, "Course");
					lblBack.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		courseManage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				courseManage.setBackground(new Color(17,45,50));
				courseManage.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				courseManage.setBackground(new Color(222,242,241));
				courseManage.setForeground(new Color(37,78,88));
			}
		});
		
		humanManage = new JButton("Human Resources Management");
		humanManage.setFont(new Font("Arial", Font.BOLD, 18));
		humanManage.setIcon(new ImageIcon(StudentMenu.class.getResource("/icon/calendar.png")));
		humanManage.setHorizontalTextPosition(JLabel.CENTER);
		humanManage.setVerticalTextPosition(JLabel.BOTTOM);
		humanManage.setVerticalAlignment(JLabel.CENTER);
		humanManage.setHorizontalAlignment(JLabel.CENTER);
		humanManage.setBounds(345,276,185,185);
		humanManage.setForeground(new Color(37,78,88));
		humanManage.setBackground(new Color(222,242,241));
		humanManage.setFocusable(false);
		humanManage.setBorder(null);
		humanManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Container HumanForm;
				try {
					HumanForm = new HumanResourceManagement(ServerConnection);
					container.add("HumanManage", HumanForm);
					CardLayout cl1 = (CardLayout)(container.getLayout());
					cl1.show(container, "HumanManage");
					lblBack.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		humanManage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				humanManage.setBackground(new Color(17,45,50));
				humanManage.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				humanManage.setBackground(new Color(222,242,241));
				humanManage.setForeground(new Color(37,78,88));
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
		
		content.setLayout(null);
		content.add(info);
		content.add(courseManage);
		content.add(humanManage);
		content.add(pass);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
