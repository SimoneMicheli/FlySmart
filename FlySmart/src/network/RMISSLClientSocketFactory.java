package network;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;

/**
 * restituisce il socket da utilizzare per la comunicazione su SSL per il client
 */
public class RMISSLClientSocketFactory implements RMIClientSocketFactory, Serializable {

	private static final long serialVersionUID = 5373789233449860363L;

	/**
	 * Method createSocket.
	 * @param host String
	 * @param port int
	 * @return Socket
	 * @throws IOException
	 * @see java.rmi.server.RMIClientSocketFactory#createSocket(String, int)
	 */
	@Override
	public Socket createSocket(String host, int port) throws IOException {
		SocketFactory factory = SSLSocketFactory.getDefault();
		return factory.createSocket(host, port);
	}

}
