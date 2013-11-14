package exception;

import org.bson.types.ObjectId;

/** Eccezione lanciata se il volo cercato
 * ha posti residui insufficienti
 * @author Demarinis - Micheli - Scarpellini
 *
 */
public class SeatsSoldOutException extends Exception {

	private static final long serialVersionUID = -3732723768131457927L;

	public SeatsSoldOutException() {
		super("seats sold out!");
	}
	
	public SeatsSoldOutException(ObjectId id){
		super("seats sold out for flight: "+ id.toString());
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
