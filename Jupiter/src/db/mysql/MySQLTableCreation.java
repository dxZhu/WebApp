package db.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class MySQLTableCreation {
	// Run this as Java application to reset db schema.
	public static void main(String[] args) {
		try {
			// This is java.sql.Connection. Not com.mysql.jdbc.Connection.
			Connection conn = null;

			// Step 1 Connect to MySQL.
			try {
				System.out.println("Connecting to " + MySQLDBUtil.URL);
				//forName: call static initializer in this class, used to add driver to our driver list.
				Class.forName("com.mysql.jdbc.Driver").getConstructor().newInstance();
				conn = DriverManager.getConnection(MySQLDBUtil.URL);
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if (conn == null) {
				return;
			}

			//Step 2 Drop table in case exists
			Statement stmt = conn.createStatement();
			String sql = "DROP TABLE IF EXISTS categories";
			stmt.executeUpdate(sql);//reurn how many instrs are updated
			
			sql = "DROP TABLE IF EXISTS history";
			stmt.executeUpdate(sql);//reurn how many instrs are updated
			
			sql = "DROP TABLE IF EXISTS items";
			stmt.executeUpdate(sql);//reurn how many instrs are updated
			
			sql = "DROP TABLE IF EXISTS users";
			stmt.executeUpdate(sql);//reurn how many instrs are updated
			
			//Step 3 create Create new tables 
			sql = "CREATE TABLE items"
					+ "(item_id VARCHAR(255) NOT NULL,"
					+ " name VARCHAR(255),"
					+ " rating FLOAT,"
					+ " address VARCHAR(255),"
					+ " image_url VARCHAR(255),"
					+ " url VARCHAR(255),"
					+ " distance FLOAT,"
					+ " PRIMARY KEY (item_id))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE categories"
					+ "(item_id VARCHAR(255) NOT NULL,"
					+ " category VARCHAR(255) NOT NULL,"
					+ " PRIMARY KEY (item_id, category),"
					+ " FOREIGN KEY (item_id) REFERENCES items(item_id))";
			stmt.executeUpdate(sql);
			//reference in foreign key means the items must be in items first to show here
			
			sql = "CREATE TABLE users"
					+ "(user_id VARCHAR(255) NOT NULL,"
					+ " password VARCHAR(255) NOT NULL,"
					+ " first_name VARCHAR(255),"
					+ " last_name VARCHAR(255),"
					+ " PRIMARY KEY (user_id))";
			stmt.executeUpdate(sql);
			
			sql = "CREATE TABLE history"
					+ "(user_id VARCHAR(255) NOT NULL,"
					+ " item_id VARCHAR(255) NOT NULL,"
					+ " last_favor_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,"
					+ " PRIMARY KEY (user_id, item_id),"
					+ " FOREIGN KEY (item_id) REFERENCES items(item_id),"
					+ " FOREIGN KEY (user_id) REFERENCES users(user_id))";
			stmt.executeUpdate(sql);
			
			// Step 4 insert data
			// Create a fake user
			sql = "INSERT INTO users VALUES ("
					+ "'dongxin2', '123456789', 'dongxin', 'zhu')";
			System.out.println("Executing query" + sql);
			stmt.executeUpdate(sql);
			
			System.out.println("Import is done successfully.");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
