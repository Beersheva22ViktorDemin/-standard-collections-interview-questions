package telran.util;

public class Connection {
	int id;
	String ipAddress;
	int port;

	public Connection(int id, String ipAddress, int port) {
		this.id = id;
		this.ipAddress = ipAddress;
		this.port = port;
	}

	public int getId() {
		return id;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public int getPort() {
		return port;
	}
}