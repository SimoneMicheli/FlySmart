package xml;

import java.util.List;

import model.GetFields;

public interface XMLToObj<T extends GetFields> {

	/**
	 * read data from XML and return related objects
	 * @param path path to file
	 * @return objects list
	 */
	public abstract List<T> readObj(String path);

}