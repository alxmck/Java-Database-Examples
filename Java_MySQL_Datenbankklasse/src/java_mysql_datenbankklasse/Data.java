package java_mysql_datenbankklasse;

/**
 *
 * @author Alexander Mack
 */

import java.sql.*;

public class Data {
    
    private static String STR_DATABASE = "DATABASE";
    private static String STR_TABLE = "testdaten";
    private static String STR_COL_ID = "id";
    private static String STR_COL_NAME = "name";
    
    private int miID = 0;
    private String msName = "";
    
    public Data() {
    }
    
    private Data(ResultSet oResult) throws Exception {
        setID(oResult.getInt(STR_COL_ID));
        setName(oResult.getString(STR_COL_NAME));
    }
    
    private void setID(int iID) {
        miID = iID;
    }
    
    public int getID() {
        return miID;
    }
    
    public void setName(String sName) {
        msName = sName;
    }
    
    public String getName() {
        return msName;
    }
    
    public static Data getByID(int iID) throws Exception {
        
        Connection oConnection = DriverManager.getConnection("jdbc:mysql://HOST/" + STR_DATABASE + "?" + 
                "user=USER&password=PASSWORD");

        PreparedStatement oPrepStatement = oConnection.prepareStatement("SELECT * FROM " + STR_DATABASE + "." + STR_TABLE + " WHERE `" + STR_COL_ID + "` = ?");
        oPrepStatement.setInt(1, iID); 

        ResultSet oResult = oPrepStatement.executeQuery();

        Data oData = new Data(); // empty object
        
        while (oResult.next()) {
            oData = new Data(oResult);
            break;
        }

        oResult.close();
        oPrepStatement.close();
        oConnection.close();
        
        return oData;
        
    }
    
    public static Data getByName(String sName) throws Exception {
        
        Connection oConnection = DriverManager.getConnection("jdbc:mysql://HOST/" + STR_DATABASE + "?" + 
                "user=USER&password=PASSWORD");

        PreparedStatement oPrepStatement = oConnection.prepareStatement("SELECT * FROM " + STR_DATABASE + "." + STR_TABLE + " WHERE `" + STR_COL_NAME + "` = ?");
        oPrepStatement.setString(1, sName); 

        ResultSet oResult = oPrepStatement.executeQuery();

        Data oData = new Data(); // empty object
        
        while (oResult.next()) {
            oData = new Data(oResult);
            break;
        }

        oResult.close();
        oPrepStatement.close();
        oConnection.close();
        
        return oData;
        
    }
    
    public void store() throws Exception {
        
        try {
        
            // create connection
            Connection oConnection = DriverManager.getConnection("jdbc:mysql://HOST/" + STR_DATABASE + "?" + 
                    "user=USER&password=PASSWORD");

            if (miID == 0) {
            
                // insert cake into database
                PreparedStatement oPrepStatement = oConnection.prepareStatement("INSERT INTO " + STR_DATABASE + "." + STR_TABLE + " (`" + STR_COL_NAME + "`) VALUES (?)",
                                                                                    Statement.RETURN_GENERATED_KEYS);

                // set parameters
                oPrepStatement.setString(1, msName);
                oPrepStatement.executeUpdate();

                ResultSet oResultSet = oPrepStatement.getGeneratedKeys();
                if(oResultSet.next())
                {
                    miID =oResultSet.getInt(1);
                }
                
                oResultSet.close();
                oPrepStatement.close();
            
            } else {
                
                // insert cake into database
                PreparedStatement oPrepStatement = oConnection.prepareStatement("UPDATE " + STR_DATABASE + "." + STR_TABLE + " SET `" + STR_COL_NAME + "` = ? WHERE `" + STR_COL_ID + "` = ?");

                // set parameters
                oPrepStatement.setString(1, msName);
                oPrepStatement.setInt(2, miID);
                oPrepStatement.executeUpdate();
                
                oPrepStatement.close();
                
            }
            
        } catch (Exception e) {
            throw new Exception("was not able to store cake! error: " + e.getMessage());
        }
    }
    
}
