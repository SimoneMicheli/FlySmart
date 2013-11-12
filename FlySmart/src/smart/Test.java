package smart;

public class Test {

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
		
		/*Prova passeggeri*/
		SmartCheckin provaPasseggeri = new SmartCheckin(null, null, null);
		
		int[] resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,3);
		
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		System.out.println("Posto: "+resultPasseggeri[1]+" "+resultPasseggeri[0]);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		System.out.println("Posto: "+resultPasseggeri[1]+" "+resultPasseggeri[0]);
		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(2,5);
		System.out.println("Posto: "+resultPasseggeri[1]+" "+resultPasseggeri[0]);

		// 		continuiamo da qua!!! 
		//		resultPasseggeri = provaPasseggeri.postoLiberoPasseggeri(,);
		//      System.out.println("Posto: "+resultPasseggeri[1]+" "+resultPasseggeri[0]);
		
		/*Prova pallet
		SmartCheckin provaPallet = new SmartCheckin(null, null, null);
		int[] resultPallet = provaPallet.postoLiberoPallet(0,2);
		resultPallet = provaPallet.postoLiberoPallet(1,1);
		resultPallet = provaPallet.postoLiberoPallet(1,0);
		resultPallet = provaPallet.postoLiberoPallet(0,3);
		resultPallet = provaPallet.postoLiberoPallet(1,2);
		resultPallet = provaPallet.postoLiberoPallet(0,1);
		resultPallet = provaPallet.postoLiberoPallet(0,0);
		resultPallet = provaPallet.postoLiberoPallet(1,3);
		System.out.println("Posto: "+resultPallet[1]+" "+resultPallet[0]);
		 */
	}

}
