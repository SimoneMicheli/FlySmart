package gui;

public class FlySmart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		//set SSL certificate
		System.setProperty("javax.net.ssl.trustStore", "src/network/clientstore");
		System.setProperty("javax.net.ssl.trustStorePassword", "clientstorepassword");
		
		LoginView loginFS = new LoginView();
		loginFS.setVisible(true);
		@SuppressWarnings("unused")
		LoginController c = new LoginController(loginFS); //controller
	}

	
}