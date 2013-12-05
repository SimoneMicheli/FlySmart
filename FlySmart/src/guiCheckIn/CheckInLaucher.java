package guiCheckIn;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import client.LoginView;


/** FlySmart. Compagnia aerea. Client di Check-In
 *
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
public class CheckInLaucher {

	
	/**
	 * Main. Programma principale dell'applicazione CheckIn
	 * @param args String[]
	 */
	public static void main(String args[]) {
		
		Properties opt = new Properties();
		
		//controllo argomento
		String configFilePath;
		if(args.length >= 1){
			configFilePath = args[0];
		}else{
			configFilePath = "src/config/clientConfig.xml";
		}
		
		try {
			//carica file opzioni
			InputStream configFile = new FileInputStream(configFilePath);
			opt.loadFromXML(configFile);
			
			//setto i SSL certificate
			System.setProperty("javax.net.ssl.trustStore", opt.getProperty("trustStore")); //imposta trust store
			System.setProperty("javax.net.ssl.trustStorePassword", opt.getProperty("trustStorePassword"));
			
			
			
			//creo una vista di tipo login e la passo al controller
			LoginView loginFS = new LoginView();
			loginFS.setVisible(true);
			new LoginCheckInController(loginFS); //controller
			
			
			
			
		} catch(FileNotFoundException e){
			System.err.println("File di configurazione non trovato");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	
	}

	
}