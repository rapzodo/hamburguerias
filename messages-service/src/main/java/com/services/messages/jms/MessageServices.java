package com.services.messages.jms;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Session;

public class MessageServices {

	private MessageProducer producer;
	private Session session;
	private MessageType type;
	
	public MessageServices(MessageType type){
		this.type = type;
	}
	
	private Message createJMSMessage(Serializable message) throws JMSException{
		ObjectMessage objMessage = session.createObjectMessage();
		objMessage.setObject(message);
		return objMessage;
	}
	
	public void sendJMSMessage(Serializable message) throws JMSException{
		if(session == null){
			throw new JMSException(MessagesServicesConstants.NO_SESSION_CREATED);
		}
		producer = session.createProducer(type.getDestination());
		producer.send(createJMSMessage(message));
	}

	public void createJmsSession(Boolean transacted, int acknowledgeMode) throws JMSException {
		session = type.getFactory().createConnection().createSession(transacted, acknowledgeMode);
	}
}
