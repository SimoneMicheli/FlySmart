package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import model.Aeroporto;
import model.Passeggero;
import model.SessoIndex;


@SuppressWarnings("serial")
public class PrenotazioneView extends View {

	//pannelli livello 1
	protected JPanel panelCardLayoutEsterno=new JPanel(); //pannello per switchare da passeggeri a pallet
	//pannelli livello 2
	protected JPanel panelPasseggeri=new JPanel(); //pannello contentente tutta l'applicazione per i passeggeri
	protected JPanel panelPallet=new JPanel(); //pannello contentente tutta l'applicazione per i pallett
	//pannelli livello 3 passeggeri
	protected JPanel panelPasseggeriAeroporti = new JPanel(); //pannello contenente gli aeroporti per l'applicazione passeggeri (3aa)
	protected JPanel panelPasseggeriVoli = new JPanel(); //pannello contenente i voli per l'applicazione passeggeri (3ab)
	protected JPanel panelPasseggeriVoliInterno = new JPanel(); //pannello contenente proprio l'elenco dei voli
	protected JPanel panelPasseggeriPasseggeri = new JPanel(); //pannello contenente i dati dei passeggeri per l'applicazione passeggeri (3ac)
	protected JPanel panelPasseggeriPasseggeriInterno = new JPanel(); //pannello contenente proprio l'elenco dei passeggeri

	//pannelli livello 3 pallet
	protected JPanel panelPalletAeroporti = new JPanel(); //pannello contenente gli aeroporti per l'applicazione pallet (3ba)
	protected JPanel panelPalletVoli = new JPanel(); //pannello contenente i voli per l'applicazione pallet (3bb)
	protected JPanel panelPalletVoliInterno = new JPanel();  //pannello contenente proprio l'elenco dei voli
	protected JPanel panelPalletPallet = new JPanel(); //pannello contenente i dati dei pallet per l'applicazione pallet (3bc)


	//barra del menu
	JMenuBar menuBar = new JMenuBar();
	//pulsanti nel menu
	JMenu mnFile = new JMenu("File");
	JMenu mnModifica = new JMenu("Modifica");
	JMenu mnInfo = new JMenu("Info");
	//varie opzioni
	JMenuItem mntmExit = new JMenuItem("Esci");
	JMenuItem mntmSwitch = new JMenuItem("Cambia");
	JMenuItem mntmAboutUs = new JMenuItem("About Us");

	//scelte
	JComboBox comboPasseggeriAeroportoPartenza= new JComboBox();
	//add(comboPasseggeriAeroportoPartenza, BorderLayout.NORTH);
	JComboBox comboPasseggeriAeroportoArrivo = new JComboBox();
	JComboBox comboPalletAeroportoPartenza= new JComboBox();
	JComboBox comboPalletAeroportoArrivo = new JComboBox();



	JTextField textPasseggeriNome = new JTextField();
	JTextField textPasseggeriCognome = new JTextField();
	JComboBox comboBoxSesso = new JComboBox();
	JComboBox comboBoxGiorno = new JComboBox();
	JComboBox comboBoxMese = new JComboBox();
	JComboBox comboBoxAnno = new JComboBox();

	//button 1 livello
	protected JButton buttonPasseggeriPasseggeriCercaVoli;
	protected JButton buttonPalletPasseggeriCercaVoli;
	//button 2 livello
	protected JButton buttonPasseggeriConfermaVolo,buttonPasseggeriAnnullaVolo;
	protected JButton buttonPalletConfermaVolo,buttonPalletAnnullaVolo;
	//button 3 livello
	protected JButton buttonPasseggeriAnnullaPrenotazione,buttonPasseggeriConfermaPrenotazione,buttonPasseggeriProssimo,buttonPasseggeriPrecedente;
	protected JButton buttonPalletAnnullaPrenotazione,buttonPalletConfermaPrenotazione;

