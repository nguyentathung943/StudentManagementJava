package menu.TeacherInterface;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.MultipleGradientPaint.ColorSpaceType;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import menu.Server;

import java.sql.*;
import java.text.DecimalFormat;

import javax.swing.JTable;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


class ShowPending extends Container {

	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	private JTable table;
	JScrollPane ScrollPane;
	
	private JTextField textIDSearch;
	
	public String ClassID;
	public String ClientID;
	public String ClassName;
	Server temp = new Server();
	public String getName(String id) throws SQLException {
		ResultSet stu = temp.statement.executeQuery("select * from student where id='"+id+"'");
		while(stu.next()) {
			return stu.getString("name");
		}
		return "";
	}
	public ShowPending(String id,String ClassID,String ClassName, Server ServerConnection,Container back) throws SQLException {		
		Container c = this;
		this.ClassID = ClassID;
		this.ClientID = ClientID;
		this.ClassName = ClassName;
		setSize(1200,800);
		this.setBackground(new Color(222,242,241));
				
		JLabel title = new JLabel("Class Registration Pending"); 
        title.setFont(new Font("Arial", Font.BOLD, 36)); 
        title.setSize(466, 38); 
        title.setLocation(55, 18);
        title.setForeground(new Color(197,84,84));
        c.add(title);
        JLabel lblID = new JLabel("Student ID");
        lblID.setFont(new Font("Arial", Font.BOLD, 15));
        lblID.setBounds(10, 133, 98, 47);
        add(lblID);
        
        JLabel lblStudentName = new JLabel("Student name");
        lblStudentName.setFont(new Font("Arial", Font.BOLD, 15));
        lblStudentName.setBounds(10, 186, 109, 47);
        add(lblStudentName);
        
        JLabel TextID = new JLabel("");
        TextID.setFont(new Font("Arial", Font.PLAIN, 18));
        TextID.setBounds(118, 133, 228, 47);
        add(TextID);
        
        JLabel TextName = new JLabel("");
        TextName.setFont(new Font("Arial", Font.PLAIN, 18));
        TextName.setBounds(118, 186, 228, 47);
        add(TextName);
        ScrollPane = new JScrollPane();
        ScrollPane.setSize(845, 668);
        ScrollPane.setLocation(355, 132);
        add(ScrollPane);
        table = new JTable();
        table.setGridColor(new Color(255, 255, 255));
        
        JLabel lblNotifi = new JLabel("");
        lblNotifi.setForeground(Color.RED);
        lblNotifi.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotifi.setBounds(10, 300, 257, 30);
        add(lblNotifi);
        
        JLabel lblMainClass = new JLabel("Main Class");
        lblMainClass.setFont(new Font("Arial", Font.BOLD, 15));
        lblMainClass.setBounds(10, 243, 109, 47);
        add(lblMainClass);
        
        JLabel TextMainClass = new JLabel("");
        TextMainClass.setFont(new Font("Arial", Font.PLAIN, 18));
        TextMainClass.setBounds(118, 243, 228, 47);
        add(TextMainClass);
        table.setBackground(Color.white);
        table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
        table.getTableHeader().setBackground(new Color(0,153,153));
        table.setOpaque(false);
        table.setSelectionBackground(new Color(0,128,255));
        table.setRowHeight(45);
        table.setFont(new Font("Arial",Font.BOLD,16));
        model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
				@Override
        	    public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }};
        
        Object[] column = {"Student ID","Student name","Main Class"};
        model.setColumnIdentifiers(column);
        ResultSet data1 = ServerConnection.ExecuteQuery("select * from student,registered_course where student.id=registered_course.studentID");
        while(data1.next()) {
        	String Stuid = data1.getString("id");
        	String name = data1.getString("name");
        	String MainClass = data1.getString("MainClass");
        	model.addRow(new Object[] {Stuid,name,MainClass});
        }
        ScrollPane.setViewportView(table);
        table.setModel(model);
        table.setBounds(226, 82, 657, 456);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
        		table.clearSelection();
			}
		});

        JLabel lblRefresh = new JLabel("");
        lblRefresh.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		model.setRowCount(0);
				ResultSet data1;
				try {
					data1 = ServerConnection.ExecuteQuery("select * from student,registered_course where student.id=registered_course.studentID");
					if(!data1.next()) {
						model.setRowCount(0);
						model.addRow(new Object[] {"No student!","","","",""});
					} 
					while(data1.next()) {
					 			model.setRowCount(0);
					      		String Stuid = data1.getString("id");
					        	String name = data1.getString("name");
					        	String MainClass = data1.getString("MainClass");
					        	model.addRow(new Object[] {Stuid,name,MainClass});			        
				}		        	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        lblRefresh.setIcon(new ImageIcon(ShowPending.class.getResource("/icon/reload.png")));
        lblRefresh.setBounds(1168, 99, 32, 32);
        add(lblRefresh);
        
        JLabel lblNewLabel = new JLabel("Search student by ID");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBounds(355, 85, 198, 47);
        add(lblNewLabel);
        
        JLabel lblNotifiSearch = new JLabel("");
        lblNotifiSearch.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotifiSearch.setForeground(Color.red);
        lblNotifiSearch.setBounds(556, 54, 235, 30);
        add(lblNotifiSearch);
        
        textIDSearch = new JTextField();
        textIDSearch.setFont(new Font("Arial", Font.PLAIN, 20));
        textIDSearch.setBounds(556, 85, 235, 47);
        add(textIDSearch);
        textIDSearch.setColumns(10);
        JLabel SearchIcon = new JLabel("");
        SearchIcon.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
				try {
					String id = textIDSearch.getText();
					ResultSet sub = ServerConnection.ExecuteQuery("select * from student,registered_course where student.id=registered_course.studentID and registered_course.studentID='"+id+"'");
					if(id.equals("")) {
						lblNotifiSearch.setText("Please fill in this field with ID");
					}					
					else if(!sub.next()) {
						lblNotifiSearch.setText("");
						model.setRowCount(0);
						model.addRow(new Object[] {"No student!","","","",""});
					}
					else {
						lblNotifiSearch.setText("");
						model.setRowCount(0);
				        ResultSet data1 = ServerConnection.ExecuteQuery("select * from student,registered_course where student.id=registered_course.studentID");
				        while(data1.next()) {
				        	String Stuid = data1.getString("id");
				        	String name = data1.getString("name");
				        	String MainClass = data1.getString("MainClass");
				        	model.addRow(new Object[] {Stuid,name,MainClass});
				        }
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        SearchIcon.setHorizontalAlignment(SwingConstants.CENTER);
        SearchIcon.setIcon(new ImageIcon(ShowPending.class.getResource("/icon/search.png")));
        SearchIcon.setBounds(791, 85, 56, 47);
        add(SearchIcon);        

        
        JLabel lblClassName = new JLabel("");
        lblClassName.setFont(new Font("Arial", Font.BOLD, 30));
        lblClassName.setBounds(657, 10, 533, 46);
        lblClassName.setText("Class: "+ ClassID+" - "+ClassName);
        lblClassName.setForeground(new Color(197,84,84));
        add(lblClassName);
        
        JButton btnApprove = new JButton("Approve");
        btnApprove.setBorder(null);
        btnApprove.setBackground(new Color(37,78,88));
        btnApprove.setForeground(new Color(136,189,188));
        btnApprove.setFocusable(false);
        btnApprove.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
        		if(index==-1) {
        			lblNotifi.setText("No student was chosen!");
        		}
        		else {
        			lblNotifiSearch.setText("");
            		String id = TextID.getText();
            		String CourseID = ClassID;
            		try {
						ServerConnection.ExcecuteQueryUpdate("delete from registered_course where courseID='"+CourseID+"' and studentID='"+id+"'");
						ServerConnection.AddStudenttoCourse(id,CourseID);
						
						model.setRowCount(0);				        
						ResultSet data1 = ServerConnection.ExecuteQuery("select * from student,registered_course where student.id=registered_course.studentID");
						if(!data1.next()) {
							model.setRowCount(0);
							model.addRow(new Object[] {"No student!","","","",""});
						}else {
							while(data1.next()) {
					        	String Stuid = data1.getString("id");
					        	String name = data1.getString("name");
					        	String MainClass = data1.getString("MainClass");
					        	model.addRow(new Object[] {Stuid,name,MainClass});
					        }
						}
		        		table.clearSelection();
		        		TextID.setText("");
		        		TextName.setText("");
		        		TextMainClass.setText("");     
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    			
            	}
        	} 
        	@Override
			public void mouseEntered(MouseEvent e) {
        		btnApprove.setBackground(new Color(17,45,50));
        		btnApprove.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnApprove.setBackground(new Color(37,78,88));
				btnApprove.setForeground(new Color(136,189,188));
			}
        });
        btnApprove.setFont(new Font("Arial", Font.BOLD, 15));
        btnApprove.setBounds(10, 340, 165, 47);
        add(btnApprove);
        
        JButton btnDelete = new JButton("Remove");
        btnDelete.setBorder(null);
        btnDelete.setBackground(new Color(37,78,88));
        btnDelete.setForeground(new Color(136,189,188));
        btnDelete.setFocusable(false);
        btnDelete.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
           		if(index==-1) {
        			lblNotifi.setText("No student was chosen!");
        		}else {
        			lblNotifiSearch.setText("");
            		String id = TextID.getText();
            		String CourseID = ClassID;
            		try {
						ServerConnection.ExcecuteQueryUpdate("delete from registered_course where courseID='"+CourseID+"' and studentID='"+id+"'");						
						model.setRowCount(0);
				        ResultSet data1 = ServerConnection.ExecuteQuery("select * from student,registered_course where student.id=registered_course.studentID");
						if(!data1.next()) {
							model.setRowCount(0);
							model.addRow(new Object[] {"No student!","","","",""});
						}
						else {
					        while(data1.next()) {
					        	String Stuid = data1.getString("id");
					        	String name = data1.getString("name");
					        	String MainClass = data1.getString("MainClass");
					        	model.addRow(new Object[] {Stuid,name,MainClass});
					        }
						}
		        		table.clearSelection();
		        		TextID.setText("");
		        		TextName.setText("");
		        		TextMainClass.setText("");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}    
        		}
        	}
        	@Override
			public void mouseEntered(MouseEvent e) {
        		btnDelete.setBackground(new Color(17,45,50));
        		btnDelete.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnDelete.setBackground(new Color(37,78,88));
				btnDelete.setForeground(new Color(136,189,188));
			}
        });
        btnDelete.setFont(new Font("Arial", Font.BOLD, 15));
        btnDelete.setBounds(10, 397, 165, 47);
        add(btnDelete);
        
        JButton btnClear = new JButton("Clear");
        btnClear.setBorder(null);
        btnClear.setBackground(new Color(37,78,88));
        btnClear.setForeground(new Color(136,189,188));
        btnClear.setFocusable(false);
        btnClear.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		lblNotifi.setText("");
        		lblNotifiSearch.setText("");
        		table.clearSelection();
        		TextID.setText("");
        		TextName.setText("");
        		TextMainClass.setText("");
        		textIDSearch.setText("");      		
        	}
        	@Override
			public void mouseEntered(MouseEvent e) {
        		btnClear.setBackground(new Color(17,45,50));
        		btnClear.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnClear.setBackground(new Color(37,78,88));
				btnClear.setForeground(new Color(136,189,188));
			}
        });
        btnClear.setFont(new Font("Arial", Font.BOLD, 15));
        btnClear.setBounds(10, 454, 165, 47);
        add(btnClear);
        
        
        JLabel lblBack = new JLabel("");
        lblBack.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
				CardLayout cl1 = (CardLayout)(back.getLayout());
				cl1.show(back, "MainUI");
        	}
        });
        lblBack.setIcon(new ImageIcon(ShowPending.class.getResource("/icon/back.png")));
        lblBack.setBounds(10, 10, 74, 46);
        add(lblBack);
        
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
        		TextID.setText(model.getValueAt(index, 0).toString());
        		TextName.setText(model.getValueAt(index, 1).toString());
        		TextMainClass.setText(model.getValueAt(index, 2).toString());
        		
        	}
        });
        
   
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
        		lblNotifi.setText("");
        		lblNotifiSearch.setText("");
        		table.clearSelection();
        		TextID.setText("");
        		TextName.setText("");
        		TextMainClass.setText("");
        		textIDSearch.setText(""); 
			}
		});
	}
}
