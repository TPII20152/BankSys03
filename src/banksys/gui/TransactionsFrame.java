package banksys.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import banksys.control.BankController;
import banksys.control.exception.BankTransactionException;

import javax.swing.JButton;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.awt.Color;

public class TransactionsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel transactionsPane;
	private ExitAction exitAction;
	private DoCreditAction doCreditAction;
	private DoDebitAction doDebitAction;
	private DoTransferAction doTransferAction;
	private ShowBalanceAction showBalanceAction;
	private RemoveAccountAction removeAccountAction;
	private EarnInterestAction earnInterestAction;
	private EarnBonusAction earnBonusAction;
	
	private BankController bank;
	private String number;

	public TransactionsFrame(BankController bank, String number) {
		this.bank = bank;
		this.number = number;
		
		setTitle("Transactions");
		setBackground(Color.ORANGE);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 320);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		initialize();
	}
	
	public void initialize() {
		exitAction = new ExitAction();
		doCreditAction = new DoCreditAction();
		doDebitAction = new DoDebitAction();
		doTransferAction = new DoTransferAction();
		showBalanceAction = new ShowBalanceAction();
		removeAccountAction = new RemoveAccountAction();
		earnInterestAction = new EarnInterestAction();
		earnBonusAction = new EarnBonusAction();
		
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
		transactionsPane = new JPanel();
		transactionsPane.setBackground(Color.ORANGE);
		transactionsPane.setLayout(new BoxLayout(transactionsPane, BoxLayout.Y_AXIS));
		
		JLabel cuteImage = new JLabel();
		cuteImage.setBackground(Color.WHITE);
		cuteImage.setHorizontalAlignment(SwingConstants.CENTER);
		cuteImage.setIcon(new ImageIcon(LoginFrame.class.getResource("/banksys/icon/icon4.png")));
		
		contentPane.add(cuteImage);
		
		JButton doCreditButton = new JButton("Do Credit");
		doCreditButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsPane.add(doCreditButton);
		
		doCreditButton.addActionListener(doCreditAction);
		
		Component rigidArea1 = Box.createRigidArea(new Dimension(5, 5));
		transactionsPane.add(rigidArea1);
		
		JButton doDebitButton = new JButton("Do Debit");
		doDebitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsPane.add(doDebitButton);
		
		doDebitButton.addActionListener(doDebitAction);
		
		Component rigidArea2 = Box.createRigidArea(new Dimension(5, 5));
		transactionsPane.add(rigidArea2);
		
		JButton doTransferButton = new JButton("Do Transfer");
		doTransferButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsPane.add(doTransferButton);
		
		doTransferButton.addActionListener(doTransferAction);
		
		Component rigidArea3 = Box.createRigidArea(new Dimension(5, 5));
		transactionsPane.add(rigidArea3);
		
		JButton showBalanceButton = new JButton("Show Balance");
		showBalanceButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsPane.add(showBalanceButton);
		
		showBalanceButton.addActionListener(showBalanceAction);
		
		Component rigidArea4 = Box.createRigidArea(new Dimension(5, 5));
		transactionsPane.add(rigidArea4);
		
		JButton removeAccountButton = new JButton("Remove Account");
		removeAccountButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsPane.add(removeAccountButton);
		
		removeAccountButton.addActionListener(removeAccountAction);
		
		Component rigidArea5 = Box.createRigidArea(new Dimension(5, 5));
		transactionsPane.add(rigidArea5);
		
		JButton earnInterestButton = new JButton("Earn Interest");
		earnInterestButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsPane.add(earnInterestButton);
		
		earnInterestButton.addActionListener(earnInterestAction);
		
		Component rigidArea6 = Box.createRigidArea(new Dimension(5, 5));
		transactionsPane.add(rigidArea6);
		
		JButton earnBonusButton = new JButton("Earn Bonus");
		earnBonusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsPane.add(earnBonusButton);
		
		earnBonusButton.addActionListener(earnBonusAction);
		
		Component rigidArea7 = Box.createRigidArea(new Dimension(5, 5));
		transactionsPane.add(rigidArea7);
		
		JButton exitButton = new JButton("Exit");
		exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		transactionsPane.add(exitButton);
		
		exitButton.addActionListener(exitAction);
		
		contentPane.add(transactionsPane);
	}
	
	private class ExitAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
		
	}
	
	private class DoCreditAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DoCreditFrame doCreditFrame = new DoCreditFrame(bank, number);
			doCreditFrame.setVisible(true);
		}
		
	}
	
	private class DoDebitAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DoDebitFrame doDebitFrame = new DoDebitFrame(bank, number);
			doDebitFrame.setVisible(true);
		}
		
	}
	
	private class DoTransferAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			DoTransferFrame doTransferFrame = new DoTransferFrame(bank, number);
			doTransferFrame.setVisible(true);
		}
		
	}
	
	private class ShowBalanceAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				JOptionPane.showMessageDialog(null, "Balance of account " + number + ": " + bank.getBalance(number), "Balance", JOptionPane.PLAIN_MESSAGE);
			} catch (BankTransactionException bte) {
				JOptionPane.showMessageDialog(null, "Error: " + bte.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private class RemoveAccountAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				int n = JOptionPane.showConfirmDialog(null, "Are you sure?", "Remove Account", JOptionPane.YES_NO_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					bank.removeAccount(number);
					JOptionPane.showMessageDialog(null, "Operation was successful!\nAccount number " + number + " was removed.", "Success", JOptionPane.PLAIN_MESSAGE);
					setVisible(false);
				}
			} catch (BankTransactionException | IOException bte) {
				JOptionPane.showMessageDialog(null, "Error: " + bte.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private class EarnInterestAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				bank.doEarnInterest(number);
				JOptionPane.showMessageDialog(null, "Operation was successful!", "Success", JOptionPane.PLAIN_MESSAGE);
			} catch (BankTransactionException | FileNotFoundException bte) {
				JOptionPane.showMessageDialog(null, "Error: " + bte.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
	
	private class EarnBonusAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				bank.doEarnBonus(number);
				JOptionPane.showMessageDialog(null, "Operation was successful!", "Success", JOptionPane.PLAIN_MESSAGE);
			} catch (BankTransactionException | FileNotFoundException bte) {
				JOptionPane.showMessageDialog(null, "Error: " + bte.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}

}