	//arraylist per i dati dei passeggeri
	protected ArrayList<JTextField> nomiPasseggeri = new ArrayList<JTextField>();
	protected ArrayList<JTextField> cognomiPasseggeri = new ArrayList<JTextField>();
	protected ArrayList<JComboBox> sessoPasseggeri = new ArrayList<JComboBox>();
	protected ArrayList<JComboBox> giornoNascitaPasseggeri = new ArrayList<JComboBox>();
	protected ArrayList<JComboBox> meseNascitaPasseggeri = new ArrayList<JComboBox>();
	protected ArrayList<JComboBox> annoNascitaPasseggeri = new ArrayList<JComboBox>();
	protected ArrayList<Passeggero> listaPasseggeri = new ArrayList<Passeggero>();

	//dati dei pallet
	protected JTextField textFieldTargaPallet = new JTextField();
	protected JTextField textFieldPesoPallet = new JTextField();


	//varie variabili
	Dimension dimensioneFinestra = new Dimension(508,440);
	protected CardLayout cardEsterno = new CardLayout();
	protected CardLayout cardPasseggeri = new CardLayout();
	protected CardLayout cardPallet = new CardLayout();
	protected boolean passeggeri; //true: sono nel pannello passeggeri; false: sono nel pannello pallet
	protected ButtonGroup buttonGroupPasseggeriVoli = new ButtonGroup();
	protected ButtonGroup buttonGroupPalletVoli = new ButtonGroup();
	JLabel labelTipoPrenotazione= new JLabel();




	public PrenotazioneView() {
		setGraphic(); //setto le cose della grafica della finestra
		setMenu(); //creo il menu
		setCardEsterne(); //creo le card esterne (passeggeri/pallett)
		setCardPasseggeri(); //creo le card interne dell'applicazione passeggeri (aeroporti/voli/passeggeri)
		setCardPallet(); //creo le card interne dell'applicazione pallet (aeroporti/voli/pallet)
		//setPasseggeriAeroporti(); //riempio livello 3aa
		//setPasseggeriVoli(); //riempio livello 3ab
		//setPasseggeriPasseggeri(); //riempio livello 3ac
		setPalletAeroporti(); //riempio livello 3ba
		//setPalletVoli(); //riempio livello 3bb
		//setPalletPallet(); //riempio livello 3bc
	}




