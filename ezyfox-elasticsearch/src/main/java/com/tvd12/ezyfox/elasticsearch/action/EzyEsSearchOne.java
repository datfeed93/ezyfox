package com.tvd12.ezyfox.elasticsearch.action;

import org.apache.http.Header;

import com.tvd12.ezyfox.elasticsearch.response.EzyEsSearchOneResponse;

public interface EzyEsSearchOne {

	<T> EzyEsSearchOneResponse<T> searchOne(Object input, Class<T> responseType, Header... headers);
	
}
