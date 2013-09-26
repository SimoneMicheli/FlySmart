package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.Aeroporto;
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
			aeroporti = serv.getAirports();
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


		//file reset fa contentpanel.removeall e poi init().. forse serve repaint


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
				view.panelPasseggeriPasseggeriInterno.removeAll();
				view.numeroPasseggeri=0;
				view.nomiPasseggeri.clear();
				view.cognomiPasseggeri.clear();
				view.giornoNascitaPasseggeri.clear();
				view.meseNascitaPasseggeri.clear();
				view.annoNascitaPasseggeri.clear();
				view.nomiPasseggeri.clear();
				view.sessoPasseggeri.clear();
				view.setPasseggeriPasseggeri();
			}

		});







		// aggiungi passeggero
		view.buttonPasseggeriAggiungiPasseggero.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				if(view.numeroPasseggeri<3){
					view.addPasseggero();
				}else{
					JOptionPane.showMessageDialog(null, "Numero massimo di passeggeri: 3","Hai superato il numero massimo di passeggeri", 1);
				}
			}

		});






		//conferma definitiva prenotazione per passeggeri
		view.buttonPasseggeriConfermaPrenotazione.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//fase di controllo
				Iterator<JTextField> iterNomi = view.nomiPasseggeri.iterator();
				Iterator<JTextField> iterCognomi = view.cognomiPasseggeri.iterator();
				Iterator<JComboBox> iterGiorno = view.giornoNascitaPasseggeri.iterator();
				Iterator<JComboBox> iterMese = view.meseNascitaPasseggeri.iterator();
				Iterator<JComboBox> iterAnno = view.annoNascitaPasseggeri.iterator();
				Iterator<JComboBox> iterSesso = view.sessoPasseggeri.iterator();
				boolean completo=true; //mi dice se il modulo e incompleto
				boolean first=true; //mi dice se l'iterator è al primo passeggero
				while (iterNomi.hasNext() && iterCognomi.hasNext() && iterGiorno.hasNext() && iterMese.hasNext() && iterAnno.hasNext() && iterSesso.hasNext()) {
					String nome = iterNomi.next().getText();
					String cognome = iterCognomi.next().getText();
					String giorno = iterGiorno.next().getSelectedItem().toString();
					String mese = iterMese.next().getSelectedItem().toString();
					String anno = iterAnno.next().getSelectedItem().toString();
					String sesso = iterSesso.next().getSelectedItem().toString();
					if(nome.compareTo("")==0 || cognome.compareTo("")==0 || sesso.compareTo("")==0 || giorno.compareTo("")==0 || mese.compareTo("")==0 || anno.compareTo("")==0 ){
						if(first || !(nome.compareTo("")==0 && cognome.compareTo("")==0 && sesso.compareTo("")==0 && giorno.compareTo("")==0 && mese.compareTo("")==0 && anno.compareTo("")==0)){ //se, dopo aver trovato uno spazio vuoto, siamo al primo oppure ha tutti gli spazi vuoti 
							completo=false;
							break;
						}
					}
					first=false;
				}


				//fase di invio
				if(completo){
					iterNomi = view.nomiPasseggeri.iterator();
					iterCognomi = view.cognomiPasseggeri.iterator();
					iterGiorno = view.giornoNascitaPasseggeri.iterator();
					iterMese = view.meseNascitaPasseggeri.iterator();
					iterAnno = view.annoNascitaPasseggeri.iterator();
					iterSesso = view.sessoPasseggeri.iterator();
					String testo="";
					while (iterNomi.hasNext()) {
						String nome = iterNomi.next().getText();
						String cognome = iterCognomi.next().getText();
						String giorno = iterGiorno.next().getSelectedItem().toString();
						String mese = iterMese.next().getSelectedItem().toString();
						String anno = iterAnno.next().getSelectedItem().toString();
						String sesso = iterSesso.next().getSelectedItem().toString();
						if(nome.compareTo("")!=0){
							testo += "Nome: "+nome+"        Cognome: "+cognome+"        Età:"+calcolaEta(giorno, mese, anno)+"        Sesso:"+sesso+"\n";
						}
					}
					//mettere conferma annulla
					JOptionPane.showMessageDialog(null,testo,"Confermare la prenotazione?", 1);

				}

			}
			// creo oggetto, valido con metodo nella classe stessa.. 

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


	//calcola l'età a partire da una data di nascita						 spostare su modello
	private int calcolaEta(String g, String m, String a){
		Calendar c = Calendar.getInstance();
		int anni = c.get(Calendar.YEAR)-Integer.parseInt(a);
		if(Integer.parseInt(m)>1+c.get(Calendar.MONTH) || (Integer.parseInt(m)==1+c.get(Calendar.MONTH) && Integer.parseInt(g)>=c.get(Calendar.DAY_OF_MONTH))){ //1+ perche gennaio è lo zero
			return anni-1;
		}
		return anni;
	}


}