	private void setGraphic(){
		lookAndFeel();
		setResizable(false);
		setTitle("Prenotazione FlySmart");
		Toolkit mioTKit = Toolkit.getDefaultToolkit();
		Dimension dimensioniSchermo = mioTKit.getScreenSize();
		int xFrame = (dimensioniSchermo.width - dimensioneFinestra.width) / 2;
		int yFrame = (dimensioniSchermo.height - dimensioneFinestra.height) / 2;
		setLocation(xFrame, yFrame);
		setSize(dimensioneFinestra.width, dimensioneFinestra.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setMenu(){
		setJMenuBar(menuBar);
		menuBar.add(mnFile);
		menuBar.add(mnModifica);
		menuBar.add(mnInfo);
		mnFile.add(mntmExit);
		mnModifica.add(mntmSwitch);
		mnInfo.add(mntmAboutUs);
	}

	private void setCardEsterne(){
		Container contentPane = getContentPane();
		contentPane.add(panelCardLayoutEsterno,BorderLayout.CENTER);
		panelCardLayoutEsterno.setLayout(cardEsterno);

		//pannello passeggeri
		panelPasseggeri.setLayout(cardPasseggeri);
		panelCardLayoutEsterno.add(panelPasseggeri,"panelPasseggeri");
		//pannello pallett
		panelPallet.setLayout(cardPallet);
		panelCardLayoutEsterno.add(panelPallet,"panelPallet");
		cardEsterno.show(panelCardLayoutEsterno,"panelPasseggeri");
		passeggeri=true;
	}

	private void setCardPasseggeri(){
		panelPasseggeriAeroporti.setLayout(null);
		panelPasseggeri.add(panelPasseggeriAeroporti,"panelPasseggeriAeroporti");
		panelPasseggeriVoli.setLayout(null);
		panelPasseggeri.add(panelPasseggeriVoli,"panelPasseggeriVoli");
		panelPasseggeriPasseggeri.setLayout(null);
		panelPasseggeri.add(panelPasseggeriPasseggeri,"panelPasseggeriPasseggeri");
		cardPasseggeri.show(panelPasseggeri,"panelPasseggeriAeroporti");
	}

	private void setCardPallet(){
		panelPalletAeroporti.setLayout(null);
		panelPallet.add(panelPalletAeroporti,"panelPalletAeroporti");
		panelPalletVoli.setLayout(null);
		panelPallet.add(panelPalletVoli,"panelPalletVoli");
		panelPalletPallet.setLayout(null);
		panelPallet.add(panelPalletPallet,"panelPalletPallet");
	}

	@SuppressWarnings("unchecked")
	public void setPasseggeriAeroporti(List<Aeroporto> aeroporti){

		JLabel labelTipoPrenotazione = new JLabel("Selezionare aeroporto di partenza e di arrivo");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPasseggeriAeroporti.add(labelTipoPrenotazione);


		JLabel labelAeroportoPartenza = new JLabel("Aereporto Partenza");
		labelAeroportoPartenza.setBounds(141, 110, 112, 14);
		panelPasseggeriAeroporti.add(labelAeroportoPartenza);


		comboPasseggeriAeroportoPartenza.setBounds(141, 127, 220, 20);
		comboPasseggeriAeroportoPartenza.addItem("");
		comboPasseggeriAeroportoArrivo.setBounds(141, 177, 220, 20);
		comboPasseggeriAeroportoArrivo.addItem("");

		Iterator i = aeroporti.iterator();
		while(i.hasNext()) {
			Aeroporto element = (Aeroporto) i.next();
			comboPasseggeriAeroportoPartenza.addItem(element.getNome());
			comboPasseggeriAeroportoArrivo.addItem(element.getNome());
		}

		panelPasseggeriAeroporti.add(comboPasseggeriAeroportoArrivo, BorderLayout.SOUTH);
		panelPasseggeriAeroporti.add(comboPasseggeriAeroportoPartenza, BorderLayout.NORTH);



		JLabel labelAeroportoArrivo = new JLabel("Aereporto Arrivo");
		labelAeroportoArrivo.setBounds(141, 160, 112, 14);
		panelPasseggeriAeroporti.add(labelAeroportoArrivo);


		buttonPasseggeriPasseggeriCercaVoli = new JButton("Cerca Voli");
		buttonPasseggeriPasseggeriCercaVoli.setBounds(141, 215, 220, 23);
		panelPasseggeriAeroporti.add(buttonPasseggeriPasseggeriCercaVoli);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 360, 482, 26);
		panelPasseggeriAeroporti.add(progressBar);
		progressBar.setValue(2);
	}

	public void setPasseggeriVoli(){

		JLabel labelTipoPrenotazione = new JLabel("Selezionare un volo:");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPasseggeriVoli.add(labelTipoPrenotazione);

		panelPasseggeriVoliInterno.setBounds(0, 40, 498, 280);
		panelPasseggeriVoliInterno.setLayout(null);
		panelPasseggeriVoli.add(panelPasseggeriVoliInterno);

		panelPasseggeriVoliInterno.removeAll();

		JRadioButton rdbtnNewRadioButton = new JRadioButton(Double.toString(Math.random()));
		buttonGroupPasseggeriVoli.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(6, 10, 480, 23);
		panelPasseggeriVoliInterno.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Orio Al Serio - Fiumicino             08:50 14/06/2013        Posti:23");
		buttonGroupPasseggeriVoli.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(6, 30, 480, 23);
		panelPasseggeriVoliInterno.add(rdbtnNewRadioButton_1);


		buttonPasseggeriConfermaVolo = new JButton("Conferma");
		buttonPasseggeriConfermaVolo.setBounds(167, 327, 89, 23);
		panelPasseggeriVoli.add(buttonPasseggeriConfermaVolo);

		buttonPasseggeriAnnullaVolo = new JButton("Annulla");
		buttonPasseggeriAnnullaVolo.setBounds(266, 327, 89, 23);
		panelPasseggeriVoli.add(buttonPasseggeriAnnullaVolo);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 360, 482, 26);
		panelPasseggeriVoli.add(progressBar);
		progressBar.setValue(50);

		repaint();
	}

