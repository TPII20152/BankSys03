package banksys.atm;

import java.io.IOException;

import banksys.control.BankController;
import banksys.gui.LoginFrame;
import banksys.persistence.AccountPersistence;

public class ATM24H {

	public static void main(String[] args) throws IOException {
		
		BankController bank = new BankController(new AccountPersistence());
		
		LoginFrame loginFrame = new LoginFrame(bank);
		loginFrame.setVisible(true);
		
	}
	
}