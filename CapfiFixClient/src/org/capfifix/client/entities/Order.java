/**
 * 
 */
package org.capfifix.client.entities;

import org.capfifix.client.enumurate.OrderSide;

/**
 * @author capfitech
 *
 */
public class Order {

	private String clientOrderId;

	private OrderSide orderSide;

	private String securityCode;

	private int quantity;

	private double price;

	public Order(String clientOrderId, OrderSide orderSide, String securityCode, int quantity, double price) {
		super();
		this.clientOrderId = clientOrderId;
		this.orderSide = orderSide;
		this.securityCode = securityCode;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Order() {
		
	}


	public  String getClientOrderId() {
		return clientOrderId;
	}

	public  void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public  OrderSide getOrderSide() {
		return orderSide;
	}

	public  void setOrderSide(OrderSide orderSide) {
		this.orderSide = orderSide;
	}

	public  String getSecurityCode() {
		return securityCode;
	}

	public  void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public  int getQuantity() {
		return quantity;
	}

	public  void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public  double getPrice() {
		return price;
	}

	public  void setPrice(int price) {
		this.price = price;
	}

	
}
