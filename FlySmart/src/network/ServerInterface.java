/**
 * 
 */
package network;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.*;

/**
 * interfaccia server, definisce i metodi eseguibili dai client
 * @author 
 *
 */
public interface ServerInterface extends Remote {
	
	/**
	 * restituisce l'elenco areoporti al client
	 * @return
	 * @throws RemoteException
	 */
	public List<Aeroporto> getAeroporti() throws RemoteException;
	
	/**
	 * passando gli id degli aeroporti di partenza e arrivo restituisce
	 * l'elenco dei voli disponibili
	 * @param idp id aeroporto di partenza
	 * @param ida id aeroporto arrivo
	 * @return elenco voli disponibili
	 * @throws RemoteException
	 */
	public List<Volo> getVoli(int idp, int ida) throws RemoteException;
	
	/**
	 * consente la prenotazione di un volo da parte di una lista di passeggeri
	 * @param listPass elnco passeggeri da prenotare
	 * @param idVolo id del volo da prenotare
	 * @return codice di errore
	 * @throws RemoteException
	 */
	public int prenotaPasseggero(List<Passeggero> listPass, int idVolo) throws RemoteException;
	
	/**
	 * consente la prenotazione di un volo da parte di una lista di pallet
	 * @param listPallet elnco pallet da prenotare
	 * @param idVolo id del volo da prenotare
	 * @return codice di errore
	 * @throws RemoteException
	 */
	public int prenotaPallet(List<Pallet> listPallet, int idVolo) throws RemoteException;
}
