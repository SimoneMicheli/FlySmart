package guiCheckIn;



import java.awt.Container;
import java.awt.Dimension;
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
		Dimension dimensioneFinestra = new Dimension(508,240);
		Toolkit mioTKit = Toolkit.getDefaultToolkit();
		Dimension dimensioniSchermo = mioTKit.getScreenSize();
		int xFrame = (dimensioniSchermo.width - dimensioneFinestra.width) / 2;
		int yFrame = (dimensioniSchermo.height - dimensioneFinestra.height) / 2;
		setLocation(xFrame, yFrame);
		setSize(dimensioneFinestra.width, dimensioneFinestra.height);
		setVisible(true);
		contentPane = getContentPane();
		contentPane.setLayout(null);
		funzione();
	}
	
	private void funzione(){
		
		
		//preparo i dati che mi servono
		//int colonne = volo.getTipoAereo().getColonnePasseggeri();

		JTextArea pass = new JTextArea();
		pass.setBounds(0, 0, 480, 220);
		contentPane.add(pass);
		
		
		for (Passeggero p : passeggeri){
			pass.append("("+ p.getColonna()+ ";" + p.getFila()+") "+p.getCognome()+ " " +p.getNome() + " \n");
		}
		
	}
}
