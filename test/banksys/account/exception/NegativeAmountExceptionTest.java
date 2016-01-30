package banksys.account.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class NegativeAmountExceptionTest {

	@Test
	public void testNegativeAmountException() {
		double amount = -10.0;
		Exception nae = new NegativeAmountException(amount);
		assertEquals(amount, ((NegativeAmountException) nae).getAmount(), 0);
	}

	@Test
	public void testGetMessage() {
		double amount = -10.0;
		Exception nae = new NegativeAmountException(amount);
		assertEquals("Negative amount! [amount = " + amount + "]", ((NegativeAmountException) nae).getMessage());
	}

	@Test
	public void testGetAmount() {
		double amount = -10.0;
		Exception nae = new NegativeAmountException(amount);
		assertEquals(amount, ((NegativeAmountException) nae).getAmount(), 0);
	}

}
