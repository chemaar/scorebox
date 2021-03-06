package org.scorebox.dummy;

import java.net.UnknownHostException;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class DummyMongoDBTest {

	public static void main(String[] args) {

		try {
			// connect to mongoDB, ip and port number
			Mongo mongo = new Mongo("localhost", 27017);
			System.out.println(mongo.getAddress());

			// get database from MongoDB,
			// if database doesn't exists, mongoDB will create it automatically
			DB db = mongo.getDB("test");
			System.out.println(db.getName());

			// Get collection from MongoDB, database named "yourDB"
			// if collection doesn't exists, mongoDB will create it automatically
			DBCollection collection = db.getCollection("test-collection");

			// create a document to store key and value
			BasicDBObject document = new BasicDBObject();
			document.put("id", 1001);
			document.put("msg", "hello world mongoDB in Java");

			// save it into collection named "yourCollection"
			collection.insert(document);

			// search query
			BasicDBObject searchQuery = new BasicDBObject();
			searchQuery.put("id", 1001);

			// query it
			DBCursor cursor = collection.find(searchQuery);

			// loop over the cursor and display the retrieved result
			while (cursor.hasNext()) {
				System.out.println(cursor.next());
			}

			System.out.println("Done");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (MongoException e) {
			e.printStackTrace();
		}

	}
}


