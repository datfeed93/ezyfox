package com.tvd12.ezyfox.hazelcast.testing.factory;

import org.testng.annotations.Test;

import com.tvd12.ezyfox.hazelcast.factory.EzyMongoAccoutFactory;
import com.tvd12.test.base.BaseTest;

public class EzyMongoAccoutFactoryTest extends BaseTest {

	@Test
	public void test() {
		EzyMongoAccoutFactory factory = new EzyMongoAccoutFactory();
		factory.newAccount(1L);
	}
	
}
