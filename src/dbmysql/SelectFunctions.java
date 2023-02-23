package dbmysql;

import forms.MainForm;
import static forms.MainForm.textArea1;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Connections;

public class SelectFunctions {

    static SelectFunctions sf = new SelectFunctions();
    static Connections conection = Connections.getInstance();
    static Connection c = conection.connectMySql();
    
    public static List<String> resultMonth = new ArrayList<String>();
    public static List<String> result1 = new ArrayList<String>();
    public static List<String> result2 = new ArrayList<String>();
    public static List<String> result3 = new ArrayList<String>();
    public static List<String> result4 = new ArrayList<String>();
    
    public void GeneralButton() {

        //Ay cədvəlləri və onlarda olan məlumatin həcmi       
        textArea1.setText("");
        try {
            sf.allMonth();
            
            //onceki setrler silinmesin deye .set yox .append
            textArea1.append("Squid_Proxy_Server databazasindaki bütün aylara aid "
                           + "cədvəllər və onların həcmi:" + "\n");

            for (int i = 0; i < sf.resultMonth.size(); i++) {
                textArea1.append(sf.resultMonth.get(i) + "  ");
                textArea1.append(sf.result1.get(i) + "  Mb");
                textArea1.append(("\n"));
            }
            sf.result1.clear();

//******************************************************************************
            // Hər aya görə iştifadəçilər tərəfindən istifadə olunan məlumatlan həcmi 
            textArea1.append(("\n"));
            textArea1.append("Hər ay istifadə olunan məlumatın həcmi (Gb-la):" + "\n");
            for (int i = 0; i < sf.resultMonth.size(); i++) {
                sf.oneResultColumn("SELECT  "
                               + " round (SUM((contentGatewayLengt)/1024/1024/1024), 4)  "
                               + " FROM Squid_Proxy_Server." + sf.resultMonth.get(i));

                textArea1.append(sf.resultMonth.get(i) + "  " + sf.result1.get(i) + " Gb");
                textArea1.append(("\n"));
            }
            sf.result1.clear();
            textArea1.append(("\n"));
//******************************************************************************
            //Aylara görə İP ünvanları və istifadə etdiyi məlumatin faizi

            textArea1.append("Aylara görə İP ünvanları və ay ərzində " + "\n"
                           + "serverə etdikləri müraciətlərin sayı, faizlə ifadəsi"
                           + " və istifadə etdikləri trafikin GB-la miqdarı:" + "\n");

            //Birinci döngü hər ay üçün sorğu göndərir   
            for (int i = 0; i < sf.resultMonth.size(); i++) {
                sf.fourResultColumn("SELECT "
                               + "IP, "
                               + "COUNT(*) AS `Count`, "
                               + "CONCAT(ROUND(COUNT(*) / (SELECT COUNT(*) "
                               + "FROM Squid_Proxy_Server." + sf.resultMonth.get(i)
                               + ") * 100, 3), '%') AS `Percentage`, "
                               + "CONCAT(ROUND(SUM(CASE WHEN contentGatewayLengt REGEXP '^[0-9]+$' "
                               + " THEN contentGatewayLengt ELSE 0 END) / "
                               + " (1024*1024*1024), 3), ' GB') AS `Total_GB` "
                               + " FROM Squid_Proxy_Server." + sf.resultMonth.get(i)
                               + " GROUP BY IP "
                               + "ORDER BY sum(contentGatewayLengt) DESC;");

                textArea1.append("*****" + (sf.resultMonth.get(i)) + "***** \n");

                // İkinci döngüdə Ay üçün alinmış məlimati çap edir
                for (int j = 0; j < sf.result1.size(); j++) {
                    textArea1.append(j + 1 + ".  " + sf.result1.get(j) + "   ");
                    textArea1.append((sf.result2.get(j) + " dəfə   " + sf.result3.get(j)));
                    textArea1.append(("   " + sf.result4.get(j)));
                    textArea1.append(("\n"));
                }
                sf.result1.clear();
                sf.result2.clear();
                sf.result3.clear();
                sf.result4.clear();
            }

//******************************************************************************
            textArea1.append("\n\nMüraciət sorğusunun tipi:\n");

            //Birinci döngü hər ay üçün sorğu göndərir   
            for (int i = 0; i < sf.resultMonth.size(); i++) {

                sf.twoResultColumn("SELECT clientRequestMethod, count(*) "
                               + "FROM Squid_Proxy_Server." + sf.resultMonth.get(i)
                               + " GROUP BY clientRequestMethod "
                               + "ORDER BY COUNT(clientRequestMethod) DESC");

                textArea1.append("*****" + (sf.resultMonth.get(i)) + "***** \n");

                // İkinci döngüdə Ay üçün alinmış məlimati çap edir
                for (int j = 0; j < sf.result1.size(); j++) {
                    textArea1.append(j + 1 + ".  " + sf.result1.get(j) + "   ");
                    textArea1.append((sf.result2.get(j)) + " dəfə");
                    textArea1.append(("\n"));
                }
                sf.result1.clear();
                sf.result2.clear();
            }
//******************************************************************************

            textArea1.append("\n\nKeş yaddaşın cavabı:\n");

            //Birinci döngü hər ay üçün sorğu göndərir   
            for (int i = 0; i < sf.resultMonth.size(); i++) {

                sf.twoResultColumn("SELECT cacheResultCode, count(*) "
                               + "FROM Squid_Proxy_Server." + sf.resultMonth.get(i)
                               + " GROUP BY cacheResultCode "
                               + "ORDER BY COUNT(cacheResultCode) DESC;");

                textArea1.append("*****" + (sf.resultMonth.get(i)) + "***** \n");

                // İkinci döngüdə Ay üçün alinmış məlimati çap edir
                for (int j = 0; j < sf.result1.size(); j++) {
                    textArea1.append(j + 1 + ".  " + sf.result1.get(j) + "   ");
                    textArea1.append((sf.result2.get(j)) + " dəfə");
                    textArea1.append(("\n"));
                }
                sf.result1.clear();
                sf.result2.clear();
            }
//******************************************************************************
            textArea1.append("\n\nMüraciət olunan serverlər və müraciət sayları:\n");

            //Birinci döngü hər ay üçün sorğu göndərir   
            for (int i = 0; i < sf.resultMonth.size(); i++) {

                sf.twoResultColumn("SELECT serverName, count(*) "
                               + "FROM Squid_Proxy_Server." + sf.resultMonth.get(i)
                               + " group by serverName "
                               + "ORDER BY COUNT(serverName) DESC");

                textArea1.append("*****" + (sf.resultMonth.get(i)) + "***** \n");

                // İkinci döngüdə Ay üçün alinmış məlimati çap edir
                for (int j = 0; j < sf.result1.size(); j++) {
                    textArea1.append(j + 1 + ".  " + sf.result1.get(j) + "   ");
                    textArea1.append((sf.result2.get(j)) + " dəfə");
                    textArea1.append(("\n"));
                }
                sf.result1.clear();
                sf.result2.clear();
            }

        } catch (SQLException ex) {
            Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        sf.resultMonth.clear();
        sf.result1.clear();
        sf.result2.clear();
        sf.result4.clear();

    }

    public static void allMonth() throws SQLException {
        String sql = "SELECT"
                       + "  TABLE_NAME AS `Table`, "
                       + "  ROUND(((DATA_LENGTH + INDEX_LENGTH) / 1024 / 1024),4) AS `Size (MB)` "
                       + "FROM "
                       + "  information_schema.TABLES "
                       + "WHERE "
                       + "  TABLE_SCHEMA = \"Squid_Proxy_Server\" ";

        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        while (rs.next()) {
            resultMonth.add(rs.getString(1));
            result1.add(rs.getString(2));

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
