package org.capfifix.client.communication;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import org.capfifix.client.message.Message;

public class Pipeline {

	private Queue<Message> fifo = new LinkedList<Message>();

	private ReentrantLock mutex = new ReentrantLock();

	private Condition condition = mutex.newCondition();

	public void push(Message message) {
		mutex.lock();
		try {
			fifo.add(message);
			condition.signal();
		} finally {

			mutex.unlock();

		}

	}

	public Message pop() throws InterruptedException {
		Message message = null;
		mutex.lock();
		try {
			while (fifo.isEmpty()) {
				boolean timeout = !condition.await(1000, TimeUnit.MILLISECONDS);

				if (timeout) {
					break;
				}

			}
			if (!fifo.isEmpty()) {
				message = this.fifo.remove();
			}

		} finally {

			mutex.unlock();

		}

		return message;
	}
	
	public void clean()
	{
		while(!fifo.isEmpty())
		{
			fifo.remove();
		}
	}

}
