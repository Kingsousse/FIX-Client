package org.capfifix.client.message.request;

import org.capfifix.client.enumurate.DictionaryTag;
import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.Message;

public class LogonRequestMsg extends RequestMessage {
	
	private String clientName;

	public LogonRequestMsg(String clientName) {
		this.clientName = clientName;
	}

	public LogonRequestMsg() {
	}
	
	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	
	public  String serialize()
	{
		return super.serialize()+"Logon"+this.separator+DictionaryTag.CLIENTNAME.getValue()+"="+this.clientName+this.separator;
	}
	
	public LogonRequestMsg deserialize(String message)
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
		return MsgProtocoleType.LOGON;
	}



}
