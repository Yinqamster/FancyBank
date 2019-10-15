package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Utils.Config;
import Utils.ErrCode;
import controller.BankController;
import controller.UserController;
import model.Transaction;

public class AccountDetail extends JFrame{
	public UserController userController = UserController.getInstance();
	public BankController managerController = BankController.getInstance();

	public AccountDetail(String username) {
		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(null);
		
		JLabel background = new JLabel();
		ImageIcon bg=new ImageIcon("./src/login_background.png");
		background.setIcon(bg);
		background.setBounds(0, 0, 500, 150);

		JPanel titlePanel = new JPanel();
		JLabel title = new JLabel("Account  Detail");
		title.setFont(new Font("Helvetica",Font.PLAIN,35));
		titlePanel.add(title);
		titlePanel.setOpaque(false);
		titlePanel.setBounds(50, 80, 400, 50);
		
		int infoPanelRows = 3;
		JPanel infoPanel = new JPanel();
		infoPanel.setBounds(40, 180, 400, 25*infoPanelRows);
		infoPanel.setLayout(new GridLayout(infoPanelRows, 2, 10, 5));
		
		JLabel uname = new JLabel("Username: " + username);
		uname.setFont(new Font("Helvetica", Font.PLAIN, 15));
		JLabel tname = new JLabel("True Name: " + userController.getTruenameByUsername(username));
		tname.setFont(new Font("Helvetica", Font.PLAIN, 15));
		infoPanel.add(uname);
		infoPanel.add(tname);
		
		List<String> checkingAccounts = userController.getAccountList(username, Config.CHECKINGACCOUNT);
		List<String> savingAccounts = userController.getAccountList(username, Config.SAVINGACCOUNT);
		
		JLabel account = new JLabel("Account*：");
//		account.setHorizontalAlignment(SwingConstants.RIGHT);
		account.setFont(new Font("Helvetica",Font.PLAIN,15));
		JComboBox<String> accountList = new JComboBox<String>();
		for(String c : checkingAccounts) {
			accountList.addItem(c);
		}
		for(String c : savingAccounts) {
			accountList.addItem(c);
		}
		infoPanel.add(account);
		infoPanel.add(accountList);
		
		JLabel accountType = new JLabel("Account Type:");
//		accountType.setHorizontalAlignment(SwingConstants.RIGHT);
		accountType.setFont(new Font("Helvetica", Font.PLAIN, 15));
		int aType = userController.getAccountDetail(username, accountList.getSelectedItem().toString()).getAccountType();
		JLabel aTypeLabel = new JLabel();
		if(aType == Config.CHECKINGACCOUNT) {
			aTypeLabel.setText("Checking Account");
		}
		else if(aType == Config.SAVINGACCOUNT) {
			aTypeLabel.setText("Saving Account");
		}
		aTypeLabel.setFont(new Font("Helvetica", Font.PLAIN, 15));
		infoPanel.add(accountType);
		infoPanel.add(aTypeLabel);
		
		
		
		Map<String, BigDecimal> balanceList = userController.getAccountDetail(username, accountList.getSelectedItem().toString()).getBalance();
		int balancePanelRows = balanceList.size() == 0 ? 1 : balanceList.size() + 2;
		JPanel balancePanel = new JPanel();
		balancePanel.setBounds(40, infoPanel.getY()+infoPanel.getHeight()+20, 400, 25*balancePanelRows);
		balancePanel.setLayout(new GridLayout(balancePanelRows, 2, 10, 5));
		balancePanel.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		JLabel balance = new JLabel("Balance: ");
		balance.setFont(new Font("Helvetica", Font.PLAIN, 15));
		JLabel emptyLabel = new JLabel();
		balancePanel.add(balance);
		balancePanel.add(emptyLabel);
		if(balanceList.size() != 0) {
			JLabel currency = new JLabel("Currency: ");
			currency.setFont(new Font("Helvetica", Font.PLAIN, 15));
			JLabel amount = new JLabel("Amount: ");
			amount.setFont(new Font("Helvetica", Font.PLAIN, 15));
			balancePanel.add(currency);
			balancePanel.add(amount);
			for(Map.Entry<String, BigDecimal> b : balanceList.entrySet()) {
				JLabel curr = new JLabel(b.getKey());
				JLabel num = new JLabel(String.valueOf(b.getValue()));
				balancePanel.add(curr);
				balancePanel.add(num);
			}
		}
		
		List<Transaction> transactionList = userController.getAccountDetail(username, accountList.getSelectedItem().toString()).getTransactionDetails();
		int transactionPanelRows = transactionList.size() == 0 ? 1 : transactionList.size() + 2;
		JPanel transactionPanel = new JPanel();
		transactionPanel.setBounds(40, balancePanel.getY()+balancePanel.getHeight()+20, 400, 25*balancePanelRows);
		transactionPanel.setLayout(new GridLayout(transactionPanelRows, 3, 10, 5));
		transactionPanel.setBorder(new LineBorder(Color.DARK_GRAY, 1, true));
		JLabel transaction = new JLabel("Transaction: ");
		transaction.setFont(new Font("Helvetica", Font.PLAIN, 15));
		transactionPanel.add(transaction);
		JLabel emptyLabel1 = new JLabel();
		JLabel emptyLabel2 = new JLabel();
		transactionPanel.add(emptyLabel1);
		transactionPanel.add(emptyLabel2);
		if(transactionList.size() != 0) {
			JLabel transactionType = new JLabel("Type: ");
			transactionType.setFont(new Font("Helvetica", Font.PLAIN, 15));
			JLabel transactionID = new JLabel("ID: ");
			transactionID.setFont(new Font("Helvetica", Font.PLAIN, 15));
			JLabel operation = new JLabel("Operation: ");
			operation.setFont(new Font("Helvetica", Font.PLAIN, 15));
			transactionPanel.add(transactionType);
			transactionPanel.add(transactionID);
			transactionPanel.add(operation);
			for(Transaction t : transactionList) {
				JLabel type = new JLabel(t.getTransactionTypeStr());
				JLabel id = new JLabel(t.getTransactionId());
				JButton view = new JButton("View Detail");
				transactionPanel.add(type);
				transactionPanel.add(id);
				transactionPanel.add(view);
				view.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						AccountDetail.this.dispose();
						new TransactionDetail();
					}
				});
			}
		}
		
		
		
		
		
		contentPanel.add(titlePanel);
		contentPanel.add(infoPanel);
		contentPanel.add(balancePanel);
		contentPanel.add(transactionPanel);
		contentPanel.add(background);
		
		getContentPane().add(contentPanel);
		
		Dimension screenSize =Toolkit.getDefaultToolkit().getScreenSize();
		int totalWidth = 500;
		int totalHeight = transactionPanel.getY() + transactionPanel.getHeight() + 50;
		totalHeight = totalHeight > 500 ? totalHeight : 500;
		int locationX = (int)screenSize.getWidth()/2 - totalWidth/2;
		int locationY = (int)screenSize.getHeight()/2 - totalHeight/2;
		
		this.setTitle( "Bank ATM Register" );
		this.setResizable(false);
		this.setSize(totalWidth, totalHeight);
		this.setLocation(locationX, locationY); 
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE); 
		this.setVisible( true );
		
		accountList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				if(transactionType == Config.WITHDRAW || transactionType == Config.TRANSFEROUT) {
//					BigDecimal balan = userController.getAccountDetail(username, fromAccountList.getSelectedItem().toString()).getBalance().get(cur.getSelectedItem().toString());
//					bal.setText(String.valueOf(balan));
//					panel.revalidate();
//					panel.repaint();
//				}
			}
		});
		
	}
}
