package dbmysql;

import static dbmysql.SelectFunctions.c;
import static dbmysql.SelectFunctionsS.result1;
import forms.MainFormS;
import static forms.MainFormS.jTable1;
import static forms.MainFormS.jTextField1;
import static forms.MainFormS.jTextField2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import util.Connections;
import util.Parser;
import static util.Parser.oll;
import util.oneLineLog;
import static forms.MainFormS.writeTableName;

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

        String sql1 = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
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

        PreparedStatement stmt = c.prepareStatement("INSERT INTO `" + writeTableName
                       + "` ( `clientRequestDate`, `reguestProsessingTime`, "
                       + "`IP`, `cacheResultCode`, `contentGatewayLengt`, "
                       + "`clientRequestMethod`, `clientUserName`, "
                       + "`serverName`, `proxyResponseContentType`) "
                       + "VALUES"
                       + " ( ?, ?, ?, ?, ?, ?, ?, ?, ?);");

        stmt.setString(1, oll.getClientRequestDate());
        stmt.setString(2, oll.getReguestProsessingTime());
        stmt.setString(3, oll.getIP());
        stmt.setString(4, oll.getCacheResultCode());
        stmt.setString(5, oll.getContentGatewayWhitBayt());
        stmt.setString(6, oll.getClientRequestMethod());
        stmt.setString(7, oll.getClientUserName());
        stmt.setString(8, oll.getServerName());
        stmt.setString(9, oll.getProxyResponseContentType());

        stmt.execute();

        //melumatlar dolduruldu
    }
    static int i = 0;

    static Object[] columnsName = {"ID", "Client Request Date", "IP",
        "Reguest Prosessing Time", "Cache Result Code", "Content Gateway Lengt (Byte)",
        "Client Request Method", " Client User Name", " Server Name", "Proxy Response Content Type"};

    static DefaultTableModel dtm = new DefaultTableModel(columnsName, 0);
    static Object[] data1 = new Object[10];

    public static void showTableInserd() throws SQLException {
        i++;
        data1[0] = i;

        data1[1] = oll.getClientRequestDate();
        data1[2] = oll.getReguestProsessingTime();
        data1[3] = oll.getIP();
        data1[4] = oll.getCacheResultCode();
        data1[5] = oll.getContentGatewayWhitBayt();
        data1[6] = oll.getClientRequestMethod();
        data1[7] = oll.getClientUserName();
        data1[8] = oll.getServerName();
        data1[9] = oll.getProxyResponseContentType();

        dtm.addRow(data1);
        InsertProsessing.jTable1.setModel(dtm);
    }

    public static void dropTable() throws SQLException {

        String sql = "Drop table " + jTextField2.getText();
        Statement statement = c.createStatement();
        statement.executeUpdate(sql);
    }
}
