package network;
import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.server.RMIServerSocketFactory;

import javax.net.ServerSocketFactory;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * restituisce il socket da utilizzare per la comunicazione su SSL per il server
 */
public class RMISSLServerSocketFactory implements RMIServerSocketFactory {

	/**
	 * Method createServerSocket.
	 * @param port int
	 * @return ServerSocket
	 * @throws IOException
	 * @see java.rmi.server.RMIServerSocketFactory#createServerSocket(int)
	 */
	@Override
	public ServerSocket createServerSocket(int port) throws IOException {
		// TODO Auto-generated method stub
		ServerSocketFactory factory = SSLServerSocketFactory.getDefault();
		return factory.createServerSocket(port);
	}

}
