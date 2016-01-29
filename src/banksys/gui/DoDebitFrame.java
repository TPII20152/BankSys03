package banksys.gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import banksys.control.BankController;
import banksys.control.exception.BankTransactionException;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

public class DoDebitFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;	
	private JTextField amountTextField;
	
	private OkAction okAction;
	
	BankController bank;
	String number;

	public DoDebitFrame(BankController bank, String number) {
		this.bank = bank;
		this.number = number;
		
		setTitle("Debit");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 450, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		okAction = new OkAction();
		
		JLabel amountLabel = new JLabel("Enter the amount to be debited");
		contentPane.add(amountLabel);
		
		amountTextField = new JTextField();
		contentPane.add(amountTextField);
		amountTextField.setColumns(10);
		
		JButton okButton = new JButton("OK");
		
		okButton.addActionListener(okAction);
		
		contentPane.add(okButton);
	}

	private class OkAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				bank.doDebit(number, Float.parseFloat(amountTextField.getText()));
				JOptionPane.showMessageDialog(null, "Operation was successful!", "Success", JOptionPane.PLAIN_MESSAGE);
				setVisible(false);
			} catch (BankTransactionException | NumberFormatException | FileNotFoundException bte) {
				JOptionPane.showMessageDialog(null, "Error: " + bte.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
	}
}
