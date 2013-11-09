package guiPrenotazione;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;


/** FlySmart. Compagnia aerea
 *
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
public class Prenotazione {

	
	/**
	 * Main. Programma principale dell'applicazione Prenotazione
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
			System.setProperty("javax.net.ssl.trustStore", "src/network/client/cacert.jks"); //imposta trust store
			System.setProperty("javax.net.ssl.trustStorePassword", "clienttruststore");
			
			//creo una vista di tipo login e la passo al controller
			LoginView loginFS = new LoginView();
			loginFS.setVisible(true);
			@SuppressWarnings("unused")
			LoginController c = new LoginController(loginFS); //controller
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Configuration file not found!!");
		}
		
	
	}

	
}