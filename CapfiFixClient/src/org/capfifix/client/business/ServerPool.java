package org.capfifix.client.business;

import java.util.ArrayList;
import java.util.List;

public class ServerPool {
	
	List<MessageManager> messageManagers;
	
	ServerPool(MessageManager messageManager)
	{
		messageManagers = new ArrayList<MessageManager>();
		messageManagers.add(messageManager);
	}
	
	ServerPool(List<MessageManager> messageManagers)
	{
		this.messageManagers = messageManagers;
	}

}
