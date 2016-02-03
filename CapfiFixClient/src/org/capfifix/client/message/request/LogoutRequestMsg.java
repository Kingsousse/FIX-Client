package org.capfifix.client.message.request;

import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.Message;

public class LogoutRequestMsg extends RequestMessage {

	
	
	public LogoutRequestMsg() {

	}

	public  String serialize()
	{
		return super.serialize()+"Logout"+this.separator;
	}
	
	public LogoutRequestMsg deserialize(String message)
	{
		return null;
	}

	@Override
	public Message deserialize(String[] items) throws InvalidMessageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MsgProtocoleType getProtocoleType() {
		return MsgProtocoleType.LOGOUT;
	}

}
