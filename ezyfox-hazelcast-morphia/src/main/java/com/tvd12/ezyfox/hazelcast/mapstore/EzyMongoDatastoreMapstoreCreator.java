package com.tvd12.ezyfox.hazelcast.mapstore;

import java.util.Properties;

import com.hazelcast.core.MapStore;
import com.tvd12.ezyfox.morphia.EzyDatastoreAware;

import lombok.Setter;
import xyz.morphia.Datastore;

public class EzyMongoDatastoreMapstoreCreator 
		extends EzyMongoDatabaseMapstoreCreator
		implements EzyDatastoreAware {

	@Setter
	protected Datastore datastore;
	
	@SuppressWarnings("rawtypes")
	@Override
	public MapStore create(String mapName, Properties properties) {
		MapStore mapstore = super.create(mapName, properties);
		if(mapstore instanceof EzyDatastoreAware)
			((EzyDatastoreAware)mapstore).setDatastore(datastore);
		return mapstore;
	}
	
}
