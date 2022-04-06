package opiskelijakurssisuoritushallinta;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Opiskejian kurssin suorituksen tietokanta luominen 
 * @author hoang
 * @version 2.0
 * @since  20-4-2021
 */
public class OpisDatabase {
    
    /**Database connection*/
    static Connection openConnection (String connString)throws SQLException {
        Connection conn = DriverManager.getConnection(connString);
        System.out.println("\t>> Yhteys ok");
        return conn;
    }
    
    /**Database disconnection*/
    static void closeConnection(Connection c)throws SQLException {
        if(c != null) {
            c.close();
        }
        System.out.println("\t>> Tietokanta yhteys suljettu");
    }
    
    /**Drop, Create and Use a database
     * @param c connection 
     * @param db database
     * @throws java.sql.SQLException Manage exception
     */
    public static void createDatabase(Connection c, String db) throws SQLException{
        Statement stmt = c.createStatement();
        stmt.executeQuery("DROP DATABASE IF EXISTS " + db);
        System.out.println("\t>> Tietokanta " + db + " tuhottu");
        
        stmt.executeQuery("CREATE DATABASE " + db);
        System.out.println("\t>> Tietokanta " + db + " luotu");
        
        stmt.executeQuery("USE " + db);
        System.out.println("\t>> Käytetään tietokanta " + db);
    }
    
    /**Create a table method */
    static void createTable(Connection c, String sql)throws SQLException {
        Statement stmt = c.createStatement();
        stmt.executeQuery(sql);
        System.out.println("\t>> Taulu luotu");
        
    }
    
    /**Add information to the 'opiskelija' table by sql query*/
    static void addName(Connection c, int opiskelijaId, String sukunimi, 
           String etunimi, String email, String puhelin)throws SQLException {
    
        PreparedStatement ps = c.prepareStatement(
        "INSERT INTO opiskelija (opiskelijaId, sukunimi, etunimi, email, puhelin)"
         + "VALUES(?, ?, ?, ?, ?)"
        );
        
        ps.setInt(1, opiskelijaId);
        ps.setString(2, sukunimi);
        ps.setString(3, etunimi);
        ps.setString(4, email);
        ps.setString(5, puhelin);
        
        ps.execute();
        
        System.out.println("\t>> Lisätty " + opiskelijaId +" "+ sukunimi +" "+ etunimi);
        
    }
    
    /**Add information to the 'kurssi' table by sql query*/
    static void addCourse(Connection c, int kurssiId, String kurssiNimi, 
           int opintopisteet, String kuvaus)throws SQLException {
    
        PreparedStatement ps = c.prepareStatement(
        "INSERT INTO kurssi (kurssiId, kurssiNimi, opintopisteet, kuvaus)"
         + "VALUES(?, ?, ?, ?)"
        );
        
        ps.setInt(1, kurssiId);
        ps.setString(2, kurssiNimi);
        ps.setInt(3, opintopisteet);
        ps.setString(4, kuvaus);
        ps.execute();
        
        System.out.println("\t>> Lisätty " + kurssiId +" "+ kurssiNimi);
    }
    
    /**Add information to the 'suoritus' table by sql query*/
    static void addGrade(Connection c, int opiskelijaId, int kurssiId, int arvosana, String suoritusPvm)throws SQLException {
    
        PreparedStatement ps = c.prepareStatement(
        "INSERT INTO suoritus (opiskelijaId, kurssiId, arvosana, suoritusPvm )"
         + "VALUES(?, ?, ?, STR_TO_DATE(?, '%d.%m.%Y'))"
        );
        
        
        ps.setInt(1, opiskelijaId);
        ps.setInt(2, kurssiId);
        ps.setInt(3, arvosana);
        ps.setString(4, suoritusPvm);
        ps.execute();
        
        System.out.println("\t>> Lisätty " + opiskelijaId +" "+ kurssiId + " " + arvosana);
    }
    
    /**Show all information from 'opiskelija' table by sql query*/
    static void selectName(Connection c)throws SQLException{
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT opiskelijaId, sukunimi, etunimi, email, puhelin FROM opiskelija ORDER BY etunimi"             
                 );  
        
        System.out.println("\nOpiskelija lista: \n ==================");
        while (rs.next()) {
            System.out.println (
            "[" + rs.getInt("opiskelijaId") + "]"
              + rs.getString("sukunimi") + " "
              + rs.getString("etunimi") + " "
              + rs.getString("email") + " "  
              + rs.getString("puhelin")
            );
        }

    }
    static void selectAll(Connection c)throws SQLException{
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(
                        "SELECT opiskelija.etunimi, opiskelija.sukunimi, suoritus.arvosana,kurssi.kurssiNimi, kurssi.opintopisteet, suoritus.suoritusPvm"
                        + " FROM opiskelija, kurssi, suoritus"
                        + " WHERE suoritus.opiskelijaId = opiskelija.opiskelijaId AND suoritus.kurssiId = kurssi.kurssiId "
                        + " ORDER BY opiskelija.etunimi"
                        );
        System.out.println("\nOpiskelija Suoritus lista: \n ==================");
        while (rs.next()) {
            System.out.println (
            "[" + rs.getString("etunimi") + "]"
              + rs.getString("sukunimi") + " "
              + rs.getInt("arvosana") + " "
              + rs.getString("kurssiNimi") + " "  
              + rs.getInt("opintopisteet")+ " "
              + rs.getString("suoritusPvm")
            );
            }
    }
}
