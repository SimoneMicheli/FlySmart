package gui;


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
	protected JMenuItem mntmExit = new JMenuItem("Esci");
	/** opzione switch. */
	protected JMenuItem mntmSwitch = new JMenuItem("Cambia");
	/** opzione su di noi. */
	protected JMenuItem mntmsuDiNoi = new JMenuItem("Su di noi");

	//combo box
	/** La combo box per la scelta dell'aeroporto di partenza per i passeggeri. */
	protected JComboBox comboPasseggeriAeroportoPartenza= new JComboBox();
	/** La combo box per la scelta dell'aeroporto di arrivo per i passeggeri. */
	protected JComboBox comboPasseggeriAeroportoArrivo = new JComboBox();
	/** La combo box per la scelta dell'aeroporto di partenza per i pallet. */
	protected JComboBox comboPalletAeroportoPartenza= new JComboBox();
	/** La combo box per la scelta dell'aeroporto di arrivo per i pallet. */
	protected JComboBox comboPalletAeroportoArrivo = new JComboBox();

	//text-field
	/** The text passeggeri nome. */
	protected JTextField textPasseggeriNome = new JTextField();
	/** The text passeggeri cognome. */
	protected JTextField textPasseggeriCognome = new JTextField();
	/** The text field targa pallet. */
	protected JTextField textFieldTargaPallet = new JTextField();
	/** The text field peso pallet. */
	protected JTextField textFieldPesoPallet = new JTextField();

	//combo-box
	/** The combo box giorno. */
	protected JComboBox comboBoxGiorno = new JComboBox();
	/** The combo box mese. */
	protected JComboBox comboBoxMese = new JComboBox();
	/** The combo box anno. */
	protected JComboBox comboBoxAnno = new JComboBox();

	//button-group
	/** The button group passeggeri voli. */
	protected ButtonGroup buttonGroupPasseggeriVoli = new ButtonGroup();
	/** The button group pallet voli. */
	protected ButtonGroup buttonGroupPalletVoli = new ButtonGroup();
	/** The button group sesso. */
	protected ButtonGroup buttonGroupSesso = new ButtonGroup();
	protected JRadioButton rdbtnNewRadioButton_uomo = new JRadioButton("Uomo");
	protected JRadioButton rdbtnNewRadioButton_donna = new JRadioButton("Donna");

	//label
	/** The label tipo prenotazione. */
	protected JLabel labelTipoPrenotazione= new JLabel();
	/** label contenente il numero del passeggero corrente. */
	protected JLabel labelNumeroPasseggero = new JLabel("1");
	/** The label lista passeggeri */
	protected JLabel labelListaPasseggeri = new JLabel("Lista passeggeri");

	//button 1 livello
	/** conferma la fase passeggeri:aeroporti */
	protected JButton buttonPasseggeriPasseggeriCercaVoli;
	/** conferma la fase pallet:aeroporti */
	protected JButton buttonPalletPasseggeriCercaVoli;
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
	/** resetta i campi dei passeggeri */
	protected JButton buttonPasseggeriReset;

	//varie variabili
	/** la dimensione della finestra */
	protected Dimension dimensioneFinestra = new Dimension(508,440);
	/** true: sono nel pannello passeggeri; false: sono nel pannello pallet */
	protected boolean passeggeri;
	/** il volo selezionato dalla fase passeggeri:voli. */
	int idVoloSelezionatoPasseggeri;
	/** la lista dei passeggeri da inviare */
	protected ArrayList<Passeggero> listaPasseggeri = new ArrayList<Passeggero>();
	/** indice dell'ultimo passeggero inserito nella prenotazione attuale */
	int lastIndex=0;
	/** indice del passeggero visualizzato */
	int currentIndex=0;
	/** passeggero visualizzato */
	Passeggero passeggeroCorrente=null;

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
		setTitle("Prenotazione FlySmart");
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
		mnInfo.add(mntmsuDiNoi);
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

		JLabel labelTipoPrenotazione = new JLabel("Selezionare aeroporto di partenza e di arrivo");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPasseggeriAeroporti.add(labelTipoPrenotazione);

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


		buttonPasseggeriPasseggeriCercaVoli = new JButton("Cerca Voli");
		buttonPasseggeriPasseggeriCercaVoli.setBounds(141, 215, 220, 23);
		panelPasseggeriAeroporti.add(buttonPasseggeriPasseggeriCercaVoli);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 360, 482, 26);
		panelPasseggeriAeroporti.add(progressBar);
		progressBar.setValue(10);
	}

	/**
	 * Carico gli oggetti di passeggeri:voli
	 *
	 * @param voli la lista dei voli ricevuta dal server
	 */
	public void setPasseggeriVoli(List<Volo> voli){

		panelPasseggeriVoli.removeAll(); //rimuovo tutti gli oggetti

		JLabel labelTipoPrenotazione = new JLabel("Selezionare un volo:");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPasseggeriVoli.add(labelTipoPrenotazione);

		Iterator<Volo> i = voli.iterator();
		int verticalPosition = 50;
		while(i.hasNext()) {
			Volo element = (Volo) i.next();
			JRadioButton rdbtnNewRadioButton = new JRadioButton(element.toString());
			buttonGroupPasseggeriVoli.add(rdbtnNewRadioButton);
			rdbtnNewRadioButton.setBounds(6, verticalPosition, 480, 23);
			panelPasseggeriVoli.add(rdbtnNewRadioButton);
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


	/**
	 * Carico gli oggetti di passeggeri:passeggeri
	 */
	@SuppressWarnings("unchecked")
	protected void setPasseggeriPasseggeri(){


		JLabel labelTipoPrenotazione = new JLabel("Inserire i dati dei passeggeri");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPasseggeriPasseggeri.add(labelTipoPrenotazione);

		/*
		JLabel labelResocontoVolo = new JLabel("ID: "+idVoloSelezionato + " From: Milano   To: Bergamo");
		labelResocontoVolo.setFont(new Font("Arial", Font.PLAIN, 14));
		labelResocontoVolo.setBounds(18, 58, 482, 25);
		panelPasseggeriPasseggeri.add(labelResocontoVolo);
		 */

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
		comboBoxGiorno.setBounds(80, 230, 42, 20);
		panelPasseggeriPasseggeri.add(comboBoxGiorno);

		//mese di nascita
		comboBoxMese.removeAllItems();
		comboBoxMese.addItem("");
		for(int i=1;i<12;i++){
			comboBoxMese.addItem(""+i);
		}
		comboBoxMese.setBounds(137, 230, 42, 20);
		panelPasseggeriPasseggeri.add(comboBoxMese);

		//anno di nascita
		comboBoxAnno.removeAllItems();
		comboBoxAnno.addItem("");
		for(int i=2013;i>1900;i--){
			comboBoxAnno.addItem(""+i);
		}
		comboBoxAnno.setBounds(190, 230, 55, 20);
		panelPasseggeriPasseggeri.add(comboBoxAnno);


		buttonPasseggeriReset = new JButton("Svuota");
		buttonPasseggeriReset.setFont(new Font("Tahoma", Font.PLAIN, 10));
		buttonPasseggeriReset.setBounds(20, 270, 70, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriReset);

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
		buttonPasseggeriAnnullaPrenotazione.setBounds(310, 327, 89, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriAnnullaPrenotazione);

		buttonPasseggeriConfermaPrenotazione = new JButton("Conferma");
		buttonPasseggeriConfermaPrenotazione.setBounds(400, 327, 89, 23);
		panelPasseggeriPasseggeri.add(buttonPasseggeriConfermaPrenotazione);


		panelResoconto.setLayout(null);
		panelResoconto.setBounds(277, 90, 205, 230);
		panelPasseggeriPasseggeri.add(panelResoconto);


		labelListaPasseggeri.setFont(new Font("Arial", Font.PLAIN, 13));
		labelListaPasseggeri.setBounds(277, 58, 482, 25);
		panelPasseggeriPasseggeri.add(labelListaPasseggeri);


		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 360, 482, 26);
		panelPasseggeriPasseggeri.add(progressBar);
		progressBar.setValue(90);


		//azzeramento dati passeggeri
		listaPasseggeri.clear();
		textPasseggeriNome.setText("");
		textPasseggeriCognome.setText("");
		passeggeroCorrente=null;
		currentIndex=0;
		lastIndex=0;
		labelNumeroPasseggero.setText(currentIndex+1+"");
		panelResoconto.removeAll();


	}

	/**
	 * Costruisce la lista dei passeggeri di cui si sono gia inseriti i dati
	 */
	private void mostraPasseggeriMemorizzati(){
		Iterator<Passeggero> el = listaPasseggeri.iterator();
		int verticalPosition = 20;
		panelResoconto.removeAll();

		while(el.hasNext()) {
			Passeggero element = (Passeggero) el.next();

			JLabel label = new JLabel(element.getCognome()+" "+element.getNome());
			label.setFont(new Font("Arial", Font.PLAIN, 11));
			label.setBounds(20, verticalPosition, 300, 18);
			panelResoconto.add(label);
			verticalPosition=verticalPosition+22;
		}
		repaint();
	}


	/**
	 * Dato un passeggero, aggiorna i dati con quelli della form
	 *
	 * @param p il passeggero
	 */
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
		p.setIdVolo(idVoloSelezionatoPasseggeri);

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
			JOptionPane.showMessageDialog(null,"Completare prima il passeggero corrente","Errore", 0);
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
			JOptionPane.showMessageDialog(null,"Completare prima il passeggero corrente o cliccare \"Svuota\"","Errore", 0);
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



	/**
	 * Carico gli oggetti di pallet:aeroporti
	 */
	@SuppressWarnings({ "unused", "unchecked" })
	private void setPalletAeroporti(){
		//rimuovere unused
		labelTipoPrenotazione.setText("Prenotazione Pallet 1/3");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPalletAeroporti.add(labelTipoPrenotazione);

		JLabel labelAeroportoPartenza = new JLabel("Aereporto Partenza");
		labelAeroportoPartenza.setBounds(141, 110, 112, 14);
		panelPalletAeroporti.add(labelAeroportoPartenza);

		comboPalletAeroportoPartenza.setBounds(141, 127, 220, 20);
		comboPalletAeroportoPartenza.addItem("");
		panelPalletAeroporti.add(comboPalletAeroportoPartenza);

		JLabel labelAeroportoArrivo = new JLabel("Aereporto Arrivo");
		labelAeroportoArrivo.setBounds(141, 160, 112, 14);
		panelPalletAeroporti.add(labelAeroportoArrivo);

		comboPalletAeroportoArrivo.setBounds(141, 177, 220, 20);
		comboPalletAeroportoArrivo.addItem("");
		panelPalletAeroporti.add(comboPalletAeroportoArrivo);

		buttonPalletPasseggeriCercaVoli = new JButton("Cerca Voli");
		buttonPalletPasseggeriCercaVoli.setBounds(141, 215, 220, 23);
		panelPalletAeroporti.add(buttonPalletPasseggeriCercaVoli);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(10, 360, 482, 26);
		panelPalletAeroporti.add(progressBar);
		progressBar.setValue(2);
	}

	/**
	 * Carico gli oggetti di pallet:voli
	 */
	public void setPalletVoli(){

		panelPalletVoli.removeAll();

		JLabel labelTipoPrenotazione = new JLabel("Pallet");
		labelTipoPrenotazione.setFont(new Font("Tahoma", Font.PLAIN, 20));
		labelTipoPrenotazione.setBounds(8, 8, 482, 25);
		panelPalletVoli.add(labelTipoPrenotazione);

		JRadioButton rdbtnNewRadioButton = new JRadioButton(Double.toString(Math.random()));
		buttonGroupPalletVoli.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setBounds(6, 50, 490, 23);
		panelPalletVoli.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Orio Al Serio -");
		buttonGroupPalletVoli.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setBounds(6, 80, 490, 23);
		panelPalletVoli.add(rdbtnNewRadioButton_1);

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

	/**
	 * Carico gli oggetti di pallet:pallet
	 */
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
