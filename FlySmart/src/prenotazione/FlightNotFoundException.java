package prenotazione;

import org.bson.types.ObjectId;

/**Eccezione lanciata se il volo cercato
 * non esiste o non è stato trovato nella lista
 * @author Demarinis - Micheli - Scarpellini
 * 
 *
 */
public class FlightNotFoundException extends BookingException {

	private static final long serialVersionUID = -3732723768131457927L;

	public FlightNotFoundException() {
		super("Required flight NOT FOUND!");
	}
	
/**
	 * Constructor for FlightNotFoundException.
	 * @param id ObjectId
	 */
		public FlightNotFoundException(ObjectId id){
		super("Required flight "+ id.toString() +"NOT FOUND!");
	}
	
/**
	 * Constructor for FlightNotFoundException.
	 * @param message String
	 */
		public FlightNotFoundException(String message){
		super("Required flight NOT FOUND!" + message);
	}
	
/**
	 * Constructor for FlightNotFoundException.
	 * @param e Throwable
	 */
		public FlightNotFoundException(Throwable e){
		super(e);
	}
}
