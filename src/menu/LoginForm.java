package menu;

import menu.AdminInterface.AdminMenu;
import menu.StudentInterface.StudentMenu;
import menu.TeacherInterface.TeacherMenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.border.LineBorder;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.Icon;

public class LoginForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtFdfg;
	private JTextField username;
	boolean passwordVisible;
	private JPasswordField password;
	private final JLabel lblX = new JLabel("X");
	private Image background = new ImageIcon(LoginForm.class.getResource("/icon/login.png")).getImage().getScaledInstance(120, 120,Image.SCALE_SMOOTH);
	private Image usr = new ImageIcon(LoginForm.class.getResource("/icon/username.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image pass = new ImageIcon(LoginForm.class.getResource("/icon/eyes.png")).getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image Key = new ImageIcon(LoginForm.class.getResource("/icon/Key.png")).getImage().getScaledInstance(30, 30,Image.SCALE_SMOOTH);

	/**
	 * Launch the application.
	 * 
	 * @throws SQLException
	 */

	public static void main(String[] args) throws SQLException {

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

	/**
	 * Create the frame.
	 * 
	 * @throws SQLException
	 */
	public LoginForm() throws SQLException {
		Server ServerConnection = new Server();
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(100, 100, 800, 600);
		setSize(800, 600);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(195, 320, 406, 53);
		contentPane.add(panel);
		panel.setLayout(null);
		username = new JTextField();
		username.setText("Username");
		username.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (username.getText().equals("Username")) {
					username.setText("");
				} else {
					username.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (username.getText().equals("")) {
					username.setText("Username");
				}
			}
		});
		username.setBorder(null);
		username.setFont(new Font("Arial", Font.PLAIN, 20));
		username.setBounds(10, 0, 316, 53);
		username.setText("18127102");
		panel.add(username);
		username.setColumns(10);

		JLabel usrIcon = new JLabel("");
		usrIcon.setLabelFor(username);
		usrIcon.setBackground(new Color(255, 255, 255));
		usrIcon.setHorizontalAlignment(SwingConstants.CENTER);
		usrIcon.setBounds(359, 0, 47, 53);
		usrIcon.setIcon(new ImageIcon(usr));
		panel.add(usrIcon);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(195, 380, 406, 53);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		password = new JPasswordField();
		password.setText("Password");
		password.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (password.getText().equals("Password")) {
					password.setEchoChar('●');
					password.setText("");
				} else {
					password.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (password.getText().equals("")) {
					password.setText("Password");
					password.setEchoChar((char) 0);
				}
			}
		});
		password.setBorder(null);
		password.setEchoChar((char) 0);
		password.setFont(new Font("Arial", Font.PLAIN, 20));
		password.setBounds(10, 0, 316, 53);
		// password.setText("nvh");
		panel_1.add(password);

		JLabel pwdIcon = new JLabel("");
		pwdIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((!password.getText().equals("")) && (!password.getText().equals("Password"))) {
					password.setText(password.getText());
					password.setEchoChar((char) 0);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if ((!password.getText().equals("")) && (!password.getText().equals("Password"))) {
					password.setEchoChar('●');
					password.setText(password.getText());
				}
			}
		});
		pwdIcon.setLabelFor(password);
		pwdIcon.setBackground(new Color(255, 255, 255));
		pwdIcon.setHorizontalAlignment(SwingConstants.CENTER);
		pwdIcon.setBounds(359, 0, 47, 53);
		pwdIcon.setIcon(new ImageIcon(pass));
		panel_1.add(pwdIcon);

		JPanel btnLogin = new JPanel();

		btnLogin.setBorder(null);
		btnLogin.setBackground(new Color(0, 255, 127));
		btnLogin.setLayout(null);
		btnLogin.setBounds(195, 465, 406, 53);
		contentPane.add(btnLogin);

		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(136, 0, 82, 53);
		btnLogin.add(lblNewLabel);

		JLabel key = new JLabel(new ImageIcon(Key));
		key.setBounds(218, 10, 30, 30);
		btnLogin.add(key);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Do you want to close this application?", "CLOSE THE PROGRAM",
						JOptionPane.YES_NO_OPTION) == 0) {
					LoginForm.this.dispose();
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
		lblX.setForeground(new Color(240, 248, 255));
		lblX.setFont(new Font("Comic Sans MS", Font.BOLD, 28));
		lblX.setHorizontalTextPosition(SwingConstants.CENTER);
		lblX.setHorizontalAlignment(SwingConstants.CENTER);
		lblX.setBounds(745, 0, 55, 53);
		contentPane.add(lblX);

		JLabel logBackground = new JLabel(new ImageIcon("/icon/login.png"));
		logBackground.setHorizontalAlignment(SwingConstants.CENTER);
		logBackground.setHorizontalTextPosition(SwingConstants.CENTER);
		logBackground.setBounds(334, 190, 120, 120);
		contentPane.add(logBackground);
		logBackground.setIcon(new ImageIcon(background));

		JLabel lblMessage = new JLabel("");
		lblMessage.setForeground(Color.RED);
		lblMessage.setFont(new Font("Arial", Font.BOLD, 14));
		lblMessage.setBounds(195, 435, 406, 25);
		contentPane.add(lblMessage);
		lblMessage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_1 = new JLabel("SCHOOL MANAGEMENT APPLICATION");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(123, 91, 558, 88);
		contentPane.add(lblNewLabel_1);

		JPanel btnResetPassword = new JPanel();
		btnResetPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					ResetPasswordForm form = new ResetPasswordForm();
					dispose();
					form.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnResetPassword.setBackground(new Color(0, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnResetPassword.setBackground(new Color(0, 255, 127));
			}
		});
		btnResetPassword.setLayout(null);
		btnResetPassword.setBorder(null);
		btnResetPassword.setBackground(new Color(0, 255, 127));
		btnResetPassword.setBounds(195, 519, 203, 53);
		contentPane.add(btnResetPassword);

		JLabel lblResetPassword = new JLabel("Reset Password");
		lblResetPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResetPassword.setFont(new Font("Arial", Font.BOLD, 14));
		lblResetPassword.setBounds(28, 0, 138, 53);
		btnResetPassword.add(lblResetPassword);

		JPanel btnLogFirsttime = new JPanel();
		btnLogFirsttime.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					FillPassForm a = new FillPassForm();
					dispose();
					a.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogFirsttime.setBackground(new Color(0, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogFirsttime.setBackground(new Color(0, 255, 127));
			}
		});
		btnLogFirsttime.setLayout(null);
		btnLogFirsttime.setBorder(null);
		btnLogFirsttime.setBackground(new Color(0, 255, 127));
		btnLogFirsttime.setBounds(398, 519, 203, 53);
		contentPane.add(btnLogFirsttime);

		JLabel lblResetPassword_1 = new JLabel("Sign up");
		lblResetPassword_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResetPassword_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblResetPassword_1.setBounds(0, 0, 123, 53);
		btnLogFirsttime.add(lblResetPassword_1);

		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin.setBackground(new Color(0, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin.setBackground(new Color(0, 255, 127));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (username.getText().equals("") || password.getText().equals("")
						|| username.getText().equals("Username") || password.getText().equals("Password")) {
					lblMessage.setForeground(new Color(255, 0, 0));
					lblMessage.setText("Username and Password must be filled!");
				} else {
					ResultSet tab;
					try {
						tab = ServerConnection.ExecuteQuery("select * from credential where username='"
								+ username.getText() + "' and password='" + password.getText() + "'");

						if (tab.next()) {
							lblMessage.setText("");
							if (tab.getString("role").equals("Student")) {
								ResultSet Client = ServerConnection.ExecuteQuery(
										"select * from student where id='" + tab.getString("username") + "'");
								if (Client.next()) {
									StudentMenu menu = new StudentMenu(Client, ServerConnection);
									dispose();
									menu.setVisible(true);
								}

							} else if (tab.getString("role").equals("Administrator")) {
								ResultSet Client = ServerConnection.ExecuteQuery(
										"select * from administrator where id='" + tab.getString("username") + "'");
								if (Client.next()) {
									AdminMenu menu = new AdminMenu(Client, ServerConnection);
									dispose();
									menu.setVisible(true);
								}
							} else if (tab.getString("role").equals("Teacher")) {
								ResultSet Client = ServerConnection
										.ExecuteQuery("select * from teacher where id='" + username.getText() + "'");
								if (Client.next()) {
									TeacherMenu menu = new TeacherMenu(Client, ServerConnection);
									dispose();
									menu.setVisible(true);
								}
							}
						} else {
							lblMessage.setText("Wrong username or password!");
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
