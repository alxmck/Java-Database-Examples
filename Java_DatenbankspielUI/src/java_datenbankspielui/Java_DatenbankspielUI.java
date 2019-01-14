package java_datenbankspielui;

/**
 * @author Alexander Mack
 */
// Einbinden der SQL-Libs
import java.sql.*;

// Einbinden von Scanner um Eingaben zu ermöglichen
import java.util.Scanner;

// Einbinden von Bibliotheken zum Erstellen von MD5 Hashs
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Java_DatenbankspielUI {

    // Methode zum Erstellen von MD5 Hashes
    public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);
            // Now we need to zero pad it if you actually want the full 32 chars.
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        try {

            // Datenbanktreiber laden
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            // Verbindung aufbauen
            Connection oConnection = DriverManager.getConnection("jdbc:mysql://HOST/DATABASE?"
                    + "user=USERNAME&password=PASSWORD");

            // Spielername in einer Variable sSpielerName speichern
            String sSpielerName = "SpielerC_123123";
            
            // Scanner erstellen (ermöglicht das Einlesen von Eingaben)
            Scanner oInputStream = new Scanner(System.in);

            // Den folgenden Code innerhalb der {} ausführen
            // solange die Variable einen Wert < 1000 hat
            // Am Anfang hat ii den Wert 0
            // Mit jedem Durchlauf wird die Variable um eins erhöht (ii++)
            for (int ii = 0; ii < 1000; ii++) {

                // Es soll Mehl eingegeben werden
                System.out.println("Bitte Mehl eingeben:");
                
                // Warten bis Mehl eingegeben und mit Enter bestätigt wurde
                String sMehl = oInputStream.next();
                
                // Die Eingabe ausgeben (um zu prüfen, ob alles funktioniert)
                System.out.println("Eingabe: " + sMehl);
                
                // Zucker aus der Datenbank lesen
                // Dazu ein Statement vorbereiten
                PreparedStatement oPrepStatement = oConnection.prepareStatement("SELECT * FROM DATABASE.Zucker WHERE `ZuckerID` = ?");
                // Platzhalter füllen
                oPrepStatement.setInt(1, 1);

                // Die Abfrage ausführen
                ResultSet oResult = oPrepStatement.executeQuery();
                
                // Eine Variable sZucker vorbereiten, in der der Zucker gespeichert wird
                // (der aus der Datenbank gelesen wurde)
                String sZucker = "";
                
                // Die Ergebnisse solange auslesen, bis keine weiteren vorhanden sind
                while (oResult.next()) {

                    // Die Spalteninformationen ausgeben (Zumindest die ID)
                    System.out.println("ZuckerID: " + oResult.getInt("ZuckerID"));
                    
                    // Den zweiten Wert, den "Zucker" in der Variable sZucker speichern
                    sZucker = oResult.getString("Zucker");
                    
                    // Und anschließend prüfen, ob es der richtige Wert ist
                    System.out.println("Zucker: " + sZucker);
                    
                    // Mit dem Befehl break; die Schleife vorzeitig beenden. 
                    break;

                }
                
                // Das Ergebnisobjekt schließen
                oResult.close();
                
                // Den Kuchen "backen"
                // Heißt: einen MD5-Hash erstellen - bestehend aus Zucker, Mehl und dem Spielernamen
                String sKuchen = getMD5(sZucker + sMehl + sSpielerName);
                
                // Den fertigen Kuchen in die Datenbank speichern
                
                // Hierzu: Die Abfrage mit Platzhaltern vorbereiten
                oPrepStatement = oConnection.prepareStatement("INSERT INTO DATABASE.Kuchen (`Kuchen`,`Spielername`) VALUES (?,?)");

                // ... die Parameter setzen ...
                oPrepStatement.setString(1, sKuchen);
                oPrepStatement.setString(2, sSpielerName);
                
                // ... und die Abfrage ausführen
                oPrepStatement.executeUpdate();

                // Am Ende von dem Codeblock das Statment Objekt schließen
                oPrepStatement.close();

            }

            // Am Programmende die Datenbankverbindung schließen
            oConnection.close();

        } catch (Exception e) {
            System.out.println("Fehler: " + e.getMessage());
        }

    }

}
