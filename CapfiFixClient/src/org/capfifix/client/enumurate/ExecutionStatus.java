/**
 * 
 */
package org.capfifix.client.enumurate;

/**
 * @author capfitech
 *
 */
public enum ExecutionStatus {

	PORTIAL("P"), COMPLETE("F");

	private final String value;

	private ExecutionStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}
}
