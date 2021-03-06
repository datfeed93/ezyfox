package com.tvd12.ezyfox.hazelcast;

import com.tvd12.ezyfox.hazelcast.mapstore.EzyMongoDatabaseMapstoreCreator;
import com.tvd12.ezyfox.hazelcast.mapstore.EzyMongoDatastoreMapstoreCreator;
import com.tvd12.ezyfox.morphia.EzyDatastoreAware;

import lombok.Setter;
import xyz.morphia.Datastore;

@Setter
public class EzyMongoDatastoreHazelcastFactory 
		extends EzyMongoDatabaseHazelcastFactory 
		implements EzyDatastoreAware {
	
	protected Datastore datastore;

	@Override
	protected EzyMongoDatabaseMapstoreCreator newDatabaseMapstoreCreator() {
		EzyMongoDatastoreMapstoreCreator creator = new EzyMongoDatastoreMapstoreCreator();
		creator.setDatastore(datastore);
		return creator;
	}
	
}
