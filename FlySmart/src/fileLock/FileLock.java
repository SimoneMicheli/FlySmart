/**
 * 
 */
package fileLock;

/**Interfaccia che definisce i metodi da usare per i lock sui file.
 * implementa il paradigma dei lettori scrittori con priorità agli scrittori
 * @author Demarinis - Micheli - Scarpellini
 *
 */
public interface FileLock {
	/**
	 * acquisisce il lock in lettura
	 */
	public abstract void acquireReadLock();
	/**
	 * acquisisce il lock in scrittura
	 */
	public abstract void acquireWriteLock();
	/**
	 * rilascia il lock in lettura
	 */
	public abstract void releaseReadLock();
	/**
	 * rilascia il lock in scrittura
	 */
	public abstract void releaseWriteLock();
}
