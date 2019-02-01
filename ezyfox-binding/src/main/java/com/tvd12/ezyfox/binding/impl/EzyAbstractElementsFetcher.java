package com.tvd12.ezyfox.binding.impl;

import static com.tvd12.ezyfox.binding.EzyAccessType.DECLARED_FIELDS;
import static com.tvd12.ezyfox.binding.EzyAccessType.DECLARED_METHODS;
import static com.tvd12.ezyfox.binding.EzyAccessType.FIELDS;
import static com.tvd12.ezyfox.binding.EzyAccessType.METHODS;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tvd12.ezyfox.binding.annotation.EzyIgnore;
import com.tvd12.ezyfox.io.EzyLists;
import com.tvd12.ezyfox.io.EzyMaps;
import com.tvd12.ezyfox.reflect.EzyByFieldMethod;
import com.tvd12.ezyfox.reflect.EzyClass;
import com.tvd12.ezyfox.reflect.EzyField;
import com.tvd12.ezyfox.reflect.EzyMethod;
import com.tvd12.ezyfox.util.EzyLoggable;

/**
 * 
 * @author tavandung12
 *
 */

public abstract class EzyAbstractElementsFetcher 
		extends EzyLoggable 
		implements EzyElementsFetcher {

	protected List<? extends EzyMethod> methodList;
	protected Map<String, ? extends EzyMethod> methodsByFieldName;
	
	@Override
	public final List<Object> getElements(EzyClass clazz, int accessType) {
		logger.debug("start scan {}", clazz);
		init(clazz, accessType);
		List<Object> elements = doGetElements(clazz, accessType);
		logger.debug("finish scan {}", clazz);
		return elements;
	}
	
	protected final void init(EzyClass clazz, int accessType) {
		this.methodList = getMethodList(clazz);
		this.methodsByFieldName = mapMethodsByFieldName(methodList);
	}
	
	protected abstract List<Object> doGetElements(EzyClass clazz, int accessType);
	
	protected final List<EzyField> getFields(EzyClass clazz, int accessType) {
		List<EzyField> fields = new ArrayList<>();
		if((accessType & FIELDS) > 0) {
			fields.addAll(clazz.getFields());
		}
		else if((accessType & DECLARED_FIELDS) > 0) {
			fields.addAll(clazz.getDeclaredFields());
		}
		return EzyLists.filter(fields, f -> !f.isAnnotated(EzyIgnore.class));
	}
	
	protected final List<? extends EzyMethod> getMethods(EzyClass clazz, int accessType) {
		List<EzyMethod> methods = new ArrayList<>();
		if((accessType & METHODS) > 0) {
			methods.addAll(methodList);
		}
		else if((accessType & DECLARED_METHODS) > 0) {
			methods.addAll(getDeclaredMethods(clazz));
		}
		return EzyLists.filter(methods, m -> !isIgnoredMethod(m, clazz));
	}
	
	protected boolean isValidGenericField(EzyField field) {
		return true;
	}
	protected boolean isValidGenericMethod(EzyMethod method) {
		return true;
	}
	
	protected abstract List<? extends EzyMethod> getMethodList(EzyClass clazz);
	protected abstract List<? extends EzyMethod> getDeclaredMethods(EzyClass clazz);
	
	private final Map<String, ? extends EzyMethod> 
			mapMethodsByFieldName(List<? extends EzyMethod> methods) {
		return EzyMaps.newHashMap(methods, m -> ((EzyByFieldMethod)m).getFieldName());
	}
	
	private boolean isIgnoredMethod(EzyMethod method, EzyClass clazz) {
		boolean ignored = method.isAnnotated(EzyIgnore.class);
		if(ignored) return true;
		String fieldName = ((EzyByFieldMethod)method).getFieldName();
		EzyField field = clazz.getField(fieldName);
		return field == null ? false : field.isAnnotated(EzyIgnore.class);
	}
}