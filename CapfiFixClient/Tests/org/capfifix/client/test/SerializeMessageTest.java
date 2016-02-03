package org.capfifix.client.test;

import java.util.ArrayList;
import java.util.List;

import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.enumurate.MsgType;
import org.capfifix.client.enumurate.OrderSide;
import org.capfifix.client.message.AbstractFactoryMessage;
import org.capfifix.client.message.Message;
import org.capfifix.client.message.MessageProducer;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class SerializeMessageTest {
	
	static AbstractFactoryMessage requestFactory;
	
	@BeforeClass
	public static void  init(){
		requestFactory = MessageProducer.getFactory(MsgType.REQUEST, "|");
	}

	@Test
	public void should_be_return_true_logon_message() {

		List<Object> params = new ArrayList<Object>();
		params.add("toto");
		Message message = requestFactory.getRequestMessage(MsgProtocoleType.LOGON, params);
		String serialized = message.serialize();
		
		Assert.assertTrue("0=Logon|2=toto|".equals(serialized));

	}
	
	@Test
	public void should_be_return_true_logout_message() {

		AbstractFactoryMessage requestFactory = MessageProducer.getFactory(MsgType.REQUEST, "|");
	
		Message message = requestFactory.getRequestMessage(MsgProtocoleType.LOGOUT, null);
		String serialized = message.serialize();
		
		Assert.assertTrue("0=Logout|".equals(serialized));

	}
	
	
	@Test
	public void should_be_return_true_on_create_order_message() {

		AbstractFactoryMessage requestFactory = MessageProducer.getFactory(MsgType.REQUEST, "|");
		List<Object> params = new ArrayList<Object>();
		params.add("OR159");
		params.add(OrderSide.BUY);
		params.add("Pomme");
		params.add(15);
		params.add(13.20);
		Message message = requestFactory.getRequestMessage(MsgProtocoleType.NEW_ORDER, params);
		String serialized = message.serialize();
		
		Assert.assertTrue("0=NewOrder|3=OR159|4=B|5=Pomme|7=15|6=13.2|".equals(serialized));

	}

	
	@Test
	public void should_be_return_true_on_cancel_order_message() {

		AbstractFactoryMessage requestFactory = MessageProducer.getFactory(MsgType.REQUEST, "|");
		List<Object> params = new ArrayList<Object>();
		params.add("OR159");
		Message message = requestFactory.getRequestMessage(MsgProtocoleType.CANCEL_ORDER, params);
		String serialized = message.serialize();
		
		Assert.assertTrue("0=CancelOrder|3=OR159|".equals(serialized));

	}
}
