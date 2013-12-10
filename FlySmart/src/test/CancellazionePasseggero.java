package test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

import org.bson.types.ObjectId;

import cancellazione.DeleteException;

import network.ServerInterface;
import model.*;

public class CancellazionePasseggero {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		ServerLauncher.main(args);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
		Properties opt = new Properties();
		
		//controllo argomento
		String configFilePath;
		if(args.length >= 1){
			configFilePath = args[0];
		}else{
			configFilePath = "src/config/clientConfig.xml";
		}
		
		try {
			//carica file opzioni
			InputStream configFile = new FileInputStream(configFilePath);
			opt.loadFromXML(configFile);
			
			//setto i SSL certificate
			System.setProperty("javax.net.ssl.trustStore", opt.getProperty("trustStore")); //imposta trust store
			System.setProperty("javax.net.ssl.trustStorePassword", opt.getProperty("trustStorePassword"));
			
		} catch(FileNotFoundException e){
			System.err.println("File di configurazione non trovato");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		ServerInterface serv;
		String url = "rmi://localhost:1099/FlySmartServer";
			try {
				serv = (ServerInterface) Naming.lookup(url);
				List<Passeggero> l = new LinkedList<Passeggero>();
				
				l.add(new Passeggero("Alberto", "Bianchi", 10, 10, 1960, Sesso.M)); //80

				List<Volo> v = serv.getVoli(1, 2);
				//serv.prenotaPasseggero(l, v.get(0).getId());
				ObjectId[] idpren = serv.prenotaPasseggero(l, v.get(0).getId());
				List<Passeggero> listaAggiornata = serv.getPasseggeriGruppo(idpren[0]);
				System.out.println(listaAggiornata.get(0).getCognome());
				boolean ok = false;
				try {
					 ok = serv.cancellaPasseggeri(listaAggiornata);
				} catch (RemoteException e) {
					JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Errore", 0);
				} catch (DeleteException e) {
					JOptionPane.showMessageDialog(null, "Errore durante la cancellazione","Errore", 0);
				}
				if(ok){System.out.println("ok");
				}
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
