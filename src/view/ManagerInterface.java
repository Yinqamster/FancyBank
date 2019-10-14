package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utils.Config;
import controller.UserController;

import javax.swing.JButton;

public class ManagerInterface extends JFrame{
	
	

	public ManagerInterface(String username){
		JPanel panel = new JPanel();
		panel.setLayout(null);
		ImageIcon bg=new ImageIcon("./src/login_background.png");
		JLabel background = new JLabel();
		background.setIcon(bg);
		background.setBounds(0, 0, 500, 150);
		
		JButton back = new JButton("<");
		back.setBounds(6, 6, 42, 29);

		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Manager   System");
		title.setFont(new Font("Helvetica",Font.PLAIN,35));
		titlePanel.add(title);
		titlePanel.setOpaque(false);
		titlePanel.setBounds(50, 80, 400, 50);
		
		JPanel operationPanel = new JPanel(new GridLayout(3, 1, 100, 20));
		operationPanel.setSize(200, 200);
		operationPanel.setLocation(150, 200);
		
		JButton checkCustomer = new JButton("Check Customer");
		operationPanel.add(checkCustomer);
		JButton getDailyReport = new JButton("Get Daily Report");
		operationPanel.add(getDailyReport);
		JButton setConfig = new JButton("Set Config");
		operationPanel.add(setConfig);
		
		
		panel.add(titlePanel);
		panel.add(operationPanel);
//		getContentPane().add(panel);
		
		
		panel.add(back);
		panel.add(background);
		
		this.add(panel);
		
		this.setTitle( "Bank ATM Manager System" );
		this.setResizable(false);
		this.setSize(500, 500);
		this.setLocation(500, 500); 
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE); 
		this.setVisible( true );
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ManagerInterface.this.dispose();
				new Login(Config.MANAGER);
			}
		});
		
		checkCustomer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		getDailyReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		setConfig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
