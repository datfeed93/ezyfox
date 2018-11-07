package com.tvd12.ezyfox.codec;

import java.nio.ByteBuffer;

import com.tvd12.ezyfox.callback.EzyCallback;

public class EzySimpleStringDataDecoder
		extends EzyAbstractMessageDataDecoder<EzyStringToObjectDecoder>
		implements EzyStringDataDecoder {

	public EzySimpleStringDataDecoder(EzyStringToObjectDecoder decoder) {
		super(decoder);
		this.buffer = ByteBuffer.allocate(32768);
	}
	
	@Override
	public void decode(String bytes, EzyCallback<Object> callback) throws Exception {
		Object answer = decoder.decode(bytes);
		callback.call(answer);
	}
	
	@Override
	public void decode(byte[] bytes, int offset, int len, EzyCallback<Object> callback) throws Exception {
		ByteBuffer buffer = newBuffer(bytes, offset, len);
		Object answer = decoder.decode(buffer);
		callback.call(answer);
	}
	
	private ByteBuffer newBuffer(byte[] bytes, int offset, int len) {
		ByteBuffer used = buffer;
		int capacity = len - offset;
		if(buffer.capacity() < capacity)
			used = ByteBuffer.allocate(capacity);
		used.clear();
		used.put(bytes, offset, len);
		used.flip();
		return used;
	}
	
}
