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


class ShowTeachingSchedule extends Container {

	private static final long serialVersionUID = 1L;
	DefaultTableModel model;
	private JTable table;
	JScrollPane ScrollPane;
	private String ClientID;
	private JTextField textIDSearch;
	public ShowTeachingSchedule(String id,JPanel ContentPane, Server ServerConnection) throws SQLException {
		ClientID = id;
		Container c = this;
		setSize(1200,650);
		JLabel title = new JLabel("Course Schedule"); 
        title.setFont(new Font("Arial", Font.BOLD, 30)); 
        title.setSize(336, 30); 
        title.setLocation(10, 10);
        c.add(title);
        JLabel lblID = new JLabel("Course ID");
        lblID.setFont(new Font("Arial", Font.BOLD, 15));
        lblID.setBounds(10, 133, 98, 47);
        add(lblID);
        
        JLabel lblCourseName = new JLabel("Course name");
        lblCourseName.setFont(new Font("Arial", Font.BOLD, 15));
        lblCourseName.setBounds(10, 186, 99, 47);
        add(lblCourseName);
        
        JLabel lblStartingDay = new JLabel("Starting Date");
        lblStartingDay.setFont(new Font("Arial", Font.BOLD, 15));
        lblStartingDay.setBounds(9, 243, 99, 47);
        add(lblStartingDay);
        
        JLabel lblEndingDate = new JLabel("Ending Date");
        lblEndingDate.setFont(new Font("Arial", Font.BOLD, 15));
        lblEndingDate.setBounds(10, 299, 99, 47);
        add(lblEndingDate);
        
        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("Arial", Font.BOLD, 15));
        lblTime.setBounds(10, 356, 99, 47);
        add(lblTime);
        
        JLabel textID = new JLabel("");
        textID.setFont(new Font("Arial", Font.PLAIN, 15));
        textID.setBounds(118, 133, 178, 47);
        add(textID);
        
        JLabel textName = new JLabel("");
        textName.setFont(new Font("Arial", Font.PLAIN, 15));
        textName.setBounds(118, 186, 178, 47);
        add(textName);
        
        JLabel textSDate = new JLabel("");
        textSDate.setFont(new Font("Arial", Font.PLAIN, 15));
        textSDate.setBounds(118, 243, 178, 47);
        add(textSDate);
        
        JLabel textEDate = new JLabel("");
        textEDate.setFont(new Font("Arial", Font.PLAIN, 15));
        textEDate.setBounds(119, 299, 178, 47);
        add(textEDate);
        
        JLabel textTime = new JLabel("");
        textTime.setFont(new Font("Arial", Font.PLAIN, 15));
        textTime.setBounds(118, 356, 178, 47);
        add(textTime);
        ScrollPane = new JScrollPane();
        ScrollPane.setSize(880, 518);
        ScrollPane.setLocation(320, 132);
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
        model = new DefaultTableModel() {
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
        		textID.setText(table.getValueAt(index, 0).toString());
        		textName.setText(table.getValueAt(index, 1).toString());
        		textSDate.setText(table.getValueAt(index, 2).toString());
        		textEDate.setText(table.getValueAt(index, 3).toString());
        		textTime.setText(table.getValueAt(index, 4).toString());
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
        lblRefresh.setIcon(new ImageIcon(ShowTeachingSchedule.class.getResource("/icon/reload.png")));
        lblRefresh.setBounds(1168, 99, 32, 32);
        add(lblRefresh);
        
        JLabel lblNewLabel = new JLabel("Search course by ID");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 20));
        lblNewLabel.setBounds(320, 87, 198, 44);
        add(lblNewLabel);
        
        textIDSearch = new JTextField();
        textIDSearch.setFont(new Font("Arial", Font.PLAIN, 20));
        textIDSearch.setBounds(517, 84, 198, 47);
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
        SearchIcon.setIcon(new ImageIcon(ShowTeachingSchedule.class.getResource("/icon/search.png")));
        SearchIcon.setBounds(712, 87, 56, 47);
        add(SearchIcon);        
        JLabel lblNotifi = new JLabel("");
        lblNotifi.setFont(new Font("Arial", Font.BOLD, 15));
        lblNotifi.setForeground(Color.red);
        lblNotifi.setBounds(10, 471, 301, 30);
        add(lblNotifi);
        

		
        JButton ClassManagePage = new JButton("Show Class Students");       
        ClassManagePage.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		int index = table.getSelectedRow();
        		if(index==-1) {
        			lblNotifi.setText("No class was chosen!");
        		}
        		else { 
        			String ClassID = textID.getText();
        			Container classManage;
					try {
						classManage = new ClassManagement(ServerConnection);
						ContentPane.add("ClassForm", classManage);
						CardLayout cl = (CardLayout)(ContentPane.getLayout());
						cl.show(ContentPane, "ClassForm");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
  	             			
        		}
        	}
        });
        ClassManagePage.setFont(new Font("Arial", Font.BOLD, 15));
        ClassManagePage.setBounds(10, 416, 198, 54);
        add(ClassManagePage);
        
        
        
        

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
        		textID.setText("");
        		textName.setText("");
        		textSDate.setText("");
        		textEDate.setText("");
        		textTime.setText("");
        		table.clearSelection();
			}
		});
	}
	
}
