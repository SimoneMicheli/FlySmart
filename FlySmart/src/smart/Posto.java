/**
 * 
 */
package smart;

/**
 * la classe rappresenta un posto libero sull'aereo non assegnato
 *
 */
public class Posto {

	int x, y;
	
	int distanza;
	
	Posto(int x, int y, int dist){
		this.x = x;
		this.y = y;
		distanza = dist;
	}
	
	Posto(int x, int y, int ottimoX, int ottimoY){
		this.x = x;
		this.y = y;
		
		distanza = 2*(Math.abs(x-ottimoX)+Math.abs(y-ottimoY));
		if(y == ottimoY && x != ottimoX)
			distanza--;
		
		//System.out.println(toString());
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the distanza
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
