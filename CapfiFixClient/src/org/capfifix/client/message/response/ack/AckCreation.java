package org.capfifix.client.message.response.ack;

import org.capfifix.client.enumurate.DictionaryTag;
import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.Message;

public class AckCreation extends AcknowledgmentMessage {

	private String clientOrderId;

	public AckCreation() {

	}

	public AckCreation(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	@Override
	public Message deserialize(String[] items) throws InvalidMessageException {
		if (items[1].split("=").length == 2
				&& Integer.valueOf(items[1].split("=")[0]) == DictionaryTag.CLIENTORDERID.getValue()) {
			this.setClientOrderId((String) items[1].split("=")[1]);
		} else {
			throw new InvalidMessageException("Message non valide");
		}
		
		return this;
	}

	@Override
	public MsgProtocoleType getProtocoleType() {
		return MsgProtocoleType.NEW_ORDER_ACK;
	}

}
