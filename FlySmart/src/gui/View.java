/*
 * 
 */
package gui;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// TODO: Auto-generated Javadoc
/**
 * The Class View.
 *
 * @author Demarinis - Micheli - Scarpellini
 * The Class View. Superclasse di tutte le viste, ognuna delle quali estende JFrame
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
			e.printStackTrace();
		}
	}
}
