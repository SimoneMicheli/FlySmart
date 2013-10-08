package exception;

/**
 * @author Demarinis - Micheli - Scarpellini
 * eccezione lanciata se il volo cercato
 * ha posti residui insufficienti
 *
 */
public class SeatsSoldOutException extends Exception {

	private static final long serialVersionUID = -3732723768131457927L;

	public SeatsSoldOutException() {
		super("seats sold out!");
	}
	
	public SeatsSoldOutException(int id){
		super("seats sold out for flight: "+ Integer.toString(id));
	}
	
	public SeatsSoldOutException(String message){
		super("seats sold out: " + message);
	}
	
	public SeatsSoldOutException(Throwable e){
		super(e);
	}
	
	public SeatsSoldOutException(String message, Throwable e){
		super(message,  e);
	}
}
