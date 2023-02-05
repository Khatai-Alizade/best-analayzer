package dbmysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import util.Connections;
import util.Parser;
import util.oneLineLog;

public class InsertMySQL {

	static Connections conection = Connections.getInstance();
	static Connection c = conection.connectMySql();

	public static void createDatabse() throws SQLException {

		String databaseName = "Squid_Proxy_Server";
		String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;
		Statement statement = c.createStatement();
		statement.executeUpdate(sql);
	}

	public static void createTables(String tableName) throws SQLException {

		String sql1 = "CREATE TABLE IF NOT EXISTS " +tableName +" ("
				+ "  `id` INT NOT NULL AUTO_INCREMENT,"
				+ "  `clientRequestDate` DATE NULL,"
				+ "   `IP` varchar(20),"
				+ "  `reguestProsessingTime` INT NULL,"
				+ "  `cacheResultCode` VARCHAR(99) NULL,"
				+ "  `contentGatewayLengt` DOUBLE NULL,"
				+ "  `clientRequestMethod` VARCHAR(45) NULL,"
				+ "  `clientUserName` VARCHAR(99) NULL,"
				+ "  `serverName` VARCHAR(255) NULL,"
				+ "  `proxyResponseContentType` VARCHAR(255) NULL,"
				+ "  PRIMARY KEY (`id`))";
		
		Statement statement = c.createStatement();
		statement.executeUpdate(sql1);
	
	}
	
	
	public static void insertTable() throws SQLException {
		
		
		
		PreparedStatement stmt = c.prepareStatement(
				"INSERT INTO `Squid_Proxy_Server`.`AA1` "
				+ "( `clientRequestDate`, `reguestProsessingTime`, "
				+ "`IP`, `cacheResultCode`, `contentGatewayLengt`, "
				+ "`clientRequestMethod`, `clientUserName`, "
				+ "`serverName`, `proxyResponseContentType`) "
				+ "VALUES"
				+ " ( ?, ?, ?, ?, ?, ?, ?, ?, ?);");
	
	stmt.setString(1, Parser.oll.getClientRequestDate());
	stmt.setString(2, Parser.oll.getReguestProsessingTime());
	stmt.setString(3, Parser.oll.getIP());
	stmt.setString(4, Parser.oll.getCacheResultCode() );
	stmt.setString(5, Parser.oll.getContentGatewayWhitBayt());
	stmt.setString(6, Parser.oll.getClientRequestMethod());
	stmt.setString(7, Parser.oll.getClientUserName() );
	stmt.setString(8, Parser.oll.getServerName());
	stmt.setString(9, Parser.oll.getProxyResponseContentType());
	
	stmt.execute();
	
		
		
	}

}
