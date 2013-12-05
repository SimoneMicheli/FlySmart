package prenotazione;

import org.bson.types.ObjectId;

/**
 * definisce una generica eccezione durante la prenotazione
 *
 */
public class BookingException extends RuntimeException {

	private static final long serialVersionUID = -7830660559787847126L;

	/**
	 * 
	 */
	public BookingException() {
	}
	
	public BookingException(ObjectId id){
		super("Exception during booking process on flight id: "+ id.toString());
	}

	/**
	 * @param message
	 */
	public BookingException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public BookingException(Throwable cause) {
		super(cause);
	}

}
