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
import java.awt.Rectangle;

public class ResetPasswordForm extends JFrame {

	private JPanel contentPane;
	private JTextField txtFdfg;
	private JPasswordField textNewPass;
	private final JLabel lblX = new JLabel("X");
	private Image background = new ImageIcon(ResetPasswordForm.class.getResource("/icon/login.png")).getImage();
	private Image usr = new ImageIcon(ResetPasswordForm.class.getResource("/icon/username.png")).getImage()
			.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image pass = new ImageIcon(ResetPasswordForm.class.getResource("/icon/password.png")).getImage()
			.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private Image Key = new ImageIcon(ResetPasswordForm.class.getResource("/icon/Key.png")).getImage()
			.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
	private JTextField txtId;

	/**
	 * Launch the application.
	 * 
	 * @throws SQLException
	 */

	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResetPasswordForm frame = new ResetPasswordForm();
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
	public ResetPasswordForm() throws SQLException {
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

		JPanel PanelPassword = new JPanel();
		PanelPassword.setBackground(new Color(255, 255, 255));
		PanelPassword.setBounds(166, 320, 406, 53);
		contentPane.add(PanelPassword);
		PanelPassword.setLayout(null);
		PanelPassword.setVisible(false);

		JLabel lblUsername = new JLabel("");
		lblUsername.setForeground(Color.RED);
		lblUsername.setFont(new Font("Arial", Font.BOLD, 14));
		lblUsername.setBounds(166, 292, 524, 24);
		contentPane.add(lblUsername);

		textNewPass = new JPasswordField();
		textNewPass.setText("Password");
		textNewPass.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (textNewPass.getText().equals("Password")) {
					textNewPass.setEchoChar('●');
					textNewPass.setText("");
				} else {
					textNewPass.selectAll();
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (textNewPass.getText().equals("")) {
					textNewPass.setText("Password");
					textNewPass.setEchoChar((char) 0);
				}
			}
		});

		textNewPass.setBorder(null);
		textNewPass.setEchoChar((char) 0);
		textNewPass.setFont(new Font("Arial", Font.PLAIN, 20));
		textNewPass.setBounds(10, 0, 316, 53);
		PanelPassword.add(textNewPass);

		JLabel pwdIcon = new JLabel(new ImageIcon(pass));
		pwdIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if ((!textNewPass.getText().equals("")) && (!textNewPass.getText().equals("Password"))) {
					textNewPass.setText(textNewPass.getText());
					textNewPass.setEchoChar((char) 0);
				}
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if ((!textNewPass.getText().equals("")) && (!textNewPass.getText().equals("Password"))) {
					textNewPass.setEchoChar('●');
					textNewPass.setText(textNewPass.getText());
				}
			}
		});
		pwdIcon.setHorizontalAlignment(SwingConstants.CENTER);
		pwdIcon.setBackground(Color.WHITE);
		pwdIcon.setBounds(359, 0, 47, 53);
		PanelPassword.add(pwdIcon);

		JPanel btnLogin = new JPanel();

		btnLogin.setBorder(null);
		btnLogin.setBackground(new Color(0, 255, 127));
		btnLogin.setLayout(null);
		btnLogin.setBounds(166, 396, 406, 53);
		contentPane.add(btnLogin);
		btnLogin.setVisible(false);

		JLabel lblNewLabel = new JLabel("Continue");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(147, 0, 83, 53);
		btnLogin.add(lblNewLabel);
		lblX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Do you want to close this application?", "CLOSE THE PROGRAM",
						JOptionPane.YES_NO_OPTION) == 0) {
					ResetPasswordForm.this.dispose();
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

		JLabel lblMessage = new JLabel("");
		lblMessage.setFont(new Font("Arial", Font.BOLD, 14));
		lblMessage.setBounds(166, 373, 406, 24);
		contentPane.add(lblMessage);
		lblMessage.setHorizontalTextPosition(SwingConstants.CENTER);
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setForeground(Color.red);

		JLabel lblNoti1 = new JLabel("RESET PASSWORD");
		lblNoti1.setForeground(Color.WHITE);
		lblNoti1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNoti1.setBounds(284, 164, 196, 46);
		contentPane.add(lblNoti1);

		JLabel lblPasswordNoti = new JLabel("Your new password must be longer than 5 characters");
		lblPasswordNoti.setForeground(Color.WHITE);
		lblPasswordNoti.setFont(new Font("Arial", Font.BOLD, 14));
		lblPasswordNoti.setBounds(166, 292, 405, 24);
		contentPane.add(lblPasswordNoti);
		lblPasswordNoti.setVisible(false);

		JPanel panelUsername = new JPanel();
		panelUsername.setBackground(Color.WHITE);
		panelUsername.setBounds(166, 240, 406, 53);
		contentPane.add(panelUsername);
		panelUsername.setLayout(null);

		txtId = new JTextField();
		txtId.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtId.getText().equals("ID")) {
					txtId.setText("");
				}
			}
		});
		txtId.setText("ID");
		txtId.setFont(new Font("Arial", Font.PLAIN, 20));
		txtId.setBorder(null);
		txtId.setBounds(new Rectangle(10, 0, 316, 53));
		panelUsername.add(txtId);
		txtId.setColumns(10);

		JPanel btnCheck = new JPanel();
		btnCheck.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCheck.setBackground(new Color(0, 255, 255));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				btnCheck.setBackground(new Color(0, 255, 127));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				String id = txtId.getText();
				if (id.equals("")) {
					lblUsername.setText("Please fill the field with your ID!");
				} else {
					try {
						ResultSet a = ServerConnection
								.ExecuteQuery("select * from credential where username='" + id + "'");
						if (a.next()) {
							lblUsername.setText("");
							PanelPassword.setVisible(true);
							txtId.setEditable(false);
							textNewPass.setVisible(true);
							lblPasswordNoti.setVisible(true);
							btnLogin.setVisible(true);
							lblUsername.setVisible(false);
						} else {
							lblUsername.setText("This ID is not existed in the system");
						}

					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
		btnCheck.setLayout(null);
		btnCheck.setBorder(null);
		btnCheck.setBackground(new Color(0, 255, 127));
		btnCheck.setBounds(571, 240, 117, 53);
		contentPane.add(btnCheck);

		JLabel lblCheck = new JLabel("CHECK");
		lblCheck.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCheck.setFont(new Font("Arial", Font.BOLD, 14));
		lblCheck.setBounds(0, 0, 82, 53);
		btnCheck.add(lblCheck);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				try {
					LoginForm form = new LoginForm();
					form.setVisible(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(ResetPasswordForm.class.getResource("/icon/back.png")));
		lblNewLabel_1.setBounds(10, 11, 46, 24);
		contentPane.add(lblNewLabel_1);

		JLabel lblID = new JLabel("Type in the ID that the administrator provided");
		lblID.setForeground(Color.WHITE);
		lblID.setFont(new Font("Arial", Font.BOLD, 14));
		lblID.setBounds(166, 216, 524, 24);
		contentPane.add(lblID);

		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});

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
				String newPass = textNewPass.getText();
				String id = txtId.getText();
				if (newPass.equals("")) {
					lblMessage.setText("Please fill in your password");
				} else if (newPass.length() < 5) {
					lblMessage.setText("Password must be bigger than 5 characters");
				} else {
					lblMessage.setText("");
					try {
						ServerConnection.ExcecuteQueryUpdate(
								"update credential set password='" + newPass + "' where username='" + id + "'");
						dispose();
						LoginForm form = new LoginForm();
						form.setVisible(true);
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

			}
		});
	}
}
