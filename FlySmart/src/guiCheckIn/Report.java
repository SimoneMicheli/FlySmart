package guiCheckIn;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
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

public class Report extends View {

	private static final long serialVersionUID = 2209345127490320486L;

	List<Passeggero> passeggeri;
	List<Pallet> pallet;
	Volo volo;
	Container contentPane;
	double sbilanciamento;

	public Report(CheckinReport cr,Volo v) {
		lookAndFeel();
		setResizable(false);
		passeggeri = cr.getPasseggeri();
		pallet = cr.getPallets();
		this.volo=v;
		sbilanciamento = Math.sqrt(Math.pow(cr.getMom()[0],2) + Math.pow(cr.getMom()[1],2));
		setTitle("Report per il volo "+v.getId());
		Dimension dimensioneFinestra = new Dimension(1200,800);
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


		int colonne = volo.getTipoAereo().getColonnePasseggeri();
		int righe = volo.getTipoAereo().getFilePasseggeri();
		boolean[][] occupancyPasseggeri = new boolean[righe][colonne];

		JTextArea reportPallet=new JTextArea();
		reportPallet.setLineWrap(false);
		reportPallet.setWrapStyleWord(false);
		JScrollPane scrollPallet=new JScrollPane(reportPallet);
		contentPane.add(scrollPallet,BorderLayout.WEST);
		for (Pallet p : pallet){
			reportPallet.append("("+ p.getColonna()+ ";" + p.getFila()+") "+p.getTarga()+ " " +p.getPeso() + "kg \n");
		}


		JTextArea reportPasseggeri=new JTextArea();
		reportPasseggeri.setLineWrap(true);
		reportPasseggeri.setWrapStyleWord(true);
		for (Passeggero p : passeggeri){
			reportPasseggeri.append("("+ p.getColonna()+ ";" + p.getFila()+") "+p.getCognome()+ " " +p.getNome() + " \n");
			occupancyPasseggeri[p.getFila()][p.getColonna()]=true;
		}
		JScrollPane scrollPasseggeri=new JScrollPane(reportPasseggeri);
		contentPane.add(scrollPasseggeri,BorderLayout.CENTER);


		JLabel labelFlySmart = new JLabel("Sbilanciamento: "+sbilanciamento);
		labelFlySmart.setFont(new Font("Calibri", Font.PLAIN, 30));
		labelFlySmart.setForeground(Color.black);
		contentPane.add(labelFlySmart,BorderLayout.SOUTH);






		JPanel destra = new JPanel();
		destra.setLayout(new BorderLayout());
		reportPasseggeri.setLineWrap(false);
		reportPasseggeri.setWrapStyleWord(false);
		contentPane.add(destra,BorderLayout.EAST);

		JButton buttonEsci = new JButton("Esci dal programma");
		destra.add(buttonEsci,BorderLayout.SOUTH);
		buttonEsci.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				System.exit(0);
			}
		});

		JButton buttonChiudi = new JButton("Chiudi il report");
		destra.add(buttonChiudi,BorderLayout.NORTH);
		buttonChiudi.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent arg0) {
				dispose();
			}
		});


		JPanel centro = new JPanel();
		centro.setLayout(new BorderLayout());
		destra.add(centro,BorderLayout.CENTER);

		Example e = new Example(righe, colonne,occupancyPasseggeri);
		e.setVisible(true);
		centro.add(e);



	}
}


class Example extends JPanel{
	private static final long serialVersionUID = 5194450620033074168L;
	private int righe;
	private int colonne;
	private boolean[][] matrix;

	Example(int righe, int colonne,boolean[][] o){
		this.righe=righe;
		this.colonne=colonne;
		matrix=new boolean[righe][colonne];
		for(int i=0;i<righe;i++){
			for(int j=0;j<colonne;j++){
				matrix[i][j]=o[i][j];
			}
		}
	}



	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.LIGHT_GRAY);
		for(int r=0;r<righe;r++){
			for(int c=0;c<colonne;c++){
				if(matrix[r][c]){
					g.setColor(Color.YELLOW);
					g.fillRect(5+20*c,10+20*r,15,15);
					g.setColor(Color.LIGHT_GRAY);
				}else{
					g.fillRect(5+20*c,10+20*r,15,15);
				}
			}
		}
	}
}