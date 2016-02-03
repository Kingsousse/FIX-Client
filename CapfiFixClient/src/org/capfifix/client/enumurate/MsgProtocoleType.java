package org.capfifix.client.enumurate;

public enum MsgProtocoleType {

	LOGON("Logon"), LOGON_ACK("LogonAck"), LOGON_REJECT("LogonReject"), LOGOUT("Logout"), LOGOUT_ACK(
			"LogoutAck"), LOGOUT_REJECT("LogoutReject"), NEW_ORDER("NewOrder"), NEW_ORDER_ACK(
					"NewOrderAck"), NEW_ORDER_REJECT("NewOrderReject"), CANCEL_ORDER("CancelOrder"), CANCEL_ORDER_ACK(
							"CancelOrderAck"), CANCEL_ORDER_REJECT("CancelOrderReject"), EXECUTION_REPORT(
									"ExecutionReport");

	private final String value;

	private MsgProtocoleType(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
