package com.tvd12.ezyfox.bean;

import java.util.List;
import java.util.Map;

@SuppressWarnings("rawtypes")
public interface EzyPrototypeFactory {
	
	EzyPrototypeSupplier getSupplier(Class objectType);

	EzyPrototypeSupplier getSupplier(String objectName, Class objectType);
	
	EzyPrototypeSupplier getSupplier(Map properties);
	
	List<EzyPrototypeSupplier> getSuppliers(Map properties);
	
	List<EzyPrototypeSupplier> getSuppliers(Class annoClass);
	
	Map getProperties(EzyPrototypeSupplier supplier);
	
	void addSupplier(EzyPrototypeSupplier supplier);
	
	void addSupplier(String objectName, EzyPrototypeSupplier supplier);
	
	void addSupplier(String objectName, EzyPrototypeSupplier supplier, Map properties);
	
}
