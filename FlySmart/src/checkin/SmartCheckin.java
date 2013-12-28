package checkin;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import prenotazione.FlightNotFoundException;
import util.Coordinata;
import util.CoordinataPallet;
import util.CoordinataPasseggero;
import db.DBSession;
import db.LockImpl;
import model.Gruppo;
import model.Pallet;
import model.Passeggero;
import model.StatoVolo;
import model.Volo;


/**
 *
 */
public class SmartCheckin implements Checkin{

	/**
	 * volo su cui calcolare i dati
	 */
	private Volo v;
	
	/**
	 * puntatore all'oggetto che gestisce i posti liberi
	 */
	private PostiLiberi posti;
	
	Logger log;

	/**
	 * al momento della creazione dell'oggeto viene verificato che il volo esiste
	 * e se è presente viene posto in stato di chiuso
	 * @param idVolo su cui calcolare le posizioni
	 * @throws FlightNotFoundException	 */
	public SmartCheckin(ObjectId idVolo) throws FlightNotFoundException{
		log = LogManager.getLogger(SmartCheckin.class.getCanonicalName().toString());
		System.out.println(SmartCheckin.class.getCanonicalName().toString());
		
		try{
			LockImpl.getInstance().acquireLock(idVolo);

			v = DBSession.getVoloDAO().get(idVolo);

			//volo non trovato
			if(v == null)
				throw new FlightNotFoundException(idVolo);

			//chiudo il volo
			v.setStato(StatoVolo.CLOSED);
			

			DBSession.getVoloDAO().save(v);
			
			posti = new PostiLiberi(v);
		}finally{
			LockImpl.getInstance().releaseLock(idVolo);
		}

	}


	/**
	 * Method calcolaCheckin.
	 * @return CheckinReport
	 * @see checkin.Checkin#calcolaCheckin()
	 */
	@Override
	public CheckinReport calcolaCheckin() {
		//ottengo lista passeggeri e pallet
		List<Passeggero> passeggeri = DBSession.getPasseggeroDAO().getByIdVolo(v.getId()).order("-peso").asList();
		List<Pallet> pallets = DBSession.getPalletDAO().getByIdVolo(v.getId()).order("-peso").asList();

		//calcola disposizione
		double mom[] = new double[2];
		mom = posizionaPallet(pallets);	
		
		log.debug("ordinamento passeggeri---------------------");
		
		mom = posizionaPasseggeri(passeggeri, mom);

		//salva dati aggiornati nel db
		DBSession.getPasseggeroDAO().saveList(passeggeri);
		DBSession.getPalletDAO().saveList(pallets);

		return new CheckinReport(passeggeri, pallets, mom);

	}

