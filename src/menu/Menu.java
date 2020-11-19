package menu;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class Menu extends JFrame implements ActionListener{
	JButton info;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public Menu() {
		setResizable(false);
		getContentPane().setLayout(null);
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		
		
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0,0,1200,150);
		panel.setLayout(new BorderLayout());
		
		JPanel container = new JPanel();
		container.setBackground(SystemColor.inactiveCaption);
		container.setBounds(0,150,1200,1200-150);
		
		JPanel content = new JPanel();
		content.setLayout(new GridLayout(2,2,10,10));
		content.setBackground(SystemColor.inactiveCaption);
		content.setBounds(400,200,400,400);
		
	
		getContentPane().add(content);
		getContentPane().add(panel);
		getContentPane().add(container);
		
		JLabel greeting = new JLabel("Chào, Nguyễn Văn Hoàng");
		panel.add(greeting);
		greeting.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 20));
		greeting.setForeground(SystemColor.textHighlightText);
		greeting.setVerticalAlignment(JLabel.BOTTOM);
		greeting.setHorizontalAlignment(JLabel.LEFT);
		
		
		
		info = new JButton("Edit Information");
		info.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		info.setIcon(new ImageIcon(Menu.class.getResource("/icon/folder.png")));
		info.setHorizontalTextPosition(JLabel.CENTER);
		info.setVerticalTextPosition(JLabel.BOTTOM);
		info.setVerticalAlignment(JLabel.CENTER);
		info.setHorizontalAlignment(JLabel.CENTER);
		info.setBounds(450,100,150,100);
		info.setBackground(new Color(191,205,219));
		info.setFocusable(false);
		info.setBorder(null);
		info.addActionListener(this);
		
		JButton register = new JButton("Register courses");
		register.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		register.setIcon(new ImageIcon(Menu.class.getResource("/icon/tick.png")));
		register.setHorizontalTextPosition(JLabel.CENTER);
		register.setVerticalTextPosition(JLabel.BOTTOM);
		register.setVerticalAlignment(JLabel.CENTER);
		register.setHorizontalAlignment(JLabel.CENTER);
		register.setBounds(600,100,150,100);
		register.setBackground(new Color(191,205,219));
		register.setFocusable(false);
		register.setBorder(null);
		
		JButton schedule = new JButton("Show schedule");
		schedule.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		schedule.setIcon(new ImageIcon(Menu.class.getResource("/icon/calendar.png")));
		schedule.setHorizontalTextPosition(JLabel.CENTER);
		schedule.setVerticalTextPosition(JLabel.BOTTOM);
		schedule.setVerticalAlignment(JLabel.CENTER);
		schedule.setHorizontalAlignment(JLabel.CENTER);
		schedule.setBounds(450,200,150,100);
		schedule.setBackground(new Color(191,205,219));
		schedule.setFocusable(false);
		schedule.setBorder(null);
		
		JButton pass = new JButton("Change password");
		pass.setFont(new Font("Sitka Text", Font.PLAIN, 15));
		pass.setIcon(new ImageIcon(Menu.class.getResource("/icon/cogwheel.png")));
		pass.setHorizontalTextPosition(JLabel.CENTER);
		pass.setVerticalTextPosition(JLabel.BOTTOM);
		pass.setVerticalAlignment(JLabel.CENTER);
		pass.setHorizontalAlignment(JLabel.CENTER);
		pass.setBounds(650,200,150,100);
		pass.setBackground(new Color(191,205,219));
		pass.setFocusable(false);
		pass.setBorder(null);
		
		content.add(info);
		content.add(register);
		content.add(schedule);
		content.add(pass);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == info) {
			Info window = new Info();
			window.setVisible(true);
			
		}
	}

}
