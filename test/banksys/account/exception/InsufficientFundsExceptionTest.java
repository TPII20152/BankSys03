package banksys.account.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class InsufficientFundsExceptionTest {

	@Test
	public void testInsufficientFundsException() {
		String number = "1234";
		double balance = 0;
		Exception ife = new InsufficientFundsException(number, balance);
		assertEquals("1234", ((InsufficientFundsException) ife).getNumber());
	}

	@Test
	public void testGetMessage() {
		String number = "1234";
		double balance = 0;
		Exception ife = new InsufficientFundsException(number, balance);
		assertEquals("Insufficient funds! [account number = " + number + " balance = " + balance + "]", ((InsufficientFundsException) ife).getMessage());
	}

	@Test
	public void testGetNumber() {
		String number = "1234";
		double balance = 0;
		Exception ife = new InsufficientFundsException(number, balance);
		assertEquals("1234", ((InsufficientFundsException) ife).getNumber());
	}

	@Test
	public void testGetBalance() {
		String number = "1234";
		double balance = 0;
		Exception ife = new InsufficientFundsException(number, balance);
		assertEquals(0, ((InsufficientFundsException) ife).getBalance(), 0);
	}

}
