package com.tvd12.ezyfox.rabbitmq;

import java.util.Map;

@Deprecated
public interface EzyRabbitServerConfig {

	String getQueue();
	
	boolean isAutoAck();
	
	boolean isNoLocal();
	
	boolean isExclusive();
	
	String getConsumerTag();
	
	Map<String, Object> getArguments();
	
}
