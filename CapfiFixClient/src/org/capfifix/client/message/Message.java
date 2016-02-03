package org.capfifix.client.message;

import org.capfifix.client.enumurate.DictionaryTag;
import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.response.NotificationMessage;
import org.capfifix.client.message.response.ReplyRejectionCancellingOrder;
import org.capfifix.client.message.response.ReplyRejectionConnection;
import org.capfifix.client.message.response.ReplyRejectionCreationOrder;
import org.capfifix.client.message.response.ReplyRejectionDeconnection;
import org.capfifix.client.message.response.ack.AckCancellation;
import org.capfifix.client.message.response.ack.AckConnection;
import org.capfifix.client.message.response.ack.AckCreation;
import org.capfifix.client.message.response.ack.AckDeconnection;

public abstract class Message {
	
	
	
	
	public abstract MsgProtocoleType getProtocoleType();

	public abstract  Message deserialize(String[] items) throws InvalidMessageException;

	protected String separator;

	public String getSeparator() {
		return separator;
	}

	public void setSeparator(String separator) {
		this.separator = separator;
	}

	public Message() {

	}

	public Message(String separator) {

		this.separator = separator;
	}

	public String serialize() {
		return DictionaryTag.MSGTYPE.getValue() + "=";

	}
	
	public static  Message valueOf(String message) throws InvalidMessageException {

		String[] items = message.split("\\|");

		String typeProtocoleMessage = items[0].split("=")[1];

		if (typeProtocoleMessage.equals(MsgProtocoleType.LOGON_ACK.getValue())) {
			AckConnection messageObj = new AckConnection();
			messageObj = (AckConnection) messageObj.deserialize(items);
			return messageObj;
		}

		if (typeProtocoleMessage.equals(MsgProtocoleType.LOGON_REJECT.getValue())) {
			ReplyRejectionConnection messageObj = new ReplyRejectionConnection();
			messageObj = (ReplyRejectionConnection) messageObj.deserialize(items);
			return messageObj;
		}

		if (typeProtocoleMessage.equals(MsgProtocoleType.LOGOUT_ACK.getValue())) {
			AckDeconnection  messageObj = new AckDeconnection();
			messageObj = (AckDeconnection) messageObj.deserialize(items);
			return messageObj;
		}

		if (typeProtocoleMessage.equals(MsgProtocoleType.LOGOUT_REJECT.getValue())) {
			ReplyRejectionDeconnection  messageObj = new ReplyRejectionDeconnection();
			messageObj = (ReplyRejectionDeconnection) messageObj.deserialize(items);
			return messageObj;
		}

		if (typeProtocoleMessage.equals(MsgProtocoleType.NEW_ORDER_ACK.getValue())) {
			AckCreation  messageObj = new AckCreation();
		
			messageObj = (AckCreation) messageObj.deserialize(items);
			return messageObj;
		}

		if (typeProtocoleMessage.equals(MsgProtocoleType.NEW_ORDER_REJECT.getValue())) {
			ReplyRejectionCreationOrder  messageObj = new ReplyRejectionCreationOrder();
			messageObj = (ReplyRejectionCreationOrder) messageObj.deserialize(items);
			return messageObj;
		}

		if (typeProtocoleMessage.equals(MsgProtocoleType.CANCEL_ORDER_ACK.getValue())) {
			AckCancellation  messageObj = new AckCancellation();
			
			
			messageObj = (AckCancellation) messageObj.deserialize(items);
			return messageObj;
		}

		if (typeProtocoleMessage.equals(MsgProtocoleType.CANCEL_ORDER_REJECT.getValue())) {
			ReplyRejectionCancellingOrder  messageObj = new ReplyRejectionCancellingOrder();
			messageObj = (ReplyRejectionCancellingOrder) messageObj.deserialize(items);
			return messageObj;
		}

		if (typeProtocoleMessage.equals(MsgProtocoleType.EXECUTION_REPORT.getValue())) {
			NotificationMessage  messageObj = new NotificationMessage();
			messageObj = (NotificationMessage) messageObj.deserialize(items);
			return messageObj;
		}

		throw new InvalidMessageException("Message non supporté");

	}
	

}
