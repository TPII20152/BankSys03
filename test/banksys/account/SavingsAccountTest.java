package banksys.account;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.account.exception.NegativeAmountException;

public class SavingsAccountTest {

	@Test
	public void testSavingsAccount() {
		AbstractAccount account = new SavingsAccount("1234");
		assertEquals("1234", account.getNumber());
	}

	@Test
	public void testEarnInterest() throws NegativeAmountException {
		AbstractAccount account = new SavingsAccount("1234");
		double amount = 10.0;
		account.credit(amount);
		((SavingsAccount) account).earnInterest();		
		assertEquals(10.01, account.getBalance(), 0);
	}

}
