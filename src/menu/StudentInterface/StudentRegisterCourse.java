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
		ResultSet data = ServerConnection.ExecuteQuery("select * from course");
		Container c = this;
		JScrollPane s = new JScrollPane();
		
		s.getVerticalScrollBar().setUI(new MyScrollBarUI());
		CourseRegisterTable table = new CourseRegisterTable();
		s.setViewportView(table);
		s.setBounds(50, 100, 400, 350);
		c.add(s);
		while(data.next()) {
			table.addItem(data.getString("courseID"), data.getString("name"));
		}
        
		
	}
}

class CourseRegisterTable extends JTable{
	DefaultTableModel model;
	Object[] column = {"ID", "Subject", ""};
	private Image check = new ImageIcon(LoginForm.class.getResource("/icon/check.png")).getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
	CourseRegisterTable() {
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
		getColumnModel().getColumn(0).setPreferredWidth(20);
		getColumnModel().getColumn(2).setMaxWidth(35);
		
		
	}
	void addItem(String data1, String data2) {
		JButton btn = new JButton();
		btn.setIcon(new ImageIcon(check));
		model.addRow(new Object[] {data1,data2,btn});
	}
}
class ButtonRenderer extends JButton implements TableCellRenderer {

public ButtonRenderer() {

setOpaque(true);

}

public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	
		JButton btn = (JButton) value;
		ImageIcon icon = (ImageIcon) btn.getIcon();
		setIcon(icon);
		setSize(20,20);
	    setBackground(new Color(163,255,166));
	     
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