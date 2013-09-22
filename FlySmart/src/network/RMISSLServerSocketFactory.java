package network;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.server.RMIServerSocketFactory;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;


public class RMISSLServerSocketFactory implements RMIServerSocketFactory {

	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		// TODO Auto-generated method stub
		ServerSocketFactory factory = SSLServerSocketFactory.getDefault();
		return factory.createServerSocket(port);
	}

}
