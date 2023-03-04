package dbmysql;

import static forms.MainFormS.jTable1;
import javax.swing.table.DefaultTableModel;
import static dbmysql.SelectFunctionsS.result2;
import static dbmysql.SelectFunctionsS.result3;


public class addJTable {
    
    public static void add_JTable(Object[] columnsName, int dm){  //data massiv
        
        int columnID = 1;
  //      Object[] columnsName = {"No", "Month", "Table Size (Mb)"};
        Object[] data = new Object[dm];
        DefaultTableModel dtm = new DefaultTableModel(columnsName, 0);

        for (int i = 0; i < result2.size(); i++) {
            data[0] = columnID;
            data[1] = result2.get(i);
            data[2] = result3.get(i);

            dtm.addRow(data);
            jTable1.setModel(dtm);
            columnID++;
        }
    
    }
    
}
