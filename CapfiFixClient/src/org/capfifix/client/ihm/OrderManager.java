package org.capfifix.client.ihm;

import java.util.ArrayList;
import java.util.List;

import org.capfifix.client.entities.Order;

public class OrderManager {

	private List<Order> orders;

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public OrderManager() {
		this.orders = new ArrayList<Order>();
	}

}
