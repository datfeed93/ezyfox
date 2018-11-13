package com.tvd12.ezyfox.binding.impl;

import static com.tvd12.ezyfox.binding.EzyAccessType.NONE;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tvd12.ezyfox.binding.annotation.EzyValue;
import com.tvd12.ezyfox.io.EzyLists;
import com.tvd12.ezyfox.reflect.EzyClass;
import com.tvd12.ezyfox.reflect.EzyField;
import com.tvd12.ezyfox.reflect.EzyMethod;

//=========================== fetcher by object =====================
public abstract class EzyObjectElementsFetcher extends EzyAbstractElementsFetcher {
	
	private Map<String, ? extends EzyMethod> methodsByFieldName;
	
	@Override
	public final List<Object> getElements(EzyClass clazz, int accessType) {
		logger.debug("start scan {}", clazz);
		initialize(clazz, accessType);
		List<Object> elements = doGetElements(clazz, accessType);
		logger.debug("finish scan {}", clazz);
		return elements;
	}
	
	private void initialize(EzyClass clazz, int accessType) {
		this.methodsByFieldName = getMethodsByFieldName(clazz);
	}
	
	private List<Object> doGetElements(EzyClass clazz, int accessType) {
		return accessType == NONE
			 ? getAnnotatedElements(clazz) 
			 : getNativeElements(clazz, accessType);
	}
	
	private List<Object> getAnnotatedElements(EzyClass clazz) {
		return getAnnotatedElements(
				getAnnotatedFields(clazz), 
				getAnnotatedMethods(clazz));
	}
	
	private List<Object> getNativeElements(EzyClass clazz, int accessType) {
		return getNativeElements(
				getFields(clazz, accessType), 
				getMethods(clazz, accessType));
	}
	
	private List<Object> getNativeElements(
			List<EzyField> fields, List<? extends EzyMethod> methods) {
		
		List<Object> elements = new ArrayList<>();
		Set<EzyMethod> remainMethods = new HashSet<>(methods);
		
		for(EzyField field : fields) {
			logger.debug("scan field {}", field.getName());
			
			EzyMethod method = methodsByFieldName.get(field.getName());
			
			if(method != null) {
				if(isValidGenericMethod(method)) {
					elements.add(method);
				}
				else {
					logger.debug("unknown generic type of method {}, ignore it", method.getName());
				}
				remainMethods.remove(method);
			} 
			else if(field.isPublic()) {
				if(isValidGenericField(field)) {
					elements.add(field);
				}
				else {
					logger.debug("unknown generic type of field {}, ignore it", field.getName());
				}
			} 
			else {
				logger.debug("field {} has not getter/setter, ignore it", field.getName());
			}
		}
		for(EzyMethod method : remainMethods) {
			logger.debug("scan method {}", method.getName());
			
			if(isValidGenericMethod(method)) {
				elements.add(method);
			}
			else {
				logger.debug("unknown generic type of method {}, ignore it", method.getName());
			}
		}
		return elements;
	}

	private List<Object> getAnnotatedElements(
			List<EzyField> fields, List<? extends EzyMethod> methods) {
		
		List<Object> elements = new ArrayList<>();
		
		for(EzyField field : fields) {
			logger.debug("scan field {}", field.getName());
			
			EzyMethod method = methodsByFieldName.get(field.getName());
			
			if(method != null) {
				if(!isValidGenericMethod(method)) {
					logger.debug("unknown generic type of method {}, ignore it", method.getName());
				}
				else {
					elements.add(method);
					methods.remove(method);
				}
			}
			else if(field.isPublic()) {
				if(!isValidGenericField(field)) {
					logger.debug("unknown generic type of field {}, ignore it", field.getName());
				}
				else {
					elements.add(field);
				}
			}
			else {
				logger.debug("field {} has not getter/setter, ignore it", field.getName());
			}
		}
		
		for(EzyMethod method : methods) {
			logger.debug("scan method {}", method.getName());
			
			if(isValidGenericMethod(method)) {
				elements.add(method);
			}
			else {
				logger.debug("unknown generic type of method {}, ignore it", method.getName());
			}
		}
		return elements;
	}
	
	private List<EzyField> getAnnotatedFields(EzyClass clazz) {
		return clazz.getFields(f -> f.isAnnotated(EzyValue.class));
	}
	
	private List<? extends EzyMethod> getAnnotatedMethods(EzyClass clazz) {
		List<EzyMethod> methods = EzyLists.filter(clazz.getMethods(), this::shouldAddAnnotatedMethod);
		return EzyLists.newArrayList(methods, this::newByFieldMethod);
	}
	
	private boolean shouldAddAnnotatedMethod(EzyMethod method) {
		return  method.isPublic() &&
				method.isAnnotated(EzyValue.class) && 
				isValidAnnotatedMethod(method);
	}
	
	protected abstract EzyMethod newByFieldMethod(EzyMethod method);
	protected abstract boolean isValidAnnotatedMethod(EzyMethod method);
}