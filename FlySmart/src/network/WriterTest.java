package network;

import java.rmi.RemoteException;

public class WriterTest implements Runnable {

	ServerInterface serv;
	int num;
	
	public WriterTest(ServerInterface s, int num){
		serv = s;
		this.num = num;
	}
	
	public void run() {
		
		while(true){
			try {
				serv.setString("scrittore: "+num);
				Thread.sleep(500);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {}
		}

	}

}