	/**
	 * calcola dove posizionare i pallet sul volo
	 * mediante algoritmo greedy
	 * @param lista lista di pallet da posizionare
 * @return double[] sbilanciamento al termine del posizionamento dei pallet
		 */
	protected double[] posizionaPallet(List<Pallet> lista){
		double mom[] = new double[2];
		Arrays.fill(mom,0);
		
		//lista vuota (uso lazy evaluation)
		if(lista == null || lista.size() == 0){
			return mom;
		}
		
		Iterator<Pallet> i = lista.iterator();
		Coordinata coord = new CoordinataPallet(v.getTipoAereo());
		
		
		//ottengo primo pallet
		Pallet p = i.next();

		//posizione pallet in pos default
		p.setFila(coord.YAbs(0.5));
		p.setColonna(0);

		mom[0] = p.getPeso() * -0.5;
		mom[1] = p.getPeso() * +0.5;
		
		log.debug("momX: "+mom[0]);
		log.debug("momY: "+mom[1]);

		//posizono il primo pallet
		PostoLibero pos = posti.postoLiberoPallet(-0.5,0.5);
		log.debug("Questo peso: di "+p.getPeso()+"kg va in [x:"+p.getColonna()+" y:"+p.getFila()+"] produce [momX:"+mom[0]+" momY: "+mom[1]+"]");
		
		//calcolo posizione per i pallet successivi
		while(i.hasNext()){
			p = i.next();

			log.debug("Pallet di peso: "+p.getPeso());
			//posizione ideale per annullare il momento
			double dist[] = new double[2];
			dist[0] = -mom[0] / p.getPeso();
			dist[1] = -mom[1] / p.getPeso();
			log.debug("Posto calcolato: x:"+dist[0] +" y:"+dist[1]);

			pos = posti.postoLiberoPallet(dist[0],dist[1]); //ritorno in modo XY quindi prima posizione è la colonna

			p.setColonna(pos.x); 
			p.setFila(pos.y);

			//sbilanciamento effettivo
			mom[0] = mom[0] + p.getPeso() * coord.XRel(pos.x); //sbilanciamento sulle x è la colonna
			mom[1] = mom[1] + p.getPeso() * coord.YRel(pos.y); //sbilanciamento sulle y è la fila
			log.debug("Questo pallet di peso: "+p.getPeso()+"kg va in [x:"+p.getColonna()+" y:"+p.getFila()+"] e lascia un mom di [momX:"+mom[0]+" momY: "+mom[1]+"]");
		
		}
		return mom;
	}
	
	
	/**
	 * Method posizionaPasseggeri.
	 * @param lista List<Passeggero> lista di passegger ida posizionare
	 * @param mom double[] sbilanciamento ottenuto dal posizionamento dei pallet
	 * @return double[] sbilanciamento al termine del posizionamento dei passeggeri
	 */
	protected double[] posizionaPasseggeri(List<Passeggero> lista, double mom[]){
		//lista vuota
		if(lista == null || lista.size() == 0)
			return mom;
			
		//genero elenco gruppi ordinati per peso
		Gruppo[] gruppi = SmartGroupOrdering.sortGroup(lista);
		
		Gruppo g = null;
		
		//moltiplico momento
		mom[0] = mom[0] * 3;
		mom[1] = mom[1] * 3;
		
		double[] pos = new double[2];
		
		for(int i = 0; i < gruppi.length ; i++){
			g = gruppi[i];

			log.debug("Prossimo gruppo che pesa "+ g.getPesoTotale());
			//calcolo posizione ideale gruppo
			pos[0] = -mom[0] / g.getPesoTotale();
			pos[1] = -mom[1] / g.getPesoTotale();
			log.debug("posto calcolato: x:"+pos[0] +" y:"+pos[1]);
			
			//posiziono il gruppo e mo faccio dare il momento rispetto a quello ideale
			double momg[] = posizionaGruppo(pos[0], pos[1], g);
			
			//aggiorno momento per prossima iterazione
			mom[0] += momg[0];
			mom[1] += momg[1];
			log.debug("######################## Questo gruppo di "+g.getPesoTotale()+"kg lascia un mom di [momX:"+mom[0]+" momY: "+mom[1]+"]");
			
			
		}
		
		return mom;
	}
	
	
	/**
	 * posiziona tutti ipasseggeri di uno stesso gruppo vicini alla posizione ottimale
	 * @param colonnaScelta double posizione ottimale gruppo
	 * @param rigaScelta double posizione ottimale gruppo
	 * @param g Gruppo gruppo da posizionare
	 * @return double[] sbilanciamento relativo lasciato dal gruppo
	 */
	protected double[] posizionaGruppo(double colonnaScelta, double rigaScelta, Gruppo g){
		Coordinata coord = new CoordinataPasseggero(v.getTipoAereo());
		
		double[] mom = new double[2];
		
		for(Passeggero p : g){
			PostoLibero postoLibero = posti.postoLiberoPasseggeri(colonnaScelta, rigaScelta);
			
			//assegno posizioni effettive
			p.setFila(postoLibero.y);
			p.setColonna(postoLibero.x);
			colonnaScelta = coord.XRel(postoLibero.x);
			rigaScelta = coord.YRel(postoLibero.y);
			
			//calcolo sbilanciamento effettivo rispetto all'ottimo
			mom[0] += p.getPeso() * colonnaScelta; //sbilanciamento colonna
			mom[1] += p.getPeso() * rigaScelta; //sbilanciamento riga
			log.debug("Questo passeggero: di "+p.getPeso()+"kg va in [x:"+p.getColonna()+" y:"+p.getFila()+"] e lascia un mom totale del gruppo di [momX:"+mom[0]+" momY: "+mom[1]+"]");
		}
		log.debug("***************mom dopo gruppo [x:"+mom[0]+" y:"+mom[1]+"]");
		
		return mom;
	}

}
