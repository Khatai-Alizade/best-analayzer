package dbmysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import  java.util.List ;

import util.Connections;

/*

BIR NEÇƏ FUNKSIYA YAZıB
BÜTÜN FUNKSIYALRı ONLARLA ƏVƏZ EDƏ BILƏRƏM

 MƏSƏLƏN DÜYMƏ FUNKSIYAYA BIR SQL SORĞUSU GÖNDƏRIR O
 DA ICRA EDIB NƏTICƏNI QAYTARIR.
  HƏR DÜYMƏ BIR SORĞU GÖNDƏRIR
  LAKIN HƏR BIRIR EYNI FUNKSIYA TƏRƏFINDƏN ICRA OLUNUR.

*/

public class SelectMySQL {
        

	static Connections conection = Connections.getInstance();
	static Connection c = conection.connectMySql();
        
      public static List<String> result = new ArrayList<String>();
     
       
	public static void showTables() throws SQLException {
       
		String sql = "SHOW TABLES";
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
               
		while (rs.next()) {
                  
                    result.add(rs.getString(1));
                   // System.out.println(rs.getString(1));
                    
                }
	}
        
	public static void groupbyIP() throws SQLException {
		String tableName = "Squid_Proxy_Server.AA1";
		String sql = "select IP from " + tableName + " group by IP";
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.println(rs.getString(1));
		}
	}
	
	public static void groupbyServerName() throws SQLException {
		
		String tableName = "Squid_Proxy_Server.AA1";
		String sql = "SELECT serverName, count(*) "
				+ "FROM "+ tableName+ " group by serverName "
				+ "ORDER BY COUNT(serverName) DESC";
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.println(rs.getString(1));
			System.out.println(rs.getString(2));
			
		}
	}
	
	public static void contentLength() throws SQLException {
		
		String tableName = "Squid_Proxy_Server.AA1";
		String sql = "SELECT IP, sum(contentGatewayLengt) "
				+ "FROM "+tableName
				+ " GROUP BY IP "
				+ "ORDER BY COUNT(contentGatewayLengt) DESC";
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.print(rs.getString(1));
		//	System.out.println("  "+rs.getString(2));
			System.out.println("   "+rs.getFloat(2)/(1e+6) +"Mb");
			
		}
	}

	public static void groupByclientRequestMethod() throws SQLException {

	String sql = "SELECT clientRequestMethod, count(*) "
			+ "FROM Squid_Proxy_Server.AA1 "
			+ "GROUP BY clientRequestMethod "
			+ "ORDER BY COUNT(clientRequestMethod) DESC";
	
	
	
	Statement stmt = c.createStatement();
	ResultSet rs = stmt.executeQuery(sql);

	while (rs.next()) {
		System.out.print(rs.getString(1));
		System.out.println("   " + rs.getString(2));
	}
}

	public static void groupByCacheResultCode() throws SQLException {

		String sql = "SELECT cacheResultCode, count(*) "
				+ "FROM Squid_Proxy_Server.AA1 "
				+ "GROUP BY cacheResultCode "
				+ "ORDER BY COUNT(cacheResultCode) DESC";
		
		
		
		Statement stmt = c.createStatement();
		ResultSet rs = stmt.executeQuery(sql);

		while (rs.next()) {
			System.out.print(rs.getString(1));
			System.out.println("   " + rs.getString(2));
		}
	}

    public SelectMySQL() {
        Arrays.asList("sup1", "sup2", "sup3");
    }
}
