package org.capfifix.client.ihm;

import org.capfifix.client.business.MessageManager;
import org.capfifix.client.entities.Order;
import org.capfifix.client.entities.ServerCapFIX;

public class Model extends AbstractModel {

	@Override
	public void connectToServer(ServerCapFIX server, String login) {
		MessageManager manager = new MessageManager(server, "|", this);
		this.managers.put(server.getName(), manager);
		manager.execLogonRequest(login);
		this.notifyLog("Attempt to connect server (" + server.getName() + ")");

	}

	@Override
	public void disconnectToServer(String server) {

		this.managers.get(server).execLogoutRequest();
		this.notifyLog("Attempt to disconnect server (" + server + ")");

	}


	@Override
	public void notifyConnectionAccepted(String server) {
		this.notifyLog("Connection to server (" + server + ") granted");
		this.notifyAddServer(server);
	}

	@Override
	public void notifyConnectionRefused(String server) {
		this.notifyLog("Connection to server (" + server + ") refused");
		this.notifyAlert("Connection to server (" + server + ") refused");
	}

	@Override
	public void notifyDisconnectedAccepted(String server) {

		this.notifyToRemoveServer(server);
		this.managers.remove(server);
		this.notifyLog("Disonnection to server (" + server + ") granted");

	}

	@Override
	public void notifyDisconnectedRefused(String server) {

		this.notifyLog("Disconnection to server (" + server + ") refused");
		this.notifyAlert("Disconnection to server (" + server + ") refused");
	}

	
	
	@Override
	public void sendOrder(String server, Order order) {
		this.managers.get(server).execCreationOrder(order.getClientOrderId(), order.getOrderSide(), order.getSecurityCode(), order.getQuantity(), order.getPrice());
		this.notifyLog("Order ("+order.getClientOrderId()+") send to server (" + server + ")");
	}


	@Override
	public void notifyOrderAccepted(String server, Order order) {
		
		this.notifyAddOrder(order, server);
		this.notifyLog("Order ("+order.getClientOrderId()+") send to server (" + server + ") Accepted");
	}

	@Override
	public void notifyOrderRefused(String server, String orderId) {

		this.notifyLog("Order ("+orderId+") send to server (" + server + ") Refused");
		this.notifyAlert("Order ("+orderId+") send to server (" + server + ") Refused");

	}
	
	
	

	@Override
	public void cancelOrder(String server, String orderId) {
		this.managers.get(server).execCancellation(orderId);
		this.notifyLog("Delete Order ("+orderId+") from server (" + server + ")");

	}

	@Override
	public void notifyOrderCancellingAccepted(String server, String orderId) {
		this.notifyDeleteOrder(orderId, server);
		this.notifyLog("Delete Order ("+orderId+") from server (" + server + ") accepted");
	}

	@Override
	public void notifyOrderCancellingRefused(String server, String orderId) {
		this.notifyLog("Delete Order ("+orderId+") from server (" + server + ") Refused");
		this.notifyAlert("Delete Order ("+orderId+") from server (" + server + ") Refused");

	}

	@Override
	public void notifyOrderExecuted(String server, Order order) {
		
		this.notifyDeleteOrder(order.getClientOrderId(), server);
		this.notifyLog("Order ("+order.getClientOrderId()+") From server (" + server + ") is executed completely");

	}

	@Override
	public void notifyOrderExecutedPartial(String server, Order order) {
		this.notifyUpdateOrder(order, server);
		this.notifyLog("Order ("+order.getClientOrderId()+") From server (" + server + ") is executed partially");
		
	}



}
