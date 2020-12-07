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


class CoursesManagement extends Container {
	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	private JTable table;
	JScrollPane ScrollPane;
	private JTextField textID;
	private JTextField textName;
	private JTextField textTeacherID;
	private JTextField textSchedule;
	private JRadioButton rdbtnMale, rdbtnFemale; 
	private ButtonGroup gender;
	private JTextField textField;
	private JLabel lblNotifiSearch;
	private JDateChooser dateChooserStart, dateChooserEnd;
	private JLabel lblNotification, lblSearchBy;
	JButton UpdateBtn, AddBtn, DeleteBtn;
	private JLabel lblStart;
	private boolean isStudent = true;
	public void ClearData() {
		table.clearSelection();
		lblNotification.setText("");
		textID.setText("");
		textName.setText("");
		textTeacherID.setText("");
		textSchedule.setText("");
		dateChooserStart.setCalendar(null);
		dateChooserEnd.setCalendar(null);
		textField.setText("");
		lblNotification.setText("");
	}
	
	void GetTableData(Server ServerConnection) {
		model.setRowCount(0);
		Object[] column = {"Course ID","Course name","Teacher ID","Start Date","End Date", "Schedule"};
	    model.setColumnIdentifiers(column);
	    ResultSet data;
		try {
			data = ServerConnection.ExecuteQuery("select * from course");
			 while(data.next()) {
	            	model.addRow(new Object[] {data.getString("courseID"),data.getString("name"),data.getString("headTeacher"),
	            			data.getString("startDate"), data.getString("endDate"), data.getString("time")});
	            }
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
           
		
		
	}
	
	public CoursesManagement(Server ServerConnection) throws SQLException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Container c = this;
		setSize(1200,650);
		JLabel title = new JLabel("Courses Management"); 
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
        
        GetTableData(ServerConnection);
  
        ScrollPane.setViewportView(table);
        table.setModel(model);
        table.setBounds(226, 82, 657, 456);
        
        lblNotification = new JLabel("");
        lblNotification.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotification.setForeground(new Color(255,0,0));
        lblNotification.setBounds(10, 449, 336, 29);
        add(lblNotification);
        
        textID = new JTextField();
        textID.setFont(new Font("Arial", Font.PLAIN, 15));
        textID.setBounds(166, 126, 207, 40);
        add(textID);
        textID.setColumns(10);
        
        JLabel lblID = new JLabel("Course ID");
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
       
        
        JLabel lblTeacherID = new JLabel("Teacher ID");
        lblTeacherID.setFont(new Font("Arial", Font.PLAIN, 22));
        lblTeacherID.setBounds(10, 232, 141, 40);
        add(lblTeacherID);
        
        JLabel lblSchedule = new JLabel("Schedule");
        lblSchedule.setFont(new Font("Arial", Font.PLAIN, 22));
        lblSchedule.setBounds(7, 287, 141, 40);
        add(lblSchedule);
        
        textTeacherID = new JTextField();
        textTeacherID.setFont(new Font("Arial", Font.PLAIN, 15));
        textTeacherID.setColumns(10);
        textTeacherID.setBounds(166, 232, 207, 40);
        add(textTeacherID);
        
        textSchedule = new JTextField();
        textSchedule.setFont(new Font("Arial", Font.PLAIN, 15));
        textSchedule.setColumns(10);
        textSchedule.setBounds(166, 287, 207, 40);
        add(textSchedule);
        
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lblNotification.setText("");
        		table.clearSelection();
        		textID.setText("");
        		textName.setText("");
        		textTeacherID.setText("");
        		textSchedule.setText("");
			}
		});
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
        		textID.setText(model.getValueAt(index, 0).toString());
        		textName.setText(model.getValueAt(index, 1).toString());
        		textTeacherID.setText(model.getValueAt(index, 2).toString());
        		String start = model.getValueAt(index, 3).toString();
        		String end = model.getValueAt(index, 4).toString();
    			Date date;
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(start);
					dateChooserStart.setDate(date);
					date = new SimpleDateFormat("yyyy-MM-dd").parse(end);
					dateChooserEnd.setDate(date);
					
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
    			
    			textSchedule.setText(model.getValueAt(index, 5).toString());
        		
        	}
        });
        AddBtn = new JButton("Add Course");
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
        AddBtn.addActionListener(new ActionListener() { 
        	public void actionPerformed(ActionEvent e) {
        		String courseID = textID.getText().toUpperCase();
        		String name = textName.getText();
        		String headTeacher = textTeacherID.getText();
        		String startDate = df.format(dateChooserStart.getDate());
        		String endDate = df.format(dateChooserEnd.getDate());
        		String time = textSchedule.getText();

        		if (courseID.equals("") || name.equals("") ||headTeacher.equals("") ||startDate.equals("") || endDate.equals("") || time.equals("")) {
        			lblNotification.setText("All fields must be filled");
        		}
        		else {
        			lblNotification.setText("");
        			try {
        				ServerConnection.InsertCourse(courseID, name, headTeacher,startDate,endDate,time);
						GetTableData(ServerConnection);
		        		ClearData();
        			} catch (SQLException e1) {
						e1.printStackTrace();
					}
        		}	
        	}
        });
        AddBtn.setFont(new Font("Arial", Font.BOLD, 15));
        AddBtn.setBounds(194, 518, 150, 49);
        add(AddBtn);
        
       
        
        JLabel lblNote = new JLabel("*Choose the row that contain course's information that you want to modify in the table ");
        lblNote.setFont(new Font("Arial", Font.PLAIN, 13));
        lblNote.setBounds(383, 95, 517, 23);
        add(lblNote);
        
        JLabel lblRefresh = new JLabel("");
        lblRefresh.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		
        		model.setRowCount(0);
        		ClearData();
        		GetTableData(ServerConnection);
        	}
        });
        lblRefresh.setIcon(new ImageIcon(CoursesManagement.class.getResource("/icon/reload.png")));
        lblRefresh.setBounds(1168, 10, 32, 32);
        add(lblRefresh);
        
        
        
        
        lblStart = new JLabel("Start Day");
        lblStart.setFont(new Font("Arial", Font.PLAIN, 22));
        lblStart.setBounds(10, 337, 121, 40);
        add(lblStart);
        UpdateBtn = new JButton("Update Course");
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
        			lblNotification.setText("Please choose one course to be updated");
        		}
        		else {
        			String o_id = model.getValueAt(index, 0).toString();
    				String courseID = textID.getText().toUpperCase();
            		String name = textName.getText();
            		String headTeacher = textTeacherID.getText();
            		String startDate = df.format(dateChooserStart.getDate());
            		String endDate = df.format(dateChooserEnd.getDate());
            		String time = textSchedule.getText();

            		if (courseID.equals("") || name.equals("") ||headTeacher.equals("") ||startDate.equals("") || endDate.equals("") || time.equals("")) {
            			lblNotification.setText("All fields must be filled");
            		}
            		try {
            			if(!o_id.equals(courseID))
            			{
            				ResultSet temp1 = ServerConnection.ExecuteQuery("select * from student where id='"+ courseID + "'");
    						if(temp1.next()) {
    							lblNotification.setText("Course ID is already existed");
    							return;
    						}
            			}
            			ServerConnection.UpdateCourseAdmin(ServerConnection, o_id, courseID, name, headTeacher, startDate,endDate,time);

                		ClearData();
                		GetTableData(ServerConnection);
            			 					
            		} catch (SQLException e1) {
            			e1.printStackTrace();
            			lblNotification.setText("Invalid data format");	
					}
        		}
        	}
        });
        UpdateBtn.setFont(new Font("Arial", Font.BOLD, 15));
        UpdateBtn.setBounds(11, 518, 150, 49);
        add(UpdateBtn);
        
        DeleteBtn = new JButton("Delete Course");
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
        			String CourseId = textID.getText();
    				try {
    					ServerConnection.DeleteCourse(CourseId);
    					GetTableData(ServerConnection);
    		        		
    				}catch(SQLException e1) {
    					lblNotification.setText("Course you are trying to delete not exist!");
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
        
        lblSearchBy = new JLabel("Search Course by ID");
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
					data = ServerConnection.ExecuteQuery("select * from course where courseID='"+id + "'");
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
						model.addRow(new Object[] {data.getString("courseID"),data.getString("name"),data.getString("headTeacher"),
		            			data.getString("startDate"), data.getString("endDate"), data.getString("time")});
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        SearchIcon.setIcon(new ImageIcon(CoursesManagement.class.getResource("/icon/search.png")));
        SearchIcon.setHorizontalAlignment(SwingConstants.CENTER);
        SearchIcon.setBounds(783, 44, 56, 47);
        add(SearchIcon);
        
        lblNotifiSearch = new JLabel("");
        lblNotifiSearch.setForeground(Color.RED);
        lblNotifiSearch.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotifiSearch.setBounds(588, 0, 226, 32);
        add(lblNotifiSearch);
        
        dateChooserStart = new JDateChooser();
        dateChooserStart.setBounds(166, 337, 207, 40);
        add(dateChooserStart);
        dateChooserStart.setDateFormatString("yyyy-MM-dd");
        
        JLabel lblEnd = new JLabel("End Day");
        lblEnd.setFont(new Font("Arial", Font.PLAIN, 22));
        lblEnd.setBounds(10, 387, 121, 40);
        add(lblEnd);
        
        dateChooserEnd = new JDateChooser();
        dateChooserEnd.setBounds(166, 387, 207, 40);
        add(dateChooserEnd);
        dateChooserEnd.setDateFormatString("yyyy-MM-dd");
	}
	public String getDateChooserDateFormatString() {
		return dateChooserStart.getDateFormatString();
	}
	public void setDateChooserDateFormatString(String dateFormatString) {
		dateChooserStart.setDateFormatString(dateFormatString);
	}
}
