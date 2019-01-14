package java_mysql_select;

// einbinden der java sql pfade
import java.sql.*;


/**
 * @author Alexander Mack
 */
public class Java_MySQL_Select {

    public static void main(String[] args) {
        
        try {
        
            System.out.println("Schritt 1: Lade Datenbanktreiber mithilfe der Class.forName Methode...");

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            System.out.println("Schritt 2: Verbindung aufbauen...");
            
            Connection oConnection = DriverManager.getConnection("jdbc:mysql://HOST/DATABASE?" + 
                    "user=USER&password=PASSWORD");
            
            System.out.println("Schritt 3: Interaktion");
            
            System.out.println("Abfrage mit Platzhalter vorbereiten..");
            PreparedStatement oPrepStatement = oConnection.prepareStatement("SELECT * FROM DATABASE.testdaten WHERE `id` = ?");
            
            System.out.println("Platzhalter füllen");
            oPrepStatement.setInt(1, 1); // Erste 1 steht für den ersten Platzhalter, zweite 1 für den Wert.
            
            System.out.println("Abfrage ausführen");
            ResultSet oResult = oPrepStatement.executeQuery();
            
            System.out.println("Ergebnisse ausgeben");
            // solange ein Ergebnis vorhanden ist
            while (oResult.next()) {
                
                // Ergebnisse ausgeben
                System.out.println("id: " + oResult.getInt("id"));
                System.out.println("name: " + oResult.getString("name"));
                
            }
            
            System.out.println("Ergebnis-Objekt schließen");
            oResult.close();
            
            System.out.println("Statement-Objekt schließen");
            oPrepStatement.close();
            
            System.out.println("Verbindung schließen");
            oConnection.close();
        
        } catch (Exception e) {
            System.out.println("Fehler beim Datenbankaufbau: " + e.getMessage());
        }
        
    }
    
}

/* Datenbankstruktur:

CREATE TABLE `testdaten` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

INSERT INTO `testdaten` VALUES (1,'Hans'),(2,'Birgit'),(3,'Dodo'),(4,'Gustav'),(5,'Darth Vader');

*/

/* Übung:

    Ändern Sie den Quellcode so ab, dass der Datensatz mit ID 2 ausgelesen wird

*/

