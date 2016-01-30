package banksys.persistence.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountCreationExceptionTest {

	@Test
	public void testAccountCreationException() {
		String message = "Testing";
		String number = "1234";
		Exception ace = new AccountCreationException(message, number);
		assertEquals(number, ((PersistenceException) ace).getNumber());
	}

}
