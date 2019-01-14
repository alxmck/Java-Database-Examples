package java_mysql_datenbankklasse;

/**
 *
 * @author Alexander Mack
 */

import java_mysql_datenbankklasse.*;
import java.sql.*;

public class Java_MySQL_Datenbankklasse {

    public static void main(String[] args) {

        try {

            Data oData = new Data();
            oData.setName("Hallo");
            oData.store();

            Data oDataCheck = Data.getByName("Hallo");
            System.out.println("ID: " + oDataCheck.getID());
                    
        
        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }
        
    }
    
}
