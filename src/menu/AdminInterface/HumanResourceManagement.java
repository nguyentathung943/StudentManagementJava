package menu.AdminInterface;

import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;

import menu.Server;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTable;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;


class HumanResourceManagement extends Container {
	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	private JTable table;
	JScrollPane ScrollPane;
	private JTextField textID;
	private JTextField textName;
	private JTextField textMainClass;
	private JTextField textEmail;
	private JTextField textPhone;
	private JRadioButton rdbtnMale, rdbtnFemale; 
	private ButtonGroup gender;
	private JTextField textField;
	private JLabel lblNotifiSearch;
	private JDateChooser dateChooser;
	private JLabel lblNotification, lblSearchBy;
	JButton UpdateBtn, AddBtn, DeleteBtn, TeacherTab, StudentTab;
	private JLabel lblDob, lblMainClass, lblGender;
	private boolean isStudent = true;
	public void ClearData() {
		table.clearSelection();
		lblNotification.setText("");
		textID.setText("");
		textName.setText("");
		textEmail.setText("");
		textMainClass.setText("");
		textPhone.setText("");
		gender.clearSelection();
		dateChooser.setCalendar(null);
		textField.setText("");
		lblNotification.setText("");
	}
	
	void GetTableData(Server ServerConnection, boolean isStudent) {
		model.setRowCount(0);
		if(isStudent) {
			
			Object[] column = {"Student ID","Student name","Main Class","Email","Gender", "DOB", "Phone"};
            model.setColumnIdentifiers(column);
            ResultSet data;
			try {
				data = ServerConnection.ExecuteQuery("select * from student");
				 while(data.next()) {
		            	model.addRow(new Object[] {data.getString("id"),data.getString("name"),data.getString("MainClass"),
		            			data.getString("email"), data.getString("gender"), data.getString("dob"), data.getString("phoneNumber")});
		            }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
           
		}
		else {
	        Object[] column = {"Teacher ID","Teacher Name", "DOB", "Email", "Phone"};
	        model.setColumnIdentifiers(column);
	        ResultSet data;
			try {
				data = ServerConnection.ExecuteQuery("select * from teacher");
				 while(data.next()) {
	                	model.addRow(new Object[] {data.getString("id"),data.getString("name"),data.getString("dob"),
	                			data.getString("email"), data.getString("phoneNumber")});
	                }
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
	
	public HumanResourceManagement(Server ServerConnection) throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Container c = this;
		setSize(1200,650);
		JLabel title = new JLabel("Human Resources Management"); 
        title.setFont(new Font("Arial", Font.BOLD, 30)); 
        title.setSize(336, 30); 
        title.setLocation(10, 10);
        c.add(title);
        ScrollPane = new JScrollPane();
        ScrollPane.setSize(817, 524);
        ScrollPane.setLocation(383, 126);
        add(ScrollPane);
        table = new JTable();
        table.setGridColor(new Color(255, 255, 255));
        
        
        table.setBackground(Color.white);
        table.getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
        table.getTableHeader().setBackground(new Color(0,153,153));
        table.setOpaque(false);
        table.setSelectionBackground(new Color(0,128,255));
        table.setRowHeight(45);
        table.setFont(new Font("Arial",Font.BOLD,16));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        model = new DefaultTableModel() {
			private static final long serialVersionUID = 1L;
        	    public boolean isCellEditable(int row, int column) {
            return false;
        }};
        
        GetTableData(ServerConnection, isStudent);
  
        ScrollPane.setViewportView(table);
        table.setModel(model);
        table.setBounds(226, 82, 657, 456);
        
        lblNotification = new JLabel("");
        lblNotification.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotification.setForeground(new Color(255,0,0));
        lblNotification.setBounds(10, 479, 336, 29);
        add(lblNotification);
        
        textID = new JTextField();
        textID.setFont(new Font("Arial", Font.PLAIN, 15));
        textID.setBounds(166, 126, 207, 40);
        add(textID);
        textID.setColumns(10);
        
        JLabel lblID = new JLabel("Student ID");
        lblID.setFont(new Font("Arial", Font.PLAIN, 22));
        
        lblID.setBounds(10, 124, 141, 40);
        add(lblID);
        
        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Arial", Font.PLAIN, 22));
        lblName.setBounds(10, 182, 141, 40);
        add(lblName);
        
        textName = new JTextField();
        textName.setFont(new Font("Arial", Font.PLAIN, 15));
        textName.setColumns(10);
        textName.setBounds(166, 182, 207, 40);
        add(textName);
        
        lblMainClass = new JLabel("Main Class");
        lblMainClass.setFont(new Font("Arial", Font.PLAIN, 22));
        lblMainClass.setBounds(10, 390, 141, 40);
        add(lblMainClass);
        
        JLabel lblEmail = new JLabel("Email");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEmail.setBounds(10, 232, 141, 40);
        add(lblEmail);
        
        JLabel lblPhone = new JLabel("Phone");
        lblPhone.setFont(new Font("Arial", Font.PLAIN, 22));
        lblPhone.setBounds(7, 287, 141, 40);
        add(lblPhone);
        
        textMainClass = new JTextField();
        textMainClass.setFont(new Font("Arial", Font.PLAIN, 15));
        textMainClass.setColumns(10);
        textMainClass.setBounds(166, 387, 210, 40);
        add(textMainClass);
        
        textEmail = new JTextField();
        textEmail.setFont(new Font("Arial", Font.PLAIN, 15));
        textEmail.setColumns(10);
        textEmail.setBounds(166, 232, 207, 40);
        add(textEmail);
        
        textPhone = new JTextField();
        textPhone.setFont(new Font("Arial", Font.PLAIN, 15));
        textPhone.setColumns(10);
        textPhone.setBounds(166, 287, 207, 40);
        add(textPhone);
        
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNotification.setText("");
        		table.clearSelection();
        		textID.setText("");
        		textName.setText("");
        		textEmail.setText("");
        		textMainClass.setText("");
        		textPhone.setText("");
			}
		});
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
        		if(isStudent) {
        			textID.setText(model.getValueAt(index, 0).toString());
            		textName.setText(model.getValueAt(index, 1).toString());
            		textMainClass.setText(model.getValueAt(index, 2).toString());
            		textEmail.setText(model.getValueAt(index, 3).toString());
            		if(model.getValueAt(index, 4).toString().equals("Female"))
            			
            			{
            			rdbtnFemale.setSelected(true);
            			rdbtnMale.setSelected(false);
            			}
            		else
            		{
            			rdbtnMale.setSelected(true);
            			rdbtnFemale.setSelected(false);
            		}
            		String dd = model.getValueAt(index, 5).toString();
        			Date date;
    				try {
    					date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
    					dateChooser.setDate(date);
    				} catch (ParseException e1) {
    					e1.printStackTrace();
    				}
    				
        			try {    				
        				textPhone.setText(model.getValueAt(index, 6).toString());
        			}catch(Exception a ) {
        				textPhone.setText("");
        			}
        					
        		}else {
        			textID.setText(model.getValueAt(index, 0).toString());
            		textName.setText(model.getValueAt(index, 1).toString());
            		
            		String dd = model.getValueAt(index, 2).toString();
        			Date date;
    				try {
    					date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
    					dateChooser.setDate(date);
    				} catch (ParseException e1) {
    					e1.printStackTrace();
    				}
    				textEmail.setText(model.getValueAt(index, 3).toString());
        			textPhone.setText(model.getValueAt(index, 4).toString());
        		}
        		
        	}
        });
        AddBtn = new JButton("Add Student");
        AddBtn.setBackground(new Color(240,240,240));
        AddBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AddBtn.setBackground(new Color(128,128,128));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		AddBtn.setBackground(new Color(240,240,240));
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		AddBtn.setBackground(new Color(204,255,229));
        	}
        });
        AddBtn.addActionListener(new ActionListener() { //// ADD COURSE
        	public void actionPerformed(ActionEvent e) {
        		if(isStudent) {
        			String StudentName = textName.getText();
            		String StudentID = textID.getText().toUpperCase();
            		String MainClass = textMainClass.getText();
            		String Email = textEmail.getText();
            		String Phone = textPhone.getText();
            		String DOB = df.format(dateChooser.getDate());
            		String Gender = "";
            		if(rdbtnMale.isSelected())
            			Gender = "Male";
            		else if(rdbtnFemale.isSelected())
            			Gender = "Female";
            		if (StudentName.equals("") || StudentID.equals("") ||MainClass.equals("") ||Email.equals("") ||DOB.equals("") || Gender.equals("")) {
            			lblNotification.setText("All fields must be filled");
            		}
            		else {
            			lblNotification.setText("");
            			try {
    						ServerConnection.InsertStudent(StudentID, StudentName, MainClass, Email, Gender, DOB, Phone);
    						GetTableData(ServerConnection, isStudent);
    		        		ClearData();
            			} catch (SQLException e1) {
    						e1.printStackTrace();
    					}
            		}
        		}else {
        			String TeacherName = textName.getText();
            		String TeacherID = textID.getText().toUpperCase();
            		String Email = textEmail.getText();
            		String Phone = textPhone.getText();
            		String DOB = df.format(dateChooser.getDate());
            		if (TeacherName.equals("") || TeacherID.equals("") ||Email.equals("") ||DOB.equals("")) {
            			lblNotification.setText("All fields must be filled");
            		}
            		else {
            			lblNotification.setText("");
            			try {
            				ServerConnection.InsertTeacher(TeacherID, TeacherName, Phone, Email, DOB);
    						GetTableData(ServerConnection, isStudent);
    		        		ClearData();
            			} catch (SQLException e1) {
    						e1.printStackTrace();
    					}
            		}
        		}
        		
        	}
        });
        AddBtn.setFont(new Font("Arial", Font.BOLD, 15));
        AddBtn.setBounds(194, 518, 150, 49);
        add(AddBtn);
        
       
        
        JLabel lblNote = new JLabel("*Choose the row that contain student's information that you want to modify in the table ");
        lblNote.setFont(new Font("Arial", Font.PLAIN, 13));
        lblNote.setBounds(383, 95, 517, 23);
        add(lblNote);
        
        JLabel lblRefresh = new JLabel("");
        lblRefresh.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		
        		model.setRowCount(0);
        		ClearData();
        		GetTableData(ServerConnection, isStudent);
        	}
        });
        lblRefresh.setIcon(new ImageIcon(HumanResourceManagement.class.getResource("/icon/reload.png")));
        lblRefresh.setBounds(1168, 10, 32, 32);
        add(lblRefresh);
        
        StudentTab = new JButton("Student");
        StudentTab.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		StudentTab.setBackground(new Color(0,129,129));
        	}
        });
        StudentTab.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		isStudent = true;
        		lblID.setText("Student ID");
        		UpdateBtn.setText("Update Student");
        		AddBtn.setText("Add Student");
        		DeleteBtn.setText("Delete Student");
        		lblDob.setVisible(true);
        		dateChooser.setVisible(true);
        		lblMainClass.setVisible(true);
        		textMainClass.setVisible(true);
        		lblGender.setVisible(true);
        		rdbtnMale.setVisible(true);
        		rdbtnFemale.setVisible(true);
                TeacherTab.setBackground(SystemColor.menu);
                lblSearchBy.setText("Search Student by ID");
                lblNote.setText("*Choose the row that contain student's information that you want to modify in the table");
                GetTableData(ServerConnection, isStudent);
        	}
        });
        StudentTab.setFont(new Font("Arial", Font.BOLD, 15));
        StudentTab.setBackground(new Color(0,129,129));
        StudentTab.setBounds(11, 50, 150, 49);
        add(StudentTab);
        
        TeacherTab = new JButton("Teacher");
        TeacherTab.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		isStudent = false;
        		lblID.setText("Teacher ID");
        		UpdateBtn.setText("Update Teacher");
        		AddBtn.setText("Add Teacher");
        		DeleteBtn.setText("Delete Teacher");
        		lblMainClass.setVisible(false);
        		textMainClass.setVisible(false);
        		lblGender.setVisible(false);
        		rdbtnMale.setVisible(false);
        		rdbtnFemale.setVisible(false);
                StudentTab.setBackground(SystemColor.menu);
                lblSearchBy.setText("Search Teacher by ID");
                lblNote.setText("*Choose the row that contain teacher's information that you want to modify in the table");
                ClearData();
                GetTableData(ServerConnection, isStudent);
        	}
        });
        TeacherTab.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		TeacherTab.setBackground(new Color(0,129,129));
        	}
        });
        TeacherTab.setFont(new Font("Arial", Font.BOLD, 15));
        TeacherTab.setBackground(SystemColor.menu);
        TeacherTab.setBounds(194, 50, 150, 49);
        add(TeacherTab);
        
        lblGender = new JLabel("Gender");
        lblGender.setFont(new Font("Arial", Font.PLAIN, 22));
        lblGender.setBounds(10, 440, 141, 40);
        add(lblGender);
        
        rdbtnMale = new JRadioButton("Male");
        rdbtnMale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdbtnMale.setBounds(166, 440, 105, 40);
        add(rdbtnMale);
        
        rdbtnFemale = new JRadioButton("Female");
        rdbtnFemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rdbtnFemale.setBounds(272, 440, 105, 40);
        add(rdbtnFemale);
        gender = new ButtonGroup();
        gender.add(rdbtnMale);
        gender.add(rdbtnFemale);
        lblDob = new JLabel("DOB");
        lblDob.setFont(new Font("Arial", Font.PLAIN, 22));
        lblDob.setBounds(10, 337, 84, 40);
        add(lblDob);
        UpdateBtn = new JButton("Update Student");
        UpdateBtn.setBackground(new Color(240,240,240));
        UpdateBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		UpdateBtn.setBackground(new Color(128,128,128));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		UpdateBtn.setBackground(new Color(240,240,240));
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		UpdateBtn.setBackground(new Color(204,255,229));
        	}
        });
        UpdateBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int index = table.getSelectedRow();
        		if(index==-1) {
        			lblNotification.setText("Please choose one line to be updated");
        		}
        		else {
        			if(isStudent) {
        				String o_id = model.getValueAt(index, 0).toString();
            			String StudentName = textName.getText();
                		String StudentID = textID.getText().toUpperCase();
                		String MainClass = textMainClass.getText();
                		String Email = textEmail.getText();
                		String Phone = textPhone.getText();
                		String dob = df.format(dateChooser.getDate());
                		String Gender = "";
                		if(rdbtnMale.isSelected())
                			Gender = "Male";
                		else if(rdbtnFemale.isSelected())
                			Gender = "Female";
                		
                		try {
                			if(!o_id.equals(StudentID))
                			{
                				ResultSet temp1 = ServerConnection.ExecuteQuery("select * from student where id="+StudentID);
        						if(temp1.next()) {
        							lblNotification.setText("Student ID is already existed");
        							return;
        						}
                			}
    						ServerConnection.UpdateStudent(ServerConnection, o_id,StudentID, StudentName, MainClass, Email, Gender, dob, Phone);
                    		ClearData();
                    		GetTableData(ServerConnection, isStudent);
                			 					
                		} catch (SQLException e1) {
                			lblNotification.setText("Invalid data format");	
    					}
            		}else {
            			String o_id = model.getValueAt(index, 0).toString();
            			String TeacherName = textName.getText();
                		String TeacherID = textID.getText().toUpperCase();
                		String Email = textEmail.getText();
                		String Phone = textPhone.getText();
                		String dob = df.format(dateChooser.getDate());
                		try {
                			if(!o_id.equals(TeacherID))
                			{
                				ResultSet temp1 = ServerConnection.ExecuteQuery("select * from teacher where id='"+ TeacherID + "'");
        						if(temp1.next()) {
        							lblNotification.setText("Teacher ID is already existed");
        							return;
        						}
                			}
							ServerConnection.UpdateTeacher(ServerConnection, o_id, TeacherID, TeacherName, Phone, Email, dob);
                    		ClearData();
                    		GetTableData(ServerConnection, isStudent);
                			 					
                		} catch (SQLException e1) {
                			e1.printStackTrace();
                			lblNotification.setText("Invalid data format");	
    					}
            		}
        		}
        	}
        });
        UpdateBtn.setFont(new Font("Arial", Font.BOLD, 15));
        UpdateBtn.setBounds(11, 518, 150, 49);
        add(UpdateBtn);
        
        DeleteBtn = new JButton("Delete Student");
        DeleteBtn.setBackground(new Color(240,240,240));
        DeleteBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		DeleteBtn.setBackground(new Color(128,128,128));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		DeleteBtn.setBackground(new Color(240,240,240));
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		DeleteBtn.setBackground(new Color(204,255,229));
        	}
        });
        DeleteBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int index = table.getSelectedRow();
        		if(index==-1) {
        			lblNotification.setText("Please choose one course to be deleted");
        		}
        		else {
        			if(isStudent) {
        				String StudentId = textID.getText();
        				try {
        					ServerConnection.DeleteStudent(StudentId);
        					GetTableData(ServerConnection, isStudent);
        		        		
        				}catch(SQLException e1) {
        					lblNotification.setText("Student you are trying to delete not exist!");
        				}
        			}else {
        				String TeacherId = textID.getText();
        				try {
        					ServerConnection.DeleteTeacher(TeacherId);
        					GetTableData(ServerConnection, isStudent);
        		        		
        				}catch(SQLException e1) {
        					lblNotification.setText("Teacher you are trying to delete not exist!");
        				}
        			}
        		}
        	}
        });
        DeleteBtn.setFont(new Font("Arial", Font.BOLD, 15));
        DeleteBtn.setBounds(11, 591, 150, 49);
        add(DeleteBtn);
        
        JButton ClearBtn = new JButton("Clear");
        ClearBtn.setBackground(new Color(240,240,240));
        ClearBtn.addMouseListener(new MouseAdapter() {
           	@Override
        	public void mouseClicked(MouseEvent e) {
           		ClearBtn.setBackground(new Color(128,128,128));
        	}
        	@Override
        	public void mouseExited(MouseEvent e) {
        		ClearBtn.setBackground(new Color(240,240,240));
        	}
        	@Override
        	public void mouseEntered(MouseEvent e) {
        		ClearBtn.setBackground(new Color(204,255,229));
        	}
        });
        ClearBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ClearData();
        	}
        });
        ClearBtn.setFont(new Font("Arial", Font.BOLD, 15));
        ClearBtn.setBounds(194, 591, 150, 49);
        add(ClearBtn);
        
        lblSearchBy = new JLabel("Search Student by ID");
        lblSearchBy.setFont(new Font("Arial", Font.BOLD, 20));
        lblSearchBy.setBounds(380, 41, 223, 44);
        add(lblSearchBy);
        
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setColumns(10);
        textField.setBounds(588, 41, 198, 47);
        add(textField);
        
        JLabel SearchIcon = new JLabel("");
        SearchIcon.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		ResultSet data;
        		try {
					String id = textField.getText();
					if(isStudent)
					{
						data = ServerConnection.ExecuteQuery("select * from student where id="+id);
					}
					else {
						data = ServerConnection.ExecuteQuery("select * from teacher where id='"+id +"'");
					}
					if(id.equals("")) {
						lblNotifiSearch.setText("Please fill out this field with course ID");
					}					
					else if(!data.next()) {
						lblNotifiSearch.setText("");
						model.setRowCount(0);
						model.addRow(new Object[] {"Not found!","","","",""});
					}
					else {
						lblNotifiSearch.setText("");
						model.setRowCount(0);
						if(isStudent)
					    model.addRow(new Object[] {data.getString("id"),data.getString("name"),data.getString("MainClass"),data.getString("email"),
			        			data.getString("gender"), data.getString("dob"), data.getString("phoneNumber")});
						else
							model.addRow(new Object[] {data.getString("id"),data.getString("name"),
										data.getString("dob"), data.getString("phoneNumber"),data.getString("email")});
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        SearchIcon.setIcon(new ImageIcon(HumanResourceManagement.class.getResource("/icon/search.png")));
        SearchIcon.setHorizontalAlignment(SwingConstants.CENTER);
        SearchIcon.setBounds(783, 44, 56, 47);
        add(SearchIcon);
        
        lblNotifiSearch = new JLabel("");
        lblNotifiSearch.setForeground(Color.RED);
        lblNotifiSearch.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotifiSearch.setBounds(588, 0, 226, 32);
        add(lblNotifiSearch);
        
        dateChooser = new JDateChooser();
        dateChooser.setBounds(166, 337, 207, 40);
        add(dateChooser);
        dateChooser.setDateFormatString("yyyy-MM-dd");
	}
	public String getDateChooserDateFormatString() {
		return dateChooser.getDateFormatString();
	}
	public void setDateChooserDateFormatString(String dateFormatString) {
		dateChooser.setDateFormatString(dateFormatString);
	}
	
}
