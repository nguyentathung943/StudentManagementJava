package menu.StudentInterface;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;

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

class StudentRegisterCourse extends Container {
	

	StudentRegisterCourse() throws SQLException {
		Server ServerConnection = new Server();
		ResultSet data = ServerConnection.ExecuteQuery("select * from course, teacher where id = headTeacher");
		Container c = this;
		
		JScrollPane s = new JScrollPane();
		s.getVerticalScrollBar().setUI(new MyScrollBarUI());
		
		CourseRegisterTable table = new CourseRegisterTable();
		s.setViewportView(table);
		s.setBounds(100, 50, 1000, 350);
		c.add(s);
		while(data.next()) {
			table.addItem(data.getString("courseID"), data.getString("course.name"), data.getString("teacher.name"), data.getString("startDate"),data.getString("endDate"),data.getString("time"));
		}
		
		JScrollPane s2 = new JScrollPane();
		s2.getVerticalScrollBar().setUI(new MyScrollBarUI());
		
		RegisteredTable rtable = new RegisteredTable();
		s2.setViewportView(rtable);
		s2.setBounds(100, 450, 1000, 150);
		c.add(s2);
		table.getTable(rtable);
		rtable.getTable(table);
        
		
	}
}

class CourseRegisterTable extends JTable{
	RegisteredTable t;
	
	DefaultTableModel model;
	Object[] column = {"ID", "Subject", "TeacherName","Start","End","Time",""};
	private Image check = new ImageIcon(LoginForm.class.getResource("/icon/check.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
	CourseRegisterTable() {
		JTable table = this;
		model = new DefaultTableModel() {
    		@Override
    	    public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    	}};
    	getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
        getTableHeader().setBackground(new Color(0,153,153));
        TableCellRenderer baseRenderer = getTableHeader().getDefaultRenderer();
        getTableHeader().setDefaultRenderer(new TableHeaderRenderer(baseRenderer));
        
        setFont(new Font("Arial",Font.BOLD,16));
    	setGridColor(new Color(255, 255, 255));
		model.setColumnIdentifiers(column);
		setModel(model);
		setRowHeight(35);
		getColumn("").setCellRenderer(new ButtonRenderer());
		
		getColumnModel().getColumn(0).setMaxWidth(60);
		getColumnModel().getColumn(0).setMinWidth(60);
		
		getColumnModel().getColumn(3).setMaxWidth(110);
		getColumnModel().getColumn(3).setMinWidth(110);

		getColumnModel().getColumn(4).setMaxWidth(110);
		getColumnModel().getColumn(4).setMinWidth(110);
		
		getColumnModel().getColumn(5).setMaxWidth(120);
		getColumnModel().getColumn(5).setMinWidth(120);
		getColumnModel().getColumn(6).setMaxWidth(35);
		getColumnModel().getColumn(6).setMinWidth(35);
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
	            int row    = e.getY()/table.getRowHeight(); //get the row of the button

	                    /*Checking the row or column is valid or not*/
	            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
	                Object value = table.getValueAt(row, column);
	                if (value instanceof JButton) {
	                    /*perform a click event*/
	                    ((JButton)value).doClick();
	                    model.removeRow(row);
	                }
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
				int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
	            int row    = e.getY()/table.getRowHeight(); //get the row of the button

	                    /*Checking the row or column is valid or not*/
	            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
	                Object value = table.getValueAt(row, column);
	                if (value instanceof JButton) {
	                	JButton btn = (JButton)value;
	                }
	            }
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
	}
	void getTable(RegisteredTable table) {
		t = table;
	}
	void addItem(String courseID, String courseName, String teacherName, String start, String end, String time) {
		JButton btn = new JButton();
		btn.setIcon(new ImageIcon(check));
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				t.addItem(courseID, courseName, teacherName, start, end, time);
			}
			
		});
		model.addRow(new Object[] {courseID,courseName,teacherName,start,end,time,btn});
	}
}

