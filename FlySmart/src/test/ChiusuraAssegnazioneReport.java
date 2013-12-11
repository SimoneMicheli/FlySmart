package test;

import guiCheckIn.Report;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import network.ServerInterface;

import prenotazione.FlightNotFoundException;
import prenotazione.PrenotazionePallet;
import prenotazione.PrenotazionePasseggero;
import smart.CheckinReport;
import smart.PostiLiberi;
import smart.Posto;
import smart.SmartCheckin;
import util.Options;

import model.Pallet;
import model.Passeggero;
import model.Sesso;
import model.Volo;
import db.DBSession;

public class ChiusuraAssegnazioneReport {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InvalidPropertiesFormatException 
	 */
	public static void main(String[] args) throws InvalidPropertiesFormatException, IOException {

		Options.initOptions();
		
		Volo v = DBSession.getInstance().createQuery(Volo.class)
				.filter("aeroportoPartenza =",1)
				.filter("aeroportoDestinazione =", 2)
				.filter("dataOra >", new Date())
				.order("dataOra")
				.asList().get(0);

		System.out.println(v);

		//testNuovoAlg(v);
		prenotaPallet(v);
		prenotaPass(v);

		long start = System.currentTimeMillis();
		
		SmartCheckin c = null;
		try {
			c = new SmartCheckin(v.getId());
		} catch (FlightNotFoundException e) {
			e.printStackTrace();
		}

		CheckinReport s = c.calcolaCheckin();
		
		long end = System.currentTimeMillis();
		
		for(Passeggero p : s.getPasseggeri()){
			System.out.println(p);
		}
		
		for(Pallet p : s.getPallets()){
			System.out.println(p);
		}
		
		System.out.println("Execution time was "+(end-start)+" ms.");
		
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
			CheckinReport cr = serv.calcolaCheckin(v.getId());
			JFrame frame = new Report(cr,v);
			frame.setVisible(true);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Impossibile connettersi al server","Error", 0);
		} catch (NotBoundException e) {
			e.printStackTrace();
		}catch (FlightNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		

	}
	
	public static void testNuovoAlg(Volo v){
		
		PostiLiberi p = new PostiLiberi(v);
		
		Posto pl = p.postoLiberoPasseggeri(0, 0);
		
		System.out.println("------------");
		System.out.println(pl);
	}

	/**
	 * Method prenotaPallet.
	 * @param v Volo
	 */
	public static void prenotaPallet(Volo v){

		List<Pallet> l = new LinkedList<Pallet>();

		PrenotazionePallet prenotaz = new PrenotazionePallet();

		l.add(new Pallet(1400, "a", v.getId()));
		l.add(new Pallet(800, "b", v.getId()));
		l.add(new Pallet(1300, "c", v.getId()));

		//prenotazione 1
		prenotaz.prenota(l, v.getId());
		l.clear();


		l.add(new Pallet(800, "d", v.getId()));
		l.add(new Pallet(1200, "e", v.getId()));
		l.add(new Pallet(900, "f", v.getId()));

		//prenotazione 2
		prenotaz.prenota(l, v.getId());
		l.clear();

		l.add(new Pallet(1100, "g", v.getId()));
		l.add(new Pallet(1000, "h", v.getId()));
		//prenotazione 3
		prenotaz.prenota(l, v.getId());
		l.clear();

		

	}

	/**
	 * Method prenotaPass.
	 * @param v Volo
	 */
	public static void prenotaPass(Volo v){

		List<Passeggero> l = new LinkedList<Passeggero>();

		PrenotazionePasseggero prenotaz = new PrenotazionePasseggero();
		

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione
		
		

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("andrea", "bianchi", 4, 4, 1997, Sesso.M)); //64
		l.add(new Passeggero("michelle", "bianchi", 4, 4, 1997, Sesso.F)); //48
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione
		
		

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("andrea", "bianchi", 4, 4, 1997, Sesso.M)); //64
		l.add(new Passeggero("andrea", "bianchi", 4, 4, 1997, Sesso.M)); //64
		l.add(new Passeggero("andrea", "bianchi", 4, 4, 1997, Sesso.M)); //64
		l.add(new Passeggero("andrea", "bianchi", 4, 4, 1997, Sesso.M)); //64
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("michelle", "bianchi", 4, 4, 1997, Sesso.F)); //48
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("michelle", "bianchi", 4, 4, 1997, Sesso.F)); //48
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("michelle", "bianchi", 4, 4, 1997, Sesso.F)); //48
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione
		
		

		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("michelle", "bianchi", 4, 4, 1997, Sesso.F)); //48
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione
		

		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione
		

		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione
		

		l.add(new Passeggero("mario", "rossi", 10, 3, 1960, Sesso.M)); //80
		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione
		

		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

		l.add(new Passeggero("maria", "rossi", 10, 3, 1960, Sesso.F)); //60
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione
		

		l.add(new Passeggero("valentina", "verdi", 15, 8, 2012, Sesso.F)); //30
		prenotaz.prenota(l, v.getId()); l.clear(); //prenotazione

	}

}
