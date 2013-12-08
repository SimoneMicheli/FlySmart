package guiCheckIn;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import client.View;
import model.Pallet;
import model.Volo;
import model.Passeggero;
import smart.CheckinReport;


// TODO: Auto-generated Javadoc
/**
 * Genera il report per un volo
 */
public class Report extends View {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2209345127490320486L;

	/** La lista dei passeggeri del volo */
	List<Passeggero> passeggeri;
	
	/** La lista dei pallet del volo */
	List<Pallet> pallet;
	
	/** Il riferimento all'oggetto volo */
	Volo volo;
	
	/** il pannello contenitore. */
	Container contentPane;
	
	/** il momento di rollio */
	double momX;
	
	/** il momento di beccheggio */
	double momY;
	
	/** il peso totale imbarcato*/
	int pesoTotale;
	

	/** The sbilanciamento di rollio in percentuale. */
	double sbilanciamentoX;

	/** The sbilanciamento di beccheggio in percentuale. */
	double sbilanciamentoY;
	
	/** The sbilanciamento globale percentuale. */
	double sbilanciamento;

	/**
	 * Instantiates a new report.
	 *
	 * @param cr l'oggetto checkinreport generato dall'applicazione di checkin
	 * @param v the il volo di riferimento
	 */
	public Report(CheckinReport cr,Volo v) {
		lookAndFeel();
		setResizable(false);
		passeggeri = cr.getPasseggeri();
		pallet = cr.getPallets();
		this.volo=v;
		momX = cr.getMom()[0];
		momY = cr.getMom()[1];
		pesoTotale=0;
		//sbilanciamentoX = Math.floor(cr.getMom()[0]/v.getTipoAereo().getColonnePasseggeri()*100)/100.0;
		//sbilanciamentoY = Math.floor(cr.getMom()[1]/v.getTipoAereo().getFilePallet()*100)/100.0;
		//sbilanciamento = Math.floor(Math.sqrt(Math.pow(sbilanciamentoX,2) + Math.pow(sbilanciamentoY,2))*100)/100.0;
		setTitle("Report per il volo "+v.getId());
		Dimension dimensioneFinestra = new Dimension(800,750);
		Toolkit mioTKit = Toolkit.getDefaultToolkit();
		Dimension dimensioniSchermo = mioTKit.getScreenSize();
		int xFrame = (dimensioniSchermo.width - dimensioneFinestra.width) / 2;
		int yFrame = (dimensioniSchermo.height - dimensioneFinestra.height) / 2;
		setLocation(xFrame, yFrame);
		setSize(dimensioneFinestra.width, dimensioneFinestra.height);
		setVisible(true);
		contentPane = getContentPane();
		creaReport();
	}

	/**
	 * Crea il report grafico
	 */
	private void creaReport(){
		
		int colonnePasseggeri = volo.getTipoAereo().getColonnePasseggeri();
		int righePasseggeri = volo.getTipoAereo().getFilePasseggeri();
		int colonnePallet = volo.getTipoAereo().getColonnePallet();
		int righePallet = volo.getTipoAereo().getFilePallet();
		boolean[][] occupancyPasseggeri = new boolean[righePasseggeri][colonnePasseggeri];
		boolean[][] occupancyPallet = new boolean[righePallet][colonnePallet];

		
		//text area centrale
		JTextArea reportTextArea=new JTextArea();
		reportTextArea.setLineWrap(false);
		reportTextArea.setWrapStyleWord(false);
		reportTextArea.append("PALLET\n");
		for (Pallet p : pallet){
			reportTextArea.append("("+ p.getColonna()+ ";" + p.getFila()+") "+p.getTarga()+ " " +p.getPeso() + "kg \n");
			occupancyPallet[p.getFila()][p.getColonna()]=true;
			pesoTotale+=p.getPeso();
		}
		
		reportTextArea.append("\n\nPASSEGGERI\n");
		for (Passeggero p : passeggeri){
			reportTextArea.append("("+ p.getColonna()+ ";" + p.getFila()+") "+p.getCognome()+ " " +p.getNome()+ " \n");
			occupancyPasseggeri[p.getFila()][p.getColonna()]=true;
			pesoTotale+=p.getPeso();
		}
		JScrollPane scrollPasseggeri=new JScrollPane(reportTextArea);
		contentPane.add(scrollPasseggeri,BorderLayout.CENTER);

		//divido il mom per il peso totale e lo divido per la massima distanza (-0.5 da ognuna delle due parti quindi -1, /2 perchè è metà aereo)
		sbilanciamentoX = Math.abs(Math.floor((momX/pesoTotale)/((volo.getTipoAereo().getColonnePasseggeri()-1)/2)*1000)/10);
		sbilanciamentoY = Math.abs(Math.floor((momY/pesoTotale)/((volo.getTipoAereo().getFilePasseggeri()+1)*1000)/2)/10);
		
		//informazioni in basso
		JLabel labelFlySmart = new JLabel("Rollio: "+sbilanciamentoX+" %    Beccheggio: "+sbilanciamentoY+" %");
		labelFlySmart.setFont(new Font("Arial", Font.PLAIN, 28));
		labelFlySmart.setForeground(Color.black);
		contentPane.add(labelFlySmart,BorderLayout.SOUTH);

		
		//pannello di sinistra
		JPanel sinistra = new JPanel();
		sinistra.setLayout(new BorderLayout());
		contentPane.add(sinistra,BorderLayout.WEST);
		
		JButton buttonEsci = new JButton("Chiudi Report");
		sinistra.add(buttonEsci,BorderLayout.SOUTH);
		buttonEsci.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				dispose();
			}
		});
		//pannello centrale a sinistra
		JPanel centroSinistra = new JPanel();
		centroSinistra.setLayout(new BorderLayout());
		sinistra.add(centroSinistra,BorderLayout.CENTER);

		SchemaAereo schemaPallet = new SchemaAereo(righePallet, colonnePallet,occupancyPallet);
		schemaPallet.setVisible(true);
		centroSinistra.add(schemaPallet);
		
		
		
		//pannello di destra
		JPanel destra = new JPanel();
		destra.setLayout(new BorderLayout());
		contentPane.add(destra,BorderLayout.EAST);

		JButton buttonChiudi = new JButton("Chiudi applicazione Checkin");
		destra.add(buttonChiudi,BorderLayout.SOUTH);
		buttonChiudi.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				System.exit(0);
			}
		});
		
		//pannello centrale a destra
		JPanel centroDestra = new JPanel();
		centroDestra.setLayout(new BorderLayout());
		destra.add(centroDestra,BorderLayout.CENTER);

		SchemaAereo schemaPasseggeri = new SchemaAereo(righePasseggeri, colonnePasseggeri,occupancyPasseggeri);
		schemaPasseggeri.setVisible(true);
		centroDestra.add(schemaPasseggeri);
		
	}
}


