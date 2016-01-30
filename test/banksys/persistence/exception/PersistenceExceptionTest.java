package banksys.persistence.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class PersistenceExceptionTest {

	@Test
	public void testPersistenceException() {
		String message = "Testing";
		String number = "1234";
		Exception pe = new PersistenceException(message, number);
		assertEquals(number, ((PersistenceException) pe).getNumber());
	}

	@Test
	public void testGetMessage() {
		String message = "Testing";
		String number = "1234";
		Exception pe = new PersistenceException(message, number);
		assertEquals(message + " [account number = " + number + "]", ((PersistenceException) pe).getMessage());
	}

	@Test
	public void testGetNumber() {
		String message = "Testing";
		String number = "1234";
		Exception pe = new PersistenceException(message, number);
		assertEquals(number, ((PersistenceException) pe).getNumber());
	}

}
