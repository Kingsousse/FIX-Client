package org.capfifix.client.ihm;

import org.capfifix.client.entities.Order;

public interface Observer {

	public void updateLog(String str);

	public void addOrder(Order order, String server);

	public void updateOrder(Order order, String server);

	public void deleteOrder(String idOrder, String server);

	public void disconnectFromServer(String server);

	public void connectToServer(String server);

	public void alertDialog(String str);

}
