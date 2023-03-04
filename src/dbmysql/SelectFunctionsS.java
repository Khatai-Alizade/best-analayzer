package dbmysql;

import static dbmysql.SelectFunctions.c;

import static forms.MainFormS.jList2;
import static forms.MainFormS.jTable1;
import static forms.MainFormS.jTextField1;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

public class SelectFunctionsS {

    public static List<String> resultMonth = new ArrayList<String>();
    public static List<String> result1 = new ArrayList<String>();
    public static List<String> result2 = new ArrayList<String>();
    public static List<String> result3 = new ArrayList<String>();
    public static List<String> result4 = new ArrayList<String>();

    public static void allMonth() throws SQLException {

        DefaultListModel listModel = new DefaultListModel();
        String sql = "SELECT  TABLE_NAME AS `Table`"
                       + " FROM  information_schema.TABLES"
                       + " WHERE   TABLE_SCHEMA = 'Squid_Proxy_Server'";

        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            resultMonth.add(rs.getString(1));
            listModel.addElement(rs.getString(1));
            jList2.setModel(listModel);
        }
     
    }

// Ümumi aylar və Teybillarin həcmi
    public static void tableSize() throws SQLException {
       
        String sql = "SELECT"
                       + "  TABLE_NAME AS `Table`, "
                       + "  ROUND(((DATA_LENGTH + INDEX_LENGTH) / 1024 / 1024),3) AS `Size (MB)` "
                       + "FROM "
                       + "  information_schema.TABLES "
                       + "WHERE "
                       + "  TABLE_SCHEMA = \"Squid_Proxy_Server\" ";

        twoResultColumn(sql);

        int columnID = 1;
        Object[] columnsName = {"No", "Month", "Table Size (Mb)"};
        Object[] data = new Object[3];
        DefaultTableModel dtm = new DefaultTableModel(columnsName, 0);

        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        for (int i = 0; i < result2.size(); i++) {
            data[0] = columnID;
            data[1] = result1.get(i);
            data[2] = result2.get(i);

            dtm.addRow(data);
            jTable1.setModel(dtm);
            columnID++;
        }
        result1.clear();
        result2.clear();
    }

//    "Hər ay istifadə olunan məlumatın həcmi (Gb-la):" + "\n"); **
    public static void usedData() throws SQLException {
        
        Object[] columnsName = {"No", "Month", "Used Data (Gb)"};
        Object[] data = new Object[3];
        DefaultTableModel dtm = new DefaultTableModel(columnsName, 0);

        allMonth();
        for (int i = 0; i < resultMonth.size(); i++) {
       String  sql="SELECT round (SUM((contentGatewayLengt)/1024/1024/1024), 4) "
                    + " FROM Squid_Proxy_Server." + resultMonth.get(i);
           
            oneResultColumn(sql);     
            data[0] = i+1;
            data[1] = resultMonth.get(i);
            data[2] = result1.get(i);
            dtm.addRow(data);
            jTable1.setModel(dtm);
        }
        result1.clear();
        resultMonth.clear();
    }
    
    //    "Hər ay istifadə olunan məlumatın həcmi (Gb-la):" + "\n"); **
    public static void tableCreatedDate() throws SQLException {
        
        Object[] columnsName = {"No", "Month", "Table Created Time"};
        Object[] data = new Object[3];
        DefaultTableModel dtm = new DefaultTableModel(columnsName, 0);

        allMonth();
        for (int i = 0; i < resultMonth.size(); i++) {
         String sql = " SELECT DATE(create_time) AS create_date"
                        + " FROM information_schema.tables "
                        + "WHERE table_schema = 'Squid_Proxy_Server' "
                        + "AND table_name ='"+resultMonth.get(i)+"'";    
          
            oneResultColumn(sql);
            data[0] = i+1;
            data[1] = resultMonth.get(i);
            data[2] = result1.get(i);
            dtm.addRow(data);
            jTable1.setModel(dtm);
        }
        result1.clear();
        resultMonth.clear();
    }   
