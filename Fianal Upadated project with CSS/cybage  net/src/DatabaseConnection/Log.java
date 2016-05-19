package DatabaseConnection;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

	public static void maintainLog(String uname)
	{
		 Logger logger = Logger.getLogger("MyLog");  
		    FileHandler fh;  

		    try {  

		        // This block configure the logger with handler and formatter  
		        fh = new FileHandler("D:/MyLogFile.log");  
		        logger.addHandler(fh);
		        SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  
		        	
		        logger.info("\n");
		        
		        // the following statement is used to log any messages  
		        logger.info(uname+" Logged in to the system at "+new Date());  

		    } catch (SecurityException e) {  
		        e.printStackTrace();  
		    } catch (IOException e) {  
		        e.printStackTrace();  
		    }  


		
	}
}
