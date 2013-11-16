/**
 * 
 */
package prenotazione;

import java.util.List;

import org.bson.types.ObjectId;

import exception.FlightNotFoundException;
import exception.SeatsSoldOutException;

import model.Model;

/**
 *interfaccia definita per le classi che gestiscono la prenotazione
 *
 */
public interface Prenotazione<T extends Model> {

	/**
	 * consente di prenotare un volo
	 * @param listToAdd lista di elementi da prenotare
	 * @param idVolo id del volo da prenotare
	 * @return groupid/palletid
	 * @throws FlightNotFoundException volo non trovato
	 * @throws SeatsSoldOutException volo pieno
	 */
	public int prenota(List<T> listToAdd, ObjectId idVolo) throws FlightNotFoundException, SeatsSoldOutException;
}
