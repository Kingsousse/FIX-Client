/**
 * 
 */
package org.capfifix.client.entities;

/**
 * @author capfitech
 *
 */
public class ServerCapFIX {

	private String name;
	private String ipAdress;
	private int port;


	public ServerCapFIX(String name, String ipAdress, int port) {

		this.name = name;
		this.ipAdress = ipAdress;
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAdress() {
		return ipAdress;
	}

	public void setIpAdress(String ipAdress) {
		this.ipAdress = ipAdress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}
	
	public String toString(){
		return this.name+ " ( "+ this.ipAdress +" : "+ this.port + " )";
	}
}
