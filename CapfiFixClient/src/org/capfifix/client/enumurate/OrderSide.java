package org.capfifix.client.enumurate;

public enum OrderSide {

	BUY("B"), SELL("S");
	
	private final String value;

	private OrderSide(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

}
