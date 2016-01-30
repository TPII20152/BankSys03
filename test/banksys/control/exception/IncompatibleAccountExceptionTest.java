package banksys.control.exception;

import static org.junit.Assert.*;

import org.junit.Test;

public class IncompatibleAccountExceptionTest {

	@Test
	public void testIncompatibleAccountException() {
		String number = "1234";
		Exception iae = new IncompatibleAccountException(number);
		assertEquals("Transaction not performed!\nCause: " + "Incompatible account type to the requested operation! [account number = " + number + "]", iae.getMessage());
	}

}
