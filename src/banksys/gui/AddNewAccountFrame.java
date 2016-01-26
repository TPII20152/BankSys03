package banksys.gui;

import java.awt.BorderLayout;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.BoxLayout;

public class AddNewAccountFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JPanel optionsPane;

	public AddNewAccountFrame() {
		setBackground(Color.ORANGE);
		setTitle("Add New Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		optionsPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.X_AXIS));
		
		initialize();
	}
	
	private void initialize() {
		optionsPane.setLayout(new BoxLayout(optionsPane, BoxLayout.Y_AXIS));
		
		ButtonGroup buttonGroup = new ButtonGroup();
		
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
		
		contentPane.add(optionsPane, BorderLayout.WEST);
	}

}
