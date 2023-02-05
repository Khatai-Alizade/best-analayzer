package util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public  class Connections {

//Singelton design pattern for security

	private static Connections connection = null;
	private Connections() {}
	
	public static Connections getInstance() {
		if(connection==null) {
			connection=new Connections();
		}
		return connection;
	}
	
//******************************************************************
	
	
	static BufferedReader bufferReader;

	
	//For conncet to MySQL 
      public static String USER = "groupone";
      public static String PASS = "Xmen1010.";
      
      public  static String connectionMessage;
	public static Connection connectMySql() {
		
		Connection c = null;
		try {
			String url = "jdbc:mysql://localhost:3306";
			c = DriverManager.getConnection(url, USER, PASS);
			
			String databaseName = "Squid_Proxy_Server";		
			String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;
			String sql1 = "USE Squid_Proxy_Server";
			Statement statement = c.createStatement();
			statement.executeUpdate(sql);
			statement.executeUpdate(sql1);
			
                        connectionMessage = "Database connection is successful √";
					
		} catch (SQLException e) {
			e.printStackTrace();
                        connectionMessage = "!* Username or passwor is wrong..";
		}
		
		return c;
		
	}

	

	//For conncet to log file(s)
	public static BufferedReader connectLogFile(String filePath) throws FileNotFoundException {
		
		FileReader fr = new FileReader(filePath);
		 bufferReader = new BufferedReader(fr);

		return bufferReader;

	}

}