//Ip unvaninia gore ne qerer data serf olunub onu qaytarir
    public static void ipDataUse() throws SQLException {
 
        int columnID = 1;
        Object[] columnsName = {"No", "IP", "How Many Times Requested", "Data used (%)", "Data Used (Gb)"};
        Object[] data1 = new Object[5];
        DefaultTableModel dtm = new DefaultTableModel(columnsName, 0);

        String sql = "SELECT "
                       + "IP, "
                       + "COUNT(*) AS `Count`, "
                       + "CONCAT(ROUND(COUNT(*) / (SELECT COUNT(*) "
                       + "FROM Squid_Proxy_Server." + jTextField1.getText()
                       + ") * 100, 3), '%') AS `Percentage`, "
                       + "CONCAT(ROUND(SUM(CASE WHEN contentGatewayLengt REGEXP '^[0-9]+$' "
                       + " THEN contentGatewayLengt ELSE 0 END) / "
                       + " (1024*1024*1024), 3), ' GB') AS `Total_GB` "
                       + " FROM Squid_Proxy_Server." + jTextField1.getText()
                       + " GROUP BY IP "
                       + "ORDER BY sum(contentGatewayLengt) DESC;";

        fourResultColumn(sql);

        for (int i = 0; i < result2.size(); i++) {
            data1[0] = columnID;
            data1[1] = result1.get(i);
            data1[2] = result2.get(i);
            data1[3] = result3.get(i);
            data1[4] = result4.get(i);

            columnID++;
            dtm.addRow(data1);
            jTable1.setModel(dtm);

        }
        
        result1.clear();
        result2.clear();
        result3.clear();
        result4.clear();

    }
  
   //  Müraciət olunan serverlər və müraciət sayları:
    public static void servers() throws SQLException {
      
        int columnID = 1;
        Object[] columnsName = {"No", "Server Name", "How Many Times Requested" };
        Object[] data1 = new Object[3];
        DefaultTableModel dtm = new DefaultTableModel(columnsName, 0);

        String sql="SELECT serverName, count(*) "
                               + "FROM Squid_Proxy_Server." + jTextField1.getText()
                               + " group by serverName "
                               + "ORDER BY COUNT(serverName) DESC";

         twoResultColumn(sql); 
         
        for (int i = 0; i < result2.size(); i++) {
            data1[0] = columnID;
            data1[1] = result1.get(i);
            data1[2] = result2.get(i);
            
            columnID++;
            dtm.addRow(data1);
            jTable1.setModel(dtm);

        }
       
        result1.clear();
        result2.clear();
   }     
    
     public static void requestMethod() throws SQLException {
      
        int columnID = 1;
        Object[] columnsName = {"No", "Request Method", "How Many Times Used" };
        Object[] data1 = new Object[3];
        DefaultTableModel dtm = new DefaultTableModel(columnsName, 0);

        String sql ="SELECT clientRequestMethod, count(*) "
                       + "FROM Squid_Proxy_Server." + jTextField1.getText()
                       + " GROUP BY clientRequestMethod "
                       +"ORDER BY COUNT(clientRequestMethod) DESC";
                
         twoResultColumn(sql); 
         
        for (int i = 0; i < result2.size(); i++) {
            data1[0] = columnID;
            data1[1] = result1.get(i);
            data1[2] = result2.get(i);
            
            columnID++;
            dtm.addRow(data1);
            jTable1.setModel(dtm);
         }
        result1.clear();
        result2.clear();
   }     
   
      public static void cacheResultAnswer() throws SQLException {
      
        int columnID = 1;
        Object[] columnsName = {"No", "Casch Request Answer", "How Many Times" };
        Object[] data1 = new Object[3];
        DefaultTableModel dtm = new DefaultTableModel(columnsName, 0);

        String sql ="SELECT cacheResultCode, count(*) "
                               + "FROM Squid_Proxy_Server." + jTextField1.getText()
                               + " GROUP BY cacheResultCode "
                               + "ORDER BY COUNT(cacheResultCode) DESC;";
         
         twoResultColumn(sql); 
        for (int i = 0; i < result2.size(); i++) {
            data1[0] = columnID;
            data1[1] = result1.get(i);
            data1[2] = result2.get(i);
            
            columnID++;
            dtm.addRow(data1);
            jTable1.setModel(dtm);
        }
        result1.clear();
        result2.clear();
   }     
//******************************************************************************
      public static void tableInside() throws SQLException {
        
          Object[] columnsName = {"ID", "Client Request Date", "IP",
          "Reguest Prosessing Time", "Cache Result Code","Content Gateway Lengt (Byte)",
          "Client Request Method"," Client User Name"," Server Name", "Proxy Response Content Type"};
          
        Object[] data1 = new Object[10];
        DefaultTableModel dtm = new DefaultTableModel(columnsName, 0);
          
        String sql = "SELECT * FROM Squid_Proxy_Server."+jTextField1.getText();
       
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {      
            data1[0] = rs.getString(1);
            data1[1] = rs.getString(2);
            data1[2] = rs.getString(3);
            data1[3] = rs.getString(4);
            data1[4] = rs.getString(5);
            data1[5] = rs.getString(6);
            data1[6] = rs.getString(7);
            data1[7] = rs.getString(8);
            data1[8] = rs.getString(9);
            data1[9] = rs.getString(10);
                            
            dtm.addRow(data1);
            jTable1.setModel(dtm);
        }
        
    
      
      
      
      }
      
      
      public static void oneResultColumn(String sql) throws SQLException {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            result1.add(rs.getString(1));
        }
    }

    public static void twoResultColumn(String sql) throws SQLException {
               
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
       
        while (rs.next()) {
            result1.add(rs.getString(1));
            result2.add(rs.getString(2));
          }
    }

    public static void threeResultColumn(String sql) throws SQLException {
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            result1.add(rs.getString(1));
            result2.add(rs.getString(2));
            result3.add(rs.getString(3));
        }
    }

    public static void fourResultColumn(String sql) throws SQLException {

        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            result1.add(rs.getString(1));
            result2.add(rs.getString(2));
            result3.add(rs.getString(3));
            result4.add(rs.getString(4));
        }
    }
}
