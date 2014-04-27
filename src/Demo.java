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
