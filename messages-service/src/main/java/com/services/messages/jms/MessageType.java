package com.services.messages.jms;

import javax.jms.ConnectionFactory;
import javax.jms.Destination;

public interface MessageType {

	public ConnectionFactory getFactory();
	public Destination getDestination();
}
