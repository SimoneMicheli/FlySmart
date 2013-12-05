/*
 * 
 */
package guiCheckIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.bson.types.ObjectId;

import prenotazione.FlightNotFoundException;

import client.Controller;
import model.Aeroporto;
import model.Volo;
import smart.CheckinReport;
import network.ServerInterface;


/**Il controller della vista di check in
 * @author Demarinis - Micheli - Scarpellini
 */
public class CheckInController extends Controller {

	/** Il riferimento alla vista */
	CheckInView view;


	/**
	 * Crea un controller per la fase di check in
	 *
	 * @param serv l'oggetto che la login mi passa per la connessione con il server
	 */
	public CheckInController(ServerInterface serv){
		view= new CheckInView(); //creo la vista
		this.serv=serv;
		caricaAeroporti();
		registraController();
		view.setVisible(true);
	}

	public void caricaAeroporti(){
		List<Aeroporto> aeroporti=null;
		try {
			aeroporti = serv.getAeroporti();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Error", 0);
			e.printStackTrace();
		}
		view.disegnaElementi(aeroporti);
	}


	/**
	 * Mostra componenti grafici relativi al volo
	 *
	 * @param x dice se mostrare o meno i componenti grafici
	 */
	private void mostraComponenti(boolean x){
		if(x){
			view.comboVoli.setVisible(true);
			view.buttonChiudiVolo.setVisible(true);
		}else{
			view.comboVoli.removeAllItems();
			view.comboVoli.setVisible(false);
			view.buttonChiudiVolo.setVisible(false);
		}

	}

	public void registraController() {
		view.comboAeroporto.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int p = ((Aeroporto)view.comboAeroporto.getSelectedItem()).getId();
					List<Volo> voli = serv.getVoli(p,-1); //carico la lista dei voli con destinazione qualsiasi
					view.comboVoli.removeAllItems();
					Iterator<Volo> v = voli.iterator();
					while(v.hasNext()) {
						Volo element = (Volo) v.next();
						view.comboVoli.addItem(element);
					}
					mostraComponenti(voli.size()!=0); //se la lista è vuota non mostro i componenti
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Errore", 0);
				} catch (ClassCastException e) {
					mostraComponenti(false);
				};
			}
		});


		view.buttonChiudiVolo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ObjectId idVolo = ((Volo)view.comboVoli.getSelectedItem()).getId();
				try {
					CheckinReport s = serv.calcolaCheckin(idVolo);
					System.out.println(s); //fare qualosa
				} catch (FlightNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Volo non trovato","Errore", 0);
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Errore", 0);
				}
				JOptionPane.showMessageDialog(null, "Check-In calcolato per il volo "+((Volo)view.comboVoli.getSelectedItem()).getId(),"Calcolato", 3);
			}
		});
	}


}
