package guiCheckIn;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import client.Controller;
import client.LoginView;

import network.ServerInterface;


/**Il controller della form di login per il check in
 * @author Demarinis - Micheli - Scarpellini
 * 
 */
public class LoginCheckInController extends Controller {


	/** Riferimento alla vista */
	LoginView view;

	/** Il controller della nuova vista */
	CheckInController p;




	/**
	 * Instantiates a new login controller.
	 *
	 * @param v il riferimento alla vista
	 */
	public LoginCheckInController(LoginView v){
		this.view=v;
		registraControllerLogin();
		
		//bypass
		ok();
	}

	/**
	 * aggiunge i listner agli oggetti della vista di login
	 */
	public void registraControllerLogin() {

		//click sul pulsante connettiti
		view.Connect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ok();
			}

		});

		//pressione tasto invio sulla casella ip
		view.ip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10){
					ok();
				}
			}
		});

		//pressione tasto invio sulla casella porta
		view.port.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10){
					ok();
				}
			}
		});



	}

	/**
	 * Connessione al server, con controllo dei campi e creazione della vista prenotazione
	 */
	public void ok(){
		if(view.ip.getText().toString().compareTo("")==0 || view.port.getText().toString().compareTo("")==0){ //se i campi sono vuoti
			JOptionPane.showMessageDialog(null, "Inserire tutti i campi","Error", 0);
		}else{ 
			String url = "rmi://"+ view.ip.getText().toString() +":"+ view.port.getText().toString() +"/FlySmartServer";
			try {
				serv = (ServerInterface) Naming.lookup(url);
				view.dispose();  //elimino la vecchia view
				p = new CheckInController(serv); //creo il nuovo controller (che creerà la nuova view)
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (RemoteException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Error", 0);
			} catch (NotBoundException e) {
				e.printStackTrace();
			}
		}
	}
}
