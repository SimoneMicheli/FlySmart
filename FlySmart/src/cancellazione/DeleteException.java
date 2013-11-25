/**
 * 
 */
package cancellazione;

/**
 * l'eccezione viene sollevata quando si verifica un problema durante la cancellazione
 *
 */
@SuppressWarnings("serial")
public class DeleteException extends RuntimeException {

	/**
	 * 
	 */
	public DeleteException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DeleteException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DeleteException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public DeleteException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DeleteException(Throwable cause) {
		super(cause);
	}

}
