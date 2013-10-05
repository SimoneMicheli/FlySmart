/**
 * 
 */
package model;

/**
 * @author simone
 *
 */
public abstract class Model implements SortableList<Integer>{

	protected  int id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int compareTo(Integer o) {
		if (id < o)
			return -1;
		if (id == 0)
			return 0;
		return 1;
	}
}
