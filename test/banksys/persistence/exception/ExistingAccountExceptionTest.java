package banksys.persistence.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class ExistingAccountExceptionTest {

	@Test
	public void testExistingAccountException() {
		String number = "1234";
		Exception eae = new ExistingAccountException(number);
		assertEquals(number, ((ExistingAccountException) eae).getNumber());
	}

	@Test
	public void testGetMessage() {
		String number = "1234";
		Exception eae = new ExistingAccountException(number);
		assertEquals("Existing OrdinaryAccount! account number = " + number, eae.getMessage());
	}

	@Test
	public void testGetNumber() {
		String number = "1234";
		Exception eae = new ExistingAccountException(number);
		assertEquals(number, ((ExistingAccountException) eae).getNumber());
	}

}
