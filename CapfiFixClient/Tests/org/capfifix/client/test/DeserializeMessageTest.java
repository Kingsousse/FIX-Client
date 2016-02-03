package org.capfifix.client.test;

import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.Message;
import org.capfifix.client.message.response.NotificationMessage;
import org.capfifix.client.message.response.ReplyRejectionCancellingOrder;
import org.capfifix.client.message.response.ReplyRejectionConnection;
import org.capfifix.client.message.response.ReplyRejectionCreationOrder;
import org.capfifix.client.message.response.ReplyRejectionDeconnection;
import org.capfifix.client.message.response.ack.AckCancellation;
import org.capfifix.client.message.response.ack.AckConnection;
import org.capfifix.client.message.response.ack.AckCreation;
import org.capfifix.client.message.response.ack.AckDeconnection;
import org.junit.Assert;
import org.junit.Test;

public class DeserializeMessageTest {

	// Logon
	@Test
	public void should_return_true_on_deserialize_LogonAck() throws InvalidMessageException {

		String message = "0=LogonAck";

		Message msgObj = Message.valueOf(message);
		Assert.assertTrue(msgObj.getProtocoleType() == MsgProtocoleType.LOGON_ACK);
		Assert.assertTrue(msgObj != null);
		Assert.assertTrue(msgObj instanceof AckConnection);
	}

	@Test(expected = InvalidMessageException.class)
	public void should_return_Exception_on_deserialize_LogonAck_with_wrong_message() throws InvalidMessageException {

		String message = "0=LogonAcksdfsdf";

		Message msgObj = Message.valueOf(message);

		Assert.assertTrue(msgObj.getProtocoleType() == MsgProtocoleType.LOGON_ACK);
	}

	@Test
	public void should_return_true_on_deserialize_Logon_Rejection() throws InvalidMessageException {

		String message = "0=LogonReject|1=redandance";

		Message msgObj = Message.valueOf(message);

		Assert.assertTrue(msgObj != null);
		Assert.assertTrue(msgObj instanceof ReplyRejectionConnection);
		Assert.assertTrue(msgObj.getProtocoleType() == MsgProtocoleType.LOGON_REJECT);
		Assert.assertTrue(((ReplyRejectionConnection) msgObj).getReason().equals("redandance"));
	}

	// logout

	@Test
	public void should_return_true_on_deserialize_LogoutAck() throws InvalidMessageException {

		String message = "0=LogoutAck";

		Message msgObj = Message.valueOf(message);
		Assert.assertTrue(msgObj != null);
		Assert.assertTrue(msgObj instanceof AckDeconnection);
		Assert.assertTrue(msgObj.getProtocoleType() == MsgProtocoleType.LOGOUT_ACK);

	}

	@Test
	public void should_return_true_on_deserialize_Logout_Rejection() throws InvalidMessageException {

		String message = "0=LogoutReject|1=dejadeconnecte";

		Message msgObj = Message.valueOf(message);

		Assert.assertTrue(msgObj != null);
		Assert.assertTrue(msgObj instanceof ReplyRejectionDeconnection);
		Assert.assertTrue(msgObj.getProtocoleType() == MsgProtocoleType.LOGOUT_REJECT);
		Assert.assertTrue(((ReplyRejectionDeconnection) msgObj).getReason().equals("dejadeconnecte"));

	}

	// Creation Order

	@Test
	public void should_return_true_on_new_order() throws InvalidMessageException {

		String message = "0=NewOrderAck|3=12";

		Message msgObj = Message.valueOf(message);

		Assert.assertTrue(msgObj != null);
		Assert.assertTrue(msgObj instanceof AckCreation);
		Assert.assertTrue(msgObj.getProtocoleType() == MsgProtocoleType.NEW_ORDER_ACK);
		Assert.assertTrue(((AckCreation) msgObj).getClientOrderId().equals("12"));
	}

	@Test
	public void should_return_true_on_new_order_reject() throws InvalidMessageException {

		String message = "0=NewOrderReject|3=12|1=OrderAlreadyExists";

		Message msgObj = Message.valueOf(message);

		Assert.assertTrue(msgObj != null);
		Assert.assertTrue(msgObj instanceof ReplyRejectionCreationOrder);
		Assert.assertTrue(msgObj.getProtocoleType() == MsgProtocoleType.NEW_ORDER_REJECT);
		Assert.assertTrue(((ReplyRejectionCreationOrder) msgObj).getClientOrderId().equals("12"));
		Assert.assertTrue(((ReplyRejectionCreationOrder) msgObj).getReason().equals("OrderAlreadyExists"));

	}

	// Cancellation Order
	@Test
	public void should_return_true_cancelling_order() throws InvalidMessageException {

		String message = "0=CancelOrderAck|3=OR12";

		Message msgObj = Message.valueOf(message);

		Assert.assertTrue(msgObj != null);
		Assert.assertTrue(msgObj instanceof AckCancellation);
		Assert.assertTrue(msgObj.getProtocoleType() == MsgProtocoleType.CANCEL_ORDER_ACK);
		Assert.assertTrue(((AckCancellation) msgObj).getClientOrderId().equals("OR12"));

	}

	@Test
	public void should_return_true_cancelling_order_rejected() throws InvalidMessageException {


		String message = "0=CancelOrderReject|3=OR12|1=OrderNotExists";
		
		
		
		Message msgObj = Message.valueOf(message);

		Assert.assertTrue(msgObj != null);
		Assert.assertTrue(msgObj instanceof ReplyRejectionCancellingOrder);
		Assert.assertTrue(msgObj.getProtocoleType() == MsgProtocoleType.CANCEL_ORDER_REJECT);
		Assert.assertTrue(((ReplyRejectionCancellingOrder) msgObj).getClientOrderId().equals("OR12"));
		Assert.assertTrue(((ReplyRejectionCancellingOrder) msgObj).getReason().equals("OrderNotExists"));
		
	

	}
	// Notification

	// Cancellation Order
	@Test
	public void should_return_true__notification_execution() throws InvalidMessageException {


		String message = "0=ExecutionReport|3=OR12|8=P|10=15|9=12.20";

		
		Message msgObj = Message.valueOf(message);

		Assert.assertTrue(msgObj != null);
		Assert.assertTrue(msgObj instanceof NotificationMessage);
		Assert.assertTrue(msgObj.getProtocoleType() == MsgProtocoleType.EXECUTION_REPORT);
		Assert.assertTrue(((NotificationMessage) msgObj).getClientOrderId().equals("OR12"));
		Assert.assertTrue(((NotificationMessage) msgObj).getExecutionStatus().getValue().equals("P"));
		Assert.assertTrue(((NotificationMessage) msgObj).getExecutionQuantity() == 15);
		Assert.assertTrue(((NotificationMessage) msgObj).getExecutionPrice() == 12.2);
	

	}

}
