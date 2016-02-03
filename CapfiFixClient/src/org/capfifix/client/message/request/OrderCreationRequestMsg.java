package org.capfifix.client.message.request;

import org.capfifix.client.enumurate.DictionaryTag;
import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.enumurate.OrderSide;
import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.Message;

public class OrderCreationRequestMsg extends RequestMessage {

	private String clientOrderId;

	

	private OrderSide orderSide;

	private String securityCode;

	private int quantity;

	private double price;
	
	public OrderCreationRequestMsg(String clientOrderId, OrderSide orderSide, String securityCode, int quantity,
			double price) {
		super();
		this.clientOrderId = clientOrderId;
		this.orderSide = orderSide;
		this.securityCode = securityCode;
		this.quantity = quantity;
		this.price = price;
	}

	public OrderCreationRequestMsg() {
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public OrderSide getOrderSide() {
		return orderSide;
	}

	public void setOrderSide(OrderSide orderSide) {
		this.orderSide = orderSide;
	}

	public String getSecurityCode() {
		return securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String serialize() {
		return super.serialize() + "NewOrder" + this.separator + DictionaryTag.CLIENTORDERID.getValue() + "="
				+ this.clientOrderId + this.separator + DictionaryTag.SIDE.getValue() + "=" + this.orderSide.getValue()
				+ this.separator + DictionaryTag.SECURITY.getValue()+ "=" + this.securityCode + this.separator
				+ DictionaryTag.QUANTITY.getValue() + "=" + this.quantity + this.separator
				+ DictionaryTag.PRICE.getValue() + "=" + this.price + this.separator;
	}

	public OrderCreationRequestMsg deserialize(String message) {
		return null;
	}

	@Override
	public Message deserialize(String[] items) throws InvalidMessageException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MsgProtocoleType getProtocoleType() {
		return MsgProtocoleType.NEW_ORDER;
	}

}
