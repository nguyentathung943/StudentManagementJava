package menu.TeacherInterface;

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
import com.toedter.calendar.JDateChooser;
import javax.swing.SwingConstants;


class ShowCourses extends Container {
	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	private JTable table;
	JScrollPane ScrollPane;
	private JTextField textCourseID;
	private JTextField textCourseName;
	private String ClientID;
	private JTextField textTime;
	private JDateChooser startDate, endDate;
	public ShowCourses(String id, Server ServerConnection) throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		ClientID = id;
		Container c = this;
		setSize(1200,650);
		JLabel title = new JLabel("Courses Management");
        title.setFont(new Font("Arial", Font.BOLD, 30)); 
        title.setSize(400, 35); 
        title.setLocation(10, 10);
        title.setForeground(new Color(197, 84, 84));
        c.add(title);
        ScrollPane = new JScrollPane();
        ScrollPane.setSize(817, 606);
        ScrollPane.setLocation(383, 44);
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
        		/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

				@Override
        	    public boolean isCellEditable(int row, int column) {
            //all cells false
            return false;
        }};
        
        Object[] column = {"Course ID","Course name","Start date","End date","Time"};
        model.setColumnIdentifiers(column);
        ResultSet data = ServerConnection.ExecuteQuery("select * from course where headTeacher='"+ClientID+"'");
        while(data.next()) {
        	model.addRow(new Object[] {data.getString("courseID"),data.getString("name"),data.getString("startDate"),data.getString("endDate"),data.getString("time")});
        }
        ScrollPane.setViewportView(table);
        table.setModel(model);
        table.setBounds(226, 82, 657, 456);
        
        JLabel lblNotification = new JLabel("");
        lblNotification.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotification.setForeground(new Color(255,0,0));
        lblNotification.setBounds(41, 352, 336, 29);
        add(lblNotification);
        
        textCourseID = new JTextField();
        textCourseID.setFont(new Font("Arial", Font.PLAIN, 15));
        textCourseID.setBounds(166, 92, 207, 40);
        add(textCourseID);
        textCourseID.setColumns(10);
        
        JLabel lblCourseID = new JLabel("Course ID");
        lblCourseID.setFont(new Font("Arial", Font.PLAIN, 22));
        
        lblCourseID.setBounds(10, 90, 141, 40);
        add(lblCourseID);
        
        JLabel lblCourseName_1 = new JLabel("Course name");
        lblCourseName_1.setFont(new Font("Arial", Font.PLAIN, 22));
        lblCourseName_1.setBounds(10, 148, 141, 40);
        add(lblCourseName_1);
        
        textCourseName = new JTextField();
        textCourseName.setFont(new Font("Arial", Font.PLAIN, 15));
        textCourseName.setColumns(10);
        textCourseName.setBounds(166, 148, 207, 40);
        add(textCourseName);
        
        JLabel lblStartDate = new JLabel("Start Date");
        lblStartDate.setFont(new Font("Arial", Font.PLAIN, 22));
        lblStartDate.setBounds(10, 201, 141, 40);
        add(lblStartDate);
        
        JLabel lblEndDate = new JLabel("End Date");
        lblEndDate.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEndDate.setBounds(10, 251, 141, 40);
        add(lblEndDate);
        
        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("Arial", Font.PLAIN, 22));
        lblTime.setBounds(10, 306, 141, 40);
        add(lblTime);
        
        textTime = new JTextField();
        textTime.setFont(new Font("Arial", Font.PLAIN, 15));
        textTime.setColumns(10);
        textTime.setBounds(166, 303, 207, 40);
        add(textTime);
        
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNotification.setText("");
        		table.clearSelection();
        		textCourseID.setText("");
        		textCourseName.setText("");
        		endDate.setCalendar(null);
        		startDate.setCalendar(null);
        		textTime.setText("");
			}
		});
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
        		textCourseID.setText(model.getValueAt(index, 0).toString());
        		textCourseName.setText(model.getValueAt(index, 1).toString());
        		String dd;
    			Date date;
    			try {
            		dd = model.getValueAt(index, 2).toString();
    				date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
    				startDate.setDate(date);
    				dd = model.getValueAt(index, 3).toString();
    				date = new SimpleDateFormat("yyyy-MM-dd").parse(dd);
    				endDate.setDate(date);
    			} catch (ParseException e1) {
    				e1.printStackTrace();
    			}
        		textTime.setText(model.getValueAt(index, 4).toString());
        	}
        });
        JButton AddBtn = new JButton("Add course");
        AddBtn.setBackground(new Color(240,240,240));
        AddBtn.setBackground(new Color(240,240,240));
        AddBtn.setBorder(null);
        AddBtn.setBackground(new Color(37,78,88));
        AddBtn.setForeground(new Color(136,189,188));
        AddBtn.setFocusable(false);
        AddBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		AddBtn.setBackground(new Color(128,128,128));
        	}
        	@Override
			public void mouseEntered(MouseEvent e) {
        		AddBtn.setBackground(new Color(17,45,50));
        		AddBtn.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				AddBtn.setBackground(new Color(37,78,88));
				AddBtn.setForeground(new Color(136,189,188));
			}
        });
        AddBtn.addActionListener(new ActionListener() { //// ADD COURSE
        	public void actionPerformed(ActionEvent e) {
        		try {
            		String CourseName = textCourseName.getText();
            		String CourseID = textCourseID.getText().toUpperCase();
            		String StartDate = df.format(startDate.getDate());
            		String EndDate = df.format(endDate.getDate());
            		String Time = textTime.getText();
            		if (CourseName.equals("") || CourseID.equals("") ||StartDate.equals("") ||EndDate.equals("") ||Time.equals("")) {
            			lblNotification.setText("All fields must be filled");
            		}
            		else {
            			lblNotification.setText("");
            			try {
    						ResultSet temp1 = ServerConnection.ExecuteQuery("select * from course where courseID='"+CourseID+"'");
    						if(temp1.next()) {
    							lblNotification.setText("Course ID is already existed");					
    						}
    						else {
    							lblNotification.setText("");
    							try {
    								ServerConnection.InsertCourse(CourseID, CourseName,ClientID,StartDate,EndDate,Time);
    								model.setRowCount(0);
    								ResultSet data = ServerConnection.ExecuteQuery("select * from course where headTeacher='"+ClientID+"'");
    								while(data.next()) {
    							        model.addRow(new Object[] {data.getString("courseID"),data.getString("name"),data.getString("startDate"),data.getString("endDate"),data.getString("time")});
    							    }
    					        		textCourseID.setText("");
    					        		textCourseName.setText("");
    					        		endDate.setCalendar(null);
    					        		startDate.setCalendar(null);
    					        		textTime.setText("");
    							}
    							catch(Exception e1) {
    								lblNotification.setText("Invalid data format");								
    							}
    						}
    					} catch (SQLException e1) {
    						e1.printStackTrace();
    					}
            		}
        		}catch(Exception exc) {
        			lblNotification.setText("Invalid data format");	
        		}
        	}
        });
        AddBtn.setFont(new Font("Arial", Font.BOLD, 15));
        AddBtn.setBounds(193, 381, 150, 49);
        add(AddBtn);
        
        JButton UpdateBtn = new JButton("Update course");
        UpdateBtn.setBackground(new Color(240,240,240));
        UpdateBtn.setBorder(null);
        UpdateBtn.setBackground(new Color(37,78,88));
        UpdateBtn.setForeground(new Color(136,189,188));
        UpdateBtn.setFocusable(false);
        UpdateBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		UpdateBtn.setBackground(new Color(128,128,128));
        	}
        	@Override
			public void mouseEntered(MouseEvent e) {
        		UpdateBtn.setBackground(new Color(17,45,50));
        		UpdateBtn.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				UpdateBtn.setBackground(new Color(37,78,88));
				UpdateBtn.setForeground(new Color(136,189,188));
        	}
        });
        UpdateBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int index = table.getSelectedRow();
        		if(index==-1) {
        			lblNotification.setText("Please choose one course to be updated");
        		}
        		else {
        			String CourseName = textCourseName.getText();
            		String CourseID = textCourseID.getText().toUpperCase();
            		String StartDate = df.format(startDate.getDate());
            		String EndDate = df.format(endDate.getDate());
            		String Time = textTime.getText();
            		String o_id = model.getValueAt(index, 0).toString();
            		if (CourseName.equals("") || CourseID.equals("") ||StartDate.equals("") ||EndDate.equals("") ||Time.equals("")) {
            			lblNotification.setText("All fields must be filled");
            		}
            		else {
            			lblNotification.setText("");
            			try {
							ServerConnection.UpdateCourse(o_id, CourseID, CourseName,StartDate,EndDate,Time);
							model.setRowCount(0);
							ResultSet data = ServerConnection.ExecuteQuery("select * from course where headTeacher='"+ClientID+"'");
							while(data.next()) {
						        model.addRow(new Object[] {data.getString("courseID"),data.getString("name"),data.getString("startDate"),data.getString("endDate"),data.getString("time")});
						    }
								textCourseID.setText("");
								textCourseName.setText("");
								endDate.setCalendar(null);
								startDate.setCalendar(null);
								textTime.setText("");
						}
						catch(Exception e1) {
							lblNotification.setText("Invalid data format");								
						}
            		}
        		}
        	}
        });
        UpdateBtn.setFont(new Font("Arial", Font.BOLD, 15));
        UpdateBtn.setBounds(10, 381, 150, 49);
        add(UpdateBtn);
        
        JButton DeleteBtn = new JButton("Delete course");
        DeleteBtn.setBackground(new Color(240,240,240));
        DeleteBtn.setBorder(null);
        DeleteBtn.setBackground(new Color(37,78,88));
        DeleteBtn.setForeground(new Color(136,189,188));
        DeleteBtn.setFocusable(false);
        DeleteBtn.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		DeleteBtn.setBackground(new Color(128,128,128));
        	}
        	@Override
			public void mouseEntered(MouseEvent e) {
        		DeleteBtn.setBackground(new Color(17,45,50));
        		DeleteBtn.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				DeleteBtn.setBackground(new Color(37,78,88));
				DeleteBtn.setForeground(new Color(136,189,188));
			}
        });
        DeleteBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int index = table.getSelectedRow();
        		if(index==-1) {
        			lblNotification.setText("Please choose one course to be deleted");
        		}
        		else {
        			String CourseName = textCourseName.getText();
            		String CourseID = textCourseID.getText().toUpperCase();
            		String StartDate = df.format(startDate.getDate());
            		String EndDate = df.format(endDate.getDate());
            		String Time = textTime.getText();
            		if (CourseName.equals("") || CourseID.equals("") ||StartDate.equals("") ||EndDate.equals("") ||Time.equals("")) {
            			lblNotification.setText("All fields must be filled");
            		}
            		else {
                		try {
        					ServerConnection.DeleteCourse(CourseID, ClientID);
        					model.setRowCount(0);
        					lblNotification.setText("");
        					ResultSet data = ServerConnection.ExecuteQuery("select * from course where headTeacher='"+ClientID+"'");
        					  while(data.next()) {
        				        	model.addRow(new Object[] {data.getString("courseID"),data.getString("name"),data.getString("startDate"),data.getString("endDate"),data.getString("time")});
        				        }
        		        		textCourseID.setText("");
        		        		textCourseName.setText("");
        		        		endDate.setCalendar(null);
        		        		startDate.setCalendar(null);
        		        		textTime.setText("");
        				} catch (SQLException e1) {
        					lblNotification.setText("Course you are trying to delete not exist!");
        				}
            		}
        		}
        	}
        });
        DeleteBtn.setFont(new Font("Arial", Font.BOLD, 15));
        DeleteBtn.setBounds(10, 454, 150, 49);
        add(DeleteBtn);
        
        JButton ClearBtn = new JButton("Clear");
        ClearBtn.setBackground(new Color(240,240,240));
        ClearBtn.setBorder(null);
        ClearBtn.setBackground(new Color(37,78,88));
        ClearBtn.setForeground(new Color(136,189,188));
        ClearBtn.setFocusable(false);
        ClearBtn.addMouseListener(new MouseAdapter() {
           	@Override
        	public void mouseClicked(MouseEvent e) {
           		ClearBtn.setBackground(new Color(128,128,128));
        	}
           	@Override
			public void mouseEntered(MouseEvent e) {
           		ClearBtn.setBackground(new Color(17,45,50));
           		ClearBtn.setForeground(new Color(255, 255, 255));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				ClearBtn.setBackground(new Color(37,78,88));
				ClearBtn.setForeground(new Color(136,189,188));
			}
        });
        ClearBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		table.clearSelection();
        		textCourseID.setText("");
        		textCourseName.setText("");
        		endDate.setCalendar(null);
        		startDate.setCalendar(null);
        		textTime.setText("");
        		lblNotification.setText("");
        	}
        });
        ClearBtn.setFont(new Font("Arial", Font.BOLD, 15));
        ClearBtn.setBounds(193, 454, 150, 49);
        add(ClearBtn);
        
        JLabel lblNewLabel = new JLabel("*Choose the row that contain course's information that you want to modify in the table ");
        lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        lblNewLabel.setBounds(383, 16, 492, 30);
        add(lblNewLabel);
        
        JLabel lblRefresh = new JLabel("");
        lblRefresh.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		model.setRowCount(0);
        		table.clearSelection();
        		textCourseID.setText("");
        		textCourseName.setText("");
        		endDate.setCalendar(null);
        		startDate.setCalendar(null);
        		textTime.setText("");
        		lblNotification.setText("");
				ResultSet data;
				try {
					data = ServerConnection.ExecuteQuery("select * from course where headTeacher='"+ClientID+"'");
					  while(data.next()) {
				        	model.addRow(new Object[] {data.getString("courseID"),data.getString("name"),data.getString("startDate"),data.getString("endDate"),data.getString("time")});
				        }
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        lblRefresh.setIcon(new ImageIcon(ShowCourses.class.getResource("/icon/reload.png")));
        lblRefresh.setBounds(1168, 10, 32, 32);
        add(lblRefresh);
        
        startDate = new JDateChooser();
        startDate.setBounds(166, 201, 207, 40);
        add(startDate);
        startDate.setDateFormatString("yyyy-MM-dd");
        
        endDate = new JDateChooser();
        endDate.setBounds(166, 251, 207, 40);
        add(endDate);
        endDate.setDateFormatString("yyyy-MM-dd");
        
        
	}
}
