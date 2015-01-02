package com.pace.riscovery;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * This is a singleton class
 * @author Andy
 *
 */
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class DBHelper {
	
	private static DBHelper INSTANCE = new DBHelper();   // A singleton class is a class which has it's constrcutor 
														 // as private and so creates objects throuh a get instance
	public static DBHelper getInstance(){				 // method
		return DBHelper.INSTANCE;
	}
	
	private static MysqlDataSource ds;
	private static Connection con;
	
	private DBHelper(){		
	}
	
	public MysqlDataSource getDataSource(){
		return ds;
	}
	// init() set's up a datasource object which serves as in interface to the database for all the application
	public void init() {
		ds = new MysqlDataSource();
		ds.setServerName("Localhost");
		ds.setPortNumber(3306);
		ds.setDatabaseName("testDB");
		ds.setUser("root");
		ds.setPassword("");		
	}
	
	// closes the connection to the datasource. but does it really close conn and what happens to ds in the end?
	public void close() {
		if(con != null){
			try{
			con.close();
			System.out.println("Closing connection...");
			} catch(SQLException e) {
				System.out.println("Failed to connect to the data source.");
			}
		}
	}
	
	// this method is used to get connection to the database through the datasource
	public static Connection getConnection() throws SQLException{
		con = getInstance().getDataSource().getConnection();
//		System.out.print("here!");
		return con;
	}	
}