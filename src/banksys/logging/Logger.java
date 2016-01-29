package banksys.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
	
	public static void register(String message) throws IOException {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String dateAndTime = LocalDateTime.now().format(formatter);
		File log = new File("log.txt");
		
		if (!log.exists()) {
			log.createNewFile();
		}
		
		FileWriter fileWriter = new FileWriter(log, true);
		
        fileWriter.write("("+ dateAndTime + ") " + message);
        fileWriter.write(System.getProperty("line.separator"));
        fileWriter.close();
        
	}
	
}
