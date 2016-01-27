package banksys.gui;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import banksys.account.AbstractAccount;
import banksys.account.OrdinaryAccount;
import banksys.account.SavingsAccount;
import banksys.account.SpecialAccount;
import banksys.account.TaxAccount;
import banksys.control.BankController;
import banksys.control.exception.BankTransactionException;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import java.awt.FlowLayout;

public class AddNewAccountFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel titlePane;
	private JPanel newAccountPane;
	private JPanel optionsPane;
	private JPanel accountNumberPane;
	private JPanel buttonsPane;
	private JTextField accountNumberTextField;
	private JLabel titleLabel;
	private JButton okButton;
	private JButton cancelButton;
	private CancelAction cancelAction;
	private OkAction okAction;
	private ButtonGroup buttonGroup;
	private JRadioButton ordinaryRadioButton;
	private JRadioButton specialRadioButton;
	private JRadioButton savingsRadioButton;
	private JRadioButton taxRadioButton;
	
	private BankController bank;

	public AddNewAccountFrame(BankController bank) {
		this.bank = bank;
		
		cancelAction = new CancelAction();
		okAction = new OkAction();
		
		setBackground(Color.CYAN);
		setTitle("Add New Account");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 400, 450);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		titlePane = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) titlePane.getLayout();
		flowLayout_2.setVgap(15);
		titlePane.setBackground(Color.WHITE);
		newAccountPane = new JPanel();
		newAccountPane.setBackground(Color.WHITE);
		FlowLayout flowLayout = (FlowLayout) newAccountPane.getLayout();
		flowLayout.setVgap(30);
		flowLayout.setHgap(30);
		optionsPane = new JPanel();
		accountNumberPane = new JPanel();
		buttonsPane = new JPanel();
		buttonsPane.setBackground(Color.WHITE);
		FlowLayout flowLayout_1 = (FlowLayout) buttonsPane.getLayout();
		flowLayout_1.setVgap(15);
		flowLayout_1.setHgap(20);
		
		okButton = new JButton("OK");
		buttonsPane.add(okButton);
		
		okButton.addActionListener(okAction);
		
		cancelButton = new JButton("Cancel");
		buttonsPane.add(cancelButton);
		
		cancelButton.addActionListener(cancelAction);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		initialize();
	}
	
	private void initialize() {		
		buttonGroup = new ButtonGroup();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		titleLabel = new JLabel("Add New Account");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		titlePane.add(titleLabel);
		
		contentPane.add(titlePane, BorderLayout.NORTH);
				
		JLabel cuteImage = new JLabel();
		cuteImage.setIcon(new ImageIcon(LoginFrame.class.getResource("/banksys/icon/icon2.png")));
		
		newAccountPane.add(cuteImage);
		
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.Y_AXIS));
		
		ordinaryRadioButton = new JRadioButton("Ordinary");
		ordinaryRadioButton.setMnemonic(KeyEvent.VK_O);
		optionsPane.add(ordinaryRadioButton);
		
		specialRadioButton = new JRadioButton("Special");
		specialRadioButton.setMnemonic(KeyEvent.VK_P);
		optionsPane.add(specialRadioButton);
		
		savingsRadioButton = new JRadioButton("Savings");
		savingsRadioButton.setMnemonic(KeyEvent.VK_A);
		optionsPane.add(savingsRadioButton);
		
		taxRadioButton = new JRadioButton("Tax");
		taxRadioButton.setMnemonic(KeyEvent.VK_T);
		optionsPane.add(taxRadioButton);
		
		buttonGroup.add(ordinaryRadioButton);
		buttonGroup.add(specialRadioButton);
		buttonGroup.add(savingsRadioButton);
		buttonGroup.add(taxRadioButton);
		
		newAccountPane.add(optionsPane);
							
		JLabel accountNumberLabel = new JLabel("Enter the account number");
		accountNumberPane.add(accountNumberLabel);
		
		accountNumberTextField = new JTextField();
		accountNumberPane.add(accountNumberTextField);
		accountNumberTextField.setColumns(10);
		
		newAccountPane.add(accountNumberPane);
		
		contentPane.add(newAccountPane, BorderLayout.CENTER);
		
		contentPane.add(buttonsPane, BorderLayout.SOUTH);
	}

	private class CancelAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			setVisible(false);
		}
		
	}
	
	private class OkAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			AbstractAccount account;
			
			if ((buttonGroup.getSelection() == null) || (accountNumberTextField.getText().isEmpty())) {
				JOptionPane.showMessageDialog(null, "You need to fill all the fields.", "Warning", JOptionPane.WARNING_MESSAGE);	
			}
			else {
				switch (buttonGroup.getSelection().getMnemonic()) {
				case (KeyEvent.VK_O):
				{
					account = new OrdinaryAccount(accountNumberTextField.getText());
					
					try {
						bank.addAccount(account);
						JOptionPane.showMessageDialog(null, "Operation was successful!\nOrdinary account number " + accountNumberTextField.getText() + " was registed.", "Success", JOptionPane.PLAIN_MESSAGE);
					} catch (BankTransactionException bte) {
						JOptionPane.showMessageDialog(null, "Error: " + bte.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
					}
				}
				break;
				
				case (KeyEvent.VK_P):
				{
					account = new SpecialAccount(accountNumberTextField.getText());
					
					try {
						bank.addAccount(account);
						JOptionPane.showMessageDialog(null, "Operation was successful!\nSpecial account number " + accountNumberTextField.getText() + " was registed.", "Success", JOptionPane.PLAIN_MESSAGE);
					} catch (BankTransactionException bte) {
						JOptionPane.showMessageDialog(null, "Error: " + bte.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
					}
				}
				break;
				
				case (KeyEvent.VK_A):
				{
					account = new SavingsAccount(accountNumberTextField.getText());
					
					try {
						bank.addAccount(account);
						JOptionPane.showMessageDialog(null, "Operation was successful!\nSavings account number " + accountNumberTextField.getText() + " was registed.", "Success", JOptionPane.PLAIN_MESSAGE);
					} catch (BankTransactionException bte) {
						JOptionPane.showMessageDialog(null, "Error: " + bte.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
					}
				}
				break;
				
				case (KeyEvent.VK_T):
				{
					account = new TaxAccount(accountNumberTextField.getText());
					
					try {
						bank.addAccount(account);
						JOptionPane.showMessageDialog(null, "Operation was successful!\nTax account number " + accountNumberTextField.getText() + " was registed.", "Success", JOptionPane.PLAIN_MESSAGE);
					} catch (BankTransactionException bte) {
						JOptionPane.showMessageDialog(null, "Error: " + bte.getMessage(), "Error", JOptionPane.PLAIN_MESSAGE);
					}
				}
				break;
				}
			}
		}
		
	}
}
