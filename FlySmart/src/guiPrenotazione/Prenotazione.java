package guiPrenotazione;

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