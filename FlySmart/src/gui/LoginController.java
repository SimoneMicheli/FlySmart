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


public class LoginController {

	
	LoginView view;
	PrenotazioneController p;
	ServerInterface serv;
	
	

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
		
		
		
	}
	
	public void ok(){
		if(view.ip.getText().toString().compareTo("")==0 || view.port.getText().toString().compareTo("")==0){
			JOptionPane.showMessageDialog(null, "Inserire tutti i campi","Error", 0);
		}else{
			String url = "rmi://"+ view.ip.getText().toString() +":"+ view.port.getText().toString() +"/FlySmartServer";
			try {
				serv = (ServerInterface) Naming.lookup(url);
			
				view.dispose();  //elimino la vecchia view
				p = new PrenotazioneController(serv); //creo il nuovo controller (che creerà la nuova view)
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
