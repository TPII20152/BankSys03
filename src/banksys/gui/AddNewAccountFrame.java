package banksys.gui;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
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

	public AddNewAccountFrame() {
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
		
		cancelButton = new JButton("Cancel");
		buttonsPane.add(cancelButton);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		initialize();
	}
	
	private void initialize() {		
		ButtonGroup buttonGroup = new ButtonGroup();
		contentPane.setLayout(new BorderLayout(0, 0));
		
		titleLabel = new JLabel("Add New Account");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 30));
		titlePane.add(titleLabel);
		
		contentPane.add(titlePane, BorderLayout.NORTH);
				
		JLabel cuteImage = new JLabel();
		cuteImage.setIcon(new ImageIcon(LoginFrame.class.getResource("/banksys/icon/icon2.png")));
		
		newAccountPane.add(cuteImage);
		
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.Y_AXIS));
		
		JRadioButton ordinaryRadioButton = new JRadioButton("Ordinary");
		optionsPane.add(ordinaryRadioButton);
		
		JRadioButton specialRadioButton = new JRadioButton("Special");
		optionsPane.add(specialRadioButton);
		
		JRadioButton savingsRadioButton = new JRadioButton("Savings");
		optionsPane.add(savingsRadioButton);
		
		JRadioButton taxRadioButton = new JRadioButton("Tax");
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

}
