package com.tvd12.ezyfox.codec;

import java.nio.ByteBuffer;
import java.util.Queue;

import com.tvd12.ezyfox.util.EzyResettable;

public interface EzyByteToObjectDecoder extends EzyResettable {

	Object decode(EzyMessage message) throws Exception;
	
	void decode(ByteBuffer bytes, Queue<EzyMessage> queue) throws Exception;
	
}
