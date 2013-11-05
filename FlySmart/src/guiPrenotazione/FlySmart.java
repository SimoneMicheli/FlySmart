/*
 * 
 */
package guiPrenotazione;

/** FlySmart. Compagnia aerea. Gui di prenotazione passeggeri e pallet
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
		System.setProperty("javax.net.ssl.trustStore", "src/network/clientstore");
		System.setProperty("javax.net.ssl.trustStorePassword", "clientstorepassword");
		
		//creo una vista di tipo login e la passo al controller
		LoginView loginFS = new LoginView();
		loginFS.setVisible(true);
		@SuppressWarnings("unused")
		LoginController c = new LoginController(loginFS); //controller
	}

	
}