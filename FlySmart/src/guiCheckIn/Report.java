package guiCheckIn;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.*;

import client.View;

import model.Pallet;
import model.Volo;
import model.Passeggero;

import smart.CheckinReport;

public class Report extends View {
	
	private static final long serialVersionUID = 2209345127490320486L;
	
	List<Passeggero> passeggeri;
	List<Pallet> pallet;
	Volo volo;
	Container contentPane;
	
	public Report(CheckinReport cr,Volo v) {
		lookAndFeel();
		setResizable(false);
		passeggeri = cr.getPasseggeri();
		pallet = cr.getPallets();
		this.volo=v;
		setTitle("Report per il volo "+v.getId());
		Dimension dimensioneFinestra = new Dimension(700,500);
		Toolkit mioTKit = Toolkit.getDefaultToolkit();
		Dimension dimensioniSchermo = mioTKit.getScreenSize();
		int xFrame = (dimensioniSchermo.width - dimensioneFinestra.width) / 2;
		int yFrame = (dimensioniSchermo.height - dimensioneFinestra.height) / 2;
		setLocation(xFrame, yFrame);
		setSize(dimensioneFinestra.width, dimensioneFinestra.height);
		setVisible(true);
		contentPane = getContentPane();
		funzione();
	}
	
	private void funzione(){
		
		
		//preparo i dati che mi servono
		//int colonne = volo.getTipoAereo().getColonnePasseggeri();
		
		
		JTextArea testo=new JTextArea();
	    testo.setLineWrap(true);
	    testo.setWrapStyleWord(true);

	    JScrollPane scroll=new JScrollPane(testo);
	    contentPane.add(scroll,BorderLayout.CENTER);
	     
	   
	    JLabel labelFlySmart = new JLabel("<html><span style='color:green'>Fly</span><span style='color:blue'>Smart</span><span style='font-size:15px;color:#666666'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Prenotazione pallet</span></html>");
		labelFlySmart.setFont(new Font("Calibri", Font.BOLD, 50));
		labelFlySmart.setForeground(Color.black);
		contentPane.add(labelFlySmart,BorderLayout.SOUTH);
	    
	   
		for (Passeggero p : passeggeri){
			testo.append("("+ p.getColonna()+ ";" + p.getFila()+") "+p.getCognome()+ " " +p.getNome() + " \n");
		}
		
		
		
		JPanel destra = new JPanel();
		destra.setLayout(new BorderLayout(10,10));
		destra.setBackground(Color.red);
		contentPane.add(destra,BorderLayout.EAST);
		
		JButton buttonCopia = new JButton("Copia asdsad a");
		destra.add(buttonCopia,BorderLayout.CENTER);
		
		
		JButton buttonCopia3 = new JButton("Copiae44444");
		destra.add(buttonCopia3,BorderLayout.NORTH);
		
		
	    
		
		
	}
}
