package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Utils.Config;

public class ChooseIdentity extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChooseIdentity () {
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
//		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER,100,20));
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel imagePanel = new JPanel();
		JLabel image = new JLabel();
		ImageIcon img = new ImageIcon("./src/bank.png");
		image.setIcon(img);
		imagePanel.add(image);

		JPanel welcomePanel = new JPanel();
		JLabel welcome = new JLabel("Welcome to my bank!");
		welcome.setFont(new Font("Helvetica",Font.PLAIN,35));
		welcomePanel.add(welcome);
		
		JPanel markWordsPanel = new JPanel();
		JLabel markWords = new JLabel("Please choose your identity:");
		markWords.setFont(new Font("Helvetica",Font.PLAIN,30));
		markWordsPanel.add(markWords);
		
		JPanel buttenPanel = new JPanel();
		GridLayout gl_buttenPanel = new GridLayout(1,2);
		gl_buttenPanel.setVgap(50);
		gl_buttenPanel.setHgap(10);
		buttenPanel.setLayout(gl_buttenPanel);
		JButton user = new JButton("User");
		user.setFont(new Font("Helvetica",Font.PLAIN,30));
		JButton manager = new JButton("Manager");
		manager.setFont(new Font("Helvetica",Font.PLAIN,30));
		buttenPanel.add(user);
		buttenPanel.add(manager);
		
		panel.add(imagePanel);
		panel.add(welcomePanel);
		panel.add(markWordsPanel);
		panel.add(buttenPanel);

		
		getContentPane().add(panel);
		this.setTitle( "Bank ATM" );
		this.setSize(500, 500);
		this.setLocation(500, 500); 
		this.setResizable(false);
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE); 
		this.setVisible( true );
		
		user.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChooseIdentity.this.dispose();
				new Login(Config.USER);
				
			}
		});
		
		manager.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ChooseIdentity.this.dispose();
				new Login(Config.MANAGER);
			}
		});
	}
}