	protected void setPasseggeriPasseggeri(){

		JLabel labelTipoPrenotazione = new JLabel("Inserire i dati dei passeggeri");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPasseggeriPasseggeri.add(labelTipoPrenotazione);


		panelPasseggeriPasseggeriInterno.setBounds(10, 40, 450, 241);
		panelPasseggeriPasseggeriInterno.setLayout(null);
		//Border lineaSpessa=BorderFactory.createLineBorder(Color.blue,5);
		//panelPasseggeriPasseggeriInterno.setBorder(lineaSpessa);
		panelPasseggeriPasseggeri.add(panelPasseggeriPasseggeriInterno);

		addPasseggero();

		buttonPasseggeriProssimo = new JButton(">");
		buttonPasseggeriProssimo.setBounds(167, 297, 188, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriProssimo);

		buttonPasseggeriPrecedente = new JButton("<");
		buttonPasseggeriPrecedente.setBounds(167, 257, 188, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriPrecedente);


		buttonPasseggeriAnnullaPrenotazione = new JButton("Annulla");
		buttonPasseggeriAnnullaPrenotazione.setBounds(266, 327, 89, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriAnnullaPrenotazione);

		buttonPasseggeriConfermaPrenotazione = new JButton("Conferma");
		buttonPasseggeriConfermaPrenotazione.setBounds(167, 327, 89, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriConfermaPrenotazione);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 360, 482, 26);
		panelPasseggeriPasseggeri.add(progressBar);
		progressBar.setValue(90);
	}


	private void setPalletAeroporti(){

		labelTipoPrenotazione.setText("Prenotazione Pallet 1/3");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPalletAeroporti.add(labelTipoPrenotazione);

		JLabel labelAeroportoPartenza = new JLabel("Aereporto Partenza");
		labelAeroportoPartenza.setBounds(141, 110, 112, 14);
		panelPalletAeroporti.add(labelAeroportoPartenza);

		comboPalletAeroportoPartenza.setBounds(141, 127, 220, 20);
		comboPalletAeroportoPartenza.addItem("");
		comboPalletAeroportoPartenza.addItem("Orio al Serio");
		panelPalletAeroporti.add(comboPalletAeroportoPartenza);

		JLabel labelAeroportoArrivo = new JLabel("Aereporto Arrivo");
		labelAeroportoArrivo.setBounds(141, 160, 112, 14);
		panelPalletAeroporti.add(labelAeroportoArrivo);

		comboPalletAeroportoArrivo.setBounds(141, 177, 220, 20);
		comboPalletAeroportoArrivo.addItem("");
		comboPalletAeroportoArrivo.addItem("Orio al Serio");
		panelPalletAeroporti.add(comboPalletAeroportoArrivo);

		buttonPalletPasseggeriCercaVoli = new JButton("Cerca Voli");
		buttonPalletPasseggeriCercaVoli.setBounds(141, 215, 220, 23);
		panelPalletAeroporti.add(buttonPalletPasseggeriCercaVoli);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 360, 482, 26);
		panelPalletAeroporti.add(progressBar);
		progressBar.setValue(2);
	}

	public void setPalletVoli(){

		JLabel labelTipoPrenotazione = new JLabel("Pallet");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPalletVoli.add(labelTipoPrenotazione);

		panelPalletVoliInterno.setBounds(0, 40, 498, 280);
		panelPalletVoliInterno.setLayout(null);
		panelPalletVoli.add(panelPalletVoliInterno);

		panelPalletVoliInterno.removeAll();

		JRadioButton rdbtnNewRadioButton = new JRadioButton(Double.toString(Math.random()));
		buttonGroupPalletVoli.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(6, 10, 490, 23);
		panelPalletVoliInterno.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Orio Al Serio -");
		buttonGroupPalletVoli.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(6, 30, 490, 23);
		panelPalletVoliInterno.add(rdbtnNewRadioButton_1);

		buttonPalletConfermaVolo = new JButton("Conferma");
		buttonPalletConfermaVolo.setBounds(167, 327, 89, 23);
		panelPalletVoli.add(buttonPalletConfermaVolo);

		buttonPalletAnnullaVolo = new JButton("Annulla");
		buttonPalletAnnullaVolo.setBounds(266, 327, 89, 23);
		panelPalletVoli.add(buttonPalletAnnullaVolo);


		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 360, 482, 26);
		panelPalletVoli.add(progressBar);
		progressBar.setValue(50);
		repaint();
	}

	public void setPalletPallet(){
		JLabel labelTipoPrenotazione = new JLabel("Prenotazione Pallet 3/3");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPalletPallet.add(labelTipoPrenotazione);

		JLabel lblIstruzioni = new JLabel("Inserire gli ultimi dati");
		lblIstruzioni.setBounds(8, 60, 100, 14);
		panelPalletPallet.add(lblIstruzioni);

		JLabel lblTarga = new JLabel("Targa del pallet");
		lblTarga.setBounds(141, 110, 112, 14);
		panelPalletPallet.add(lblTarga);

		textFieldTargaPallet.setBounds(141, 127, 220, 20);
		panelPalletPallet.add(textFieldTargaPallet);
		textFieldTargaPallet.setColumns(10);

		JLabel lblPeso = new JLabel("Peso complessivo");
		lblPeso.setBounds(141, 160, 112, 14);
		panelPalletPallet.add(lblPeso);

		textFieldPesoPallet.setBounds(141, 177, 220, 20);
		panelPalletPallet.add(textFieldPesoPallet);
		textFieldPesoPallet.setColumns(10);


		buttonPalletConfermaPrenotazione = new JButton("Conferma");
		buttonPalletConfermaPrenotazione.setBounds(167, 327, 89, 23);
		panelPalletPallet.add(buttonPalletConfermaPrenotazione);

		buttonPalletAnnullaPrenotazione = new JButton("Annulla");
		buttonPalletAnnullaPrenotazione.setBounds(266, 327, 89, 23);
		panelPalletPallet.add(buttonPalletAnnullaPrenotazione);


		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 360, 482, 26);
		panelPalletPallet.add(progressBar);
		progressBar.setValue(90);

		repaint();
	}




	int lastIndex=0;
	int currentIndex=0;

	Passeggero passeggeroCorrente=null;

	protected void save(Passeggero p){
		Character sex = null;
		if(comboBoxSesso.getSelectedIndex()==1) sex='m';
		else sex='f';

		p.setNome(textPasseggeriNome.getText());
		p.setCognome(textPasseggeriCognome.getText());
		p.setSesso(sex);
		p.setGiorno(Integer.parseInt(comboBoxGiorno.getSelectedItem().toString()));
		p.setMese(Integer.parseInt(comboBoxMese.getSelectedItem().toString()));
		p.setAnno(Integer.parseInt(comboBoxAnno.getSelectedItem().toString()));
		p.calcolaEta();
	}



	protected void successivoPasseggero(){
		if(true){ //se è tutto ok

			if(passeggeroCorrente != null){ //riaggiorno il passeggero esistete
				save(passeggeroCorrente);
			}
			else{ //creo uno nuovo
				Passeggero nuovoPasseggero = new Passeggero();
				save(nuovoPasseggero);
				listaPasseggeri.add(nuovoPasseggero);
			}

			if(currentIndex==lastIndex){
				lastIndex++;
			}
			currentIndex++;
			System.out.println("dopo: "+currentIndex+"--"+lastIndex);
		}

		if(lastIndex==currentIndex){
			//lo azzero
			textPasseggeriNome.setText("");
			textPasseggeriCognome.setText("");
			comboBoxSesso.setSelectedIndex(0);
			comboBoxGiorno.setSelectedIndex(0);
			comboBoxMese.setSelectedIndex(0);
			comboBoxAnno.setSelectedIndex(0);
			passeggeroCorrente=null;
		}else{
			passeggeroCorrente = listaPasseggeri.get(currentIndex);
			showPasseggero(passeggeroCorrente);
		}

	}

	protected void precedentePasseggero(){
		if(currentIndex!=lastIndex){
			save(passeggeroCorrente);
		}
		currentIndex--;
		passeggeroCorrente = listaPasseggeri.get(currentIndex);
		showPasseggero(passeggeroCorrente);

	}


	protected void showPasseggero(Passeggero p){

		textPasseggeriNome.setText(p.getNome());
		textPasseggeriCognome.setText(p.getCognome());
		if(p.getSesso()=='m'){
			comboBoxSesso.setSelectedIndex(1);
		}else{
			comboBoxSesso.setSelectedIndex(2);
		}
		comboBoxGiorno.setSelectedIndex(p.getGiorno());
		comboBoxMese.setSelectedIndex(p.getMese());
		comboBoxAnno.setSelectedIndex(2013-p.getAnno()+1);
	}







	protected void addPasseggero(){

		JLabel labelPasseggeriNome = new JLabel("Nome");
		labelPasseggeriNome.setBounds(10, 21+(75), 72, 14);
		panelPasseggeriPasseggeriInterno.add(labelPasseggeriNome);


		textPasseggeriNome.setBounds(70, 18+(75), 130, 20);
		panelPasseggeriPasseggeriInterno.add(textPasseggeriNome);
		textPasseggeriNome.setColumns(10);

		JLabel labelPasseggeriCognome = new JLabel("Cognome");
		labelPasseggeriCognome.setBounds(10, 49+(75), 46, 14);
		panelPasseggeriPasseggeriInterno.add(labelPasseggeriCognome);


		textPasseggeriCognome.setBounds(70, 46+(75), 130, 20);
		panelPasseggeriPasseggeriInterno.add(textPasseggeriCognome);
		textPasseggeriCognome.setColumns(10);


		JLabel labelPasseggeriSesso = new JLabel("Sesso");
		labelPasseggeriSesso.setBounds(210, 21+(75), 46, 14);
		panelPasseggeriPasseggeriInterno.add(labelPasseggeriSesso);

		comboBoxSesso.addItem("");
		comboBoxSesso.addItem("Uomo");
		comboBoxSesso.addItem("Donna");
		comboBoxSesso.setBounds(260, 18+(75), 72, 20);
		panelPasseggeriPasseggeriInterno.add(comboBoxSesso);

		JLabel labelPasseggeriEta = new JLabel("Nato il");
		labelPasseggeriEta.setBounds(210, 49+(75), 46, 14);
		panelPasseggeriPasseggeriInterno.add(labelPasseggeriEta);


		//giorni di nascita
		comboBoxGiorno.addItem("");
		for(int i=1;i<31;i++){
			comboBoxGiorno.addItem(""+i);
		}
		comboBoxGiorno.setBounds(260, 46+(75), 42, 20);
		panelPasseggeriPasseggeriInterno.add(comboBoxGiorno);

		//mese di nascita
		comboBoxMese.addItem("");
		for(int i=1;i<12;i++){
			comboBoxMese.addItem(""+i);
		}
		comboBoxMese.setBounds(310, 46+(75), 42, 20);
		panelPasseggeriPasseggeriInterno.add(comboBoxMese);

		//anno di nascita
		comboBoxAnno.addItem("");
		for(int i=2013;i>1900;i--){
			comboBoxAnno.addItem(""+i);
		}
		comboBoxAnno.setBounds(360, 46+(75), 55, 20);
		panelPasseggeriPasseggeriInterno.add(comboBoxAnno);


		repaint();
	}
}
