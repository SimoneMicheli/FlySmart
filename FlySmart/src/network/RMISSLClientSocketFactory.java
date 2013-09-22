package network;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.rmi.server.RMIClientSocketFactory;

import javax.net.SocketFactory;
import javax.net.ssl.SSLSocketFactory;


public class RMISSLClientSocketFactory implements RMIClientSocketFactory, Serializable {

	@Override
	public Socket createSocket(String host, int port) throws IOException {
		SocketFactory factory = SSLSocketFactory.getDefault();
		return factory.createSocket(host, port);
	}

}
