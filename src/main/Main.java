package main;

import java.io.*;
import java.sql.SQLException;

import dbmysql.InsertMySQL;
import dbmysql.SelectMySQL;
import util.Connections;
import util.Parser;

public class Main {

	
	public static void main(String[] args) throws IOException, SQLException{
	
//		SelectMySQL sl = new SelectMySQL(); 
//		InsertMySQL.createTables("AA1");
//		Parser.lines("/home/adminx/Desktop/logs/Java/Squid logs/access-2015-12-30.log");
//		 Connections connection =  Connections.getInstance();
//			connection.connectMySql();
//		
	//SelectMySQL.groupbyIP();
	//SelectMySQL.contentLength();
	SelectMySQL.groupByCacheResultCode();
		
			
	}
	
	
}
