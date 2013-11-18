/**
 * 
 */
package db;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;

/**
 * implementazione dell'interfaccia Lock, la classe permette
 * di acquisire e rilasciare i lock su un volo.
 * l'implementazione avviane attraverso il design pattern singleton
 *
 */
public final class Lock implements LockInterface{
	
	/**
	 * hasmap contenente per ogni id del volo il thread che detiene il lock su di esso
	 */
	private HashMap<ObjectId, Thread> locks;
	
	/**
	 * logger
	 */
	private Logger log;
	
	/**
	 * puntatore all'istanza della classe
	 */
	private static Lock l;
	
	/**
	 * costruttore privato della classe
	 */
	private Lock(){
		log = LogManager.getLogger(Lock.class.getCanonicalName().toString());
		locks = new HashMap<>();
	};
	
	/* (non-Javadoc)
     * @see db.LockInterface#acquireLock()
     */
	public synchronized void acquireLock(ObjectId idVolo){
		log.entry(idVolo);
		
		Thread current = Thread.currentThread();
		log.debug("il thread "+current.getName()+" richiede il lock su: "+idVolo);
		
		//il lock è occupato
		while(locks.get(idVolo) != null){
			try {
				log.debug("il thread "+current.getName()+" viene sospeso su+"+idVolo);
				wait();
			} catch (InterruptedException e) {}
		}
		
		log.debug("il thread "+current.getName()+" acuqisisce il lock su: "+idVolo);
		//acquisisci il lock
		locks.put(idVolo, current);
		
		log.exit();
	};
	
	/* (non-Javadoc)
     * @see db.LockInterface#releaseLock()
     */
	public synchronized void  releaseLock(ObjectId idVolo){
		log.entry(idVolo);
		
		Thread current = Thread.currentThread();
		
		Thread saved = locks.get(idVolo);
		
		
		//il lock cercato non esiste
		if(saved == null){
			log.warn("Il thread "+current.getName()+" cerca di rilasciare un lock non acquisito su:"+idVolo.toString());
			return;
		}
		
		//il thread corrente cerca di rilasciare un lock
		//assegnato ad un'altro thread
		if(!saved.equals(current)){
			log.warn("Il thread "+current.getName()+" cerca di rilasciare un lock a lui non assegnato id: "+idVolo.toString());
			return;
		}
		
		//rilascia il lock e notifica agli altri
		log.debug("il thread "+current.getName()+" rilascia il lock su: "+idVolo);
		locks.remove(idVolo);
		notifyAll();
		
		log.exit();
	}
	
	/**
	 * ritorna l'istanza dell'oggetto se esiste, altrimenti istanzia un nuovo oggetto
	 * @return istanza della classe lock
	 */
	public synchronized static Lock getInstance(){
		if(l == null)
			l = new Lock();
		return l;
	}
}
