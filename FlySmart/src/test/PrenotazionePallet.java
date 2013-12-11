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

import network.ServerInterface;
import model.*;

public class PrenotazionePallet {

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
				List<Pallet> l = new LinkedList<Pallet>();
				List<Volo> v = serv.getVoli(1, 2);		
				l.add(new Pallet(800,"rthfdg", v.get(0).getId()));
				serv.prenotaPallet(l, v.get(0).getId());
				
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
