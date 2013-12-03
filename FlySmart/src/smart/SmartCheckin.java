package smart;


import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.bson.types.ObjectId;
import prenotazione.FlightNotFoundException;
import util.Coordinata;
import util.CoordinataPallet;
import util.CoordinataPasseggero;
import db.DBSession;
import db.Lock;
import model.Gruppo;
import model.Pallet;
import model.Passeggero;
import model.StatoVolo;
import model.Volo;


/**
 *
 */
public class SmartCheckin implements SmartAlgorithm{

	/**
	 * volo su cui calcolare i dati
	 */
	private Volo v;
	
	/**
	 * puntatore all'oggetto che gestisce i posti liberi
	 */
	private PostiLiberi posti;

	/**
	 * al momento della creazione dell'oggeto viene verificato che il volo esiste
	 * e se è presente viene posto in stato di chiuso
	 * @param idVolo su cui calcolare le posizioni
	 * @throws FlightNotFoundException
	 */
	public SmartCheckin(ObjectId idVolo) throws FlightNotFoundException{

		try{
			Lock.getInstance().acquireLock(idVolo);

			v = DBSession.getVoloDAO().get(idVolo);

			//volo non trovato
			if(v == null)
				throw new FlightNotFoundException(idVolo);

			//chiudo il volo
			v.setStato(StatoVolo.CLOSED);
			

			DBSession.getVoloDAO().save(v);
			
			posti = new PostiLiberi(v);
		}finally{
			Lock.getInstance().releaseLock(idVolo);
		}

	}


	@Override
	public void calcolaCheckin() {
		//ottengo lista passeggeri e pallet
		List<Passeggero> passeggeri = DBSession.getPasseggeroDAO().getByIdVolo(v.getId()).order("-peso").asList();
		List<Pallet> pallets = DBSession.getPalletDAO().getByIdVolo(v.getId()).order("-peso").asList();
		

		for(Pallet p : pallets)
			System.out.println(p);

		//calcola disposizione
		double mom[] = new double[2];
		mom = posizionaPallet(pallets);
		
		
		//fino a qui va
		
		System.out.println("ordinamento passeggeri---------------------");
		
		mom = posizionaPasseggeri(passeggeri, mom);

		System.out.println("FINE");

		//salva dati aggiornati nel db
		DBSession.getPasseggeroDAO().saveList(passeggeri);
		DBSession.getPalletDAO().saveList(pallets);

	}

	/**
	 * calcola dove posizionare i pallet sul volo
	 * mediante algoritmo greedy
	 * @param lista lista di pallet da posizionare
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
		
		System.out.println("Primo pallet");
		//ottengo primo pallet
		Pallet p = i.next();

		//posizione pallet in pos default
		p.setFila(coord.YAbs(0.5));
		p.setColonna(0);

		mom[0] = p.getPeso() * -0.5;
		mom[1] = p.getPeso() * +0.5;
		
		System.out.println("momX: "+mom[0]);
		System.out.println("momY: "+mom[1]);

		//posizono il primo pallet
		int[] pos = posti.postoLiberoPallet(-0.5,0.5);
		System.out.println("Questo peso: di "+p.getPeso()+"kg va in [x:"+p.getColonna()+" y:"+p.getFila()+"] produce [momX:"+mom[0]+" momY: "+mom[1]+"]");
		
		//calcolo posizione per i pallet successivi
		int passo=1;
		while(i.hasNext()){
			posti.stampaOccupancyPallet(passo);
			passo++;
			p = i.next();

			System.out.println("Prossimo peso di "+p.getPeso());
			//posizione ideale per annullare il momento
			double dist[] = new double[2];
			dist[0] = -mom[0] / p.getPeso();
			dist[1] = -mom[1] / p.getPeso();
			System.out.println("Calcolato: x:"+dist[0] +" y:"+dist[1]);

			pos = posti.postoLiberoPallet(dist[0],dist[1]); //ritorno in modo XY quindi prima posizione è la colonna

			p.setColonna(pos[0]); 
			p.setFila(pos[1]);

			//sbilanciamento effettivo
			System.out.println("Calcolo lo sbilanciamento effettivo, nuovo");
			mom[0] = mom[0] + p.getPeso() * coord.XRel(pos[0]); //sbilanciamento sulle x è la colonna
			mom[1] = mom[1] + p.getPeso() * coord.YRel(pos[1]); //sbilanciamento sulle y è la fila
			System.out.println("Questo peso: di "+p.getPeso()+"kg va in [x:"+p.getColonna()+" y:"+p.getFila()+"] e lascia un mom di [momX:"+mom[0]+" momY: "+mom[1]+"]");
		
		}
		posti.stampaOccupancyPallet(passo);
		return mom;
	} 

	protected double[] posizionaPasseggeri(List<Passeggero> lista, double mom[]){
		//lista vuota
		if(lista == null || lista.size() == 0)
			return mom;
			
		//genero elenco gruppi ordinati per peso
		Gruppo[] gruppi = SmartGroupOrdering.sortGroup(lista);
		
		System.out.println(Arrays.deepToString(gruppi));
		
		Gruppo g = null;
		
		//moltiplico momento
		mom[0] = mom[0] * 3;
		mom[1] = mom[1] * 3;
		
		double[] pos = new double[2];
		
		for(int i = 0; i < gruppi.length ; i++){
			g = gruppi[i];

			System.out.println("Prossimo gruppo che pesa "+ g.getPesoTotale());
			//calcolo posizione ideale gruppo
			pos[0] = -mom[0] / g.getPesoTotale();
			pos[1] = -mom[1] / g.getPesoTotale();
			System.out.println("Calcolato: x:"+pos[0] +" y:"+pos[1]);
			
			//posiziono il gruppo e mo faccio dare il momento rispetto a quello ideale
			double momg[] = posizionaGruppo(pos[0], pos[1], g);
			
			//aggiorno momento per prossima iterazione
			mom[0] += momg[0];
			mom[1] += momg[1];
			System.out.println("######################## Questo gruppo di "+g.getPesoTotale()+"kg lascia un mom di [momX:"+mom[0]+" momY: "+mom[1]+"]");
			
			
		}
		
		return mom;
	}
	
	protected double[] posizionaGruppo(double colonnaScelta, double rigaScelta, Gruppo g){
		Coordinata coord = new CoordinataPasseggero(v.getTipoAereo());
		
		double[] mom = new double[2];
		
		//solo per test
		int i=0;
		
		
		for(Passeggero p : g){
			
			
			//test
			i++;
			
			
			//cerco posizione effettiva
			int[] pos = posti.postoLiberoPasseggeri(colonnaScelta, rigaScelta);
			//assegno posizioni effettive
			p.setFila(pos[1]);
			p.setColonna(pos[0]);
			colonnaScelta = coord.XRel(pos[0]);
			rigaScelta = coord.YRel(pos[1]);
			
			//calcolo sbilanciamento effettivo rispetto all'ottimo
			System.out.println("Calcolo lo sbilanciamento effettivo, nuovo per passeggero "+i);
			
			mom[0] += p.getPeso() * coord.XRel(pos[0]); //sbilanciamento colonna
			mom[1] += p.getPeso() * coord.YRel(pos[1]); //sbilanciamento riga
			System.out.println("Questo passeggero: di "+p.getPeso()+"kg va in [x:"+p.getColonna()+" y:"+p.getFila()+"] e lascia un mom totale del gruppo di [momX:"+mom[0]+" momY: "+mom[1]+"]");
			
		}
		System.out.println("***************mom dopo gruppo [x:"+mom[0]+" y:"+mom[1]+"]");
		
		return mom;
	}

}
