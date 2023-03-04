package util;

import dbmysql.InsertMySQL;
import static dbmysql.InsertMySQL.showTableInserd;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import forms.MainForm;

public class Parser {

    static Connections connection = Connections.getInstance();
    static String[] logParts = new String[10];
    public static oneLineLog oll = new oneLineLog();

    public static void lines(String filePath) throws IOException, SQLException {

        connection.connectLogFile(filePath);

        while (connection.bufferReader.ready()) {
               oll.oneline = connection.bufferReader.readLine();

            tenPieces(oll.oneline);
            timeSampUTC(oll.timeStampUTC);
            trimHostname(oll.proxyHierarchyRoute);
            showTableInserd();
            InsertMySQL.insertTable();
        }
    }

    public static void tenPieces(String oneLine) {

        String[] tokens = oneLine.split(" ");
        int i = 0;

        for (String t : tokens) {
            if (!t.equals("")) { 	// Bəzən aradakı boşluq bir necə space olur
                tokens[i] = t; 		// Soemtime there is more space
                i++;
            }
        }

        oll = new oneLineLog(tokens[0], tokens[1], tokens[2],
                       tokens[3], tokens[4], tokens[5], tokens[6], tokens[7],
                       tokens[8], tokens[9]);

    }

    public static void timeSampUTC(String timeStampString) {

        Date dateUTC = new Date((long) (Double.parseDouble(timeStampString) * 1000L));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat timeFormat = new SimpleDateFormat("hh:mm ");

        oll.clientRequestDate = dateFormat.format(dateUTC);
        oll.clientRequestTime = timeFormat.format(dateUTC);

    }

    public static void trimHostname(String url) {

        String parts[] = url.split("/"); 		//split with '/'
        String parts1[] = parts[2].split("\\.");   //split with '.'(dot)
        int l = parts1.length;
        if (l >= 3) {
            oll.serverName = parts1[l - 2] + "." + parts1[l - 1];
        } else {
            oll.serverName = parts[2];
        }
    }
    
    
    
    
    
}
