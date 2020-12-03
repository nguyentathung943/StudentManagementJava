package menu.StudentInterface;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.sun.jdi.event.EventQueue;

import menu.LoginForm;
import menu.Server;

class StudentShowSchedule extends Container {
	

	StudentShowSchedule(String ClientID, Server ServerConnection) throws SQLException {
		String query = "select * from course_attend, course, teacher where StudentID ='" + ClientID + "' and course.courseID = course_attend.courseID and headTeacher = teacher.id";
		ResultSet data = ServerConnection.ExecuteQuery(query);
		Container c = this;
		
		JScrollPane s = new JScrollPane();
		s.getVerticalScrollBar().setUI(new MyScrollBarUI());
		
		AttendedCourseTable table = new AttendedCourseTable(ClientID);
		s.setViewportView(table);
		s.setBounds(50, 50, 800, 350);
		c.add(s);
		while(data.next()) {
			table.addItem(data.getString("course.courseID"), data.getString("course.name"), data.getString("teacher.name"), data.getString("startDate"),data.getString("endDate"),data.getString("time"));
		}
		
        ScoreTable scoreTable = new ScoreTable();
        c.add(scoreTable);
        table.setScoreTable(scoreTable);
        addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
        		table.clearSelection();
        		scoreTable.setVisible(false);
			}
		});
		
	}
}

class AttendedCourseTable extends JTable{
	String ClientID;
	ScoreTable scoreTable;
	DefaultTableModel model;
	Object[] column = {"ID", "Subject", "TeacherName","Start","End","Time"};
	AttendedCourseTable(String ClientID) {
		JTable table = this;
		model = new DefaultTableModel() {
    		@Override
    	    public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    	}};
    	getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
        getTableHeader().setBackground(new Color(0,153,153));
        getTableHeader().setForeground(Color.white);
        TableCellRenderer baseRenderer = getTableHeader().getDefaultRenderer();
        getTableHeader().setDefaultRenderer(new TableHeaderRenderer(baseRenderer));
        
        setFont(new Font("Arial",Font.BOLD,16));
    	setGridColor(new Color(255, 255, 255));
		model.setColumnIdentifiers(column);
		setModel(model);
		setRowHeight(35);
		
		getColumnModel().getColumn(0).setMaxWidth(60);
		getColumnModel().getColumn(0).setMinWidth(60);
		
		getColumnModel().getColumn(3).setMaxWidth(110);
		getColumnModel().getColumn(3).setMinWidth(110);

		getColumnModel().getColumn(4).setMaxWidth(110);
		getColumnModel().getColumn(4).setMinWidth(110);
		
		getColumnModel().getColumn(5).setMaxWidth(120);
		getColumnModel().getColumn(5).setMinWidth(120);
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int column = table.getColumnModel().getColumnIndexAtX(e.getX());
	            int row    = e.getY()/table.getRowHeight();

	                    /*Checking the row or column is valid or not*/
	            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
	                Server ServerConnection;
	                String courseID = (String) table.getValueAt(row, 0);
					try {
						ServerConnection = new Server();
						String query = "select * from course_attend where StudentID ='" + ClientID + "' and courseID ='"+courseID+"'";
		        		ResultSet data = ServerConnection.ExecuteQuery(query);
		        		if (data.next()) {
		        			scoreTable.setValue((String)table.getValueAt(row, 1), data.getString("practice_point"), data.getString("theory_point"), data.getString("overall"), data.getString("pass_status"));
		        		}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	                
	            }
	            else {
	            	scoreTable.setVisible(false);
	            }
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	void setScoreTable(ScoreTable s) {
		scoreTable = s;
	}
	void addItem(String courseID, String courseName, String teacherName, String start, String end, String time) {
		model.addRow(new Object[] {courseID,courseName,teacherName,start,end,time});
	}
}
class ScoreTable extends JPanel{
	JLabel courseHeader;
	JLabel practiceHeader;
	JLabel theoryHeader;
	JLabel overallHeader;
	JLabel statusHeader;
	
	JLabel courseText;
	JLabel practiceText;
	JLabel theoryText;
	JLabel overallText;
	JLabel statusText;
	ScoreTable() {
        setBounds(870, 50, 300, 350);
        setBackground(new Color(191,205,219));
        setLayout(null);
        
        courseHeader = new JLabel("Course :");
        courseHeader.setFont(new Font("Arial", Font.BOLD, 18));
        courseHeader.setBounds(0, 0, 100, 30);
        add(courseHeader);
        
        practiceHeader = new JLabel("Practice Score :");
        practiceHeader.setFont(new Font("Arial", Font.BOLD, 18));
        practiceHeader.setBounds(0, 60, 150, 30);
        add(practiceHeader);
        
        theoryHeader = new JLabel("Theory Score :");
        theoryHeader.setFont(new Font("Arial", Font.BOLD, 18));
        theoryHeader.setBounds(0, 120, 150, 30);
        add(theoryHeader);
        
        overallHeader = new JLabel("Overall :");
        overallHeader.setFont(new Font("Arial", Font.BOLD, 18));
        overallHeader.setBounds(0, 180, 150, 30);
        add(overallHeader);
        
        statusHeader = new JLabel("Status :");
        statusHeader.setFont(new Font("Arial", Font.BOLD, 18));
        statusHeader.setBounds(0, 240, 150, 30);
        add(statusHeader);
        
        courseText = new JLabel("None");
        courseText.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        courseText.setBounds(80, 0, 290, 30);
        add(courseText);
        
        practiceText = new JLabel("Not yet");
        practiceText.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        practiceText.setBounds(150, 60, 170, 30);
        add(practiceText);
        
        theoryText = new JLabel("Not yet");
        theoryText.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        theoryText.setBounds(150, 120, 170, 30);
        add(theoryText);
        
        overallText = new JLabel("Not yet");
        overallText.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        overallText.setBounds(150, 180, 170, 30);
        add(overallText);
        
        statusText = new JLabel("Not yet");
        statusText.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
        statusText.setBounds(150, 240, 170, 30);
        add(statusText);
        
        setVisible(false);
	}
	void setValue(String cText,String pText, String tText, String oText, String sText) {
		courseText.setText(cText);
		if (pText!= null)
			practiceText.setText(pText);
		else practiceText.setText("Not yet");
		
		if (tText != null)
			theoryText.setText(tText);
		else theoryText.setText("Not yet");
		
		if (oText!= null)
			overallText.setText(oText);
		else overallText.setText("Not yet");
		
		if (sText!= null)
			statusText.setText(sText);
		else statusText.setText("Not yet");
		
		setVisible(true);
	}
}