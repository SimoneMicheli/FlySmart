package guiCheckIn;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

// TODO: Auto-generated Javadoc
/**
 * Disegna lo schema dell'aereo
 */
class SchemaAereo extends JPanel{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5194450620033074168L;
	
	/** il numero di righe del grafico. */
	private int righe;
	
	/** il numero di colonne del grafico. */
	private int colonne;
	
	/** la matrice dei posti, true se occupato */
	private boolean[][] matrix;

	/**
	 * Instantiates a new schema aereo.
	 *
	 * @param righe il numero di righe
	 * @param colonne il numero di colonne
	 * @param o la matrice di occupanza
	 */
	SchemaAereo(int righe, int colonne,boolean[][] o){
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
		for(int r=0;r<righe;r++){
			for(int c=0;c<colonne;c++){
				if(matrix[r][c]){ //se occupata, gialla
					g.setColor(Color.YELLOW);
					g.fillRect(32+17*c,20+17*r,13,13);
				}else{ //se libera, grigio
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(32+17*c,20+17*r,13,13);
				}
			}
		}
	}
}