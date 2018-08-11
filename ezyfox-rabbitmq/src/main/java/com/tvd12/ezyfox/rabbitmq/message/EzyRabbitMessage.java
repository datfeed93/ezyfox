package com.tvd12.ezyfox.rabbitmq.message;

import com.rabbitmq.client.AMQP.BasicProperties;

public interface EzyRabbitMessage {

	Object getBody();
	
	String getExchange();
	
	boolean isMandatory();
	
	boolean isImmediate();
	
	String getRoutingKey();
	
	BasicProperties getProperties();
	
}
