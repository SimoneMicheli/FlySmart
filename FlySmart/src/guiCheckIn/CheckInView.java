package guiCheckIn;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

import model.Aeroporto;

import client.View;


/**La vista del check in
 *
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
@SuppressWarnings("rawtypes")
public class CheckInView extends View {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1234797801734727711L;
	
	protected Dimension dimensioneFinestra = new Dimension(508,240);
	protected JComboBox comboAeroporto= new JComboBox();
	protected JComboBox comboVoli= new JComboBox();
	protected JButton buttonChiudiVolo = new JButton("Chiudi e calcola Check-In");
	Container contentPane;
	
	public CheckInView(){
		contentPane = getContentPane();
		contentPane.setLayout(null);
		setGraphic();
	}
	
	
	private void setGraphic(){
		lookAndFeel();
		setResizable(false);
		setTitle("FlySmart: Check-In");
		Toolkit mioTKit = Toolkit.getDefaultToolkit();
		Dimension dimensioniSchermo = mioTKit.getScreenSize();
		int xFrame = (dimensioniSchermo.width - dimensioneFinestra.width) / 2;
		int yFrame = (dimensioniSchermo.height - dimensioneFinestra.height) / 2;
		setLocation(xFrame, yFrame);
		setSize(dimensioneFinestra.width, dimensioneFinestra.height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * Method disegnaElementi.
	 * @param aeroporti List<Aeroporto>
	 */
	@SuppressWarnings("unchecked")
	protected void disegnaElementi(List<Aeroporto> aeroporti){
		
		JLabel labelFlySmart = new JLabel("<html><span style='color:green'>Fly</span><span style='color:blue'>Smart</span><span style='font-size:15px;color:#666666'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Check-In</span></html>");
		labelFlySmart.setFont(new Font("Calibri", Font.BOLD, 50));
		labelFlySmart.setForeground(Color.black);
		labelFlySmart.setBounds(8, 0, 482, 70);
		contentPane.add(labelFlySmart);
		
		
		JLabel labelAeroportoPartenza = new JLabel("Aeroporto");
		labelAeroportoPartenza.setBounds(130, 99, 112, 14);
		contentPane.add(labelAeroportoPartenza);
		comboAeroporto.setBounds(191, 95, 170, 20);
		comboAeroporto.addItem("");
		Iterator<Aeroporto> i = aeroporti.iterator();
		while(i.hasNext()) {
			Aeroporto element = (Aeroporto) i.next();
			comboAeroporto.addItem(element);
		}
		contentPane.add(comboAeroporto);
		
		comboVoli.setBounds(12, 130, 470, 23);
		comboVoli.setVisible(false);
		contentPane.add(comboVoli);
		
		buttonChiudiVolo.setBounds(141, 170, 220, 23);
		buttonChiudiVolo.setVisible(false);
		contentPane.add(buttonChiudiVolo);
		
		
	}
	
}
