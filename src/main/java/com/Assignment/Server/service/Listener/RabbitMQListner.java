package com.Assignment.Server.service.Listener;

import com.Assignment.Server.service.Aggregator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListner implements MessageListener {
	final Logger LOGGER = LoggerFactory.getLogger(getClass());

	private Aggregator aggregator;

	@Autowired
	public RabbitMQListner(Aggregator aggregator) {
		this.aggregator = aggregator;
	}

	public void onMessage(Message message) {
		String messageFromClient = new String(message.getBody());
		aggregator.aggregator(messageFromClient);
	}
}