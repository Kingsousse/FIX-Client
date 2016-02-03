package org.capfifix.client.communication;

import java.io.IOException;

import org.capfifix.client.message.Message;

/**
 * @author CapFiTech
 *
 */
public class MsgSender extends Thread {

	private boolean stop = false;

	private Pipeline pipeline;
	private NetworkConnector network;


	public MsgSender(NetworkConnector network, Pipeline pipeline) {
		this.network = network;
		this.pipeline = pipeline;

	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		while (!stop) {

			try {
				Message msgObj = pipeline.pop();
				if (msgObj != null) {
					String messageString = msgObj.serialize();
					this.sendMsg(messageString);
					System.out.println(messageString);
				}

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}
	
	
	
	private  void sendMsg(String frame) {
		if (!this.network.isConnected()) {
			this.network.connectSocket();
		}

		try {
			this.network.getSocket().getOutputStream().write(frame.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
