## SQl Similar Querying for MongoDB

This is a very Small attempt to implement easy way of writing MongoDb queries, in this way
I feel developers will be lot more comfortable to write Queries since they have to structure queries similar to Sql

=

###Project is having few dependencies

Mongo-java-driver.jar


=

###Example 


```java
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;


public class Demo {

	
		public static void main(String[] args) {
			MongoDBQuery query = new MongoDBQuery();
			//here is how we construct query, the query is similar to below sql query
			//select* from employees where salary > 100 && salary < 1000 && (employee_type = 'Manager' || employee_type = 'Director' ) 
			query.greaterthan("salary", 100).AND(query.lessthan("salary", 1000)).AND(query.EQUALS("employee_type", "Manager").OR(query.EQUALS("employee_type", "Director")));
			BasicDBObject dbo = (BasicDBObject)query;
			
			//searching 
			DBCursor cur = DBConnector.searchMONGO("employees", dbo);
			//looping through results 
			while(cur.hasNext()){
				DBObject obj = cur.next();
				String empname = (String)obj.get("emp_name");
				
			}

		}
	
}
```

##### let me know any suggestions, improvements

