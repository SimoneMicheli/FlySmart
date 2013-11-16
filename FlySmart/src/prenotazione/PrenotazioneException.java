package prenotazione;

import org.bson.types.ObjectId;

/**
 * definisce una generica eccezione durante la prenotazione
 *
 */
public class PrenotazioneException extends RuntimeException {

	private static final long serialVersionUID = -7830660559787847126L;

	/**
	 * 
	 */
	public PrenotazioneException() {
	}
	
	public PrenotazioneException(ObjectId id){
		super("Exception during booking process on flight id: "+ id.toString());
	}

	/**
	 * @param message
	 */
	public PrenotazioneException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public PrenotazioneException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public PrenotazioneException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public PrenotazioneException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
