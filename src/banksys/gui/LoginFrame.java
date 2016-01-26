package banksys.gui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel titlePane;
	private JPanel accountNumberPane;
	private JPanel buttonsPane;
	private JTextField accountNumberTextField;

	public LoginFrame() {
		setBackground(Color.PINK);
		setTitle("Automated Teller Machine");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);	
		
		contentPane = new JPanel();
		titlePane = new JPanel();
		accountNumberPane = new JPanel();
		buttonsPane = new JPanel();
		
		titlePane.setBackground(Color.WHITE);
		accountNumberPane.setBackground(Color.WHITE);
		buttonsPane.setBackground(Color.WHITE);	
		contentPane.setBackground(Color.PINK);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		menuBar();
		initialize();
	}
	
	private void menuBar() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.PINK);
		setJMenuBar(menuBar);
		
		JMenu optionsMenu = new JMenu("Options");
		optionsMenu.setBackground(Color.PINK);
		menuBar.add(optionsMenu);
		
		JMenuItem addNewAccountMenuItem = new JMenuItem("Add New Account");
		optionsMenu.add(addNewAccountMenuItem);
		addNewAccountMenuItem.setBackground(Color.WHITE);
		
		JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.setBackground(Color.WHITE);
		optionsMenu.add(exitMenuItem);
		
		JMenu helpMenu = new JMenu("Help");
		helpMenu.setBackground(Color.PINK);
		menuBar.add(helpMenu);
		
		JMenuItem aboutMenuItem = new JMenuItem("About");
		helpMenu.add(aboutMenuItem);
		aboutMenuItem.setBackground(Color.WHITE);
	}
	
	private void initialize() {
		contentPane.setLayout(new BorderLayout(0, 0));
		
		FlowLayout fl_accountNumberPane = new FlowLayout();
		fl_accountNumberPane.setHgap(15);
		accountNumberPane.setLayout(fl_accountNumberPane);
		
		FlowLayout fl_buttonsPane = new FlowLayout();
		fl_buttonsPane.setVgap(15);
		fl_buttonsPane.setHgap(20);
		buttonsPane.setLayout(fl_buttonsPane);
		
		titlePane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
		
		JLabel welcomeLabel = new JLabel("Welcome to our Bank!");
		welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		welcomeLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
		titlePane.add(welcomeLabel);
		
		contentPane.add(titlePane, BorderLayout.NORTH);		
		
		JLabel accountNumberLabel = new JLabel("Account Number");
		accountNumberLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		accountNumberLabel.setFont(new Font("Arial", Font.BOLD, 14));
		accountNumberPane.add(accountNumberLabel);
		
		accountNumberTextField = new JTextField();
		accountNumberTextField.setHorizontalAlignment(SwingConstants.LEFT);
		accountNumberTextField.setColumns(15);
		accountNumberPane.add(accountNumberTextField);
		
		JLabel cuteImage = new JLabel();
		cuteImage.setHorizontalAlignment(SwingConstants.CENTER);
		cuteImage.setIcon(new ImageIcon(LoginFrame.class.getResource("/banksys/icon/icon1.png")));
		accountNumberPane.add(cuteImage);
		
		contentPane.add(accountNumberPane, BorderLayout.CENTER);
		
		JButton okButton = new JButton("OK");
		buttonsPane.add(okButton);
		
		JButton exitButton = new JButton("Exit");
		buttonsPane.add(exitButton);
		
		contentPane.add(buttonsPane, BorderLayout.SOUTH);
	}

}
