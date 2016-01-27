package banksys.gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import javax.swing.JButton;

public class DoCreditFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField ammountTextField;

	public DoCreditFrame() {
		setTitle("Credit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 140);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		
		JLabel ammountLabel = new JLabel("Enter the amount to be credited");
		contentPane.add(ammountLabel);
		
		ammountTextField = new JTextField();
		contentPane.add(ammountTextField);
		ammountTextField.setColumns(10);
		
		JButton okButton = new JButton("OK");
		contentPane.add(okButton);
	}

}
