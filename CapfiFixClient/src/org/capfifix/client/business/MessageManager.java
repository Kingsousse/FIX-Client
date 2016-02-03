package org.capfifix.client.business;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.capfifix.client.communication.Pipeline;
import org.capfifix.client.communication.Stub;
import org.capfifix.client.entities.Order;
import org.capfifix.client.entities.ServerCapFIX;
import org.capfifix.client.enumurate.ExecutionStatus;
import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.enumurate.MsgType;
import org.capfifix.client.enumurate.OrderSide;
import org.capfifix.client.ihm.AbstractModel;
import org.capfifix.client.message.AbstractFactoryMessage;
import org.capfifix.client.message.Message;
import org.capfifix.client.message.MessageProducer;
import org.capfifix.client.message.response.NotificationMessage;
import org.capfifix.client.message.response.ReplyRejectionCancellingOrder;
import org.capfifix.client.message.response.ReplyRejectionConnection;
import org.capfifix.client.message.response.ReplyRejectionCreationOrder;
import org.capfifix.client.message.response.ReplyRejectionDeconnection;
import org.capfifix.client.message.response.ack.AckCancellation;
import org.capfifix.client.message.response.ack.AckConnection;
import org.capfifix.client.message.response.ack.AckCreation;
import org.capfifix.client.message.response.ack.AckDeconnection;

public class MessageManager {

	private Stub stub;

	private ServerCapFIX server;

	private Pipeline queueMessageToSend;

	private Pipeline queueMessageToRead;

	private List<Order> orders;

	private Map<String, Order> ordersDisired;

	private Map<String, Order> ordersDisiredToDelete;

	private AbstractFactoryMessage requestFactory;

	private MsgJob job;
	
	private AbstractModel model;

	public MessageManager(ServerCapFIX server, String separator, AbstractModel model) {
		this.server = server;
		this.model = model;

		this.queueMessageToRead = new Pipeline();
		this.queueMessageToSend = new Pipeline();

		this.ordersDisired = new HashMap<String, Order>();
		this.ordersDisiredToDelete = new HashMap<String, Order>();

		this.orders = new ArrayList<Order>();

		this.requestFactory = MessageProducer.getFactory(MsgType.REQUEST, separator);

		this.stub = new Stub(server, queueMessageToSend, queueMessageToRead);

		this.job = new MsgJob(this, this.queueMessageToRead);

		this.job.start();
	}

	public void exec(Message msg) {

		switch (msg.getProtocoleType()) {
		case LOGON_ACK:
			this.execAckConnection((AckConnection) msg);
			break;

		case LOGON_REJECT:
			this.execReplyRejectionConnection((ReplyRejectionConnection) msg);
			break;

		case LOGOUT_ACK:
			this.execAckDeconnection((AckDeconnection) msg);
			break;

		case LOGOUT_REJECT:
			this.execReplyRejectionDeconnection((ReplyRejectionDeconnection) msg);
			break;
		case NEW_ORDER_ACK:
			this.execAckCreation((AckCreation) msg);
			break;

		case NEW_ORDER_REJECT:
			this.execReplyRejectionCreationOrder((ReplyRejectionCreationOrder) msg);
			break;

		case CANCEL_ORDER_ACK:
			this.execAckCancellation((AckCancellation) msg);
			break;

		case CANCEL_ORDER_REJECT:
			this.execReplyRejectionCancellingOrder((ReplyRejectionCancellingOrder) msg);
			break;

		case EXECUTION_REPORT:
			this.execNotificationMessage((NotificationMessage) msg);
			break;

		default:
			break;
		}

	}

	// Ack Connection management
	private void execAckConnection(AckConnection msg) {

		this.model.notifyConnectionAccepted(this.server.getName());
	}

	private void execReplyRejectionConnection(ReplyRejectionConnection msg) {
		this.model.notifyConnectionRefused(server.getName());
	}

	private void execAckCancellation(AckCancellation msg) {
		this.ordersDisiredToDelete.remove(msg.getClientOrderId());
		this.model.notifyOrderCancellingAccepted(this.server.getName(), msg.getClientOrderId());

	}

	private void execAckDeconnection(AckDeconnection msg) {
		this.stub.closeConnection();
		this.model.notifyDisconnectedAccepted(this.server.getName());
	}

	private void execAckCreation(AckCreation msg) {
		Order order = this.ordersDisired.get(msg.getClientOrderId());
		this.orders.add(order);
		this.ordersDisired.remove(msg.getClientOrderId());	
		
		this.model.notifyOrderAccepted(this.server.getName(), order);

	}

	private void execNotificationMessage(NotificationMessage msg) {
		Order order = null;
		for (int i = 0; i < this.orders.size(); i++) {

			if (this.orders.get(i).getClientOrderId().equals(msg.getClientOrderId())) {
				order = this.orders.get(i);
				
				if (msg.getExecutionStatus() == ExecutionStatus.COMPLETE) {
					this.orders.remove(i);
					this.model.notifyOrderExecuted(this.server.getName(), order);
				} else {
					this.orders.get(i).setQuantity(this.orders.get(i).getQuantity() - msg.getExecutionQuantity());
					this.model.notifyOrderExecutedPartial(this.server.getName(), order);
				}
				break;
			}

		}

	}

	private void execReplyRejectionCancellingOrder(ReplyRejectionCancellingOrder msg) {
		this.orders.add(this.ordersDisiredToDelete.get(msg.getClientOrderId()));
		this.ordersDisiredToDelete.remove(msg.getClientOrderId());
		this.model.notifyOrderCancellingRefused(this.server.getName(), msg.getClientOrderId());
	}

	private void execReplyRejectionCreationOrder(ReplyRejectionCreationOrder msg) {
		this.ordersDisired.remove(msg.getClientOrderId());
		this.model.notifyOrderRefused(this.server.getName(), msg.getClientOrderId());
	}

	private void execReplyRejectionDeconnection(ReplyRejectionDeconnection msg) {

		this.model.notifyDisconnectedRefused(server.getName());
	}

	/////////////////////////
	// requests management //
	/////////////////////////

	public void execLogonRequest(String login) {
		List<Object> params = new ArrayList<Object>();
		params.add(login);
		Message message = requestFactory.getRequestMessage(MsgProtocoleType.LOGON, params);
		this.queueMessageToSend.push(message);
	}

	public void execLogoutRequest() {
		Message message = requestFactory.getRequestMessage(MsgProtocoleType.LOGON, null);
		this.queueMessageToSend.push(message);
	}

	public void execCreationOrder(String id, OrderSide side, String Security, int quantity, double price) {
		List<Object> params = new ArrayList<Object>();
		params.add(id);
		params.add(side);
		params.add(Security);
		params.add(quantity);
		params.add(price);
		Message message = requestFactory.getRequestMessage(MsgProtocoleType.NEW_ORDER, params);
		this.queueMessageToSend.push(message);
		this.ordersDisired.put(id, new Order(id, side, Security, quantity, price));
	}

	public void execCancellation(String id) {
		Order order = null;

		for (int i = 0; i < this.orders.size(); i++) {

			if (this.orders.get(i).getClientOrderId() == id) {
				order = this.orders.get(i);
				this.orders.remove(i);
				break;
			}

		}

		if (order != null) {
			List<Object> params = new ArrayList<Object>();
			params.add(id);

			Message message = requestFactory.getRequestMessage(MsgProtocoleType.CANCEL_ORDER, params);
			this.queueMessageToSend.push(message);
			this.ordersDisiredToDelete.put(id, order);
		}

	}

}
