package org.capfifix.client.communication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.Message;

public class MsgReceiver extends Thread {

	private NetworkConnector network;

	private boolean stop = false;
	
	private Pipeline pipeline;


	public MsgReceiver(NetworkConnector network, Pipeline pipeline) {

		this.network = network;
		
		this.pipeline = pipeline;
	}

	@Override
	public void run() {

		while (!stop) {

			String message = this.recvMsg();
			Message msgObj=null;
			try {
				msgObj = Message.valueOf(message);
				System.out.println(message);
				this.pipeline.push(msgObj);
			} catch (InvalidMessageException e) {
				e.printStackTrace();
			}
			

		}
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	
	
	public String recvMsg() {

		String message = null;
		try {
			InputStreamReader ir = new InputStreamReader(this.network.getSocket().getInputStream());
			BufferedReader br = new BufferedReader(ir);
			message = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return message;
	}

}
