package smart;

public class postiPallet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] mat = {{10,2},{3,4},{10,10},{6,10},{7,10},{8,50},{1,2},{3,4},{10,10},{6,10},{7,10},{8,0}};
		for(int i=0; i<12; i=i+1) {
			System.out.println("");
			for(int j=0; j<2; j=j+1) {
				System.out.print((int)mat[i][j] + " ");
			}
		}
		int[] result = postoLibero(mat,0,0);
		System.out.println("Scelto: "+result[0]+" "+result[1]);

	}

	public static int[] postoLibero(int[][] occupancy, int colonnaScelta, int rigaScelta){
		int maxRighe =occupancy.length;
		System.out.println("\n");
		int distanzaMassima = maxRighe-rigaScelta-1;
		if(rigaScelta>distanzaMassima) distanzaMassima=rigaScelta;
		System.out.println("Distanza massima: "+distanzaMassima);
		for(int i=0;i<=distanzaMassima;i++){
			
			try{
				System.out.println("Salgo di "+i+" e cambio colonna");
				if(occupancy[rigaScelta-i][1-colonnaScelta]==0){
					int[] posto = {rigaScelta-i, 1-colonnaScelta};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Cella di matrice invalida");
			}
			
			try{
				System.out.println("Salgo di "+i);
				if(occupancy[rigaScelta+i][colonnaScelta]==0){
					int[] posto = {rigaScelta+i, colonnaScelta};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Cella di matrice invalida");
			}

			try{
				System.out.println("Scendo di "+i);
				if(occupancy[rigaScelta-i][colonnaScelta]==0){
					int[] posto = {rigaScelta-i, colonnaScelta};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Cella di matrice invalida");
			}
			
			try{
				System.out.println("Scendo di "+i+" e cambio colonna");
				if(occupancy[rigaScelta+i][1-colonnaScelta]==0){
					int[] posto = {rigaScelta+i, 1-colonnaScelta};
					return posto;
				}
			}catch(ArrayIndexOutOfBoundsException e){
				System.out.println("Cella di matrice invalida");
			}
		}
		int[] posto = {-1, -1};
		return posto;
	}

}


