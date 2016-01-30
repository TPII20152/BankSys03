package banksys.account;

import static org.junit.Assert.*;

import org.junit.Test;

import banksys.account.exception.NegativeAmountException;

public class SpecialAccountTest {

	@Test
	public void testSpecialAccount() {
		AbstractAccount account = new SpecialAccount("1234");
		assertEquals("1234", account.getNumber());
	}
	
	@Test
	public void testEarnBonus() throws NegativeAmountException {
		AbstractAccount account = new SpecialAccount("1234");
		account.credit(10.0);
		((SpecialAccount) account).earnBonus();
		assertEquals(10.1, account.getBalance(), 0);
	}
	
	@Test
	public void testGetBonus() throws NegativeAmountException {
		AbstractAccount account = new SpecialAccount("1234");
		account.credit(10.0);
		assertEquals(0.1, ((SpecialAccount) account).getBonus(), 0);
	}
	
	@Test
	public void testPositiveCredit() throws NegativeAmountException {
		AbstractAccount account = new SpecialAccount("1234");
		account.credit(10.0);
		assertEquals(10.0, account.getBalance(), 0);
	}
	
	@Test (expected = NegativeAmountException.class)
	public void testNegativeCredit() throws NegativeAmountException {
		AbstractAccount account = new SpecialAccount("1234");
		account.credit(-10.0);
		assertEquals(0, account.getBalance(), 0);
	}

}
