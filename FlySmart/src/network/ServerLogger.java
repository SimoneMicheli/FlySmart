/**
 * 
 */
package network;

import java.io.IOException;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


/**
 * server logger, la classe gestisce il log del server
 *
 */
public class ServerLogger {
	
	private static final boolean enableFile = false;
	private static Logger log = null; 
	

	/**
	 * restituisce l'istanza del logger
	 * @return logger
	 */
	public static Logger getLog() {
		return log;
	}
	
	static{
		System.out.println("creating logger");
		log = Logger.getLogger(ServerLogger.class.getName());
		//log = Logger.getLogger("sun.rmi.server.call");
		log.setUseParentHandlers(false);
		
		//string formatter
		Formatter formatter = new Formatter() {
            @Override
            public String format(LogRecord arg0) {
                StringBuilder b = new StringBuilder();
                b.append(new Date());
                b.append(" ");
                b.append(arg0.getSourceClassName());
                b.append(" ");
                b.append(arg0.getSourceMethodName());
                b.append(" ");
                b.append(arg0.getLevel());
                b.append(" ");
                b.append(arg0.getMessage());
                b.append(System.getProperty("line.separator"));
                return b.toString();
            }

        };
		
		//console logger
		ConsoleHandler console = new ConsoleHandler();
		console.setFormatter(formatter);
		console.setLevel(Level.ALL);
		log.addHandler(console);
		
		//file logger
		if(enableFile)
			try {
				FileHandler file = new FileHandler("ServerLogger.txt", true);
				file.setFormatter(formatter);
				file.setLevel(Level.INFO);
				log.addHandler(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
}
