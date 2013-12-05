package client;


import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**Vista della Form di Login
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
public class LoginView extends View {

	private static final long serialVersionUID = 1202625258583639175L;

	/** Pannello contenente tutti gli oggetti */
	private JPanel contentPane;

	/** ip del server a cui connettersi */
	public JTextField ip;

	/** porta del server */
	public JTextField port;

	/** conferma la connessione */
	public JButton Connect;


	/**
	 * Costruttore della vista login
	 */
	public LoginView() {
		lookAndFeel();
		setResizable(false);
		int l=282;
		int h=130;
		setSize(l, h);
		Toolkit mioTKit = Toolkit.getDefaultToolkit();
		Dimension dimensioniSchermo = mioTKit.getScreenSize();
		int xFrame = (dimensioniSchermo.width - l) / 2;
		int yFrame = (dimensioniSchermo.height - h) / 2;
		setLocation(xFrame, yFrame); //imposto la finestra al centro dello schermo
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //chiusura del programma quando la login viene chiusa
		setTitle("Login");
		contentPane = new JPanel(); //pannello principale
		setContentPane(contentPane);
		contentPane.setLayout(null); 
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		disegnaPulsanti();
	}

	private void disegnaPulsanti(){
		
		Connect = new JButton("Connettiti");//tasto connettiti
		Connect.setBounds(10, 73, 254, 23);
		contentPane.add(Connect);

		
		ip = new JTextField();//casella per l'ip
		ip.setText("localhost");
		ip.setBounds(82, 11, 184, 20);
		contentPane.add(ip);
		ip.setColumns(10);

		
		port = new JTextField();//casella per la porta
		port.setText("1099");
		port.setBounds(82, 42, 184, 20);
		contentPane.add(port);
		port.setColumns(10);

		
		JLabel lblIp = new JLabel("IP");//label ip
		lblIp.setBounds(10, 14, 46, 14);
		contentPane.add(lblIp);

		
		JLabel lblPort = new JLabel("Port");//label port
		lblPort.setBounds(10, 45, 46, 14);
		contentPane.add(lblPort);
	}
}
