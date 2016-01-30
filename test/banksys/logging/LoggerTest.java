package banksys.logging;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Test;

public class LoggerTest {

	@Test
	public void testRegister() throws IOException {
		String message = "Testing Logger class";
		String dateAndTime = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
		
		BufferedReader input = new BufferedReader(new FileReader("log.txt"));
	    String last = "";
	    String line = "";
	    
	    Logger.register(message);

	    while ((line = input.readLine()) != null) {
	        last = line;
	    }
	    
	    input.close();   
		
		assertEquals("("+ dateAndTime + ") " + message, last);
	}

}
