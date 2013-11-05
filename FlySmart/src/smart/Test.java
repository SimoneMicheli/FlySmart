package smart;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		/*Prova passeggeri
		SmartCheckin provaPasseggeri = new SmartCheckin(null, null, null);
		int[] resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(1,0);
		provaPasseggeri.stampaOccupancyPasseggeri(1);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(0,0);
		provaPasseggeri.stampaOccupancyPasseggeri(2);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(0,0);
		provaPasseggeri.stampaOccupancyPasseggeri(3);*/
		
		
		/*Prova pallet*/
		SmartCheckin provaPallet = new SmartCheckin(null, null, null);
		int[] resultPallet = provaPallet.postoLiberoPallet(0,4);
		provaPallet.stampaOccupancyPallet(1);
		resultPallet = provaPallet.postoLiberoPallet(1,3);
		provaPallet.stampaOccupancyPallet(2);
		resultPallet = provaPallet.postoLiberoPallet(1,3);
		provaPallet.stampaOccupancyPallet(3);

	}

}
