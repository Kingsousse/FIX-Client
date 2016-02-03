package org.capfifix.client.message.response;

import org.capfifix.client.enumurate.DictionaryTag;
import org.capfifix.client.enumurate.ExecutionStatus;
import org.capfifix.client.enumurate.MsgProtocoleType;
import org.capfifix.client.exception.InvalidMessageException;
import org.capfifix.client.message.Message;

public class NotificationMessage extends ReplyMessage {

	private String clientOrderId;

	private ExecutionStatus executionStatus;

	private int executionQuantity;

	private double executionPrice;
	public NotificationMessage() {

	}

	public NotificationMessage(String clientOrderId, ExecutionStatus executionStatus, int executionQuantity,
			double executionPrice) {
		super();
		this.clientOrderId = clientOrderId;
		this.executionStatus = executionStatus;
		this.executionQuantity = executionQuantity;
		this.executionPrice = executionPrice;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public ExecutionStatus getExecutionStatus() {
		return executionStatus;
	}

	public void setExecutionStatus(ExecutionStatus executionStatus) {
		this.executionStatus = executionStatus;
	}

	public int getExecutionQuantity() {
		return executionQuantity;
	}

	public void setExecutionQuantity(int executionQuantity) {
		this.executionQuantity = executionQuantity;
	}

	public double getExecutionPrice() {
		return executionPrice;
	}

	public void setExecutionPrice(double executionPrice) {
		this.executionPrice = executionPrice;
	}

	@Override
	public  Message deserialize(String[] items) throws InvalidMessageException {
		
		
		
		if(items[1].split("=").length == 2 && Integer.valueOf(items[1].split("=")[0]) == DictionaryTag.CLIENTORDERID.getValue()  ) {
			this.setClientOrderId((String)items[1].split("=")[1]);
		} else {
			throw new InvalidMessageException("Message non valide");
		}

		if(items[2].split("=").length == 2 && Integer.valueOf(items[2].split("=")[0]) == DictionaryTag.EXCECSTATUS.getValue()  ) {
			if (items[2].split("=")[1].equals(ExecutionStatus.PORTIAL.getValue())){
				this.setExecutionStatus(ExecutionStatus.PORTIAL);
			} else if(items[2].split("=")[1].equals(ExecutionStatus.COMPLETE.getValue())){
				this.setExecutionStatus(ExecutionStatus.COMPLETE);
			}else {
				throw new InvalidMessageException("Message non valide");
			}
			
		}else{
			throw new InvalidMessageException("Message non valide");
		}

		if(items[3].split("=").length == 2 && Integer.valueOf(items[3].split("=")[0]) == DictionaryTag.EXECQUANTITY.getValue()  ) {
			this.setExecutionQuantity(Integer.valueOf(items[3].split("=")[1]));
		} else {
			throw new InvalidMessageException("Message non valide");
		}
		
		if(items[4].split("=").length == 2 && Integer.valueOf(items[4].split("=")[0]) == DictionaryTag.EXECPRICE.getValue()  ) {
			this.setExecutionPrice(Double.valueOf(items[4].split("=")[1]));
		} else {
			throw new InvalidMessageException("Message non valide");
		}
		
		return this;
	}

	@Override
	public MsgProtocoleType getProtocoleType() {
		return MsgProtocoleType.EXECUTION_REPORT;
	}
	
	
	
}
