package java_mysql_insert;

import java.sql.*;

/**
 *
 * @author Alexander Mack
 */

public class Java_MySQL_Insert {

    public static void main(String[] args) {
        
        try {
        
            System.out.println("Schritt 1: Lade Datenbanktreiber mithilfe der Class.forName Methode...");

            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            System.out.println("Schritt 2: Verbindung aufbauen...");
            
            Connection oConnection = DriverManager.getConnection("jdbc:mysql://HOST/DATABASE?" + 
                    "user=USER&password=PASSWORD");
            
            System.out.println("Schritt 3: Interaktion");
            
            System.out.println("INSERT Query vorbereiten");
            PreparedStatement oPrepStatementInsert = oConnection.prepareStatement("INSERT INTO DATABASE.testdaten (`name`) VALUES (?)");
            
            System.out.println("Platzhalter füllen");
            oPrepStatementInsert.setString(1, "Romulus der Grosse"); // Empfehlung von Dürrenmatt 
            
            System.out.println("Update ausführen");
            // Achtung: Bei Update / Insert -> executeUpdate und NICHT executeQuery wie bei SELECT
            oPrepStatementInsert.executeUpdate();
            
            System.out.println("Prüfen ob es funktioniert hat. Abfrage mit Platzhalter vorbereiten..");
            PreparedStatement oPrepStatementSelect = oConnection.prepareStatement("SELECT * FROM DATABASE.testdaten WHERE `name` = ?");
            
            System.out.println("Platzhalter füllen");
            oPrepStatementSelect.setString(1, "Romulus der Grosse"); // Erste 1 steht für den ersten Platzhalter, zweite Stelle hat hier jetzt einen String Wert
            
            System.out.println("Abfrage ausführen");
            ResultSet oResult = oPrepStatementSelect.executeQuery();
            
            System.out.println("Ergebnisse ausgeben");
            // solange ein Ergebnis vorhanden ist
            while (oResult.next()) {
                
                // Ergebnisse ausgeben
                System.out.println("id: " + oResult.getInt("id"));
                System.out.println("name: " + oResult.getString("name"));
                
            }
            
            System.out.println("Ergebnis-Objekt schließen");
            oResult.close();
            
            System.out.println("Statement-Objekte schließen");
            oPrepStatementInsert.close();
            oPrepStatementSelect.close();
            
            System.out.println("Verbindung schließen");
            oConnection.close();
        
        } catch (Exception e) {
            System.out.println("Fehler beim Datenbankaufbau: " + e.getMessage());
        }
        
        
        
    }
    
}
