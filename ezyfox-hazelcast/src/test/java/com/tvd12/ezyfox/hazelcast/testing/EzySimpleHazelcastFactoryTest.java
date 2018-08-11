package com.tvd12.ezyfox.hazelcast.testing;

import org.testng.annotations.Test;

import com.hazelcast.config.Config;
import com.tvd12.ezyfox.hazelcast.EzyAbstractHazelcastFactory;
import com.tvd12.ezyfox.hazelcast.EzySimpleHazelcastFactory;
import com.tvd12.ezyfox.hazelcast.mapstore.EzyMapstoresFetcher;
import com.tvd12.ezyfox.hazelcast.mapstore.EzySimpleMapstoresFetcher;
import com.tvd12.test.base.BaseTest;

public class EzySimpleHazelcastFactoryTest extends BaseTest {

	@Test
	public void test() {
		EzyAbstractHazelcastFactory factory = new EzySimpleHazelcastFactory();
		factory.setMapstoresFetcher(newMapstoresFetcher());
		factory.newHazelcast(new Config());
	}
	
	private EzyMapstoresFetcher newMapstoresFetcher() {
		return EzySimpleMapstoresFetcher.builder()
				.scan("com.tvd12.ezyfox.hazelcast.testing.mapstore")
				.build();
	}
}
