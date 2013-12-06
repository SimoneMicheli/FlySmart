package guiCheckIn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFrame;
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

	public void registraController() {
		view.comboAeroporto.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(view.comboAeroporto.getSelectedIndex()!=0){
					int p = ((Aeroporto)view.comboAeroporto.getSelectedItem()).getId();
					List<Volo> voli=null;
					try {
						voli = serv.getVoli(p,-1); //carico la lista dei voli
						if(voli.size()!=0){
							view.comboVoli.removeAllItems();
							Iterator<Volo> v = voli.iterator();
							while(v.hasNext()) {
								Volo element = (Volo) v.next();
								view.comboVoli.addItem(element);
							}
							view.comboVoli.setVisible(true);
							view.buttonChiudiVolo.setVisible(true);
						}else{
							view.comboVoli.removeAllItems();
							view.comboVoli.setVisible(false);
							view.buttonChiudiVolo.setVisible(false);
						}
					} catch (RemoteException e) {
						JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Errore", 0);
						e.printStackTrace();
					};
				}else{
					view.comboVoli.removeAllItems();
					view.comboVoli.setVisible(false);
					view.buttonChiudiVolo.setVisible(false);
				}
			}
		});


		view.buttonChiudiVolo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ObjectId idVolo = ((Volo)view.comboVoli.getSelectedItem()).getId();
				try {
					CheckinReport s = serv.calcolaCheckin(idVolo);
					System.out.println(s); //fare qualosa
					JOptionPane.showMessageDialog(null, "Check-In calcolato per il volo "+((Volo)view.comboVoli.getSelectedItem()).getId(),"Calcolato", 3);
					@SuppressWarnings("unused")
					JFrame frame = new Report(s,(Volo)view.comboVoli.getSelectedItem());
				} catch (FlightNotFoundException e) {
					JOptionPane.showMessageDialog(null, "Volo non trovato","Errore", 0);
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Errore", 0);
				}
			}
		});
	}


}
