package banksys.persistence.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountNotFoundExceptionTest {

	@Test
	public void testAccountNotFoundException() {
		String message = "Testing";
		String number = "1234";
		Exception anfe = new AccountNotFoundException(message, number);
		assertEquals(number, ((PersistenceException) anfe).getNumber());
	}

}
