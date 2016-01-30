package banksys.persistence.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class AccountDeletionExceptionTest {

	@Test
	public void testAccountDeletionException() {
		String message = "Testing";
		String number = "1234";
		Exception ade = new AccountDeletionException(message, number);
		assertEquals(number, ((PersistenceException) ade).getNumber());
	}

}
