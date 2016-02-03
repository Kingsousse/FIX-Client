package org.capfifix.client.message.response;

import org.capfifix.client.enumurate.DictionaryTag;
import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.Message;

public class ReplyRejectionDeconnection extends RejectionMessage {

	@Override
	public Message deserialize(String[] items) throws InvalidMessageException {
		if (items[1].split("=").length == 2
				&& Integer.valueOf(items[1].split("=")[0]) == DictionaryTag.REASON.getValue()) {
			this.setReason((String) items[1].split("=")[1]);
		} else {
			throw new InvalidMessageException("Message non valide");
		}
		return this;

	}

	@Override
	public MsgProtocoleType getProtocoleType() {
		return MsgProtocoleType.LOGOUT_REJECT;
	}

}
