package com.tvd12.ezyfox.mongodb;

import org.bson.Document;

import com.mongodb.client.MongoCollection;

public interface EzyMongoCollectionAware {

	void setCollection(MongoCollection<Document> collection);
	
}
