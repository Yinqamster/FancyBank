package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Utils.Config;
import Utils.ErrCode;
import controller.BankController;
import controller.UserController;
import model.Loan;

import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.border.LineBorder;

public class UserInterface extends JFrame{
	
	UserController userController = UserController.getInstance();
	
	public UserInterface(String username) {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		
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
		contentPanel.add(back);
		
		JButton logout = new JButton("");
		logout.setIcon(new ImageIcon("./src/logout.png"));
		logout.setBounds(452, 6, 35, 35);
		contentPanel.add(logout);
		
		JPanel namePanel = new JPanel();
		namePanel.setBounds(50, 165, 400, 20);
		namePanel.setLayout(new GridLayout(1, 2, 5, 5));
		JLabel uname = new JLabel("Username: " + username);
		uname.setFont(new Font("Helvetica", Font.PLAIN, 15));
		JLabel tname = new JLabel("True Name: " + userController.getTruenameByUsername(username));
		tname.setFont(new Font("Helvetica", Font.PLAIN, 15));
		namePanel.add(uname);
		namePanel.add(tname);
		

		List<String> checkingAccounts = userController.getAccountList(username, Config.CHECKINGACCOUNT);
		List<String> savingAccounts = userController.getAccountList(username, Config.SAVINGACCOUNT);
		
		System.out.println("c size: " + checkingAccounts.size());
		System.out.println("s size: " + savingAccounts.size());
		
		boolean hasOperation = savingAccounts.size()==0&&checkingAccounts.size()==0 ? false : true;
		int rows = savingAccounts.size() + checkingAccounts.size();
		rows += hasOperation ? 4 : 2;
		int panelHeight = 25 * rows;
		
		
		JPanel accountPanel = new JPanel();
		accountPanel.setBounds(50, 190, 400, panelHeight);
		accountPanel.setLayout(new GridLayout(rows, 2, 5, 5));
		accountPanel.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));  
		
		JLabel cacc = new JLabel("Checking Account:");
		cacc.setFont(new Font("Helvetica", Font.PLAIN, 15));
		JButton createCacc = new JButton("Create");
		accountPanel.add(cacc);
		accountPanel.add(createCacc);
		
		for(int i = 0; i < checkingAccounts.size(); i++) {
			JLabel accNumL = new JLabel("Account number: ");
			JLabel accNum = new JLabel(checkingAccounts.get(i));
			accountPanel.add(accNumL);
			accountPanel.add(accNum);
		}
		
		JLabel sacc = new JLabel("Saving Account:");
		sacc.setFont(new Font("Helvetica", Font.PLAIN, 15));
		JButton createSacc = new JButton("Create");
		accountPanel.add(sacc);
		accountPanel.add(createSacc);
		
		for(int i = 0; i < savingAccounts.size(); i++) {
			JLabel accNumL = new JLabel("Account number: ");
			JLabel accNum = new JLabel(String.valueOf(savingAccounts.get(i)));
			accountPanel.add(accNumL);
			accountPanel.add(accNum);
		}
		
		JButton deposit = new JButton("Deposit");
		JButton withdraw = new JButton("Withdraw");
		JButton transfer = new JButton("Transfer");
		JButton showDetail = new JButton("Show Details");
		if(hasOperation) {
			accountPanel.add(deposit);
			accountPanel.add(withdraw);
			accountPanel.add(transfer);
			accountPanel.add(showDetail);
		}
		
		
		List<Loan> loanList = userController.getLoanList(username);
		boolean hasLoan = loanList.size() == 0 ? false : true;
		int loanRows = hasLoan ? 2 + loanList.size() : 1;
		int loanPanelHeight = 25 * loanRows;
		int YBegin = accountPanel.getY()+accountPanel.getHeight() + 5;
		
		JPanel loanPanel = new JPanel();
		loanPanel.setBounds(50, YBegin, 400, loanPanelHeight);
		loanPanel.setLayout(new GridLayout(loanRows, 2, 5, 5));
		loanPanel.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));  
		
		JLabel LoanLabel = new JLabel("Loan");
		LoanLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
		JButton takeLoan = new JButton("Take Loan");
		loanPanel.add(LoanLabel);
		loanPanel.add(takeLoan);
		
		JLabel loanName = new JLabel("Name");
		JLabel loanNumber = new JLabel("Number");
		
		if(hasLoan){
			loanPanel.add(loanName);
			loanPanel.add(loanNumber);
			for(int i = 0; i < loanList.size(); i++) {
				JLabel na = new JLabel(loanList.get(i).getName());
				JLabel nu = new JLabel(String.valueOf(loanList.get(i).getNumber()));
				loanPanel.add(na);
				loanPanel.add(nu);
			}
		}
		
		
		
		contentPanel.add(namePanel);
		contentPanel.add(accountPanel);
		contentPanel.add(loanPanel);
		contentPanel.add(titlePanel);
		contentPanel.add(background);
		getContentPane().add(contentPanel);
		
		
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
		int totalWidth = 500;
		int totalHeight = loanPanel.getY() + loanPanel.getHeight() + 50;
//		int totalHeight = contentPanel.getHeight() + 20;
		totalHeight = totalHeight > 500 ? totalHeight : 500;
		int locationX = (int)screenSize.getWidth()/2 - totalWidth/2;
		int locationY = (int)screenSize.getHeight()/2 - totalHeight/2;
		
		this.setTitle( "Bank ATM User Interface" );
		this.setResizable(false);
		this.setSize(totalWidth, totalHeight);
		this.setLocation(locationX, locationY); 
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
		
		createCacc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userController.createAccount(username, Config.CHECKINGACCOUNT, Config.DEFAULTCURRENCY, BankController.getBank().getOpenAccountFee());
				UserInterface.this.dispose();
				new UserInterface(username);
			}
		});
		
		createSacc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				userController.createAccount(username, Config.SAVINGACCOUNT, Config.DEFAULTCURRENCY, BankController.getBank().getOpenAccountFee());
				UserInterface.this.dispose();
				new UserInterface(username);
			}
		});
		
		deposit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		withdraw.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		transfer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		showDetail.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		takeLoan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
