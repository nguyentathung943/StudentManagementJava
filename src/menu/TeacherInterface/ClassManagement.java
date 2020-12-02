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
	
	private String ClassID;
	private String ClientID;
	public ClassManagement(Server ServerConnection) throws SQLException {		
		Container c = this;			
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
        TextID.setFont(new Font("Arial", Font.PLAIN, 15));
        TextID.setBounds(118, 133, 178, 47);
        add(TextID);
        
        JLabel TextName = new JLabel("");
        TextName.setFont(new Font("Arial", Font.PLAIN, 15));
        TextName.setBounds(118, 186, 178, 47);
        add(TextName);
        ScrollPane = new JScrollPane();
        ScrollPane.setSize(880, 668);
        ScrollPane.setLocation(320, 132);
        add(ScrollPane);
        table = new JTable();
        table.setGridColor(new Color(255, 255, 255));
        
        JLabel TextOverall = new JLabel("");
        TextOverall.setBounds(118, 357, 178, 46);
        add(TextOverall);
        
        JLabel TextPass = new JLabel("");
        TextPass.setBounds(118, 413, 178, 46);
        add(TextPass);
        
        TextPractice = new JTextField();
        TextPractice.setBounds(118, 243, 178, 47);
        add(TextPractice);
        TextPractice.setColumns(10);
        
        TextTheory = new JTextField();
        TextTheory.setColumns(10);
        TextTheory.setBounds(119, 300, 178, 47);
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
//        System.out.println(ClassID);
        ResultSet data1 = ServerConnection.ExecuteQuery("select * from course_attend where courseID='"+ClassID+"'");
//        if(data1.next()) {
//        	System.out.println(data1.getString("courseID"));
//        }
        while(data1.next()) {
//        	ResultSet stu = ServerConnection.ExecuteQuery("select name from student where id='"+data1.getString("courseID")+"'");
//        	if(stu.next()) {
        		model.addRow(new Object[] {data1.getString("courseID"),"",data1.getString("practice_point"),data1.getString("theory_point"),data1.getString("overall"),data1.getString("pass_status")});
        	//}    	
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
        		TextPass.setText(table.getValueAt(index, 5).toString());
        	}
        });
        JLabel lblRefresh = new JLabel("");
        lblRefresh.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		model.setRowCount(0);
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
        lblRefresh.setIcon(new ImageIcon(ClassManagement.class.getResource("/icon/reload.png")));
        lblRefresh.setBounds(1168, 99, 32, 32);
        add(lblRefresh);
        
        JLabel lblNewLabel = new JLabel("Search student by ID");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBounds(320, 87, 198, 44);
        add(lblNewLabel);
        
        textIDSearch = new JTextField();
        textIDSearch.setFont(new Font("Arial", Font.PLAIN, 20));
        textIDSearch.setBounds(523, 88, 235, 47);
        add(textIDSearch);
        textIDSearch.setColumns(10);
        JLabel SearchIcon = new JLabel("");
        SearchIcon.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
				try {
					String id = textIDSearch.getText();
					ResultSet sub = ServerConnection.ExecuteQuery("select * from course where courseID='"+id+"' and headTeacher='"+ClientID+"'");
					if(id.equals("")) {
						ResultSet data = ServerConnection.ExecuteQuery("select * from course where headTeacher='"+ClientID+"'");
						model.setRowCount(0);
						while(data.next()) {							
					        model.addRow(new Object[] {data.getString("courseID"),data.getString("name"),data.getString("startDate"),data.getString("endDate"),data.getString("time")});
					    }
					}					
					else if(!sub.next()) {
						model.setRowCount(0);
						model.addRow(new Object[] {"Not found!","","","",""});
					}
					else {
						model.setRowCount(0);
					    model.addRow(new Object[] {sub.getString("courseID"),sub.getString("name"),sub.getString("startDate"),sub.getString("endDate"),sub.getString("time")});
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
        	}
        });
        SearchIcon.setHorizontalAlignment(SwingConstants.CENTER);
        SearchIcon.setIcon(new ImageIcon(ClassManagement.class.getResource("/icon/search.png")));
        SearchIcon.setBounds(750, 87, 56, 47);
        add(SearchIcon);        
        JLabel lblNotifi = new JLabel("");
        lblNotifi.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotifi.setForeground(Color.red);
        lblNotifi.setBounds(10, 471, 301, 30);
        add(lblNotifi);
        JButton btnAdd = new JButton("Add");
        btnAdd.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
        		if(index==-1) {
        			lblNotifi.setText("No class was chosen!");
        		}
        		else {
        			String ClassID = TextID.getText();
        		}
        	}
        });
        btnAdd.setFont(new Font("Arial", Font.BOLD, 15));
        btnAdd.setBounds(10, 528, 165, 47);
        add(btnAdd);
        
        JLabel lblClassName = new JLabel("");
        lblClassName.setFont(new Font("Arial", Font.BOLD, 30));
        lblClassName.setBounds(657, 10, 533, 30);
        add(lblClassName);
//        ResultSet cou = ServerConnection.ExecuteQuery("select name from course where courseID='"+ClassID+"'");
//        
//        lblClassName.setText("Class: "+ ClassID+" - "+cou.getString("name"));
        
        JLabel lblPass = new JLabel("Pass");
        lblPass.setFont(new Font("Arial", Font.BOLD, 15));
        lblPass.setBounds(10, 413, 99, 47);
        add(lblPass);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Arial", Font.BOLD, 15));
        btnUpdate.setBounds(10, 598, 165, 47);
        add(btnUpdate);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Arial", Font.BOLD, 15));
        btnDelete.setBounds(10, 668, 165, 47);
        add(btnDelete);
        
        JButton btnClear = new JButton("Clear");
        btnClear.setFont(new Font("Arial", Font.BOLD, 15));
        btnClear.setBounds(10, 738, 165, 47);
        add(btnClear);
        
        JLabel lblBack = new JLabel("");
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
