package controller;

import javax.swing.JFrame;

import view.ChooseIdentity;

public class BankATM {

	public static void main(String args[]) {
//		JFrame frame = new JFrame("Bank ATM");
		BankController.createBank();
		ChooseIdentity cr = new ChooseIdentity();
//		frame.setTitle("Online Dictionary");
//		frame.setSize(500, 300);
//		frame.setLocationRelativeTo(null);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setVisible(true);
	}
}
