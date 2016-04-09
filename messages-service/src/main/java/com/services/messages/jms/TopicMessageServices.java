package com.services.messages.jms;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Topic;

public class TopicMessageServices implements MessageType {
	
	@Resource(mappedName=MessagesServicesConstants.TOPIC_JNDI_NAME)
	private ConnectionFactory connFactory;
	@Resource(mappedName=MessagesServicesConstants.TOPIC_DESTINATION)
	private Topic destination;
	
	public ConnectionFactory getFactory() {
		return connFactory;
	}
	public Destination getDestination() {
		return destination;
	}

	
}
