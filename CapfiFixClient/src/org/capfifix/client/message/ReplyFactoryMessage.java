package org.capfifix.client.message;

import java.util.List;

import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.message.request.RequestMessage;
import org.capfifix.client.message.response.ReplyMessage;

public class ReplyFactoryMessage extends AbstractFactoryMessage {

	@Override
	public RequestMessage getRequestMessage(MsgProtocoleType type, List<Object> params) {
		return null;
	}

	@Override
	public ReplyMessage getReplyMessage(MsgProtocoleType type, List<Object> params) {

		return null;
	}

}
