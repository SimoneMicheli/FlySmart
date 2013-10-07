package exception;

/**
 * eccezione lanciata se il volo cercato
 * non esiste o non è stato trovato nella lista
 *
 */
public class FlightNotFoundException extends Exception {

	private static final long serialVersionUID = -3732723768131457927L;

	public FlightNotFoundException() {
		super("required flight NOT FOUND!");
	}
	
	public FlightNotFoundException(int id){
		super("required flight "+ Integer.toString(id) +"NOT FOUND!");
	}
	
	public FlightNotFoundException(String message){
		super("required flight NOT FOUND!" + message);
	}
	
	public FlightNotFoundException(Throwable e){
		super(e);
	}
	
	public FlightNotFoundException(String message, Throwable e){
		super(message,  e);
	}
}
