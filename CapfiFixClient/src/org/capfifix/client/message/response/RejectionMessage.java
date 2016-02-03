/**
 * 
 */
package org.capfifix.client.message.response;

/**
 * @author capfitech
 *
 */
public abstract class RejectionMessage extends ReplyMessage {

	protected String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	

}
