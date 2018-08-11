package com.tvd12.ezyfox.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

import com.tvd12.ezyfox.reflect.EzyByFieldMethod;
import com.tvd12.ezyfox.reflect.EzyMethod;

public class EzySetterMethod extends EzyByFieldMethod {

	public EzySetterMethod(Method method) {
		this(new EzyMethod(method));
	}
	
	public EzySetterMethod(EzyMethod method) {
		super(method.getMethod());
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Class getType() {
		return getParameterTypes()[0];
	}
	
	@Override
	public Type getGenericType() {
		return getGenericParameterTypes()[0];
	}
	
	@Override
	public String getFieldName() {
		String name = method.getName();
		if(name.length() <= 3)
			return name;
		if(name.startsWith("set"))
			return super.getFieldName();
		return name;
	}
	
}
