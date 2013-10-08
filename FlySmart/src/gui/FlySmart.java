/*
 * 
 */
package gui;

// TODO: Auto-generated Javadoc
/**
 * The Class FlySmart.
 *
 * @author Demarinis - Micheli - Scarpellini
 * The Class FlySmart. Compagnia aerea
 */
public class FlySmart {

	
	/**
	 * Main. Programma principale dell'applicazione Prenotazione
	 */
	public static void main(String args[]) {
		
		//setto i SSL certificate
		System.setProperty("javax.net.ssl.trustStore", "src/network/clientstore");
		System.setProperty("javax.net.ssl.trustStorePassword", "clientstorepassword");
		
		//creo una vista di tipo login e la passo al controller
		LoginView loginFS = new LoginView();
		loginFS.setVisible(true);
		@SuppressWarnings("unused")
		LoginController c = new LoginController(loginFS); //controller
	}

	
}