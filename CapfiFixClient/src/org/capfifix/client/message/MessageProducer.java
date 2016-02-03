package org.capfifix.client.message;

import org.capfifix.client.enumurate.MsgType;

public class MessageProducer {

	public static AbstractFactoryMessage getFactory(MsgType type, String separator) {

		if (type == MsgType.REQUEST) {
			return new RequestFactoryMessage(separator);
		}

		if (type == MsgType.REPLY) {
			return new RequestFactoryMessage(separator);
		}
		return null;

	}

}
