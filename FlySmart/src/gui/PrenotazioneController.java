package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import org.w3c.dom.*;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import xml.XMLCreate;

import model.Aeroporto;
import model.Passeggero;
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
				System.out.println("Siamo 3 coglioni");
				JOptionPane.showMessageDialog(null,"Siamo 3 coglioni","Ha ragione questo qui sotto.. Zizi", 1);
			}

		});



		//scelto un aereoporto di partenza per i passeggeri
		view.comboPasseggeriAeroportoPartenza.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1 && e.getItem().toString().compareTo("")!=0){ //selezionato un non nullo
					view.comboPasseggeriAeroportoArrivo.removeItem(e.getItem().toString());  //rimuovo dagli arrivi
					if(e.getItem().toString().compareTo(view.comboPasseggeriAeroportoArrivo.getSelectedItem().toString())==0){ //se seleziono
						view.comboPasseggeriAeroportoArrivo.setSelectedItem(""); //seleziono il nulla come arrivo
					}
				}
				if(e.getStateChange()==2 && e.getItem().toString().compareTo("")!=0){ //selezionato un non nullo
					view.comboPasseggeriAeroportoArrivo.addItem(e.getItem().toString());  //inserisco negli arrivi
				}
			}

		});

		//scelto un aereoporto di partenza per i pallet
		view.comboPalletAeroportoPartenza.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1 && e.getItem().toString().compareTo("")!=0){ //selezionato un non nullo
					view.comboPalletAeroportoArrivo.removeItem(e.getItem().toString());  //rimuovo dagli arrivi
					if(e.getItem().toString().compareTo(view.comboPalletAeroportoArrivo.getSelectedItem().toString())==0){ //se seleziono
						view.comboPalletAeroportoArrivo.setSelectedItem(""); //seleziono il nulla come arrivo
					}
				}
				if(e.getStateChange()==2 && e.getItem().toString().compareTo("")!=0){ //selezionato un non nullo
					view.comboPalletAeroportoArrivo.addItem(e.getItem().toString());  //inserisco negli arrivi
				}
			}

		});

		//cerco il volo per i passeggeri
		view.buttonPasseggeriPasseggeriCercaVoli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null,"Hai scelto di partire da "+((Aeroporto)view.comboPasseggeriAeroportoPartenza.getSelectedItem()).getId()+" e arrivare a "+((Aeroporto)view.comboPasseggeriAeroportoArrivo.getSelectedItem()).getId(),"Errore", 1);
				if(view.comboPasseggeriAeroportoArrivo.getSelectedItem().toString()!="" && view.comboPasseggeriAeroportoPartenza.getSelectedItem().toString()!=""){
					view.setPasseggeriVoli(); //da fare per ricaricare tutto
					registraControllerFase2Passeggeri();
					view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriVoli");
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


		//precedente
		view.buttonPasseggeriPrecedente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.currentIndex!=0){
					view.passeggeroPrecedente(); //lo aggiungo all'arraylist
				}
			}

		});






		//conferma definitiva prenotazione per passeggeri
		view.buttonPasseggeriConfermaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.controllaCampi()){
					view.updateOrSavePasseggero();
					view.cardPasseggeri.show(view.panelPasseggeri,"panelPasseggeriAeroporti");
					JOptionPane.showMessageDialog(null,view.listaPasseggeri.toString(),"Dato", 1);
				}
			
				//creo la lista passeggeri xml per prova
				XMLCreate<Passeggero> xml = new XMLCreate<Passeggero>();
				Document d = xml.createFlySmartDocument(view.listaPasseggeri);
				try {
					xml.printDocument(d, "passeggeri");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			
				
				
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




