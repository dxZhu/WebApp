package db;

import db.mysql.MySQLConnection;

public class DBConnectionFactory {
	private static final String DEFAULT_DB = "mysql";
	
	public static DBConnection getDBConnection(String db) {
		switch(db) {
		case "mysql":
			return new MySQLConnection();
//			return null;
		case "mongodb" :
//			return new MongoDBConnection();
			return null;
		default:
			throw new IllegalArgumentException("Invalid db: " + db);
		}
	}
	public static DBConnection getConnection() {
		return getDBConnection(DEFAULT_DB);
	}

}
