package prenotazione;

import org.bson.types.ObjectId;

/** Eccezione lanciata se il volo cercato
 * ha posti residui insufficienti
 * @author Demarinis - Micheli - Scarpellini
 *
 */
public class SeatsSoldOutException extends BookingException {

	private static final long serialVersionUID = -3732723768131457927L;

	public SeatsSoldOutException() {
		super("Seats sold out!");
	}
	
	public SeatsSoldOutException(ObjectId id){
		super("Seats sold out for flight: "+ id.toString());
	}
	
	public SeatsSoldOutException(String message){
		super("Seats sold out: " + message);
	}
	
	public SeatsSoldOutException(Throwable e){
		super(e);
	}
	
	public SeatsSoldOutException(String message, Throwable e){
		super(message,  e);
	}
}
