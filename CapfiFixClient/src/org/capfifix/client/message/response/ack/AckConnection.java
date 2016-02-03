package org.capfifix.client.message.response.ack;

import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.Message;

public class AckConnection extends AcknowledgmentMessage {

	@Override
	public  Message deserialize(String[] items) throws InvalidMessageException {

		return this;
	}

	@Override
	public MsgProtocoleType getProtocoleType() {
		return MsgProtocoleType.LOGON_ACK;
	}

}
