package gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import network.ServerInterface;


public class LoginController implements Controller{

	/**
	 * @param args
	 */
	LoginView view;
	PrenotazioneController p;
	
	
	

	public LoginController(LoginView v){
		view=v;
		registraController();
		//temporaneo bypass
		//view.dispose();  //elimino la vecchia view
	    //p = new PrenotazioneController(); //creo il nuovo controller (che creera la nuova view)
	}

	public void registraController() {
		view.Connect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("Clic sul connect");
				ok();
			}
			
		});
		view.port.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode() == 10){
					 ok();
				 }
			}
		});
		view.ip.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 if (e.getKeyCode() == 10){
					 ok();
				 }
			}
		});
		
		view.Reset.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				System.out.println("Clic sul reset");
				view.ip.setText("");
				view.port.setText("");
			}
		});
		
		
	}
	
	public void ok(){
		if(view.ip.getText().toString().compareTo("")==0 || view.port.getText().toString().compareTo("")==0){
			JOptionPane.showMessageDialog(null, "Inserire tutti i campi","Error", 0);
		}else{
			String url = "rmi://"+ view.ip.getText().toString() +":"+ view.port.getText().toString() +"/FlySmartServer";
			System.out.println(url);
			try {
				ServerInterface serv = (ServerInterface) Naming.lookup(url);
				//System.out.println(serv.getAirports());
				view.dispose();  //elimino la vecchia view
				p = new PrenotazioneController(serv); //creo il nuovo controller (che creer� la nuova view)
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Error", 0);
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
