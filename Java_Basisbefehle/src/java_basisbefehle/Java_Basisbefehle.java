package java_basisbefehle;

/**
 *
 * @author Alexander Mack
 */
public class Java_Basisbefehle {

    public static void main(String[] args) {
        
        // Einzeilige Kommentare
        
        /*
        Mehrzeilige Kommentare
        */
        
        System.out.println("Text ausgeben");
        
        // Werte in Variablen speichern
        
        // Text
        String sEineVariable = "Hallo";
        
        // Ganzzahlen / Integer
        int iTest = 10;
        
        // Fließkomma / Float
        float fTest = 1.5f;
        
        // Variablen ausgeben
        
        System.out.println(sEineVariable);
        System.out.println(iTest);
        System.out.println(fTest);
        
        // Bedingungen 
        if (iTest == 10) {
            System.out.println("Die Zahl ist 10!");
        } else {
            System.out.println("Huch! Die Zahl ist nicht 10");
        }
        
        // Schleifen
        for(int ii = 0; ii < 10; ii++) {
            System.out.println("Ausgabe " + ii);
        }
        
        int iZaehler = 1;
        
        while(iZaehler < 10) {
            System.out.println("While Ausgabe " + iZaehler);
            iZaehler = iZaehler + 2;
        }
        
        
        // Schleife vorzeitig abbrechen
        for(int ii = 0; ii < 10; ii++) {
            System.out.println("Ausgabe " + ii);
            if (ii == 4) {
                break;
            }
        }
        
        
    }
    
}
