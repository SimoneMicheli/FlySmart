package network;

import java.rmi.RemoteException;

public class ReaderTest implements Runnable {

	ServerInterface serv;
	int num;
	
	public ReaderTest(ServerInterface s, int num){
		serv = s;
		this.num = num;
		
	}
	@Override
	public void run() {
		while(true){
			try {
				serv.getString();
				Thread.sleep(300);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {}
		}

	}

}
