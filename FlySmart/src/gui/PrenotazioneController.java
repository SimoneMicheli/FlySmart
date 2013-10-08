package gui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

import exception.FlightNotFoundException;
import exception.SeatsSoldOutException;

import javax.swing.JOptionPane;

import model.Aeroporto;
import model.Volo;
import network.ServerInterface;


public class PrenotazioneController{

	PrenotazioneView view;
	ServerInterface serv;

	public PrenotazioneController(ServerInterface serv){
		view= new PrenotazioneView();
		this.serv=serv;
		view.setVisible(true);
		initController();
		registraController();
	}

	public void initController(){
		List<Aeroporto> aeroporti=null;
		try {
			aeroporti = serv.getAeroporti();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.setPasseggeriAeroporti(aeroporti);

	}


	public void registraController() {

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
				if(view.passeggeri){
					view.passeggeri=false;
					view.cardEsterno.show(view.panelCardLayoutEsterno,"panelPallet");
					System.out.println("Pallet!");
				}else{
					view.passeggeri=true;
					view.cardEsterno.show(view.panelCardLayoutEsterno,"panelPasseggeri");
					System.out.println("Passeggeri!");
				}
			}

		});

		//info->about us
		view.mntmAboutUs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null,"Siamo 3 geni","Ha ragione questo qui sotto.. Zizi", 1);
			}

		});


		//cerco il volo per i passeggeri
		view.buttonPasseggeriPasseggeriCercaVoli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int p = ((Aeroporto)view.comboPasseggeriAeroportoPartenza.getSelectedItem()).getId();
				int a = ((Aeroporto)view.comboPasseggeriAeroportoArrivo.getSelectedItem()).getId();
				if(p==a){
					JOptionPane.showMessageDialog(null,"Sei gia arrivato!","Complimenti!", 1);
				}else{
					if(view.comboPasseggeriAeroportoArrivo.getSelectedItem().toString()!="" && view.comboPasseggeriAeroportoPartenza.getSelectedItem().toString()!=""){
						List<Volo> voli=null;
						try {
							voli = serv.getVoli(p,a);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
						view.setPasseggeriVoli(voli);
						registraControllerFase2Passeggeri();
						view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriVoli");
					}
				}


			}

		});

		//cerco il volo per i pallet
		view.buttonPalletPasseggeriCercaVoli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.comboPalletAeroportoArrivo.getSelectedItem().toString()!="" && view.comboPalletAeroportoPartenza.getSelectedItem().toString()!=""){
					view.setPalletVoli();
					registraControllerFase2Pallet();
					view.cardPallet.show(view.panelPallet,"panelPalletVoli");
				}

			}

		});


	}


	private void registraControllerFase2Passeggeri() {  
		//non potevo registrare i pulsanti prima ancora di averli disegnati; non potevo disegnarli subito perche non sapevo che volo avrebbe scelto etc..


		//annulla la fase due per i passeggeri
		view.buttonPasseggeriAnnullaVolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriAeroporti");

			}

		});





		//confermo il volo (fase 2) per i passeggeri
		view.buttonPasseggeriConfermaVolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.buttonGroupPasseggeriVoli.getSelection()!=null){

					//risalgo all'id del volo
					String delimiter = " ";
					String[] temp = view.getSelectedButtonText(view.buttonGroupPasseggeriVoli).split(delimiter);
					view.idVoloSelezionato= Integer.parseInt(temp[0]);


					//richiamo la fase 3
					view.setPasseggeriPasseggeri();
					registraControllerFase3Passeggeri();
					view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriPasseggeri");
				}
			}

		});


	}





	private void registraControllerFase2Pallet() {
		//confermo il volo (fase 2) per i pallet
		view.buttonPalletConfermaVolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.buttonGroupPalletVoli.getSelection()!=null){
					view.setPalletPallet();
					registraControllerFase3Pallet();
					view.cardPallet.show(view.panelPallet,"panelPalletPallet");
				}
			}

		});
		//annulla la fase due per i pallet
		view.buttonPalletAnnullaVolo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.cardPallet.show(view.panelPallet,"panelPalletAeroporti");

			}

		});
	}

	private void registraControllerFase3Passeggeri() { 



		//annulla la fase tre per i passeggeri
		view.buttonPasseggeriAnnullaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriAeroporti");
				//view.setPasseggeriPasseggeri();

			}

		});







		// prossimo passeggero
		view.buttonPasseggeriProssimo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.passeggeroSuccessivo(); //lo aggiungo all'arraylist

			}

		});


		//svuota
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


		//precedente
		view.buttonPasseggeriPrecedente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.currentIndex!=0){
					view.passeggeroPrecedente();
				}
			}

		});





		//conferma definitiva prenotazione per passeggeri
		view.buttonPasseggeriConfermaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.controllaCampi() || (view.campiVuoti() && view.listaPasseggeri.size()!=0)){
					view.updateOrSavePasseggero();
					view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriAeroporti");


					try {
						serv.prenotaPasseggero(view.listaPasseggeri, view.idVoloSelezionato);
						JOptionPane.showMessageDialog(null,"Prenotazione effettuata con successo","", 0);
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Connessione persa","Errore", 1);
						System.exit(0);
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();JOptionPane.showMessageDialog(null,"Errore di input","Errore", 1);
					} catch (FlightNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();JOptionPane.showMessageDialog(null,"Volo non trovato, ritentare","Errore", 1);
					} catch (SeatsSoldOutException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,"I posti non sono più disponibili","Errore", 1);
					}


				}else{
					JOptionPane.showMessageDialog(null,"","Errore", 1);

				}


				/*
				//creo la lista passeggeri xml per prova
				XMLCreate<Passeggero> xml = new XMLCreate<Passeggero>();
				Document d = xml.createFlySmartDocument(view.listaPasseggeri);
				try {
					xml.printDocument(d, "passeggeri");
				} catch (IOException e) {
					e.printStackTrace();
				}
				 */



			}
		});


	}

	private void registraControllerFase3Pallet() { 
		//conferma definitiva prenotazione per pallet
		view.buttonPalletConfermaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//fase di controllo
				if(view.textFieldPesoPallet.getText().compareTo("")!=0 && view.textFieldTargaPallet.getText().compareTo("")!=0 ){
					JOptionPane.showMessageDialog(null,view.textFieldPesoPallet.getText()+" "+view.textFieldTargaPallet.getText(),"Confermare la prenotazione?", 1);
				}
			}

		});

		//annulla la fase tre per i pallet pi
		view.buttonPalletAnnullaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				view.cardPallet.show(view.panelPallet,"panelPalletAeroporti");
			}

		});
	}





}




