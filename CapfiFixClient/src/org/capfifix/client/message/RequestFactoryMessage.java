package org.capfifix.client.message;

import java.util.List;

import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.enumurate.OrderSide;
import org.capfifix.client.message.request.CancellationRequestMsg;
import org.capfifix.client.message.request.LogonRequestMsg;
import org.capfifix.client.message.request.LogoutRequestMsg;
import org.capfifix.client.message.request.OrderCreationRequestMsg;
import org.capfifix.client.message.request.RequestMessage;
import org.capfifix.client.message.response.ReplyMessage;

public class RequestFactoryMessage extends AbstractFactoryMessage {

	private String separator;

	public RequestFactoryMessage(String separator) {
		this.separator = separator;
	}

	@Override
	public RequestMessage getRequestMessage(MsgProtocoleType type, List<Object> params) {
		RequestMessage message = null;
		
		if (type == MsgProtocoleType.LOGON) {
			message = new LogonRequestMsg((String) params.get(0));
		}

		if (type == MsgProtocoleType.LOGOUT) {
			message = new LogoutRequestMsg();

		}

		if (type == MsgProtocoleType.NEW_ORDER) {

			message = new OrderCreationRequestMsg((String) params.get(0), (OrderSide) params.get(1),
					(String) params.get(2), (int) params.get(3), (double) params.get(4));

		}

		if (type == MsgProtocoleType.CANCEL_ORDER) {
			message = new CancellationRequestMsg((String) params.get(0));

		}
		message.setSeparator(separator);
		return message;
	}

	@Override
	public ReplyMessage getReplyMessage(MsgProtocoleType type, List<Object> params) {

		return null;
	}

}
