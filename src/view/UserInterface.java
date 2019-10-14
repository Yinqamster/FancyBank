package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utils.Config;
import Utils.ErrCode;
import controller.UserController;

public class UserInterface extends JFrame{
	
	UserController userController = UserController.getInstance();
	
	public UserInterface(String username) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel background = new JLabel();
		ImageIcon bg=new ImageIcon("./src/login_background.png");
		background.setIcon(bg);
		background.setBounds(0, 0, 500, 150);
		

		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("User   System");
		title.setFont(new Font("Helvetica",Font.PLAIN,35));
		titlePanel.add(title);
		titlePanel.setOpaque(false);
		titlePanel.setBounds(50, 80, 400, 50);
		
		JButton back = new JButton("");
		back.setIcon(new ImageIcon("./src/back.png"));
		back.setBounds(6, 6, 35, 35);
		panel.add(back);
		
		JButton logout = new JButton("");
		logout.setIcon(new ImageIcon("./src/logout.png"));
		logout.setBounds(452, 6, 35, 35);
		panel.add(logout);

		
		
		panel.add(titlePanel);
		panel.add(background);
		this.add(panel);
		
		this.setTitle( "Bank ATM Login" );
		this.setResizable(false);
		this.setSize(500, 500);
		this.setLocation(500, 500); 
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE); 
		this.setVisible( true );
		
		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UserInterface.this.dispose();
				new Login(Config.USER);
			}
		});
		
		logout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int res = userController.logout(username);
				if(res == ErrCode.OK) {
					new Login(Config.USER);
				}
				else {
					//this shouldn't happen
					System.out.println("Logout Error");
				}
			}
		});
	}
}
