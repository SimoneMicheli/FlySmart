/*
 * 
 */
package guiPrenotazione;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**Superclasse di tutte le viste, ognuna delle quali estende JFrame
 *
 * @author Demarinis - Micheli - Scarpellini
 */
public abstract class View extends JFrame{


	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -638748601963627482L;

	/**
	 * Look and feel. Imposta il look and feel di Windows sostituendolo a quello di Java standard
	 */
	public void lookAndFeel(){
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			//e.printStackTrace();
			Logger log = LogManager.getLogger(View.class.getCanonicalName().toString());
			log.warn(e.getMessage());
		}
	}
}
