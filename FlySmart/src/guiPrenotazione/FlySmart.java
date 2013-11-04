/*
 * 
 */
package guiPrenotazione;

/** The Class FlySmart. Compagnia aerea
 *
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
public class FlySmart {

	
	/**
	 * Main. Programma principale dell'applicazione Prenotazione
	 */
	public static void main(String args[]) {
		
		//setto i SSL certificate
		System.setProperty("javax.net.ssl.trustStore", "src/network/client/cacert.jks"); //imposta trust store
		System.setProperty("javax.net.ssl.trustStorePassword", "clienttruststore");
		
		//creo una vista di tipo login e la passo al controller
		LoginView loginFS = new LoginView();
		loginFS.setVisible(true);
		@SuppressWarnings("unused")
		LoginController c = new LoginController(loginFS); //controller
	}

	
}