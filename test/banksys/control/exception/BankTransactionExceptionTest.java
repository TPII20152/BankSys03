package banksys.control.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTransactionExceptionTest {

	@Test
	public void testBankTransactionExceptionString() {
		String message = "test";
		Exception bte = new BankTransactionException(message);
		assertEquals("Transaction not performed!\nCause: " + message, bte.getMessage());
	}

	@Test
	public void testBankTransactionExceptionException() {
		Exception cause = new Exception("test");
		Exception bte = new BankTransactionException(cause);
		assertEquals("Transaction not performed!\nCause: " + cause.getMessage(), bte.getMessage());
	}

	@Test
	public void testGetMessage() {
		String message = "test";
		Exception bte = new BankTransactionException(message);
		assertEquals("Transaction not performed!\nCause: " + message, bte.getMessage());
	}

	@Test
	public void testGetCause() {
		Exception cause = new Exception("test");
		Exception bte = new BankTransactionException(cause);
		assertEquals(cause, bte.getCause());
	}

}
