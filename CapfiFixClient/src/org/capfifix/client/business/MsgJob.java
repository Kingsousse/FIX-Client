/**
 * 
 */
package org.capfifix.client.business;

import org.capfifix.client.communication.Pipeline;
import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.message.Message;

/**
 * @author capfitech
 *
 */
public class MsgJob extends Thread {

	private boolean stop = false;

	private MessageManager manager;

	private Pipeline pipeline;

	public MsgJob(MessageManager manager, Pipeline pipeline) {
		this.manager = manager;
		this.pipeline = pipeline;
	}

	@Override
	public void run() {

		while (!stop) {

			try {
				Message msgObj = pipeline.pop();
				if (msgObj != null) {

					this.manager.exec(msgObj);

				}

			} catch (InterruptedException e) {

				e.printStackTrace();
			}

		}

	}

}
