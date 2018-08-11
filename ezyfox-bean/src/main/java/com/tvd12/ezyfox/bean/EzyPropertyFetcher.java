package com.tvd12.ezyfox.bean;

public interface EzyPropertyFetcher {

	boolean containsProperty(Object key);
	
	<T> T getProperty(Object key, Class<T> outType);
	
}
