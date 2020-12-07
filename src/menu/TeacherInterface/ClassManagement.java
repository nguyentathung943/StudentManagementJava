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


class ClassManagement extends Container {

	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	private JTable table;
	JScrollPane ScrollPane;
	
	private JTextField textIDSearch;
	private JTextField TextPractice;
	private JTextField TextTheory;
	
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
	public float get2decimal(float a) {
		DecimalFormat df = new DecimalFormat("0.00");
		return Float.parseFloat(df.format(a));
	}
	public float getOverAll(Float a,Float b) {
		return get2decimal((float) ((0.5*a) + (0.5)*b));
	}
	public String getKind(float point) {
		if(point>=5.0) {
			return "Pass";
		}
		return "Fail";
	}
	public ClassManagement(String ClassID,String ClientID,String ClassName,Server ServerConnection,JPanel back) throws SQLException {		
		Container c = this;
		this.ClassID = ClassID;
		this.ClientID = ClientID;
		this.ClassName = ClassName;
		setSize(1200,800);
		JLabel title = new JLabel("Class Management"); 
        title.setFont(new Font("Arial", Font.BOLD, 30)); 
        title.setSize(336, 30); 
        title.setLocation(10, 93);
        c.add(title);
        JLabel lblID = new JLabel("Student ID");
        lblID.setFont(new Font("Arial", Font.BOLD, 15));
        lblID.setBounds(10, 133, 98, 47);
        add(lblID);
        
        JLabel lblStudentName = new JLabel("Student name");
        lblStudentName.setFont(new Font("Arial", Font.BOLD, 15));
        lblStudentName.setBounds(10, 186, 109, 47);
        add(lblStudentName);
        
        JLabel lblStartingDay = new JLabel("Practice point");
        lblStartingDay.setFont(new Font("Arial", Font.BOLD, 15));
        lblStartingDay.setBounds(10, 243, 110, 47);
        add(lblStartingDay);
        
        JLabel lblEndingDate = new JLabel("Theory point");
        lblEndingDate.setFont(new Font("Arial", Font.BOLD, 15));
        lblEndingDate.setBounds(10, 299, 99, 47);
        add(lblEndingDate);
        
        JLabel lblTime = new JLabel("Overall ");
        lblTime.setFont(new Font("Arial", Font.BOLD, 15));
        lblTime.setBounds(10, 356, 99, 47);
        add(lblTime);
        
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
        
        JLabel TextOverall = new JLabel("");
        TextOverall.setFont(new Font("Arial", Font.PLAIN, 18));
        TextOverall.setBounds(118, 357, 98, 46);
        add(TextOverall);
        
        JLabel TextPass = new JLabel("");
        TextPass.setFont(new Font("Arial", Font.PLAIN, 20));
        TextPass.setBounds(118, 413, 228, 46);
        add(TextPass);
        
        JLabel lblNotifi = new JLabel("");
        lblNotifi.setForeground(Color.RED);
        lblNotifi.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotifi.setBounds(10, 450, 235, 30);
        add(lblNotifi);
        
        TextPractice = new JTextField();
        TextPractice.setFont(new Font("Arial", Font.PLAIN, 18));
        TextPractice.setBounds(118, 243, 98, 47);
        add(TextPractice);
        TextPractice.setColumns(10);
        
        TextTheory = new JTextField();
        TextTheory.setFont(new Font("Arial", Font.PLAIN, 18));
        TextTheory.setColumns(10);
        TextTheory.setBounds(118, 300, 98, 47);
        add(TextTheory);

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
        
        Object[] column = {"Student ID","Student name","Practice point","Theory point","Overall score","Pass"};
        model.setColumnIdentifiers(column);
        ResultSet data1 = ServerConnection.ExecuteQuery("select * from course_attend where courseID='"+this.ClassID+"'");
        while(data1.next()) {
        	String stuid = data1.getString("StudentID");
        	String stuName = getName(stuid);
        	String pracP = data1.getString("practice_point");
        	String TheoP = data1.getString("theory_point");
        	String ovl = data1.getString("overall");
        	String status = data1.getString("pass_status");
        	if(pracP==null) {
        		pracP= "";
        	}
        	if(TheoP==null) {
        		TheoP= "";
        	}
        	if(ovl==null) {
        		 ovl= "";
        	}
        	if(status==null) {
        		status= "";
        	}
        	model.addRow(new Object[] {stuid,stuName,pracP,TheoP,ovl,status});
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
        table.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
        		TextID.setText(table.getValueAt(index, 0).toString());
        		TextName.setText(table.getValueAt(index, 1).toString());
        		TextPractice.setText(table.getValueAt(index, 2).toString());
        		TextTheory.setText(table.getValueAt(index, 3).toString());
        		TextOverall.setText(table.getValueAt(index, 4).toString());
        		String status = table.getValueAt(index, 5).toString();
        		if(status.equals("Fail")) {
        			TextPass.setForeground(Color.red);
        		}
        		else if(status.equals("Pass")) {
        			TextPass.setForeground(Color.green);
        		}
       
        		TextPass.setText(status);       		
        	}
        });
        JLabel lblRefresh = new JLabel("");
        lblRefresh.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		model.setRowCount(0);
				ResultSet data1;
				try {
					data1 = ServerConnection.ExecuteQuery("select * from course_attend where courseID='"+ClassID+"'");
					 while(data1.next()) {
				        	String stuid = data1.getString("StudentID");
				        	String stuName = getName(stuid);
				        	String pracP = data1.getString("practice_point");
				        	String TheoP = data1.getString("theory_point");
				        	String ovl = data1.getString("overall");
				        	String status = data1.getString("pass_status");
				        	if(pracP==null) {
				        		pracP= "";
				        	}
				        	if(TheoP==null) {
				        		TheoP= "";
				        	}
				        	if(ovl==null) {
				        		 ovl= "";
				        	}
				        	if(status==null) {
				        		status= "";
				        	}
				        	model.addRow(new Object[] {stuid,stuName,pracP,TheoP,ovl,status});
					 }		        	
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        lblRefresh.setIcon(new ImageIcon(ClassManagement.class.getResource("/icon/reload.png")));
        lblRefresh.setBounds(1168, 99, 32, 32);
        add(lblRefresh);
        
        JLabel lblNewLabel = new JLabel("Search student by ID");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBounds(355, 89, 198, 44);
        add(lblNewLabel);
        
        JLabel lblNotifiSearch = new JLabel("");
        lblNotifiSearch.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotifiSearch.setForeground(Color.red);
        lblNotifiSearch.setBounds(556, 54, 235, 30);
        add(lblNotifiSearch);
        
        textIDSearch = new JTextField();
        textIDSearch.setFont(new Font("Arial", Font.PLAIN, 20));
        textIDSearch.setBounds(556, 84, 235, 47);
        add(textIDSearch);
        textIDSearch.setColumns(10);
        JLabel SearchIcon = new JLabel("");
        SearchIcon.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
				try {
					String id = textIDSearch.getText();
					ResultSet sub = ServerConnection.ExecuteQuery("select * from course_attend where StudentID='"+id+"' and courseID='"+ClassID+"'");
					if(id.equals("")) {
						lblNotifiSearch.setText("Please fill in this field with ID");
					}					
					else if(!sub.next()) {
						lblNotifiSearch.setText("");
						model.setRowCount(0);
						model.addRow(new Object[] {"Not found!","","","",""});
					}
					else {
						lblNotifiSearch.setText("");
						model.setRowCount(0);
						String stuid = sub.getString("StudentID");
			        	String stuName = getName(stuid);
			        	String pracP = sub.getString("practice_point");
			        	String TheoP = sub.getString("theory_point");
			        	String ovl = sub.getString("overall");
			        	String status = sub.getString("pass_status");
			        	if(pracP==null) {
			        		pracP= "";
			        	}
			        	if(TheoP==null) {
			        		TheoP= "";
			        	}
			        	if(ovl==null) {
			        		 ovl= "";
			        	}
			        	if(status==null) {
			        		status= "";
			        	}
			        	model.addRow(new Object[] {stuid,stuName,pracP,TheoP,ovl,status});
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        SearchIcon.setHorizontalAlignment(SwingConstants.CENTER);
        SearchIcon.setIcon(new ImageIcon(ClassManagement.class.getResource("/icon/search.png")));
        SearchIcon.setBounds(793, 84, 56, 47);
        add(SearchIcon);        

        
        JLabel lblClassName = new JLabel("");
        lblClassName.setFont(new Font("Arial", Font.BOLD, 30));
        lblClassName.setBounds(657, 10, 533, 46);
        lblClassName.setText("Class: "+ ClassID+" - "+ClassName);
        add(lblClassName);
     
        JLabel lblPass = new JLabel("Status");
        lblPass.setFont(new Font("Arial", Font.BOLD, 15));
        lblPass.setBounds(10, 413, 99, 47);
        add(lblPass);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addMouseListener(new MouseAdapter() {

        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
        		if(index==-1) {
        			lblNotifi.setText("No student was chosen!");
        		}
        		else {
        			String StuID = TextID.getText();
    	        	String PracPr = TextPractice.getText();
    	        	String TheoPr = TextTheory.getText();
    	        	String OVL = TextOverall.getText();
    	        	String STATUS = TextPass.getText();
    	        	if(!PracPr.equals("")&& !TheoPr.equals("")){
    	        		Float a = get2decimal(Float.parseFloat(PracPr));
    	        		Float b = get2decimal(Float.parseFloat(TheoPr));
    	        		Float ov = getOverAll(a,b);
    	        		String kind = getKind(ov);
    	        		try {
    	        			model.setRowCount(0);
    						ServerConnection.UpdateStudentCourse(StuID, ClassID, a,b, ov,kind);
    						ResultSet data1 = ServerConnection.ExecuteQuery("select * from course_attend where courseID='"+ClassID+"'");
    						 while(data1.next()) {
    					        	String stuid = data1.getString("StudentID");
    					        	String stuName = getName(stuid);
    					        	String pracP = data1.getString("practice_point");
    					        	String TheoP = data1.getString("theory_point");
    					        	String ovl = data1.getString("overall");
    					        	String status = data1.getString("pass_status");
    					        	if(pracP==null) {
    					        		pracP= "";
    					        	}
    					        	if(TheoP==null) {
    					        		TheoP= "";
    					        	}
    					        	if(ovl==null) {
    					        		 ovl= "";
    					        	}
    					        	if(status==null) {
    					        		status= "";
    					        	}
    					        	model.addRow(new Object[] {stuid,stuName,pracP,TheoP,ovl,status});
    						 }	
    					} catch (SQLException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
    	        	}
    	        	else {
    	        		Float Practice = null;
    	        		Float Theory = null;	   	        		
    	        		if(!PracPr.equals("")) {
    	        			Practice=get2decimal(Float.parseFloat(PracPr));
    	        		}	       	       
    	        		if(!TheoPr.equals("")) {
    	        			Theory= get2decimal(Float.parseFloat(TheoPr));
    	        		}
    	        		try {
    	        			model.setRowCount(0);
    						ServerConnection.UpdateStudentCourse(StuID, ClassID, Practice,Theory,null,null);
    						ResultSet data1 = ServerConnection.ExecuteQuery("select * from course_attend where courseID='"+ClassID+"'");
    						 while(data1.next()) {
    					        	String stuid = data1.getString("StudentID");
    					        	String stuName = getName(stuid);
    					        	String pracP = data1.getString("practice_point");
    					        	String TheoP = data1.getString("theory_point");
    					        	String ovl = data1.getString("overall");
    					        	String status = data1.getString("pass_status");
    					        	if(pracP==null) {
    					        		pracP= "";
    					        	}
    					        	if(TheoP==null) {
    					        		TheoP= "";
    					        	}
    					        	if(ovl==null) {
    					        		 ovl= "";
    					        	}
    					        	if(status==null) {
    					        		status= "";
    					        	}
    					        	model.addRow(new Object[] {stuid,stuName,pracP,TheoP,ovl,status});
    						 }		   
    					} catch (SQLException e1) {
    						// TODO Auto-generated catch block
    						e1.printStackTrace();
    					}
    	        	}	        		        		        		    
            	}
        		TextID.setText("");       		
        		TextName.setText("");
        		TextPractice.setText("");
        		TextTheory.setText("");
        		TextOverall.setText("");
        		TextPass.setText("");
        		}
	        	
        });
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 15));
        btnUpdate.setBounds(10, 491, 165, 47);
        add(btnUpdate);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
           		if(index==-1) {
        			lblNotifi.setText("No student was chosen!");
        		}
           		else {
           			String StuID = TextID.getText();
    	        	try {
    					ServerConnection.DeleteStudentCourse(StuID, ClassID);
    					model.setRowCount(0);
    					ResultSet data1 = ServerConnection.ExecuteQuery("select * from course_attend where courseID='"+ClassID+"'");
    					 while(data1.next()) {
    				        	String stuid = data1.getString("StudentID");
    				        	String stuName = getName(stuid);
    				        	String pracP = data1.getString("practice_point");
    				        	String TheoP = data1.getString("theory_point");
    				        	String ovl = data1.getString("overall");
    				        	String status = data1.getString("pass_status");
    				        	if(pracP==null) {
    				        		pracP= "";
    				        	}
    				        	if(TheoP==null) {
    				        		TheoP= "";
    				        	}
    				        	if(ovl==null) {
    				        		 ovl= "";
    				        	}
    				        	if(status==null) {
    				        		status= "";
    				        	}
    				        	model.addRow(new Object[] {stuid,stuName,pracP,TheoP,ovl,status});
    					 }
    		        		TextID.setText("");       		
    		        		TextName.setText("");
    		        		TextPractice.setText("");
    		        		TextTheory.setText("");
    		        		TextOverall.setText("");
    		        		TextPass.setText("");
    					 } catch (SQLException e1) {
    					// TODO Auto-generated catch block
    					e1.printStackTrace();
    				}
           		}	        	
        	}
        });
        btnDelete.setFont(new Font("Arial", Font.BOLD, 15));
        btnDelete.setBounds(10, 556, 165, 47);
        add(btnDelete);
        
        JButton btnClear = new JButton("Clear");
        btnClear.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		lblNotifi.setText("");
        		lblNotifiSearch.setText("");
        		table.clearSelection();
        		TextID.setText("");
        		TextName.setText("");
        		TextPractice.setText("");
        		TextTheory.setText("");
        		TextOverall.setText("");
        		TextPass.setText("");
        		textIDSearch.setText("");      		
        	}
        });
        btnClear.setFont(new Font("Arial", Font.BOLD, 15));
        btnClear.setBounds(10, 623, 165, 47);
        add(btnClear);
        
        
        JLabel lblBack = new JLabel("");
        lblBack.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
				CardLayout cl1 = (CardLayout)(back.getLayout());
				cl1.show(back, "MainUI");
        	}
        });
        lblBack.setIcon(new ImageIcon(ClassManagement.class.getResource("/icon/back.png")));
        lblBack.setBounds(10, 10, 74, 46);
        add(lblBack);
        

          
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
        		TextID.setText("");       		
        		TextName.setText("");
        		TextPractice.setText("");
        		TextTheory.setText("");
        		TextOverall.setText("");
        		TextPass.setText("");
			}
		});
	}
}
