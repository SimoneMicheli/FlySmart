package guiPrenotazione;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.List;
import exception.FlightNotFoundException;
import exception.SeatsSoldOutException;
import javax.swing.JOptionPane;
import model.Aeroporto;
import model.Pallet;
import model.Volo;
import network.ServerInterface;


/**Il controller della vista di prenotazione
 * @author Demarinis - Micheli - Scarpellini
 */
public class PrenotazioneController{

	/** Il riferimento alla vista */
	PrenotazioneView view;

	/** L'oggetto per la connessione con il server */
	ServerInterface serv;

	/**
	 * Crea un controller per la fase di prenotazione
	 *
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
			e.printStackTrace();
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
				if(view.passeggeri){ //se sono su passeggeri
					view.passeggeri=false;
					view.cardEsterno.show(view.panelCardLayoutEsterno,"panelPallet");
				}else{ //se sono su pallet
					view.passeggeri=true;
					view.cardEsterno.show(view.panelCardLayoutEsterno,"panelPasseggeri");
				}
			}

		});

		//info->Chi siamo
		view.mntmCopyright.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null,"Copyright by Demarinis Gianluca, Micheli Simone and Scarpellini Alan ","Copyright", 3);
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
				if(p!=0 && a!=0){
					if(p==a){ //scelta destinazione=partenza e il try ha funzionato
						JOptionPane.showMessageDialog(null,"Sei gia arrivato!","Complimenti!", 1);
					}else{
						List<Volo> voli=null;
						try {
							voli = serv.getVoli(p,a); //carico la lista dei voli
						} catch (RemoteException e) {
							JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Error", 0);
							e.printStackTrace();
						}
						view.aeroportoPartenzaPasseggeri=((Aeroporto)view.comboPasseggeriAeroportoPartenza.getSelectedItem()).getNome();
						view.aeroportoArrivoPasseggeri=((Aeroporto)view.comboPasseggeriAeroportoArrivo.getSelectedItem()).getNome();
						view.setPasseggeriVoli(voli);  //carico gli oggetti nella facciata passeggeri:voli
						registraControllerFase2Passeggeri(); //registro i listner della facciata passeggeri:voli
						view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriVoli"); //visualizzo il pannello passeggeri:voli passandogli la lista dei voli

					}
				}

			}

		});

		//conferma di pallet:aeroporti

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
				if(p!=0 && a!=0){
					if(p==a){ //scelta destinazione=partenza e il try ha funzionato
						JOptionPane.showMessageDialog(null,"Sei gia arrivato!","Complimenti!", 1);
					}else{
						List<Volo> voli=null;
						try {
							voli = serv.getVoli(p,a); //carico la lista dei voli
						} catch (RemoteException e) {
							JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Error", 0);
							e.printStackTrace();
						}
						view.aeroportoPartenzaPallet=((Aeroporto)view.comboPalletAeroportoPartenza.getSelectedItem()).getNome();
						view.aeroportoArrivoPallet=((Aeroporto)view.comboPalletAeroportoArrivo.getSelectedItem()).getNome();
						view.setPalletVoli(voli);  //carico gli oggetti nella facciata pallet:voli
						registraControllerFase2Pallet(); //registro i listner della facciata pallet:voli
						view.cardPallet.show(view.panelPallet,"panelPalletVoli"); //visualizzo il pannello pallet:voli passandogli la lista dei voli

					}
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
				view.voloSelezionatoPasseggeri = ((Volo)view.comboVoliDisponibili.getSelectedItem());  //codice del volo
				view.setPasseggeriPasseggeri();
				registraControllerFase3Passeggeri();
				view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriPasseggeri");
			}
		});

	}



	/**
	 * Aggiungo i listner agli oggetti della facciata passeggeri:passeggeri
	 */
	private void registraControllerFase3Passeggeri() { 

		// prossimo passeggero
		view.buttonPasseggeriProssimo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.passeggeroSuccessivo(); 

			}

		});

		//precedente
		view.buttonPasseggeriPrecedente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.currentIndex!=0){
					view.passeggeroPrecedente();
				}
			}

		});

		//svuoto i campi del passeggero
		view.buttonPasseggeriReset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.textPasseggeriNome.setText("");
				view.textPasseggeriCognome.setText("");
				view.comboBoxGiorno.setSelectedIndex(0);
				view.comboBoxMese.setSelectedIndex(0);
				view.comboBoxAnno.setSelectedIndex(0);
			}

		});


		//conferma passeggeri:passeggeri
		view.buttonPasseggeriConfermaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.controllaCampi() || (view.campiVuoti() && view.listaPasseggeri.size()!=0)){ //se sono pieni oppure vuoti
					view.passeggeroSuccessivo(); 
					if (JOptionPane.showConfirmDialog(null,"Vuoi confermare la spesa di "+view.prezzoTotaleVolo+" €?","Conferma prenotazione passeggeri",JOptionPane.YES_NO_OPTION,JOptionPane.NO_OPTION) == JOptionPane.OK_OPTION) {
						try {
							serv.prenotaPasseggero(view.listaPasseggeri, view.voloSelezionatoPasseggeri.getId());
							JOptionPane.showMessageDialog(null,"Prenotazione effettuata con successo","", 1);
							view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriAeroporti"); //torno alla schermata iniziale
						} catch (RemoteException e) {
							JOptionPane.showMessageDialog(null,"Connessione persa","Errore", 1);
							System.exit(0);
						} catch (FlightNotFoundException e) {
							JOptionPane.showMessageDialog(null,"Volo non trovato, ritentare","Errore", 0);
						} catch (SeatsSoldOutException e) {
							JOptionPane.showMessageDialog(null,"I posti non sono più disponibili","Errore", 0);
						}
					}else{

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
		 
	}





	/**
	 * Aggiungo i listner agli oggetti della facciata pallet:pallet
	 */

	private void registraControllerFase3Pallet() { 
		

		//confermo pallet:pallet
		view.buttonPalletConfermaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.textFieldPesoPallet.getText().compareTo("")!=0 && view.textFieldTargaPallet.getText().compareTo("")!=0 ){
					view.listaPallet.add(new Pallet(null,Double.parseDouble(view.textFieldPesoPallet.getText()),view.textFieldTargaPallet.getText(),view.voloSelezionatoPallet.getId(),null,null));
					if (JOptionPane.showConfirmDialog(null,"Vuoi confermare?","Conferma prenotazione pallet",JOptionPane.YES_NO_OPTION,JOptionPane.NO_OPTION) == JOptionPane.OK_OPTION) {
						try {
							serv.prenotaPallet(view.listaPallet,view.voloSelezionatoPallet.getId());
							JOptionPane.showMessageDialog(null,"Prenotazione effettuata con successo","", 1);
							view.cardPallet.show(view.panelPallet,"panelPalletAeroporti"); //torno alla schermata iniziale
						} catch (RemoteException e) {
							JOptionPane.showMessageDialog(null,"Connessione persa","Errore", 1);
							System.exit(0);
						} catch (FlightNotFoundException e) {
							JOptionPane.showMessageDialog(null,"Volo non trovato, ritentare","Errore", 0);
						} catch (SeatsSoldOutException e) {
							JOptionPane.showMessageDialog(null,"I posti non sono più disponibili","Errore", 0);
						}
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

}