package smart;

public class postiPasseggeri {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] mat = {{10,2,5,2,1,5},{10,54,5,2,1,5},{10,5,5,2,1,5},{10,52,5,2,16,5},{10,2,5,2,1,5},{10,2,5,2,1,5},};
		for(int i=0; i<mat.length; i=i+1) {
			System.out.println("");
			for(int j=0; j<mat[0].length; j=j+1) {
				System.out.print((int)mat[i][j] + " ");
			}
		}
		int[] result = postoLiberoPasseggeri(mat,0,0);
		System.out.println("Scelto: "+result[0]+" "+result[1]);

	}

	public static int[] postoLiberoPasseggeri(int[][] occupancy, int colonnaScelta, int rigaScelta){
		int maxRighe =occupancy.length;
		System.out.println("\n");
		int distanzaMassima = maxRighe-rigaScelta-1;
		if(rigaScelta>distanzaMassima) distanzaMassima=rigaScelta;
		System.out.println("Distanza massima: "+distanzaMassima);


		for(int i=0;i<=distanzaMassima;i++){

			for(int vicino=0;vicino<6;vicino++){
				int colonnaCalcolata = colonnaScelta;
				try{
					if(occupancy[rigaScelta][colonnaCalcolata]==0){
						int[] posto = {rigaScelta, colonnaScelta};
						return posto;
					}
				}catch(ArrayIndexOutOfBoundsException e){
					System.out.println("Cella di matrice invalida");
				}
				colonnaCalcolata=6; //formulazza
			}
		}

		int[] posto = {-1, -1};
		return posto;

	}
}

