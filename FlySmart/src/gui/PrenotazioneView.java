//scandurra ti amo

package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import model.Aeroporto;
import model.Passeggero;
import model.Volo;


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
	
	
	
	
	
	
	
	
	//111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111
	
	
	JComboBox<Serializable> comboPasseggeriAeroportoPartenza= new JComboBox<Serializable>();
	//JComboBox comboPasseggeriAeroportoPartenza= new JComboBox();
	
	
	JComboBox comboPasseggeriAeroportoArrivo = new JComboBox();
	JComboBox comboPalletAeroportoPartenza= new JComboBox();
	JComboBox comboPalletAeroportoArrivo = new JComboBox();



	JTextField textPasseggeriNome = new JTextField();
	JTextField textPasseggeriCognome = new JTextField();
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
	protected ButtonGroup buttonGroupSesso = new ButtonGroup();
	JLabel labelTipoPrenotazione= new JLabel();
	JLabel labelPasseggeriNumero = new JLabel("1");
	
	
	JRadioButton rdbtnNewRadioButton_uomo = new JRadioButton("Uomo");
	JRadioButton rdbtnNewRadioButton_donna = new JRadioButton("Donna");

	JLabel labelResoconto = new JLabel("Lista utenti registrata");


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

		Iterator<Aeroporto> i = aeroporti.iterator();
		while(i.hasNext()) {
			Aeroporto element = (Aeroporto) i.next();
			comboPasseggeriAeroportoPartenza.addItem(element);
			comboPasseggeriAeroportoArrivo.addItem(element);
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

	public void setPasseggeriVoli(List<Volo> voli){

		JLabel labelTipoPrenotazione = new JLabel("Selezionare un volo:");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPasseggeriVoli.add(labelTipoPrenotazione);

		panelPasseggeriVoliInterno.setBounds(0, 40, 498, 280);
		panelPasseggeriVoliInterno.setLayout(null);
		panelPasseggeriVoli.add(panelPasseggeriVoliInterno);

		panelPasseggeriVoliInterno.removeAll();

		Iterator<Volo> i = voli.iterator();
		int verticalPosition = 10;
		while(i.hasNext()) {
			Volo element = (Volo) i.next();
			JRadioButton rdbtnNewRadioButton = new JRadioButton(element.toString());
			buttonGroupPasseggeriVoli.add(rdbtnNewRadioButton);
			rdbtnNewRadioButton.setBounds(6, verticalPosition, 480, 23);
			panelPasseggeriVoliInterno.add(rdbtnNewRadioButton);
			verticalPosition=verticalPosition+20;
		}

		buttonPasseggeriConfermaVolo = new JButton("Conferma");
		buttonPasseggeriConfermaVolo.setBounds(400, 327, 89, 23);
		panelPasseggeriVoli.add(buttonPasseggeriConfermaVolo);

		buttonPasseggeriAnnullaVolo = new JButton("Annulla");
		buttonPasseggeriAnnullaVolo.setBounds(310, 327, 89, 23);
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
		
		
		JLabel labelResocontoVolo = new JLabel("From: Milano   To: Bergamo");
		labelResocontoVolo.setFont(new Font("Arial", Font.PLAIN, 14));
		labelResocontoVolo.setBounds(18, 58, 482, 25);
		panelPasseggeriPasseggeri.add(labelResocontoVolo);



		labelPasseggeriNumero.setBounds(20, 100, 40, 23);
		labelPasseggeriNumero.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelPasseggeriPasseggeri.add(labelPasseggeriNumero);


		JLabel labelPasseggeriNome = new JLabel("Nome");
		labelPasseggeriNome.setBounds(20, 136, 72, 14);

		panelPasseggeriPasseggeri.add(labelPasseggeriNome);


		textPasseggeriNome.setBounds(80, 134, 163, 20);
		panelPasseggeriPasseggeri.add(textPasseggeriNome);
		textPasseggeriNome.setColumns(10);

		JLabel labelPasseggeriCognome = new JLabel("Cognome");
		labelPasseggeriCognome.setBounds(20, 164, 46, 14);
		panelPasseggeriPasseggeri.add(labelPasseggeriCognome);


		textPasseggeriCognome.setBounds(80, 161, 163, 20);
		panelPasseggeriPasseggeri.add(textPasseggeriCognome);
		textPasseggeriCognome.setColumns(10);




		JLabel labelPasseggeriSesso = new JLabel("Sesso");
		labelPasseggeriSesso.setBounds(20, 195, 46, 14);
		panelPasseggeriPasseggeri.add(labelPasseggeriSesso);


		rdbtnNewRadioButton_uomo.setSelected(true);
		rdbtnNewRadioButton_uomo.setBounds(80, 195, 72, 20);
		buttonGroupSesso.add(rdbtnNewRadioButton_uomo);
		panelPasseggeriPasseggeri.add(rdbtnNewRadioButton_uomo);

		rdbtnNewRadioButton_donna.setBounds(150, 195, 72, 20);
		buttonGroupSesso.add(rdbtnNewRadioButton_donna);
		panelPasseggeriPasseggeri.add(rdbtnNewRadioButton_donna);


	

		JLabel labelPasseggeriEta = new JLabel("Nato il");
		labelPasseggeriEta.setBounds(20, 230, 46, 14);
		panelPasseggeriPasseggeri.add(labelPasseggeriEta);


		//giorni di nascita
		comboBoxGiorno.removeAllItems();
		comboBoxGiorno.addItem("");
		for(int i=1;i<31;i++){
			comboBoxGiorno.addItem(""+i);
		}
		comboBoxGiorno.setBounds(80, 230, 42, 20);
		panelPasseggeriPasseggeri.add(comboBoxGiorno);

		//mese di nascita
		comboBoxMese.removeAllItems();
		comboBoxMese.addItem("");
		for(int i=1;i<12;i++){
			comboBoxMese.addItem(""+i);
		}
		comboBoxMese.setBounds(130, 230, 42, 20);
		panelPasseggeriPasseggeri.add(comboBoxMese);

		//anno di nascita
		comboBoxAnno.removeAllItems();
		comboBoxAnno.addItem("");
		for(int i=2013;i>1900;i--){
			comboBoxAnno.addItem(""+i);
		}
		comboBoxAnno.setBounds(190, 230, 55, 20);
		panelPasseggeriPasseggeri.add(comboBoxAnno);


		buttonPasseggeriPrecedente = new JButton("<");
		buttonPasseggeriPrecedente.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buttonPasseggeriPrecedente.setBounds(165, 270, 40, 23);
		buttonPasseggeriPrecedente.setForeground(Color.red);
		panelPasseggeriPasseggeri.add(buttonPasseggeriPrecedente);

		buttonPasseggeriProssimo = new JButton(">");
		buttonPasseggeriProssimo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buttonPasseggeriProssimo.setBounds(210, 270, 40, 23);
		buttonPasseggeriProssimo.setForeground(Color.red);
		panelPasseggeriPasseggeri.add(buttonPasseggeriProssimo);



		buttonPasseggeriAnnullaPrenotazione = new JButton("Annulla");
		buttonPasseggeriAnnullaPrenotazione.setBounds(310, 327, 89, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriAnnullaPrenotazione);

		buttonPasseggeriConfermaPrenotazione = new JButton("Conferma");
		buttonPasseggeriConfermaPrenotazione.setBounds(400, 327, 89, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriConfermaPrenotazione);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 360, 482, 26);
		panelPasseggeriPasseggeri.add(progressBar);
		progressBar.setValue(90);

		
		labelResoconto.setFont(new Font("Tahoma", Font.PLAIN, 14));
		labelResoconto.setBounds(308, 88, 200, 18);
		panelPasseggeriPasseggeri.add(labelResoconto);

		//azzeramento dati passeggeri
		listaPasseggeri.clear();
		textPasseggeriNome.setText("");
		textPasseggeriCognome.setText("");
		passeggeroCorrente=null;
		currentIndex=0;
		lastIndex=0;
		labelPasseggeriNumero.setText(currentIndex+1+"");
		
		
		
	}
	
	private void setResoconto(){
		Iterator<Passeggero> el = listaPasseggeri.iterator();
		int verticalPosition = 150;
		while(el.hasNext()) {
			Passeggero element = (Passeggero) el.next();

			System.out.println(element.getCognome()+" "+element.getNome());
			JLabel label = new JLabel(element.getCognome()+" "+element.getNome());
			label.setFont(new Font("Arial", Font.PLAIN, 11));
			label.setBounds(308, verticalPosition, 300, 18);
			panelPasseggeriPasseggeri.add(label);   //sembra che non funzioni sempre non posso distruggere la label dopo l'uscita del while
			verticalPosition=verticalPosition+22;
		}
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


	//dato un button group mi restituisce il nome
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}


	int lastIndex=0;
	int currentIndex=0;

	Passeggero passeggeroCorrente=null;

	protected void save(Passeggero p){
		Character sex = null;
		if(getSelectedButtonText(buttonGroupSesso).compareTo("Uomo")==0) sex='m'; 
		else sex='f';

		p.setNome(textPasseggeriNome.getText());
		p.setCognome(textPasseggeriCognome.getText());
		p.setSesso(sex);
		p.setGiorno(Integer.parseInt(comboBoxGiorno.getSelectedItem().toString()));
		p.setMese(Integer.parseInt(comboBoxMese.getSelectedItem().toString()));
		p.setAnno(Integer.parseInt(comboBoxAnno.getSelectedItem().toString()));
		p.calcolaEta();
		//per test XML: la classe XMLCreate non va se non ho settato tutti i parametri
		p.setId(0);
		p.setIdGruppo(0);
		p.setPesoBagagli(10.0);
		p.setPosto(2);
		p.setIdVolo(5);

	}


	protected boolean controllaCampi(){
		return !(textPasseggeriNome.getText().equals("") || 
				textPasseggeriCognome.getText().equals("") || 
				comboBoxGiorno.getSelectedIndex()==0 ||
				comboBoxMese.getSelectedIndex()==0 ||
				comboBoxAnno.getSelectedIndex()==0
				);
	}

	protected void updateOrSavePasseggero(){
		if(controllaCampi()){ 
			if(passeggeroCorrente != null){ //riaggiorno il passeggero esistente
				save(passeggeroCorrente);
			}
			else{ //creo uno nuovo
				Passeggero nuovoPasseggero = new Passeggero();
				save(nuovoPasseggero);
				listaPasseggeri.add(nuovoPasseggero);
			}
			setResoconto();
			System.out.println(listaPasseggeri);
			if(currentIndex==lastIndex){
				lastIndex++;
			}
			currentIndex++;
		}
	}


	protected void passeggeroSuccessivo(){
		if(controllaCampi()){ 
			updateOrSavePasseggero();
			if(lastIndex==currentIndex){
				//lo azzero
				textPasseggeriNome.setText("");
				textPasseggeriCognome.setText("");
				comboBoxGiorno.setSelectedIndex(0);
				comboBoxMese.setSelectedIndex(0);
				rdbtnNewRadioButton_uomo.setSelected(true);
				rdbtnNewRadioButton_donna.setSelected(false);
				comboBoxAnno.setSelectedIndex(0);
				passeggeroCorrente=null;
			}else{
				passeggeroCorrente = listaPasseggeri.get(currentIndex);
				mostraPasseggero(passeggeroCorrente);
			}
			labelPasseggeriNumero.setText(currentIndex+1+"");
		}else{
			JOptionPane.showMessageDialog(null,"Completare prima il passeggero corrente","Errore", 1);
		}
		
	}

	protected void passeggeroPrecedente(){
		if(currentIndex!=lastIndex){
			save(passeggeroCorrente);
		}
		currentIndex--;
		passeggeroCorrente = listaPasseggeri.get(currentIndex);
		mostraPasseggero(passeggeroCorrente);

		labelPasseggeriNumero.setText(currentIndex+1+"");
	}


	protected void mostraPasseggero(Passeggero p){

		textPasseggeriNome.setText(p.getNome());
		textPasseggeriCognome.setText(p.getCognome());
		if(p.getSesso()=='m'){
			rdbtnNewRadioButton_uomo.setSelected(true);
			rdbtnNewRadioButton_donna.setSelected(false);
		}else{
			rdbtnNewRadioButton_uomo.setSelected(false);
			rdbtnNewRadioButton_donna.setSelected(true);
		}
		comboBoxGiorno.setSelectedIndex(p.getGiorno());
		comboBoxMese.setSelectedIndex(p.getMese());
		comboBoxAnno.setSelectedIndex(2013-p.getAnno()+1);

		
	}




	// Quando seleziono febbraio, togliere i giorni invalidi eccetera

}
