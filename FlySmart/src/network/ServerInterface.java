package network;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import exception.FlightNotFoundException;
import exception.SeatsSoldOutException;

import model.*;

/**Interfaccia server, definisce i metodi eseguibili dai client
 * @author Demarinis - Micheli - Scarpellini
 * 
 *
 */
public interface ServerInterface extends Remote {
	
	/**
	 * @return l'elenco areoporti al client
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
	 * @param listToAdd elnco passeggeri da prenotare
	 * @param idVolo id del volo da prenotare
	 * @return codice di errore
	 * @throws RemoteException
	 * @throws FlightNotFoundException 
	 * @throws SeatsSoldOutException 
	 */
	public int prenotaPasseggero(List<Passeggero> listToAdd, int idVolo) throws RemoteException, FlightNotFoundException, SeatsSoldOutException;
	
	/**
	 * consente la prenotazione di un volo da parte di una lista di pallet
	 * @param listToAdd elnco pallet da prenotare
	 * @param idVolo id del volo da prenotare
	 * @return codice di errore
	 * @throws RemoteException
	 * @throws IOException 
	 * @throws FlightNotFoundException 
	 * @throws SeatsSoldOutException 
	 */
	public int prenotaPallet(List<Pallet> listToAdd, int idVolo) throws RemoteException, FlightNotFoundException, SeatsSoldOutException;
}
