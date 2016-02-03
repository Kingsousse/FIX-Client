package org.capfifix.client.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class NetworkConnector extends Thread {

	private String serverName;
	private Socket socket;
	
	private InetAddress address;
	private int port;

	private boolean isConnected;

	/**
	 * @return the isConnected
	 */
	public boolean isConnected() {
		return isConnected;
	}

	public NetworkConnector(String address, int port, String name) {
		this.port = port;
		this.serverName = name;
		try {
			this.address = InetAddress.getByName(address);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		this.socket = new Socket();
	}

	public void connectSocket() {
		try {
			this.socket.connect(new InetSocketAddress(address, port));
		} catch (IOException e) {
			e.printStackTrace();
		}
		isConnected = true;
		System.out.println("Open socket server :"+ this.serverName);
	}

	

	

	public void disconnectSocket() {
		System.out.println("Closing socket server :"+ this.serverName);
		try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		isConnected = false;
	}
	
	public Socket getSocket() {
		return socket;
	}

}
