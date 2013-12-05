package guiPrenotazione;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import org.bson.types.ObjectId;
import client.Controller;
import cancellazione.DeleteException;
import prenotazione.SeatsSoldOutException;
import model.Aeroporto;
import model.Pallet;
import model.Passeggero;
import model.Volo;
import network.ServerInterface;


/**Il controller della vista di prenotazione
 * @author Demarinis - Micheli - Scarpellini
 */
public class PrenotazioneController extends Controller {

	/** Il riferimento alla vista */
	PrenotazioneView view;


	/**
	 * Crea un controller per la fase di prenotazione
	 * @param serv l'oggetto che la login mi passa per la connessione con il server
	 */
	public PrenotazioneController(ServerInterface serv){
		view= new PrenotazioneView(); //creo la vista
		view.setVisible(true);
		this.serv=serv;
		caricaAeroporti();
		registraControllerPrenotazione();
	}

	/**
	 * Carica gli aereoporti dal server
	 */
	public void caricaAeroporti(){
		List<Aeroporto> aeroporti=null;
		try {
			aeroporti = serv.getAeroporti();
		} catch (RemoteException e) {
			JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Error", 0);
		}
		view.setPasseggeriAeroporti(aeroporti); //carico gli oggetti nella vista passeggeri:aeroporti
		view.setPalletAeroporti(aeroporti); //carico gli oggetti nella vista pallet:aeroporti
	}