class RegisteredTable extends JTable{
	CourseRegisterTable t;
	DefaultTableModel model;
	Object[] column = {"ID", "Subject", "TeacherName","Start","End","Time",""};
	private Image check = new ImageIcon(LoginForm.class.getResource("/icon/trash-can.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
	RegisteredTable() {
		JTable table = this;
		model = new DefaultTableModel() {
    		@Override
    	    public boolean isCellEditable(int row, int column) {
        //all cells false
        return false;
    	}};
    	getTableHeader().setFont(new Font("Arial",Font.BOLD,20));
        getTableHeader().setBackground(new Color(0,153,153));
        TableCellRenderer baseRenderer = getTableHeader().getDefaultRenderer();
        getTableHeader().setDefaultRenderer(new TableHeaderRenderer(baseRenderer));
        
        setFont(new Font("Arial",Font.BOLD,16));
    	setGridColor(new Color(255, 255, 255));
		model.setColumnIdentifiers(column);
		setModel(model);
		setRowHeight(35);
		getColumn("").setCellRenderer(new ButtonRenderer(new Color(246,90,90)));
		
		getColumnModel().getColumn(0).setMaxWidth(60);
		getColumnModel().getColumn(0).setMinWidth(60);
		
		getColumnModel().getColumn(3).setMaxWidth(110);
		getColumnModel().getColumn(3).setMinWidth(110);

		getColumnModel().getColumn(4).setMaxWidth(110);
		getColumnModel().getColumn(4).setMinWidth(110);
		
		getColumnModel().getColumn(5).setMaxWidth(120);
		getColumnModel().getColumn(5).setMinWidth(120);
		getColumnModel().getColumn(6).setMaxWidth(35);
		getColumnModel().getColumn(6).setMinWidth(35);
		
		addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
	            int row    = e.getY()/table.getRowHeight(); //get the row of the button

	                    /*Checking the row or column is valid or not*/
	            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
	                Object value = table.getValueAt(row, column);
	                if (value instanceof JButton) {
	                    /*perform a click event*/
	                    ((JButton)value).doClick();
	                    model.removeRow(row);
	                }
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
				// TODO Auto-generated method stub
				int column = table.getColumnModel().getColumnIndexAtX(e.getX()); // get the coloum of the button
	            int row    = e.getY()/table.getRowHeight(); //get the row of the button

	                    /*Checking the row or column is valid or not*/
	            if (row < table.getRowCount() && row >= 0 && column < table.getColumnCount() && column >= 0) {
	                Object value = table.getValueAt(row, column);
	                if (value instanceof JButton) {
	                    /*perform a click event*/
	                    JButton btn = ((JButton)value);
	                }
	            }
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
	}
	void getTable(CourseRegisterTable table) {
		t = table;
	}
	void addItem(String courseID, String courseName, String teacherName, String start, String end, String time) {
		JButton btn = new JButton();
		btn.setIcon(new ImageIcon(check));
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				t.addItem(courseID, courseName, teacherName, start, end, time);
				
			}
			
		});
		model.addRow(new Object[] {courseID,courseName,teacherName,start,end,time,btn});
	}
}
class ButtonRenderer extends JButton implements TableCellRenderer {
	Color color = new Color(163,255,166);
	public ButtonRenderer(Color c) {
		color = c;
		setOpaque(true);
	}
	public ButtonRenderer() {
		setOpaque(true);
	}
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		
			JButton btn = (JButton) value;
			ImageIcon icon = (ImageIcon) btn.getIcon();
			setIcon(icon);
			setSize(20,20);
		    setBackground(color);
		    return this;
		
		}
}
class TableHeaderRenderer implements TableCellRenderer {

    private final TableCellRenderer baseRenderer;

    public TableHeaderRenderer(TableCellRenderer baseRenderer) {
        this.baseRenderer = baseRenderer;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent c = (JComponent)baseRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBorder(new EmptyBorder(2,2,2,2));
        return c;
    }
}