package opiskelijakurssisuoritushallinta;

import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static opiskelijakurssisuoritushallinta.OpisDatabase.addCourse;
import static opiskelijakurssisuoritushallinta.OpisDatabase.addGrade;
import static opiskelijakurssisuoritushallinta.OpisDatabase.addName;
import static opiskelijakurssisuoritushallinta.OpisDatabase.closeConnection;
import static opiskelijakurssisuoritushallinta.OpisDatabase.createDatabase;
import static opiskelijakurssisuoritushallinta.OpisDatabase.createTable;
import static opiskelijakurssisuoritushallinta.OpisDatabase.selectAll;
import static opiskelijakurssisuoritushallinta.OpisDatabase.selectName;

/**Main luokka
 * Luoda taulut
 * Lisätä taluun opiskelijoita, kurssia ja suoritusta
 * @author hoang
 * @version 3.0
 * Date 11.5.2021
 */
public class OpiskelijaKurssiSuoritusHallinta extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("OpKuSuDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        
        stage.setTitle("Opiskelijoiden kurssisuoritusten hallintasovellus");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     * @throws java.sql.SQLException Manage exception
     */
    public static void main(String[] args) throws SQLException {
        Connection conn = OpisDatabase.openConnection(
                "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:"
                 +"3306?user=opiskelija&password=opiskelija1"
        );
        
        createDatabase(conn, "karelia_hoangat");
        
        /**Create an 'opiskelija' table*/
        createTable(conn, 
                "CREATE TABLE opiskelija ("
                        + "opiskelijaId INT NOT NULL PRIMARY KEY AUTO_INCREMENT," 
                        + "sukunimi VARCHAR (20),"
                        + "etunimi VARCHAR (40),"
                        + "email VARCHAR (50),"
                        + "puhelin VARCHAR (12))"        
                );
        
        
        /**Create a 'kurssi' table*/
        createTable(conn, 
                "CREATE TABLE kurssi ("
                        + "kurssiId INT NOT NULL PRIMARY KEY AUTO_INCREMENT," 
                        + "kurssiNimi VARCHAR (50),"
                        + "opintopisteet INT,"
                        + "kuvaus VARCHAR (500))"  
                );
        
        /**Create a 'suoritus' table*/
        createTable(conn, 
                "CREATE TABLE suoritus ("
                        + "opiskelijaId INT," 
                        + "kurssiId INT,"
                        + "arvosana INT,"
                        + "suoritusPvm DATE," 
                        + "PRIMARY KEY (opiskelijaId, kurssiId),"
                        + "FOREIGN KEY (kurssiId) REFERENCES kurssi (kurssiId) ON DELETE CASCADE,"
                        + "FOREIGN KEY (opiskelijaId) REFERENCES opiskelija (opiskelijaId) ON DELETE CASCADE)"
                );
        
       
        /**add student into 'opiskleija' table*/
	addName(conn, 210 , "Mononen", "Henri", "henri.m@hotmail.com", "010333444");
	addName(conn, 211 , "Pesonen", "Jenny", "jenny.p@hotmail.com", "010333555");	
	addName(conn, 212 , "Päivinen", "Anna", "anna.p@hotmail.com", "010353414");
	
        /**add courses into 'kurssi' table*/
        addCourse(conn, 6001, "Olio-ohjelmointi", 5 , "Java ojelmointi kieli");
        addCourse(conn, 6002, "Web/Ohjelmointi", 5 , "HTML, CSS, JavaScript ojelmointi kieli");
        OpisDatabase.addCourse(conn, 6003, "Logiikka", 3 , " tietojenkäsittelyn logiikka");
        
        /**add grade into 'suoritus' table*/
        addGrade(conn, 210, 6001, 5, "18.4.2020");
        addGrade(conn, 211, 6001, 4, "18.4.2021");
        addGrade(conn, 212, 6002, 3, "10.3.2021");
        addGrade(conn, 211, 6003, 5, "1.12.2020");
        
        selectName(conn);
        selectAll(conn);
        closeConnection(conn);
    
        launch(args);
    }
    
}
