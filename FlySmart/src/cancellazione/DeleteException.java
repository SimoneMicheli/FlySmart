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
