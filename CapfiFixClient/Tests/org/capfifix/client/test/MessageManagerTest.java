package org.capfifix.client.test;

import org.capfifix.client.business.MessageManager;
import org.capfifix.client.entities.ServerCapFIX;
import org.junit.BeforeClass;
import org.junit.Test;

/*
 * NB: Avant de commencer ce est Lancer netcat sur 127.0.0.1 sur le port 45000
 * 
 * "192.168.1.158"
 */
public class MessageManagerTest {

	public static ServerCapFIX server;

	public static MessageManager manager;

	@BeforeClass
	public static void init() {

		server = new ServerCapFIX("server1", "192.168.1.158", 45000);
		//server = new ServerCapFIX("server1", "127.0.0.1", 45000);
		manager = new MessageManager(server, "|");

	}

	@Test
	public void should_return_true_on_logon() {
		
		manager.execLogonRequest("client1");
		try {
			Thread.sleep(1000000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
