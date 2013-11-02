package smart;

public class postiPallet {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] mat = {{1,2},{3,4},{10,10},{6,10},{7,10},{8,50},{1,2},{3,4},{10,10},{6,10},{7,10},{8,0}};
		for(int i=0; i<12; i=i+1) {
			System.out.println("");
			for(int j=0; j<2; j=j+1) {
				System.out.print((int)mat[i][j] + " ");
			}
		}
		int[] result = postoLibero(mat,2,12,0,0);
		System.out.println("Scelto: "+result[0]+" "+result[1]);


	}

	public static int[] postoLibero(int[][] occupancy,int maxColonne, int maxRighe, int colonnaScelta, int rigaScelta){
		System.out.println("\n");
		System.out.println("Provo in "+rigaScelta+" "+colonnaScelta+"");
		if(occupancy[rigaScelta][colonnaScelta]==0){
			int[] posto = {rigaScelta, colonnaScelta};
			return posto;
		}
		System.out.println("Provo a cambiare colonna "+(1-colonnaScelta)+" "+rigaScelta+"");
		if(occupancy[rigaScelta][1-colonnaScelta]==0){
			int[] posto = {rigaScelta, 1-colonnaScelta};
			return posto;
		}
		int distanzaMassima = maxRighe-rigaScelta-1;
		if(rigaScelta>distanzaMassima) distanzaMassima=rigaScelta;
		System.out.println("Distanza massima: "+distanzaMassima);
		for(int i=1;i<=distanzaMassima;i++){
			try{
				System.out.println("Salgo di "+i+" "+(colonnaScelta)+" "+(rigaScelta+i)+"");
				if(occupancy[rigaScelta+i][colonnaScelta]==0){
					int[] posto = {rigaScelta+i, colonnaScelta};
					return posto;
				}
			}catch(Exception e){
			}

			try{
				System.out.println("Scendo di "+i+"");
				if(occupancy[rigaScelta-1][colonnaScelta]==0){
					int[] posto = {rigaScelta-1, colonnaScelta};
					return posto;
				}
			}catch(Exception e){
			}
			try{
				System.out.println("Salgo di "+i+" e cambio colonna");
				if(occupancy[rigaScelta-1][1-colonnaScelta]==0){
					int[] posto = {rigaScelta-1, 1-colonnaScelta};
					return posto;
				}
			}catch(Exception e){
			}
			try{
				System.out.println("Scendo di "+i+" e cambio colonna");
				if(occupancy[rigaScelta+i][1-colonnaScelta]==0){
					int[] posto = {rigaScelta+i, 1-colonnaScelta};
					return posto;
				}
			}catch(Exception e){
			}
		}
		int[] posto = {-1, -1};
		return posto;

	}

}


