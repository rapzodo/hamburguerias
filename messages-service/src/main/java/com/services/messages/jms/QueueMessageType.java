package com.services.messages.jms;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Queue;

public class QueueMessageType implements MessageType{
	
	@Resource(mappedName=MessagesServicesConstants.QUEUE_JNDI_NAME)
	private ConnectionFactory connFactory;
	@Resource(mappedName=MessagesServicesConstants.QUEUE_DESTINATION)
	private Queue destination;
	
	public ConnectionFactory getFactory() {
		return connFactory;
	}
	public Destination getDestination() {
		return destination;
	}

	
}
