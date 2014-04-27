

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

/*
 * DBConnector 
 * imal hasarnaga 
 * 2013-10-09
 * this class can use to connect to mongo db 
 * */


public class DBConnector
{

	//mongodb db connectoin data
	private static final String MONGO_HOST = "";
	private static final int 	MONGO_PORT = 0;
	private static final String MONGO_DB = "";
	private static final String MONGO_USER = "";
	private static final String MONGO_PASSWORD = "";
	
	private static DB database = null;
	
	
	
	
	public static synchronized DB getDBConnection()
	{
		if ( database == null )
		{
			try
			{
				MongoClient mongo = new MongoClient( MONGO_HOST, MONGO_PORT );
				database = mongo.getDB( MONGO_DB );
				boolean auth = database.authenticate( MONGO_USER, MONGO_PASSWORD.toCharArray() );
				if ( !auth )
				{
					throw new Exception( "Unable To Authenticate, plese check the credentials" );
				}
				else
				{
					System.out.println( "Successfull Connection and Authentication has done>>>>" );
				}
			}
			catch ( Exception e )
			{
				System.out.println(MONGO_USER+"/"+ MONGO_PASSWORD);
				System.out.println( "Erorr in establising connection : " + e.getMessage() );
			}
		}
		return database;
	}

	public static  synchronized void insertDataMONGO( String CollectionName, BasicDBObject document )
	{

		DBCollection table = getDBConnection().getCollection( CollectionName );
		table.insert( document );
	}
	
	public static synchronized void updateaDataMONGO(String collectionname,BasicDBObject searchQuery,BasicDBObject newDocumentupdateObj){
		BasicDBObject updateObj = new BasicDBObject("$set", newDocumentupdateObj);
		getDBConnection().getCollection( collectionname).update(searchQuery, updateObj);
	}
	
	public static synchronized void updateArray(String collectionname,BasicDBObject searchQuery,BasicDBObject newDocumentupdateObj){
		BasicDBObject updateObj = new BasicDBObject("$push", newDocumentupdateObj);
		DBCollection table = getDBConnection().getCollection( collectionname);
		table.update(searchQuery, updateObj);
		
	}
	
	
	public static synchronized DBCursor searchMONGO(String collectionname,BasicDBObject searchQuery){
		DBCollection table = getDBConnection().getCollection(collectionname);
		return table.find(searchQuery);
	 
	}
	
	
	
	
}
