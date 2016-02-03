package org.capfifix.client.message.request;

import org.capfifix.client.enumurate.DictionaryTag;
import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.Message;

public class CancellationRequestMsg extends RequestMessage {

	private String clientOrderId;

	public CancellationRequestMsg(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public CancellationRequestMsg() {
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public String serialize() {
		return super.serialize() + "CancelOrder" + this.separator + DictionaryTag.CLIENTORDERID.getValue() + "="
				+ this.clientOrderId + this.separator;
	}

	public CancellationRequestMsg deserialize(String message) {
		return null;
	}

	@Override
	public Message deserialize(String[] items) throws InvalidMessageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MsgProtocoleType getProtocoleType() {
		return MsgProtocoleType.CANCEL_ORDER;
	}
}
