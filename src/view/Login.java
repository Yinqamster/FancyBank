package view;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Utils.Config;
import Utils.ErrCode;
import controller.ManagerController;
import controller.UserController;

//import javafx.scene.shape.Box;

public class Login extends JFrame{
	
	public UserController userController = UserController.getInstance();
	public ManagerController managerController = ManagerController.getInstance();

	public Login(String identity) {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		JPanel panel = new JPanel();
		panel.setBounds(50, 200, 400, 175);
		panel.setLayout(new GridLayout(3, 2, 20, 15));
		
		JLabel background = new JLabel();
		ImageIcon bg=new ImageIcon("./src/login_background.png");
		background.setIcon(bg);
		background.setBounds(0, 0, 500, 150);
		
		JButton back = new JButton("<");
		back.setBounds(6, 6, 42, 29);

		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel(identity + "   Login");
		title.setFont(new Font("Helvetica",Font.PLAIN,35));
		titlePanel.add(title);
		titlePanel.setOpaque(false);
		titlePanel.setBounds(50, 80, 400, 50);
		
//		JPanel usernamePanel = new JPanel();
		JLabel username = new JLabel("Username:");
		username.setHorizontalAlignment(SwingConstants.RIGHT);
		username.setFont(new Font("Helvetica",Font.PLAIN,15));
		JTextField uname = new JTextField(10);
//		usernamePanel.add(username);
//		usernamePanel.add(uname);
////		usernamePanel.setOpaque(false);
//		usernamePanel.setBounds(40, 180, 400, 50);
		
//		JPanel passwordPanel = new JPanel();
		JLabel password = new JLabel("Password:");
		password.setHorizontalAlignment(SwingConstants.RIGHT);
		password.setFont(new Font("Helvetica",Font.PLAIN,15));
		JPasswordField pwd = new JPasswordField(10);
//		passwordPanel.add(password);
//		passwordPanel.add(pwd);
////		passwordPanel.setOpaque(false);
//		passwordPanel.setBounds(40, 250, 400, 50);
		
//		JPanel buttenPanel = new JPanel();
//		buttenPanel.setLayout(new BoxLayout(buttenPanel,BoxLayout.X_AXIS));
		JButton register = new JButton("Register");
		register.setFont(new Font("Helvetica",Font.PLAIN,15));
		JButton login = new JButton("Login");
		login.setFont(new Font("Helvetica",Font.PLAIN,15));
//		JButton back = new JButton("Back");
//		back.setFont(new Font("Helvetica",Font.PLAIN,15));
//		buttenPanel.add(register);
//		buttenPanel.add(login);
////		buttenPanel.add(back);
////		buttenPanel.setOpaque(false);
//		buttenPanel.setBounds(40, 320, 400, 50);
		panel.add(username);
		panel.add(uname);
//		panel.add(Box.createVerticalStrut(10));
		panel.add(password);
		panel.add(pwd);
//		panel.add(Box.createVerticalStrut(10));
		panel.add(register);
		panel.add(login);
		
		contentPanel.add(back);
		contentPanel.add(titlePanel);
		contentPanel.add(panel);
//		panel.add(Box.createVerticalStrut(10));
		

		contentPanel.add(background);
		getContentPane().add(contentPanel);
		
		this.setTitle( "Bank ATM Login" );
		this.setResizable(false);
		this.setSize(500, 500);
		this.setLocation(500, 500); 
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE); 
		this.setVisible( true );
		
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login.this.dispose();
				new Register(identity);
			}
		});
		
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String username = uname.getText();
				String password = pwd.getText();
				int res = -1;
				if(identity == Config.USER) {
					res = userController.login(username, password);
					if(res == ErrCode.OK) {
						Login.this.dispose();
						new UserInterface(username);
					}
					else {
						Object[] options = {"OK"};
				        JOptionPane.showOptionDialog(null,  
				                "Error Message", "Error",  
				                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null,   
				                options,   
				                options[0]); 
					}
					
				}
				else if(identity == Config.MANAGER) {
					res = managerController.login(username, password);
					if(res == ErrCode.OK) {
						Login.this.dispose();
						new ManagerInterface(username);
					}
					else {
						Object[] options = {"OK"};
				        JOptionPane.showOptionDialog(null,  
				                "Error Message", "Error",  
				                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, null,   
				                options,   
				                options[0]); 
					}
					
				}
			}
		});

		back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Login.this.dispose();
				new ChooseIdentity();
			}
		});
	}
}
