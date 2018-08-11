package com.tvd12.ezyfox.binding;

public interface EzyMarshaller {

	<T> T marshal(Object object);
	
	@SuppressWarnings("rawtypes")
	<T> T marshal(Class<? extends EzyWriter> writerClass, Object object);
	
}
