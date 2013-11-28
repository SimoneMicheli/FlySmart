package guiCheckIn;

import client.Controller;
import network.ServerInterface;


/**Il controller della vista di check in
 * @author Demarinis - Micheli - Scarpellini
 */
public class CheckInController extends Controller {

	/** Il riferimento alla vista */
	CheckInView view;


	/**
	 * Crea un controller per la fase di check in
	 *
	 * @param serv l'oggetto che la login mi passa per la connessione con il server
	 */
	public CheckInController(ServerInterface serv){
		view= new CheckInView(); //creo la vista
	}


}
