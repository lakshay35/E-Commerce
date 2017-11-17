package persistent;

/* Bradley Reeves
 * Section 26666
 * 
 * Contains the information that is needed to connect to the database
 * with DbAccessImpl.
 * 
 */

public abstract class DbAccessConfiguration {
	
	protected static final String DB_DRIVER_NAME = "com.mysql.jdbc.Driver";
	
	protected static final String DB_CONNECTION_URL = "jdbc:mysql://localhost:330/onlinebookstoredb?useSSL=false";
	
	protected static final String DB_CONNECTION_USERNAME = "root";
	
	protected static final String DB_CONNECTION_PASSWORD = "password";

}
