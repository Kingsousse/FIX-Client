package org.capfifix.client.enumurate;

public enum DictionaryTag {
	MSGTYPE(0), REASON(1), CLIENTNAME(2), CLIENTORDERID(3), SIDE(4), SECURITY(5), PRICE(6), QUANTITY(7), EXCECSTATUS(
			8), EXECPRICE(9), EXECQUANTITY(10);

	private final int value;

	private DictionaryTag(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

}
