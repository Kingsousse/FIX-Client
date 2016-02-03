package org.capfifix.client.ihm;

import org.capfifix.client.entities.Order;

public interface Observable {
	
	public void addObserver(Observer obs);

	public void removeObserver();

	public void notifyLog(String str);
	
	public void notifyAddServer(String str);
	
	public void notifyAlert(String str);
	
	public void notifyToRemoveServer(String str);
	
	public void notifyAddOrder(Order order, String server);
	
	public void notifyUpdateOrder(Order order, String server);
	
	public void notifyDeleteOrder(String order, String server);
}
