package org.capfifix.client.communication;

import org.capfifix.client.entities.ServerCapFIX;

public class Stub {

	private NetworkConnector network;

	private MsgSender sender;

	private MsgReceiver receiver;
	
	private Pipeline queueMessageToSend;
	
	private Pipeline queueMessageToRead;

	public Stub(ServerCapFIX server, Pipeline queueMessageToSend, Pipeline queueMessageToRead) {
		this.network = new NetworkConnector(server.getIpAdress(), server.getPort(), server.getName());
		
		this.queueMessageToRead = queueMessageToRead;
		this.queueMessageToSend = queueMessageToSend;

		this.network.connectSocket();
		
		this.sender = new MsgSender(this.network, queueMessageToSend);
		this.receiver = new MsgReceiver(this.network, queueMessageToRead);
		
		this.sender.start();
		this.receiver.start();
	}

	public MsgSender getSender() {
		return sender;
	}

	public MsgReceiver getReceiver() {
		return receiver;
	}
	
	public void closeConnection()
	{
		this.sender.setStop(true);
		this.receiver.setStop(true);
		
		this.queueMessageToRead.clean();
		this.queueMessageToSend.clean();
		
		try {
			this.sender.join();
			this.receiver.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.network.disconnectSocket();
	}

}
