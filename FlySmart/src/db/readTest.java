package db;

import java.net.UnknownHostException;
import java.util.List;

import org.bson.types.ObjectId;

import sun.security.krb5.internal.PAData;

import model.Pallet;
import model.Passeggero;
import model.Volo;

import com.google.code.morphia.Datastore;

public class readTest {

	/**
	 * @param args
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException {
		// TODO Auto-generated method stub
		//creo e salvo alcuni pallet
		Datastore ds = DBSession.getInstance();
		
		List<Pallet> pl = ds.createQuery(Pallet.class).asList();
		
		for(Pallet p : pl)
			System.out.println(p);
		
		List<Passeggero> passl = ds.createQuery(Passeggero.class).asList();
		
		VoloDAO vDAO = DBSession.getVoloDAO();
		
		/*for(Passeggero p : passl)
			System.out.println(p);*/
		
		Volo vl = vDAO.getById("5283e2c80364fcb7ffc1cc93");
		
			
			//for(Passeggero p : passl)
				//vl.getPasseggeri().add(p);
			
			//vDAO.save(vl);
			System.out.println(vl);
			
			for(Passeggero p : vl.getPasseggeri())
				System.out.println(p);
		
	}

}
