package banksys.account;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.account.exception.InsufficientFundsException;
import banksys.account.exception.NegativeAmountException;

public class OrdinaryAccountTest {

	@Test
	public void testOrdinaryAccount() {
		AbstractAccount account = new OrdinaryAccount("1234");
		assertEquals("1234", account.getNumber());
	}
	
	@Test
	public void testPositiveDebit() throws NegativeAmountException, InsufficientFundsException {
		AbstractAccount account = new OrdinaryAccount("1234");
		double creditAmount = 10.0;
		double debitAmount = 2.0;
		account.credit(creditAmount);
		account.debit(debitAmount);
		assertEquals(8, account.getBalance(), 0);
	}
	
	@Test (expected = NegativeAmountException.class)
	public void testNegativeDebit() throws NegativeAmountException, InsufficientFundsException {
		AbstractAccount account = new OrdinaryAccount("1234");
		double amount = -2.0;
		account.debit(amount);
		assertEquals(0, account.getBalance(), 0);
	}
	
	@Test (expected = InsufficientFundsException.class)
	public void testNoFundsDebit() throws NegativeAmountException, InsufficientFundsException {
		AbstractAccount account = new OrdinaryAccount("1234");
		double amount = 10.0;
		account.debit(amount);
		assertEquals(0, account.getBalance(), 0);
	}

}
