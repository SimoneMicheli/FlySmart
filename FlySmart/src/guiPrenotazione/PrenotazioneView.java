package guiPrenotazione;


import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import client.View;

import model.Aeroporto;
import model.Pallet;
import model.Passeggero;
import model.Sesso;
import model.Volo;

/**La vista della prenotazione
 *
 * @author Demarinis - Micheli - Scarpellini
 * 
 */

@SuppressWarnings("rawtypes")
public class PrenotazioneView extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7352763063630645243L;


	//pannelli livello 1
	/** I layout per il card layout */
	protected CardLayout cardEsterno = new CardLayout();
	protected CardLayout cardPasseggeri = new CardLayout();
	protected CardLayout cardPallet = new CardLayout();
	/** il pannello esterno per switchare da passeggeri a pallet. */
	protected JPanel panelCardLayoutEsterno=new JPanel();
	//pannelli livello 2
	/** pannello contentente tutta l'applicazione per i passeggeri. */
	protected JPanel panelPasseggeri=new JPanel();
	/** pannello contentente tutta l'applicazione per i pallett. */
	protected JPanel panelPallet=new JPanel();
	//pannelli livello 3 passeggeri
	/** pannello contenente gli aeroporti per l'applicazione passeggeri. */
	protected JPanel panelPasseggeriAeroporti = new JPanel();
	/** pannello contenente i voli per l'applicazione passeggeri. */
	protected JPanel panelPasseggeriVoli = new JPanel();
	/** parte sinistra dei dati dei passeggeri per l'applicazione passeggeri. */
	protected JPanel panelPasseggeriPasseggeri = new JPanel();
	/** parte destra dei dati dei passeggeri per l'applicazione passeggeri (resoconto passeggeri). */
	protected JPanel panelResoconto = new JPanel();
	//pannelli livello 3 pallet
	/** pannello contenente gli aeroporti per l'applicazione pallet. */
	protected JPanel panelPalletAeroporti = new JPanel();
	/** pannello contenente i voli per l'applicazione pallet. */
	protected JPanel panelPalletVoli = new JPanel();
	/** pannello contenente i dati dei pallet per l'applicazione pallet. */
	protected JPanel panelPalletPallet = new JPanel();

	//barra degli strumenti
	/** container degli strumenti. */
	JMenuBar menuBar = new JMenuBar();
	//pulsanti 
	/** strumento file. */
	protected JMenu mnFile = new JMenu("File");
	/** strumento modifica. */
	protected JMenu mnModifica = new JMenu("Modifica");
	/** strumento info. */
	protected JMenu mnInfo = new JMenu("Info");
	//opzioni
	/** opzione esci. */
	protected JMenuItem mntmExit = new JMenuItem("Esci",new ImageIcon("./img/exit.gif"));
	/** opzione rimuovi prenotazione. */
	protected JMenuItem mntmRimuovi = new JMenuItem("Elimina prenotazione",new ImageIcon("./img/trash.gif"));
	/** opzione switch. */
	protected JMenuItem mntmSwitch = new JMenuItem("Prenotazioni pallet",new ImageIcon("./img/folder.gif"));
	/** opzione about flysmart. */
	protected JMenuItem mntmAboutFlySmart = new JMenuItem("About FlySmart",new ImageIcon("./img/info.gif"));
	
	//combo box
	/** La combo box per la scelta dell'aeroporto di partenza per i passeggeri. */
	protected JComboBox comboPasseggeriAeroportoPartenza= new JComboBox();
	/** La combo box per la scelta dell'aeroporto di arrivo per i passeggeri. */
	protected JComboBox comboPasseggeriAeroportoArrivo = new JComboBox();
	/** La combo box per la scelta dell'aeroporto di partenza per i pallet. */
	protected JComboBox comboPalletAeroportoPartenza= new JComboBox();
	/** La combo box per la scelta dell'aeroporto di arrivo per i pallet. */
	protected JComboBox comboPalletAeroportoArrivo = new JComboBox();
	/** La combo box per la scelta del volo. */
	protected JComboBox comboVoliDisponibili = new JComboBox();
	/** The combo box giorno. */
	protected JComboBox comboBoxGiorno = new JComboBox();
	/** The combo box mese. */
	protected JComboBox comboBoxMese = new JComboBox();
	/** The combo box anno. */
	protected JComboBox comboBoxAnno = new JComboBox();

	//text-field
	/** The text passeggeri nome. */
	protected JTextField textPasseggeriNome = new JTextField();
	/** The text passeggeri cognome. */
	protected JTextField textPasseggeriCognome = new JTextField();
	/** The text field targa pallet. */
	protected JTextField textFieldTargaPallet = new JTextField();
	/** The text field peso pallet. */
	protected JTextField textFieldPesoPallet = new JTextField();


	//button-group
	/** The button group sesso. */
	protected ButtonGroup buttonGroupSesso = new ButtonGroup();
	protected JRadioButton rdbtnNewRadioButton_uomo = new JRadioButton("Uomo");
	protected JRadioButton rdbtnNewRadioButton_donna = new JRadioButton("Donna");

	//label
	protected JLabel labelTipoPrenotazione= new JLabel();
	protected JLabel labelNumeroPasseggero = new JLabel("1");
	protected JLabel labelListaPasseggeri = new JLabel("Lista passeggeri:");
	protected JLabel labelPrezzoTotale= new JLabel("");
	JLabel labelResocontoVolo= new JLabel("");
	protected JLabel labelDataOraVoloPasseggeri= new JLabel();
	protected JLabel labelDataOraVoloPallet= new JLabel();
	protected JLabel labelPasseggeriDisponibili= new JLabel();
	protected JLabel labelPalletDisponibili= new JLabel();
	protected JLabel labelPrezzoPasseggeri= new JLabel();
	protected JLabel labelPrezzoPallet= new JLabel();
	protected JLabel labelCodiceAeroportoPartenzaPallet= new JLabel();
	protected JLabel labelCodiceAeroportoPartenzaPasseggeri= new JLabel();
	protected JLabel labelCodiceAeroportoArrivoPallet= new JLabel();
	protected JLabel labelCodiceAeroportoArrivoPasseggeri= new JLabel();
	protected JLabel labelStatoVoloPasseggeri= new JLabel();
	protected JLabel labelStatoVoloPallet= new JLabel();




	//button 1 livello
	/** conferma la fase passeggeri:aeroporti */
	protected JButton buttonPasseggeriCercaVoli;
	/** conferma la fase pallet:aeroporti */
	protected JButton buttonPalletCercaVoli;
	//button 2 livello
	/** conferma la fase passeggeri:voli */
	protected JButton buttonPasseggeriConfermaVolo;
	/** conferma la fase pallet:voli */
	protected JButton buttonPalletConfermaVolo;
	/** annulla la fase passeggeri:voli */
	protected JButton buttonPasseggeriAnnullaVolo;
	/** annulla la fase pallet:voli */
	protected JButton buttonPalletAnnullaVolo;
	//button 3 livello
	/** conferma la fase passeggeri:passeggeri */
	protected JButton buttonPasseggeriConfermaPrenotazione;
	/** conferma la fase pallet:passeggeri */
	protected JButton buttonPalletConfermaPrenotazione;
	/** annulla la fase passeggeri:passeggeri */
	protected JButton buttonPasseggeriAnnullaPrenotazione;
	/** annulla la fase pallet:passeggeri */
	protected JButton buttonPalletAnnullaPrenotazione;
	/** passa al prossimo passeggero */
	protected JButton buttonPasseggeriProssimo;
	/** passa al passeggero precedente */
	protected JButton buttonPasseggeriPrecedente;

	//varie variabili
	/** la dimensione della finestra */
	protected Dimension dimensioneFinestra = new Dimension(508,416);
	/** true: sono nel pannello passeggeri; false: sono nel pannello pallet */
	protected boolean passeggeri;
	/** la lista dei passeggeri da inviare */
	protected ArrayList<Passeggero> listaPasseggeri = new ArrayList<Passeggero>();
	/** la lista dei pallet da inviare */
	protected ArrayList<Pallet> listaPallet = new ArrayList<Pallet>();
	/** indice dell'ultimo passeggero inserito nella prenotazione attuale */
	int lastIndex=0;
	/** indice del passeggero visualizzato */
	int currentIndex=0;
	/** passeggero visualizzato */
	Passeggero passeggeroCorrente=null;
	/** volo scelto per i passeggeri*/
	Volo voloSelezionatoPasseggeri=null;
	/** volo scelto per i pallet*/
	Volo voloSelezionatoPallet=null;
	/** prezzo totale del volo */
	double prezzoTotaleVolo=0;
	/** aeroporto di partenza per i passeggeri */
	String aeroportoPartenzaPasseggeri=null;
	/** aeroporto di arrivo per i passeggeri */
	String aeroportoArrivoPasseggeri=null;
	/** aeroporto di partenza per i pallet*/
	String aeroportoPartenzaPallet=null;
	/** aeroporto di arrivo per i pallet*/
	String aeroportoArrivoPallet=null;

	/**
	 * crea la vista prenotazione
	 */
	public PrenotazioneView() {
		setGraphic();
		setMenu();
		setCardEsterne();
		setCardPasseggeri();
		setCardPallet();
	}


	/**
	 * setto le cose della grafica della finestra
	 */
	private void setGraphic(){
		lookAndFeel();
		setResizable(false);
		setTitle("FlySmart: Prenotazione");
		Toolkit mioTKit = Toolkit.getDefaultToolkit();
		Dimension dimensioniSchermo = mioTKit.getScreenSize();
		int xFrame = (dimensioniSchermo.width - dimensioneFinestra.width) / 2;
		int yFrame = (dimensioniSchermo.height - dimensioneFinestra.height) / 2;
		setLocation(xFrame, yFrame);
		setSize(dimensioneFinestra.width, dimensioneFinestra.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * creo il menu
	 */
	private void setMenu(){
		setJMenuBar(menuBar);
		menuBar.add(mnFile);
		menuBar.add(mnModifica);
		menuBar.add(mnInfo);
		mnFile.add(mntmExit);
		mnModifica.add(mntmSwitch);
		mnModifica.add(mntmRimuovi);
		mnInfo.add(mntmAboutFlySmart);
	}

	/**
	 * creo le card esterne (passeggeri/pallett)
	 */
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

	/**
	 * creo le card interne dell'applicazione passeggeri (aeroporti/voli/passeggeri)
	 */
	private void setCardPasseggeri(){
		panelPasseggeriAeroporti.setLayout(null);
		panelPasseggeri.add(panelPasseggeriAeroporti,"panelPasseggeriAeroporti");
		panelPasseggeriVoli.setLayout(null);
		panelPasseggeri.add(panelPasseggeriVoli,"panelPasseggeriVoli");
		panelPasseggeriPasseggeri.setLayout(null);
		panelPasseggeri.add(panelPasseggeriPasseggeri,"panelPasseggeriPasseggeri");
		cardPasseggeri.show(panelPasseggeri,"panelPasseggeriAeroporti");
	}

	/**
	 * creo le card interne dell'applicazione pallet (aeroporti/voli/pallet)
	 */
	private void setCardPallet(){
		panelPalletAeroporti.setLayout(null);
		panelPallet.add(panelPalletAeroporti,"panelPalletAeroporti");
		panelPalletVoli.setLayout(null);
		panelPallet.add(panelPalletVoli,"panelPalletVoli");
		panelPalletPallet.setLayout(null);
		panelPallet.add(panelPalletPallet,"panelPalletPallet");
	}



	/**
	 * Carico gli oggetti di passeggeri:aeroporti
	 *
	 * @param aeroporti la lista degli aeroporti
	 */
	@SuppressWarnings("unchecked")
	public void setPasseggeriAeroporti(List<Aeroporto> aeroporti){
		
		
		JLabel labelFlySmart = new JLabel("<html><span style='color:green'>Fly</span><span style='color:blue'>Smart</span><span style='font-size:15px;color:#666666'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prenotazione passeggeri</span></html>");
		labelFlySmart.setFont(new Font("Calibri", Font.BOLD, 50));
		labelFlySmart.setForeground(Color.black);
		labelFlySmart.setBounds(8, 0, 482, 70);
		panelPasseggeriAeroporti.add(labelFlySmart);

		/*JLabel labelTipoPrenotazione = new JLabel("Selezionare aeroporto di partenza e di arrivo");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 16));
		labelTipoPrenotazione.setBounds(8, 68, 482, 25);
		panelPasseggeriAeroporti.add(labelTipoPrenotazione);*/

		JLabel labelAeroportoPartenza = new JLabel("Aereporto Partenza");
		labelAeroportoPartenza.setBounds(141, 110, 112, 14);
		panelPasseggeriAeroporti.add(labelAeroportoPartenza);

		JLabel labelAeroportoArrivo = new JLabel("Aereporto Arrivo");
		labelAeroportoArrivo.setBounds(141, 160, 112, 14);
		panelPasseggeriAeroporti.add(labelAeroportoArrivo);

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


		buttonPasseggeriCercaVoli = new JButton("Cerca Voli");
		buttonPasseggeriCercaVoli.setBounds(141, 215, 220, 23);
		panelPasseggeriAeroporti.add(buttonPasseggeriCercaVoli);

		JLabel labelFondo = new JLabel("<html><span style='font-size:10px;font-weight:bold'>Passeggeri</span>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:red'>Selezione tratta</span><span style='font-size:16px'>&nbsp;&nbsp;&nbsp;→&nbsp;&nbsp;</span><span style='color:'>Ricerca volo</span><span style='font-size:16px'>&nbsp;&nbsp;→&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='color:'>Inserimento anagrafica</span></html>");
		labelFondo.setBounds(10, 310, 480, 50);
		panelPasseggeriAeroporti.add(labelFondo);

	}

	/**
	 * Carico gli oggetti di passeggeri:voli
	 *
	 * @param voli la lista dei voli ricevuta dal server
	 */

	@SuppressWarnings("unchecked")
	public void setPasseggeriVoli(List<Volo> voli){

		panelPasseggeriVoli.removeAll(); //rimuovo tutti gli oggetti

		JLabel labelTipoPrenotazione = new JLabel("Voli disponibili:");
		labelTipoPrenotazione.setFont(new Font("Calibri", Font.PLAIN, 16));
		labelTipoPrenotazione.setBounds(8, 28, 482, 25);
		panelPasseggeriVoli.add(labelTipoPrenotazione);

		comboVoliDisponibili.removeAllItems(); //rimuovo gli elementi scritti al passo prima
		comboVoliDisponibili.setBounds(12, 50, 470, 23);
		Iterator<Volo> v = voli.iterator();
		while(v.hasNext()) {
			Volo element = (Volo) v.next();
			comboVoliDisponibili.addItem(element);
		}
		panelPasseggeriVoli.add(comboVoliDisponibili, BorderLayout.NORTH);

		Volo voloCorrente = ((Volo)comboVoliDisponibili.getSelectedItem());
		labelCodiceAeroportoPartenzaPasseggeri.setText("<html><b style='color:#242589'>Codice aeroporto di partenza: </b>"+" "+voloCorrente.getAeroportoPartenza()+"</html>");
		labelCodiceAeroportoPartenzaPasseggeri.setFont(new Font("Arial", Font.PLAIN, 12));
		labelCodiceAeroportoPartenzaPasseggeri.setBounds(16, 100, 482, 25);
		panelPasseggeriVoli.add(labelCodiceAeroportoPartenzaPasseggeri);

		labelCodiceAeroportoArrivoPasseggeri.setText("<html><b style='color:#242589'>Codice aeroporto di arrivo: </b>"+" "+voloCorrente.getAeroportoDestinazione()+"</html>");
		labelCodiceAeroportoArrivoPasseggeri.setFont(new Font("Arial", Font.PLAIN, 12));
		labelCodiceAeroportoArrivoPasseggeri.setBounds(16, 130, 482, 25);
		panelPasseggeriVoli.add(labelCodiceAeroportoArrivoPasseggeri);

		labelDataOraVoloPasseggeri.setText("<html><b style='color:#242589'>Data e ora volo: </b>"+" "+voloCorrente.getDataOraString()+"</html>");
		labelDataOraVoloPasseggeri.setFont(new Font("Arial", Font.PLAIN, 12));
		labelDataOraVoloPasseggeri.setBounds(16, 160, 482, 25);
		panelPasseggeriVoli.add(labelDataOraVoloPasseggeri);

		labelStatoVoloPasseggeri.setText("<html><b style='color:#242589'>Stato volo: </b>"+" "+voloCorrente.getStato()+"</html>");
		labelStatoVoloPasseggeri.setFont(new Font("Arial", Font.PLAIN, 12));
		labelStatoVoloPasseggeri.setBounds(16, 190, 482, 25);
		panelPasseggeriVoli.add(labelStatoVoloPasseggeri);

		labelPasseggeriDisponibili.setText("<html><b style='color:#242589'>Numero posti disponibili: </b>"+" "+voloCorrente.getPostiDisponibili()+"</html>");
		labelPasseggeriDisponibili.setFont(new Font("Arial", Font.PLAIN, 12));
		labelPasseggeriDisponibili.setBounds(16, 220, 482, 25);
		panelPasseggeriVoli.add(labelPasseggeriDisponibili);

		labelPrezzoPasseggeri.setText("<html><b style='color:#242589'>Prezzo singolo: </b>"+" "+voloCorrente.getPrezzoPasseggero()+" &euro;</html>");
		labelPrezzoPasseggeri.setFont(new Font("Arial", Font.PLAIN, 12));
		labelPrezzoPasseggeri.setBounds(16, 250, 482, 25);
		panelPasseggeriVoli.add(labelPrezzoPasseggeri);

		buttonPasseggeriConfermaVolo = new JButton("Conferma");
		buttonPasseggeriConfermaVolo.setBounds(400, 287, 89, 23);
		panelPasseggeriVoli.add(buttonPasseggeriConfermaVolo);

		buttonPasseggeriAnnullaVolo = new JButton("Annulla");
		buttonPasseggeriAnnullaVolo.setBounds(310, 287, 89, 23);
		panelPasseggeriVoli.add(buttonPasseggeriAnnullaVolo);
		
		

		JLabel labelFondo = new JLabel("<html><span style='font-size:10px;font-weight:bold'>Passeggeri</span>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style=''>Selezione tratta</span><span style='font-size:16px'>&nbsp;&nbsp;&nbsp;→&nbsp;&nbsp;</span><span style='color:red'>Ricerca volo</span><span style='font-size:16px'>&nbsp;&nbsp;→&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='color:'>Inserimento anagrafica</span></html>");
		labelFondo.setBounds(10, 310, 480, 50);
		panelPasseggeriVoli.add(labelFondo);

		repaint();
	}


	/**
	 * Carico gli oggetti di passeggeri:passeggeri
	 */
	@SuppressWarnings("unchecked")
	protected void setPasseggeriPasseggeri(){

		panelPasseggeriPasseggeri.removeAll(); //rimuovo tutti gli oggetti
		
		labelResocontoVolo.setText("");
		labelResocontoVolo.setText("<html><span style='color:orange'>"+aeroportoPartenzaPasseggeri+"</span><span style='font-size:18px'>  →  </span><span style='color:orange'>"+aeroportoArrivoPasseggeri+"</span></html>");
		labelResocontoVolo.setFont(new Font("Calibri", Font.PLAIN, 24));
		labelResocontoVolo.setBounds(8, 8, 482, 35);
		panelPasseggeriPasseggeri.add(labelResocontoVolo);

		JLabel labelTipoPrenotazione = new JLabel("Dati anagrafici passeggeri:");
		labelTipoPrenotazione.setFont(new Font("Calibri", Font.PLAIN, 16));
		labelTipoPrenotazione.setBounds(25, 70, 482, 25);
		panelPasseggeriPasseggeri.add(labelTipoPrenotazione);

		labelNumeroPasseggero.setBounds(20, 100, 40, 23);
		labelNumeroPasseggero.setFont(new Font("Tahoma", Font.BOLD, 18));
		panelPasseggeriPasseggeri.add(labelNumeroPasseggero);

		JLabel labelPasseggeriNome = new JLabel("Nome");
		labelPasseggeriNome.setBounds(20, 136, 72, 14);
		panelPasseggeriPasseggeri.add(labelPasseggeriNome);

		JLabel labelPasseggeriCognome = new JLabel("Cognome");
		labelPasseggeriCognome.setBounds(20, 164, 46, 14);
		panelPasseggeriPasseggeri.add(labelPasseggeriCognome);

		textPasseggeriNome.setBounds(80, 134, 163, 20);
		panelPasseggeriPasseggeri.add(textPasseggeriNome);
		textPasseggeriNome.setColumns(10);

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
		comboBoxGiorno.setSelectedIndex(1);
		comboBoxGiorno.setBounds(80, 230, 42, 20);
		panelPasseggeriPasseggeri.add(comboBoxGiorno);

		//mese di nascita
		comboBoxMese.removeAllItems();
		comboBoxMese.addItem("");
		for(int i=1;i<12;i++){
			comboBoxMese.addItem(""+i);
		}
		comboBoxMese.setSelectedIndex(1);
		comboBoxMese.setBounds(137, 230, 42, 20);
		panelPasseggeriPasseggeri.add(comboBoxMese);

		//anno di nascita
		comboBoxAnno.removeAllItems();
		comboBoxAnno.addItem("");
		int annoMax=2013;
		int annoMin=1900;
		for(int i=annoMax;i>annoMin;i--){
			comboBoxAnno.addItem(""+i);
		}
		comboBoxAnno.setSelectedIndex(annoMax-annoMin);
		comboBoxAnno.setBounds(190, 230, 55, 20);
		panelPasseggeriPasseggeri.add(comboBoxAnno);



		buttonPasseggeriPrecedente = new JButton("<");
		buttonPasseggeriPrecedente.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buttonPasseggeriPrecedente.setBounds(165, 270, 40, 23);
		buttonPasseggeriPrecedente.setForeground(Color.red);
		panelPasseggeriPasseggeri.add(buttonPasseggeriPrecedente);

		buttonPasseggeriProssimo = new JButton(">");
		buttonPasseggeriProssimo.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buttonPasseggeriProssimo.setBounds(207, 270, 40, 23);
		buttonPasseggeriProssimo.setForeground(Color.red);
		panelPasseggeriPasseggeri.add(buttonPasseggeriProssimo);


		buttonPasseggeriAnnullaPrenotazione = new JButton("Annulla");
		buttonPasseggeriAnnullaPrenotazione.setBounds(310, 287, 89, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriAnnullaPrenotazione);

		buttonPasseggeriConfermaPrenotazione = new JButton("Conferma");
		buttonPasseggeriConfermaPrenotazione.setBounds(400, 287, 89, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriConfermaPrenotazione);


		panelResoconto.setLayout(null);
		panelResoconto.setBounds(277, 60, 205, 170);
		panelPasseggeriPasseggeri.add(panelResoconto);


		labelListaPasseggeri.setFont(new Font("Calibri", Font.PLAIN, 14));
		labelListaPasseggeri.setBounds(277, 38, 482, 25);
		panelPasseggeriPasseggeri.add(labelListaPasseggeri);

		labelPrezzoTotale.setFont(new Font("Arial", Font.BOLD, 12));
		labelPrezzoTotale.setForeground(Color.RED);
		labelPrezzoTotale.setBounds(310, 260, 220, 25);
		panelPasseggeriPasseggeri.add(labelPrezzoTotale);

		JLabel labelFondo = new JLabel("<html><span style='font-size:10px;font-weight:bold'>Passeggeri</span>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>Selezione tratta</span><span style='font-size:16px'>&nbsp;&nbsp;&nbsp;→&nbsp;&nbsp;</span><span>Ricerca volo</span><span style='font-size:16px'>&nbsp;&nbsp;→&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='color:red'>Inserimento anagrafica</span></html>");
		labelFondo.setBounds(10, 310, 480, 50);
		panelPasseggeriPasseggeri.add(labelFondo);



		//azzeramento dati passeggeri
		listaPasseggeri.clear();
		textPasseggeriNome.setText("");
		textPasseggeriCognome.setText("");
		passeggeroCorrente=null;
		currentIndex=0;
		lastIndex=0;
		labelNumeroPasseggero.setText(currentIndex+1+"");
		panelResoconto.removeAll();
		labelPrezzoTotale.setText("");


	}



	/**
	 * Carico gli oggetti di pallet:aeroporti
	 *
	 * @param aeroporti la lista degli aeroporti
	 */
	@SuppressWarnings("unchecked")
	public void setPalletAeroporti(List<Aeroporto> aeroporti){
		
		JLabel labelFlySmart = new JLabel("<html><span style='color:green'>Fly</span><span style='color:blue'>Smart</span><span style='font-size:15px;color:#666666'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prenotazione pallet</span></html>");
		labelFlySmart.setFont(new Font("Calibri", Font.BOLD, 50));
		labelFlySmart.setForeground(Color.black);
		labelFlySmart.setBounds(8, 0, 482, 70);
		panelPalletAeroporti.add(labelFlySmart);

		/*JLabel labelIstruzioni = new JLabel("Selezionare aeroporto di partenza e di arrivo");
		labelIstruzioni.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelIstruzioni.setBounds(8, 58, 482, 25);
		panelPalletAeroporti.add(labelIstruzioni);*/

		JLabel labelAeroportoPartenza = new JLabel("Aereporto Partenza");
		labelAeroportoPartenza.setBounds(141, 110, 112, 14);
		panelPalletAeroporti.add(labelAeroportoPartenza);

		JLabel labelAeroportoArrivo = new JLabel("Aereporto Arrivo");
		labelAeroportoArrivo.setBounds(141, 160, 112, 14);
		panelPalletAeroporti.add(labelAeroportoArrivo);

		comboPalletAeroportoPartenza.setBounds(141, 127, 220, 20);
		comboPalletAeroportoPartenza.addItem("");
		comboPalletAeroportoArrivo.setBounds(141, 177, 220, 20);
		comboPalletAeroportoArrivo.addItem("");
		Iterator<Aeroporto> i = aeroporti.iterator();
		while(i.hasNext()) {
			Aeroporto element = (Aeroporto) i.next();
			comboPalletAeroportoPartenza.addItem(element);
			comboPalletAeroportoArrivo.addItem(element);
		}

		panelPalletAeroporti.add(comboPalletAeroportoArrivo, BorderLayout.SOUTH);
		panelPalletAeroporti.add(comboPalletAeroportoPartenza, BorderLayout.NORTH);


		buttonPalletCercaVoli = new JButton("Cerca Voli");
		buttonPalletCercaVoli.setBounds(141, 215, 220, 23);
		panelPalletAeroporti.add(buttonPalletCercaVoli);


		JLabel labelFondo = new JLabel("<html><span style='font-size:10px;font-weight:bold'>Pallet</span>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span style='color:red'>Selezione tratta</span><span style='font-size:16px'>&nbsp;&nbsp;&nbsp;→&nbsp;&nbsp;</span><span style='color:'>Ricerca volo</span><span style='font-size:16px'>&nbsp;&nbsp;→&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='color:'>Inserimento dati pallet</span></html>");
		labelFondo.setBounds(10, 310, 480, 50);
		panelPalletAeroporti.add(labelFondo);

	}




	/**
	 * Carico gli oggetti di pallet:voli
	 *
	 * @param voli la lista dei voli ricevuta dal server
	 */

	@SuppressWarnings("unchecked")
	public void setPalletVoli(List<Volo> voli){


		panelPalletVoli.removeAll(); //rimuovo tutti gli oggetti

		JLabel labelIstruzioni = new JLabel("Voli disponibili:");
		labelIstruzioni.setFont(new Font("Calibri", Font.PLAIN, 16));
		labelIstruzioni.setBounds(8, 28, 482, 25);
		panelPalletVoli.add(labelIstruzioni);

		comboVoliDisponibili.removeAllItems(); //rimuovo gli elementi scritti al passo prima
		comboVoliDisponibili.setBounds(12, 50, 470, 23);
		Iterator<Volo> v = voli.iterator();
		while(v.hasNext()) {
			Volo element = (Volo) v.next();
			comboVoliDisponibili.addItem(element);
		}
		panelPalletVoli.add(comboVoliDisponibili, BorderLayout.NORTH);


		Volo voloCorrente = ((Volo)comboVoliDisponibili.getSelectedItem());
		labelCodiceAeroportoPartenzaPallet.setText("<html><b style='color:#242589'>Codice aeroporto di partenza: </b>"+" "+voloCorrente.getAeroportoPartenza()+"</html>");
		labelCodiceAeroportoPartenzaPallet.setFont(new Font("Arial", Font.PLAIN, 12));
		labelCodiceAeroportoPartenzaPallet.setBounds(16, 100, 482, 25);
		panelPalletVoli.add(labelCodiceAeroportoPartenzaPallet);

		labelCodiceAeroportoArrivoPallet.setText("<html><b style='color:#242589'>Codice aeroporto di arrivo: </b>"+" "+voloCorrente.getAeroportoDestinazione()+"</html>");
		labelCodiceAeroportoArrivoPallet.setFont(new Font("Arial", Font.PLAIN, 12));
		labelCodiceAeroportoArrivoPallet.setBounds(16, 130, 482, 25);
		panelPalletVoli.add(labelCodiceAeroportoArrivoPallet);

		labelDataOraVoloPallet.setText("<html><b style='color:#242589'>Data e ora volo: </b>"+" "+voloCorrente.getDataOraString()+"</html>");
		labelDataOraVoloPallet.setFont(new Font("Arial", Font.PLAIN, 12));
		labelDataOraVoloPallet.setBounds(16, 160, 482, 25);
		panelPalletVoli.add(labelDataOraVoloPallet);

		labelStatoVoloPallet.setText("<html><b style='color:#242589'>Stato volo: </b>"+" "+voloCorrente.getStato()+"</html>");
		labelStatoVoloPallet.setFont(new Font("Arial", Font.PLAIN, 12));
		labelStatoVoloPallet.setBounds(16, 190, 482, 25);
		panelPalletVoli.add(labelStatoVoloPallet);

		labelPalletDisponibili.setText("<html><b style='color:#242589'>Numero pallet disponibili: </b>"+" "+voloCorrente.getPalletDisponibili()+"</html>");
		labelPalletDisponibili.setFont(new Font("Arial", Font.PLAIN, 12));
		labelPalletDisponibili.setBounds(16, 220, 482, 25);
		panelPalletVoli.add(labelPalletDisponibili);

		labelPrezzoPallet.setText("<html><b style='color:#242589'>Prezzo al kg: </b>"+" "+voloCorrente.getPrezzoPallet()+" &euro;</html>"); //modificare in prezzo pallet
		labelPrezzoPallet.setFont(new Font("Arial", Font.PLAIN, 12));
		labelPrezzoPallet.setBounds(16, 250, 482, 25);
		panelPalletVoli.add(labelPrezzoPallet);




		buttonPalletConfermaVolo = new JButton("Conferma");
		buttonPalletConfermaVolo.setBounds(400, 287, 89, 23);
		panelPalletVoli.add(buttonPalletConfermaVolo);

		buttonPalletAnnullaVolo = new JButton("Annulla");
		buttonPalletAnnullaVolo.setBounds(310, 287, 89, 23);
		panelPalletVoli.add(buttonPalletAnnullaVolo);


		JLabel labelFondo = new JLabel("<html><span style='font-size:10px;font-weight:bold'>Pallet</span>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>Selezione tratta</span><span style='font-size:16px'>&nbsp;&nbsp;&nbsp;→&nbsp;&nbsp;</span><span  style='color:red'>Ricerca volo</span><span style='font-size:16px'>&nbsp;&nbsp;→&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='color:'>Inserimento dati pallet</span></html>");
		labelFondo.setBounds(10, 310, 480, 50);
		panelPalletVoli.add(labelFondo);

		repaint();

	}


	/**
	 * Carico gli oggetti di pallet:pallet
	 */
	public void setPalletPallet(){

		panelPalletPallet.removeAll(); //rimuovo tutti gli oggetti

		JLabel labelIstruzioni = new JLabel("Inserire gli ultimi dati:");
		labelIstruzioni.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelIstruzioni.setBounds(8, 8, 482, 25);
		panelPalletPallet.add(labelIstruzioni);


		JLabel lblTarga = new JLabel("Targa del pallet");
		lblTarga.setBounds(141, 110, 112, 14);
		panelPalletPallet.add(lblTarga);

		textFieldTargaPallet.setBounds(141, 127, 220, 20);
		panelPalletPallet.add(textFieldTargaPallet);
		textFieldTargaPallet.setColumns(10);

		JLabel lblPeso = new JLabel("Peso complessivo [600-1400]");
		lblPeso.setBounds(141, 160, 212, 14);
		panelPalletPallet.add(lblPeso);

		textFieldPesoPallet.setBounds(141, 177, 220, 20);
		panelPalletPallet.add(textFieldPesoPallet);
		textFieldPesoPallet.setColumns(10);


		buttonPalletConfermaPrenotazione = new JButton("Conferma");
		buttonPalletConfermaPrenotazione.setBounds(400, 287, 89, 23);
		panelPalletPallet.add(buttonPalletConfermaPrenotazione);

		buttonPalletAnnullaPrenotazione = new JButton("Annulla");
		buttonPalletAnnullaPrenotazione.setBounds(310, 287, 89, 23);
		panelPalletPallet.add(buttonPalletAnnullaPrenotazione);

		JLabel labelFondo = new JLabel("<html><span style='font-size:10px;font-weight:bold'>Pallet</span>:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>Selezione tratta</span><span style='font-size:16px'>&nbsp;&nbsp;&nbsp;→&nbsp;&nbsp;</span><span>Ricerca volo</span><span style='font-size:16px'>&nbsp;&nbsp;→&nbsp;&nbsp;&nbsp;&nbsp;</span><span style='color:red'>Inserimento dati pallet</span></html>");
		labelFondo.setBounds(10, 310, 480, 50);
		panelPalletPallet.add(labelFondo);

		//azzeramento dati pallet
		listaPallet.clear();
		textFieldTargaPallet.setText("");
		textFieldPesoPallet.setText("");
		repaint();
	}



	/**
	 * Costruisce la lista dei passeggeri di cui si sono gia inseriti i dati
	 */
	private void mostraPasseggeriMemorizzati(){

		Iterator<Passeggero> el = listaPasseggeri.iterator();
		int verticalPosition = 8;
		panelResoconto.removeAll();

		while(el.hasNext()) {
			Passeggero element = (Passeggero) el.next();

			JLabel label = new JLabel(element.getCognome()+" "+element.getNome());
			label.setFont(new Font("Monotype Corsiva", Font.PLAIN, 15));
			label.setBounds(12, verticalPosition, 300, 18);
			panelResoconto.add(label);
			verticalPosition=verticalPosition+24;
		}
		prezzoTotaleVolo = listaPasseggeri.size()*voloSelezionatoPasseggeri.getPrezzoPasseggero();
		prezzoTotaleVolo= Math.floor(prezzoTotaleVolo * 100) / 100.0;
		labelPrezzoTotale.setText("<html>Totale: " + prezzoTotaleVolo+ " &euro;  ("+listaPasseggeri.size()+" passeggeri)</html>");
		repaint();
	}


	/**
	 * Dato un passeggero, aggiorna i dati con quelli della form
	 *
	 * @param p il passeggero
	 */
	protected void save(Passeggero p){

		String sex = null;
		if(getSelectedButtonText(buttonGroupSesso).compareTo("Uomo")==0) sex="M"; 
		else sex="F";

		p.setNome(textPasseggeriNome.getText());
		p.setCognome(textPasseggeriCognome.getText());
		p.setSesso(Sesso.valueOf(sex));
		p.setGiorno(Integer.parseInt(comboBoxGiorno.getSelectedItem().toString()));
		p.setMese(Integer.parseInt(comboBoxMese.getSelectedItem().toString()));
		p.setAnno(Integer.parseInt(comboBoxAnno.getSelectedItem().toString()));
		p.calcolaEta();
		p.setIdVolo(voloSelezionatoPasseggeri.getId());

	}


	/**
	 * Controlla che i campi siano pieni
	 *
	 * @return vero se tutti i campi sono stati inseriti correttamente
	 */
	protected boolean controllaCampi(){
		return !(textPasseggeriNome.getText().equals("") || 
				textPasseggeriCognome.getText().equals("") || 
				comboBoxGiorno.getSelectedIndex()==0 ||
				comboBoxMese.getSelectedIndex()==0 ||
				comboBoxAnno.getSelectedIndex()==0
				);
	}

	/**
	 * 
	 * @return true se tutti i campi sono vuoti
	 */
	protected boolean campiVuoti(){
		return (textPasseggeriNome.getText().equals("") && 
				textPasseggeriCognome.getText().equals("") && 
				comboBoxGiorno.getSelectedIndex()==0 &&
				comboBoxMese.getSelectedIndex()==0 &&
				comboBoxAnno.getSelectedIndex()==0
				);
	}

	/**
	 * Capisce se si sta modificando un passeggero esistente o creandone uno nuovo
	 */
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
			mostraPasseggeriMemorizzati();
			if(currentIndex==lastIndex){
				lastIndex++;
			}
			currentIndex++;
		}
	}


	/**
	 * Passa alla visualizzazione del passeggero successivo.
	 */
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
			labelNumeroPasseggero.setText(currentIndex+1+"");
		}else{
			//JOptionPane.showMessageDialog(null,"Completare prima il passeggero corrente","Errore", 0); //tolto perchè compare anche quando faccio conferma prenotazione
		}

	}

	/**
	 * Passa alla visualizzazione del passeggero precedente.
	 */
	protected void passeggeroPrecedente(){
		if(controllaCampi() || campiVuoti()){ 
			if(currentIndex!=lastIndex && !campiVuoti()){ 
				save(passeggeroCorrente);
			}
			/*if(campiVuoti()){ e sono all'ultimo q
				listaPasseggeri.remove(currentIndex);
			}*/
			currentIndex--;
			passeggeroCorrente = listaPasseggeri.get(currentIndex);
			mostraPasseggero(passeggeroCorrente);
			labelNumeroPasseggero.setText(currentIndex+1+"");
		}else{
			JOptionPane.showMessageDialog(null,"Completare prima il passeggero corrente","Errore", 0);
		}
	}


	/**
	 * Mostra i dati del passeggero sullo schermo
	 *
	 * @param p the p
	 */
	protected void mostraPasseggero(Passeggero p){
		textPasseggeriNome.setText(p.getNome());
		textPasseggeriCognome.setText(p.getCognome());
		if(p.getSesso().equals(Sesso.M)){
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




	/**
	 * Dato un button group mi restituisce il testo del selezionato
	 *
	 * @param buttonGroup il button group di riferimento
	 * @return il testo dell'elemento selezionato
	 */
	public String getSelectedButtonText(ButtonGroup buttonGroup) {
		for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			AbstractButton button = buttons.nextElement();

			if (button.isSelected()) {
				return button.getText();
			}
		}
		return null;
	}

}
