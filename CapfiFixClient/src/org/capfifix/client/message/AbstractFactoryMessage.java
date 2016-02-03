package org.capfifix.client.message;

import java.util.List;

import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.message.request.RequestMessage;
import org.capfifix.client.message.response.ReplyMessage;

public abstract class AbstractFactoryMessage {

	public abstract RequestMessage getRequestMessage(MsgProtocoleType type, List<Object> params);

	public abstract ReplyMessage getReplyMessage(MsgProtocoleType type, List<Object> params);

}
