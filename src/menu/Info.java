package menu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Info extends JFrame {

	public Info() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(true);
		getContentPane().setLayout(null);
		getContentPane().setBackground(SystemColor.inactiveCaptionBorder);
		setSize(1200, 800);
		setVisible(false);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.activeCaption);
		panel.setBounds(0,0,1200,150);
		panel.setLayout(new BorderLayout());
		
		JPanel container = new JPanel();
		container.setBackground(SystemColor.inactiveCaption);
		container.setBounds(0,150,1200,1200-150);
		
		
		getContentPane().add(panel);
		getContentPane().add(container);
		   
		JLabel greeting = new JLabel("Chào, Nguyễn Văn Hoàng");
		panel.add(greeting);
		greeting.setFont(new Font("Source Sans Pro Semibold", Font.PLAIN, 20));
		greeting.setForeground(SystemColor.textHighlightText);
		greeting.setVerticalAlignment(JLabel.BOTTOM);
		greeting.setHorizontalAlignment(JLabel.LEFT);
		
	}

}
