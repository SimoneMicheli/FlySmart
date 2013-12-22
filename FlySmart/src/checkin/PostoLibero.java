/**
 * 
 */
package checkin;

/**
 * la classe rappresenta un posto libero sull'aereo non assegnato
 *
 */
public class PostoLibero {

	/**
	 * variabili x,y che indicano la pposizione del posto sull'aereo
	 */
	int x, y;
	
	/**
	 * distanza dal posto dal posto ottimo cercato
	 */
	int distanza;
	
	/**
	 * the constructor
	 * @param x colonna del posto
	 * @param y fila del posto
	 * @param dist distanza dal posto ottimo
	 */
	PostoLibero(int x, int y, int dist){
		this.x = x;
		this.y = y;
		distanza = dist;
	}
	
	/**
	 * il costruttore dato il posto e il posto ottimo calcola automaticamente la distanza tra i due
	 * @param x colonna del posto
	 * @param y fila del posto
	 * @param ottimoX colonna del posto ottimo
	 * @param ottimoY fila del posto ottimo
	 */
	PostoLibero(int x, int y, int ottimoX, int ottimoY){
		this.x = x;
		this.y = y;
		
		distanza = 2*(Math.abs(x-ottimoX)+Math.abs(y-ottimoY));
		if(y == ottimoY && x != ottimoX)
			distanza--;
	}

	/**
	 * @return colonna del posto (X)
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return fila del posto (Y)
	 */
	public int getY() {
		return y;
	}

	/**
	 * restituisce la distanza del posto dal posto ottimo
	 * 
	 * @return distanza
	 */
	public int getDistanza() {
		return distanza;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "x: "+x+" y: "+y+" dist: "+distanza;
	}
}