	/**
	 * Aggiungo i listner agli oggetti della facciata passeggeri:aeroporti e pallet:aeroporti
	 */
	public void registraControllerPrenotazione() {

		//file->esci
		view.mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.exit(0);
			}

		});

		//modifica->cambia
		view.mntmSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.passeggeri){ //se ero su passeggeri
					view.passeggeri=false;
					view.cardEsterno.show(view.panelCardLayoutEsterno,"panelPallet");
					view.cardPallet.show(view.panelPallet,"panelPalletAeroporti");
					view.mntmSwitch.setText("Prenotazioni passeggeri");
				}else{ //se ero su pallet
					view.passeggeri=true;
					view.cardEsterno.show(view.panelCardLayoutEsterno,"panelPasseggeri");
					view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriAeroporti");
					view.mntmSwitch.setText("Prenotazioni pallet");
				}
			}

		});

		//cancella prenotazione passeggero
		view.mntmRimuoviPasseggero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String option = JOptionPane.showInputDialog(null, "Inserire il codice della prenotazione", "Cancella prenotazione", JOptionPane.OK_CANCEL_OPTION); //ottengo il codice della prenotazione
				try {
					List<Passeggero>  myList = serv.getPasseggeriGruppo((new ObjectId(option))); //ottengo la lista dei passeggeri del volo selezionato
					List<JCheckBox> listaCB = new ArrayList<JCheckBox>();
					for (Passeggero p : myList){
						listaCB.add(new JCheckBox(p.getCognome()+" "+p.getNome()));
					}
					Object[] params = new Object[listaCB.size()+1];
					params[0] = (String)"Selezionare i passeggeri da rimuovere";
					int i=1;
					for (JCheckBox j : listaCB){
						params[i]=j;
						i++;
					}
					selezionePasseggeriDaEliminare(params,listaCB,myList,option);
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Errore", 0);
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(null, "Codice errato","Errore", 0);
				}
			}

		});
		registraControllerPrenotazioneSecondaParte();

	}

	public void registraControllerPrenotazioneSecondaParte() {
		//cancella prenotazione pallet
		view.mntmRimuoviPallet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String option = JOptionPane.showInputDialog(null, "Inserire il codice della prenotazione", "Cancella prenotazione", JOptionPane.OK_CANCEL_OPTION);
				if (JOptionPane.showConfirmDialog(null,"<html>Vuoi confermare la cancellazione della prenotazione "+ option +" ?</html>","Conferma eliminazione prenotazione",JOptionPane.YES_NO_OPTION,JOptionPane.NO_OPTION) == JOptionPane.OK_OPTION) {
					try {
						serv.cancellaPallet(new ObjectId(option));
						JOptionPane.showMessageDialog(null, "La prenotazione "+ option +" è stata rimossa","Prenotazione eliminata", 0);
					} catch (RemoteException e) {
						JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Errore", 0);
					} catch (DeleteException e) {
						JOptionPane.showMessageDialog(null, "Errore durante la cancellazione","Errore", 0);
					} catch (IllegalArgumentException exc){
						JOptionPane.showMessageDialog(null, "Codice non valido","Errore", 0);
					}
				}
			}
		});


		//info->Chi siamo
		view.mntmAboutFlySmart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null,"FlySmart\nVersion: 2.0.0\n(c) Copyright FlySmart contributors and others 2000, 2014.\nAll rights reserved.\n Visit http://www.flysmart.it/","About FlySmart", 3);
			}
		});


		//conferma di passeggeri:aeroporti
		view.buttonPasseggeriCercaVoli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int p=0,a=0;
				try{
					p = ((Aeroporto)view.comboPasseggeriAeroportoPartenza.getSelectedItem()).getId();  //codice aeroporto di partenza
					a = ((Aeroporto)view.comboPasseggeriAeroportoArrivo.getSelectedItem()).getId();  //codice aeroporto di arrivo
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Selezionare gli aeroporti","Errore", 1);
				}
				if(p!=0 && a!=0 && p!=a){
					List<Volo> voli=null;
					try {
						voli = serv.getVoli(p,a); //carico la lista dei voli
					} catch (RemoteException e) {
						JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Errore", 0);
					}
					passaAdElencoVoli(voli,0);
				}

			}

		});

		view.buttonPalletCercaVoli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int p=0,a=0;
				try{
					p = ((Aeroporto)view.comboPalletAeroportoPartenza.getSelectedItem()).getId();  //codice aeroporto di partenza
					a = ((Aeroporto)view.comboPalletAeroportoArrivo.getSelectedItem()).getId();  //codice aeroporto di arrivo
				}catch(Exception e){
					JOptionPane.showMessageDialog(null,"Selezionare gli aeroporti","Errore", 1);
				}
				if(p!=0 && a!=0 && p!=a){
					List<Volo> voli=null;
					try {
						voli = serv.getVoli(p,a); //carico la lista dei voli
					} catch (RemoteException e) {
						JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Error", 0);
					}
					passaAdElencoVoli(voli,1);
				}
			}
		});

	}


	/**
	 * Aggiungo i listner agli oggetti della facciata passeggeri:voli
	 */
	private void registraControllerFase2Passeggeri() {  

		//annulla passeggeri:voli
		view.buttonPasseggeriAnnullaVolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriAeroporti");
			}

		});

		//confermo passeggeri:voli
		view.buttonPasseggeriConfermaVolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.voloSelezionatoPasseggeri = ((Volo)view.comboVoliDisponibili.getSelectedItem());
				view.setPasseggeriPasseggeri();
				registraControllerFase3APasseggeri();
				view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriPasseggeri");
			}
		});

		//cambio il volo scelto per i passeggeri:voli 
		view.comboVoliDisponibili.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Volo voloCorrente = ((Volo)view.comboVoliDisponibili.getSelectedItem());
				view.labelCodiceAeroportoPartenzaPasseggeri.setText("<html><b style='color:#242589'>Codice aeroporto di partenza: </b>"+" "+voloCorrente.getAeroportoPartenza()+"</html>");
				view.labelCodiceAeroportoArrivoPasseggeri.setText("<html><b style='color:#242589'>Codice aeroporto di arrivo: </b>"+" "+voloCorrente.getAeroportoDestinazione()+"</html>");
				view.labelDataOraVoloPasseggeri.setText("<html><b style='color:#242589'>Data e ora volo: </b>"+" "+voloCorrente.getDataOraString()+"</html>");
				view.labelStatoVoloPasseggeri.setText("<html><b style='color:#242589'>Stato volo: </b>"+" "+voloCorrente.getStato()+"</html>");
				view.labelPasseggeriDisponibili.setText("<html><b style='color:#242589'>Numero posti disponibili: </b>"+" "+voloCorrente.getPostiDisponibili()+"</html>");
				view.labelPrezzoPasseggeri.setText("<html><b style='color:#242589'>Prezzo singolo: </b>"+" "+voloCorrente.getPrezzoPasseggero()+" &euro;</html>");
			}
		});



	}



	/**
	 * Aggiungo i listner agli oggetti della facciata passeggeri:passeggeri
	 */
	private void registraControllerFase3APasseggeri() { 

		// prossimo passeggero
		view.buttonPasseggeriProssimo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.passeggeroSuccessivo(); 
			}

		});

		// passeggero precedente
		view.buttonPasseggeriPrecedente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.currentIndex!=0){
					view.passeggeroPrecedente();
				}
			}

		});

		//conferma passeggeri:passeggeri
		view.buttonPasseggeriConfermaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if( (view.controllaCampi() || (view.campiVuoti() && view.listaPasseggeri.size()!=0))){ //se sono pieni oppure vuoti
					view.passeggeroSuccessivo(); 
					if((JOptionPane.showConfirmDialog(null,"<html>Vuoi confermare la spesa di "+view.prezzoTotaleVolo+" &euro;?</html>","Conferma prenotazione passeggeri",JOptionPane.YES_NO_OPTION,JOptionPane.NO_OPTION) == JOptionPane.OK_OPTION)){
						try {
							ObjectId idGruppo = serv.prenotaPasseggero(view.listaPasseggeri, view.voloSelezionatoPasseggeri.getId())[0];
							JOptionPane.showMessageDialog(null,"Prenotazione effettuata con successo;\n Codice prenotazione: "+idGruppo.toString()+" ","Conferma prenotazione", 1);
							view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriAeroporti"); //torno alla schermata iniziale
						} catch (SeatsSoldOutException | RemoteException e) {
							JOptionPane.showMessageDialog(null,"Volo non trovato, ritentare","Errore", 0);
						}
					}
				}else{
					JOptionPane.showMessageDialog(null,"Completare l'inserimento dei dati","Errore", 0);
				}
			}
		});

		//annulla passeggeri:passeggeri
		view.buttonPasseggeriAnnullaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriAeroporti");
			}
		});

	}



	/**
	 * Aggiungo i listner agli oggetti della facciata pallet:voli
	 */
	private void registraControllerFase2Pallet() {


		//confermo pallet:voli
		view.buttonPalletConfermaVolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.voloSelezionatoPallet = ((Volo)view.comboVoliDisponibili.getSelectedItem());  //codice del volo
				view.setPalletPallet();
				registraControllerFase3Pallet();
				view.cardPallet.show(view.panelPallet,"panelPalletPallet");
			}

		});


		//annulla pallet:voli
		view.buttonPalletAnnullaVolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.cardPallet.show(view.panelPallet,"panelPalletAeroporti");
			}
		});


		//cambio il volo scelto per i pallet:voli 
		view.comboVoliDisponibili.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				Volo voloCorrente = ((Volo)view.comboVoliDisponibili.getSelectedItem());
				view.labelCodiceAeroportoPartenzaPallet.setText("<html><b style='color:#242589'>Codice aeroporto di partenza: </b>"+" "+voloCorrente.getAeroportoPartenza()+"</html>");
				view.labelCodiceAeroportoArrivoPallet.setText("<html><b style='color:#242589'>Codice aeroporto di arrivo: </b>"+" "+voloCorrente.getAeroportoDestinazione()+"</html>");
				view.labelDataOraVoloPallet.setText("<html><b style='color:#242589'>Data e ora volo: </b>"+" "+voloCorrente.getDataOraString()+"</html>");
				view.labelStatoVoloPallet.setText("<html><b style='color:#242589'>Stato volo: </b>"+" "+voloCorrente.getStato()+"</html>");
				view.labelPalletDisponibili.setText("<html><b style='color:#242589'>Numero pallet disponibili: </b>"+" "+voloCorrente.getPalletDisponibili()+"</html>");
				view.labelPrezzoPallet.setText("<html><b style='color:#242589'>Prezzo al kg: </b>"+" "+voloCorrente.getPrezzoPallet()+" &euro;</html>");
			}
		});

	}




	/**
	 * Aggiungo i listner agli oggetti della facciata pallet:pallet
	 */


	private boolean controllaInputPalletPallet(){
		if(view.textFieldPesoPallet.getText().compareTo("")!=0 && view.textFieldTargaPallet.getText().compareTo("")!=0 && view.textFieldPesoPallet.getText().matches ("\\d+") && Integer.parseInt(view.textFieldPesoPallet.getText())>=600 && Integer.parseInt(view.textFieldPesoPallet.getText())<=1400){
			if((JOptionPane.showConfirmDialog(null,"Vuoi confermare?","Conferma prenotazione pallet",JOptionPane.YES_NO_OPTION,JOptionPane.NO_OPTION) == JOptionPane.OK_OPTION)){
				return true;
			}
		}else{
			JOptionPane.showMessageDialog(null,"Errore nei dati","Errore", 0);
		}
		return false;
	}

	private void registraControllerFase3Pallet() { 


		//confermo pallet:pallet
		view.buttonPalletConfermaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(controllaInputPalletPallet()){
					view.listaPallet.add(new Pallet(Integer.parseInt(view.textFieldPesoPallet.getText()),view.textFieldTargaPallet.getText(),view.voloSelezionatoPallet.getId(),null,null));
					try {
						ObjectId idPallet = serv.prenotaPallet(view.listaPallet,view.voloSelezionatoPallet.getId())[0];
						JOptionPane.showMessageDialog(null,"Prenotazione effettuata con successo;\n Codice prenotazione: "+idPallet.toString()+" ","Conferma prenotazione", 1);
						view.cardPallet.show(view.panelPallet,"panelPalletAeroporti"); //torno alla schermata iniziale
					} catch (SeatsSoldOutException | RemoteException e) {
						JOptionPane.showMessageDialog(null,"Volo non trovato, ritentare","Errore", 0);
					}
				}
			}
		});

		//annullo pallet:pallet
		view.buttonPalletAnnullaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.cardPallet.show(view.panelPallet,"panelPalletAeroporti");
			}

		});
	}




	/**
	 * selezionePasseggeriDaEliminare
	 *
	 * @param params oggetti da visualizzare nella confirm dialog tra cui un messaggio e le varie checkbox
	 * @param listaCB la lista delle check box
	 * @param listaPasseggeri la lista dei passeggeri
	 */
	private void selezionePasseggeriDaEliminare(Object[] params,List<JCheckBox> listaCB,List<Passeggero> listaPasseggeri,String codicePrenotazione){
		if (JOptionPane.showConfirmDialog(null, params, "Rimozione passeggeri", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) { 
			int passo=0;
			for (JCheckBox cb : listaCB){
				if(!cb.isSelected()){
					listaPasseggeri.remove(passo); //rimuovo dalla lista quelli da tenere
				}else{
					passo++; //quando rimuovo non devo andare avanti con il passo
				}
			}
			if(passo!=0){ //ne ho selezionato almeno uno
				String pass = "";
				for (Passeggero p : listaPasseggeri){
					pass += "\n"+p.getCognome()+" " +p.getNome();
				}
				boolean ok = false;
				try {
					ok = serv.cancellaPasseggeri(listaPasseggeri);
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Errore", 0);
				} catch (DeleteException e) {
					JOptionPane.showMessageDialog(null, "Errore durante la cancellazione","Errore", 0);
				}
				if(ok){
					JOptionPane.showMessageDialog(null, "Dalla prenotazione "+ codicePrenotazione +" sono stati rimossi i passeggeri:"+pass,"Prenotazione eliminata", 1);
				}
			}
		}
	}

	/**
	 * Passa ad elenco voli.
	 *
	 * @param voli the voli
	 * @param tipo 0 per passeggeri 1 per pallet
	 */
	private void passaAdElencoVoli(List<Volo> voli,int tipo){
		if(voli.size()==0){
			JOptionPane.showMessageDialog(null, "Nessun volo trovato","Errore", 0);
		}else{
			if(tipo==0){
				view.aeroportoPartenzaPasseggeri=((Aeroporto)view.comboPasseggeriAeroportoPartenza.getSelectedItem()).getNome();
				view.aeroportoArrivoPasseggeri=((Aeroporto)view.comboPasseggeriAeroportoArrivo.getSelectedItem()).getNome();
				for( ActionListener al : view.comboVoliDisponibili.getActionListeners() ) {
					view.comboVoliDisponibili.removeActionListener( al );
				}
				view.setPasseggeriVoli(voli);  //carico gli oggetti nella facciata passeggeri:voli
				registraControllerFase2Passeggeri(); //registro i listner della facciata passeggeri:voli
				view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriVoli"); //visualizzo il pannello passeggeri:voli passandogli la lista dei voli
			}else{
				view.aeroportoPartenzaPallet=((Aeroporto)view.comboPalletAeroportoPartenza.getSelectedItem()).getNome();
				view.aeroportoArrivoPallet=((Aeroporto)view.comboPalletAeroportoArrivo.getSelectedItem()).getNome();
				for( ActionListener al : view.comboVoliDisponibili.getActionListeners() ) {
					view.comboVoliDisponibili.removeActionListener( al );
				}
				view.setPalletVoli(voli);  //carico gli oggetti nella facciata pallet:voli
				registraControllerFase2Pallet(); //registro i listner della facciata pallet:voli
				view.cardPallet.show(view.panelPallet,"panelPalletVoli"); //visualizzo il pannello pallet:voli passandogli la lista dei voli
			}
		}
	}
}
