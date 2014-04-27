import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;

public class MongoDBQuery extends BasicDBObject {

	public MongoDBQuery() {

	}

	public MongoDBQuery(String key, Object value) {
		super(key, value);
	}

	public MongoDBQuery EQUALS(String key, String value) {
		this.append(key, value);
		return this;
	}

	public MongoDBQuery EQUALS(String key, int value) {
		this.append(key, value);
		return this;
	}

	public MongoDBQuery notEQUALS(String key, String value) {

		this.append(key, new BasicDBObject("$ne", value));
		return this;
	}

	public MongoDBQuery notEQUALS(String key, int value) {

		this.append(key, new BasicDBObject("$ne", value));
		return this;
	}

	public MongoDBQuery lessthan(String key, double value) {
		this.append(key, new BasicDBObject("$lt", value));
		return this;
	}

	public MongoDBQuery greaterthan(String key, double value) {
		this.append(key, new BasicDBObject("$gt", value));
		return this;
	}

	public MongoDBQuery between(String key, double lowerend, double higherend) {
		this.append(key, new BasicDBObject("$gt", lowerend).append("$lt", higherend));
		return this;
	}

	public MongoDBQuery AND(MongoDBQuery query) {
		return this;
	}

	public MongoDBQuery OR(MongoDBQuery query) {
		BasicDBList or = new BasicDBList();
		or.add(this);
		or.add(query);
		MongoDBQuery orQuery = new MongoDBQuery("$or", or);
		return orQuery;
	}

	public static void main(String args[]){
		
		MongoDBQuery query = new MongoDBQuery();
		(query.greaterthan("area", 100).OR(query.lessthan("area", 1000))).AND(query.EQUALS("pi", "IMAL"));
	}
}
