/**
 * 
 */
package fileLock;

/**
 * @author simone
 *
 */
public class FileLockImpl implements FileLock {
	int writers_wait = 0;
	int readers_wait = 0;
	int readers_counter = 0;
	boolean writing = false;

	/* (non-Javadoc)
	 * @see fileLock.FileLock#acquireReadLock()
	 */
	@Override
	public synchronized void acquireReadLock() {
		System.out.println(writers_wait+" "+writing+" "+readers_wait+" "+readers_counter);
		readers_wait++;
		//se ci sono scrittori in attesa o in fase di scrittura attendi
		while(writers_wait > 0 || writing == true){
			try {
				System.out.println("sospendo lettore");
				wait();
			} catch (InterruptedException e) {}
		}
		readers_wait--;
		readers_counter++;
		System.out.println("leggo: "+readers_counter);
	}

	/* (non-Javadoc)
	 * @see fileLock.FileLock#acquireWriteLock()
	 */
	@Override
	public synchronized void acquireWriteLock() {
		System.out.println(writers_wait+" "+writing+" "+readers_wait+" "+readers_counter);
		writers_wait++;
		//se ci sono lettori o scrittori attivi sospendi
		while(readers_counter > 0 || writing == true){
			try {
				System.out.println("sospendo scrittore");
				wait();
			} catch (InterruptedException e) {}
		}
		writers_wait--;
		writing = true;
		System.out.println("scrivo");
	}

	/* (non-Javadoc)
	 * @see fileLock.FileLock#releaseReadLock()
	 */
	@Override
	public synchronized void releaseReadLock() {
		//un lettore esce dalla sezione critica
		readers_counter--;
		notifyAll();
	}

	/* (non-Javadoc)
	 * @see fileLock.FileLock#releaseWriteLock()
	 */
	@Override
	public synchronized void releaseWriteLock() {
		//lo scrittore esce dalla sezione critica
		writing = false;
		notifyAll();
	}

}
