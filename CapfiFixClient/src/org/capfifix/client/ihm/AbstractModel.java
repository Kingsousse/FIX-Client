package org.capfifix.client.ihm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.capfifix.client.business.MessageManager;
import org.capfifix.client.entities.Order;
import org.capfifix.client.entities.ServerCapFIX;

public abstract class AbstractModel implements Observable {

	protected Map<String, MessageManager> managers = new HashMap<String, MessageManager>();

	private ArrayList<Observer> listObserver = new ArrayList<Observer>();

	public abstract void connectToServer(ServerCapFIX server, String login);

	public abstract void disconnectToServer(String server);

	public abstract void sendOrder(String server, Order order);

	public abstract void cancelOrder(String server, String orderId);

	public abstract void notifyConnectionAccepted(String server);

	public abstract void notifyConnectionRefused(String server);

	public abstract void notifyDisconnectedAccepted(String server);

	public abstract void notifyDisconnectedRefused(String server);

	public abstract void notifyOrderAccepted(String server, Order order);

	public abstract void notifyOrderRefused(String server, String orderId);

	public abstract void notifyOrderCancellingAccepted(String server, String orderId);

	public abstract void notifyOrderCancellingRefused(String server, String orderId);

	public abstract void notifyOrderExecuted(String server, Order order);
	
	public abstract void notifyOrderExecutedPartial(String server, Order order);

	// Implémentation du pattern observer
	public void addObserver(Observer obs) {
		this.listObserver.add(obs);
	}

	public void notifyLog(String str) {
		for (Observer obs : listObserver)
			obs.updateLog(str);
	}

	public void removeObserver() {
		listObserver = new ArrayList<Observer>();
	}

	public void notifyAddServer(String str) {
		for (Observer obs : listObserver)
			obs.connectToServer(str);

	}

	public void notifyAlert(String str) {
		for (Observer obs : listObserver)
			obs.alertDialog(str);
	}

	public void notifyToRemoveServer(String str) {
		for (Observer obs : listObserver)
			obs.disconnectFromServer(str);

	}

	public void notifyAddOrder(Order order, String server) {
		for (Observer obs : listObserver)
			obs.addOrder(order, server);
	}

	public void notifyUpdateOrder(Order order, String server) {
		for (Observer obs : listObserver)
			obs.updateOrder(order, server);

	}

	public void notifyDeleteOrder(String order, String server) {
		for (Observer obs : listObserver)
			obs.deleteOrder(order, server);

	}

}
