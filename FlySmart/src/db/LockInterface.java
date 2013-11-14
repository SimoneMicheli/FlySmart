/**
 * 
 */
package db;

import org.bson.types.ObjectId;

/**
 *definisce l'interfaccia per acuisire e rilasciare un lock sui voli
 *
 */
public interface LockInterface {

	/**
	 * Acquisisce il lock sul volo richiesto o sospende il thread se la risorsa è occupata
	 * @param idVolo id del volo di cui acquisire il lock
	 */
	public void acquireLock(ObjectId idVolo);
	
	/**
	 * rilascia il lock sul volo richiesto
	 * @param idVolo id del volo su cui rilasciare il lock
	 */
	public void  releaseLock(ObjectId idVolo);

}
