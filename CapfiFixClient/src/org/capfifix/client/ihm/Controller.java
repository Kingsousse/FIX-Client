package org.capfifix.client.ihm;

import org.capfifix.client.entities.Order;
import org.capfifix.client.entities.ServerCapFIX;
import org.capfifix.client.enumurate.OrderSide;

public class Controller {

	private AbstractModel model;
	// Autre item

	public Controller(AbstractModel model) {
		this.model = model;
	}

	public void connectToserver(ServerCapFIX server, String login) {
		//
		this.model.connectToServer(server, login);
	}

	public void disconnectToServer(String server) {
		this.model.disconnectToServer(server);
	}

	public void addOrder(String clientOrderId, OrderSide orderSide, String securityCode, int quantity, String price,
			String server) {

		this.model.sendOrder(server,
				new Order(clientOrderId, orderSide, securityCode, quantity, Double.valueOf(price)));

	}

	public void cancelOrder(String orderId, String server) {

		this.model.cancelOrder(server, orderId);
	}

}
