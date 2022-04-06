package opiskelijakurssisuoritushallinta;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * Opiskelijan kurssin suorituksen controller 
 * @author hoang
 * @version 3.0
 * Date 11.5.2021
 */
public class OpKuSuDocumentController implements Initializable {   
    
    /**
     * Opiskelija Hallinta Tap
     */
    @FXML
    private Tab tabOpiskelija;   
    @FXML
    private Label lbOpiskelijaId;
    @FXML
    private Label lbEtunimi;
    @FXML
    private Label lbSukunimi;
    @FXML
    private Label lbEmail;
     @FXML
    private Label lbPuhelin;    
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfOpiskelijaId;
    @FXML
    private TextField txfEtunimi;
    @FXML
    private TextField txfSukunimi;
    @FXML
    private TextField txfPuhelin;
    
    //Opiskelija Table view
    @FXML
    private TableView<Opiskelija> tbvOpiskelija;
    @FXML
    private TableColumn<Opiskelija, Integer> colOpiskelijaId;
    @FXML
    private TableColumn<Opiskelija, String> colEtunimi;
    @FXML
    private TableColumn<Opiskelija, String> colSukunimi;
    @FXML
    private TableColumn<Opiskelija, String> colEmail;
    @FXML
    private TableColumn<Opiskelija, String> colPuhelin;
    @FXML
    private Button btLisaa;
    @FXML
    private Button btMuoka;
    @FXML
    private Button btPoista;
    
    /**
     * Suoritus Tap
     */
    @FXML
    private Tab tabSuoritus; 
    @FXML
    private Label lbOpiskelijaIdS;
    @FXML
    private Label lbKurssiIdS;
    @FXML
    private Label lbArvosana;
    @FXML
    private Label lbPaiva; 
    @FXML
    private TextField txfOpiskelijaIdS;
    @FXML
    private TextField txfKurssiIdS;
    @FXML
    private TextField txfArvosana;
    @FXML
    private TextField txfSuoritusPaiva;
    @FXML
    private TableView<Suoritus> tbvSuoritus;
    @FXML
    private TableColumn<Suoritus, Integer> colOpiskelijaIdS;
    @FXML
    private TableColumn<Suoritus, Integer> colKurssiIdS;
    @FXML
    private TableColumn<Suoritus, Integer> colArvosana;
    @FXML
    private TableColumn<Suoritus, Date> colSuoritusPvm;
    @FXML
    private Button btLisaSuoritus;
    @FXML
    private Button btMuokaSuoritus;
    @FXML
    private Button btPoistaSuoritus;
    
    /**
     * Kurssi Hallinta Tap
     */
    @FXML
    private Tab tabKurssi;
    //Kurssi Label
    @FXML
    private Label lbKurssiNimi;
    @FXML
    private Label lbKurssiId;
    @FXML
    private Label lbOpintopisteet;
    @FXML
    private Label lbKuvaus;   
    //Kurssin TextField
    @FXML
    private TextField txfKurssiId;
    @FXML
    private TextField txfKurssiNimi;
    @FXML
    private TextField txfOpintopisteet;
    @FXML
    private TextField txfKuvaus;   
    //Kurssin Tableview and column
    @FXML
    private TableView<Kurssi> tbvKurssi;
    @FXML
    private TableColumn<Kurssi, Integer> colKurssiId;
    @FXML
    private TableColumn<Kurssi, String> colKurssiNimi;
    @FXML
    private TableColumn<Kurssi, Integer> colOpintopisteet;
    @FXML
    private TableColumn<Kurssi, String> colKuvaus;
    //Kurssin button
    @FXML
    private Button btLisaaK;
    @FXML
    private Button btMuokaK;
    @FXML
    private Button btPoistaK;

    
    @FXML
    private TableView<OpiskelijaSuoritusTaulu> tbvOpisSuoritus;
    @FXML
    private TableColumn<OpiskelijaSuoritusTaulu, String> colEtu;
    @FXML
    private TableColumn<OpiskelijaSuoritusTaulu, String> colSuku;
    @FXML
    private TableColumn<OpiskelijaSuoritusTaulu, Integer> colArvo;
    @FXML
    private TableColumn<OpiskelijaSuoritusTaulu, Integer>colOpinto;
    @FXML
    private TableColumn<OpiskelijaSuoritusTaulu, String> colPvm;
    @FXML
    private TextField txfOpisID;
    
    
    //
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    Statement st;
    @FXML
    private TableColumn<?, ?> colKurssiN;
    @FXML
    private Label lableMessage;
    @FXML
    private Label labelPoistaOpiskelija;
    @FXML
    private Label labelPoistaKurssi;
    /**
     * Tietokanta yhteytää
     * tietokantanimi:OpiskelijaSuoristusKurssi
     * username:root
     * password:Talvi2020
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    public void Connect() throws ClassNotFoundException, SQLException{
        try {Class.forName("org.mariadb.jdbc.Driver");
        con = DriverManager.getConnection( "jdbc:mariadb://maria.westeurope.cloudapp.azure.com:3306/karelia_hoangat?user=opiskelija&password=opiskelija1");
        } catch (SQLException e) {
            System.out.println("\t>> Yhteys epäonnistu");
         }
    }
    
    /**
     * Tietokanta suljettu 
     */
    public void closeConnection(){
        if (con == null) {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println("\t>> Tietokanta yhteys suljettu");
            }
        }
    }
    
    //OPISKELIJA
    /**
     * Luoda Opiskelijat lista 
     * @return opiskelijaList
     * @throws SQLException  Manage sql exception
     * @throws java.lang.ClassNotFoundException Manage class exception
     */
    public ObservableList<Opiskelija> getOpiskelijaList() throws ClassNotFoundException, SQLException {
        
        ObservableList<Opiskelija> opiskelijaList = FXCollections.observableArrayList();
        Connect();
        String sql = "SELECT * FROM opiskelija"; 
        
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            Opiskelija opiskelija;
            while (rs.next()){
               Opiskelija opis = new Opiskelija();
               opis.setOpiskelijaId(rs.getInt("opiskelijaId"));
               opis.setEtunimi(rs.getString("etunimi"));
               opis.setSukunimi(rs.getString("sukunimi"));
               opis.setEmail(rs.getString("email"));
               opis.setPuhelin(rs.getString("puhelin"));
               
                       
               opiskelijaList.add(opis);
               closeConnection();
            }
        } catch (SQLException e){
        }
        return opiskelijaList;
    }
    
    /**
     * Luoda Opiskelija talukko joka näytetään opiskelija lista
     * @throws ClassNotFoundException Manage Class exception
     * @throws SQLException Manage sql exception
     */
    public void showOpiskelijat() throws ClassNotFoundException, SQLException{
        ObservableList<Opiskelija> list = getOpiskelijaList();
            colOpiskelijaId.setCellValueFactory(new PropertyValueFactory<>("opiskelijaId"));
            colEtunimi.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
            colSukunimi.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colPuhelin.setCellValueFactory(new PropertyValueFactory<>("puhelin"));
            
            tbvOpiskelija.setItems(list);
            closeConnection();
    }
    
     /**
     * Lisata tietokantaan opiskelijoita
     * @param event
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception 
     */
    @FXML
    private void lisaaOpiskelijaClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
         Connect();
        int opiskelijaId = Integer.parseInt(txfOpiskelijaId.getText());
        String etunimi = txfEtunimi.getText();
        String sukunimi = txfSukunimi.getText(); 
        String email = txfEmail.getText(); 
        String puhelin = txfPuhelin.getText(); 
         try {
            //Lisää opiskelija sql kysely
            pst = con.prepareStatement("INSERT INTO opiskelija (opiskelijaId, etunimi, sukunimi, email, puhelin) VALUES (?, ?, ?, ?, ?)");
            pst.setInt(1, opiskelijaId);
            pst.setString(2, etunimi);
            pst.setString(3, sukunimi);
            pst.setString(4, email);
            pst.setString(5, puhelin);
            //Suorita kysely
            int status = pst.executeUpdate(); 
            if (status == 1) {
                //Lisää onnistu hälytys
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle ("Onnistu");
                alert.setHeaderText("Opiskelija");
                alert.setContentText("Lisä opiskelija onnistui");
                alert.showAndWait();             
                System.out.println("t>> Lisätty " + txfEtunimi.getText());
                //Pävittää ja näyttää
                tbvOpiskelija.refresh();
                showOpiskelijat();
                //Tyhjätään textfield kenttä
                txfOpiskelijaIdS.setText("");
                txfKurssiIdS.setText("");
                txfArvosana.setText("");
                txfSuoritusPaiva.setText("");
                //Sulje tietokanta
                closeConnection();
      
            }else {
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle ("Ei onnistu");
                alert.setHeaderText("Opiskelija");
                alert.setContentText("Opiskelija lisä ei onnistu");
                alert.showAndWait();
            } 
        }catch(SQLException e) {
                
        }
    }
     
    /**
     * Muokata  opiskelijoiden tiedot
     * @param event
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    @FXML
    private void muokaOpiskelijaClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
        //Tietokanta yhteyttää
        Connect();
        try {
            int opiskelijaId = Integer.parseInt(txfOpiskelijaId.getText());
            String etunimi = txfEtunimi.getText();
            String sukunimi = txfSukunimi.getText(); 
            String email = txfEmail.getText(); 
            String puhelin = txfPuhelin.getText();  
            // muokka opiskelija sql kysely
            pst = con.prepareStatement("UPDATE opiskelija SET etunimi=?, sukunimi=?, email=?, puhelin =? WHERE opiskelijaId=?");
            
            pst.setString(1, etunimi);
            pst.setString(2, sukunimi);
            pst.setString(3, email);
            pst.setString(4, puhelin);
            pst.setInt(5, opiskelijaId);
            //Suorita sql kysely
            pst.executeUpdate(); 
            // Päivittää ja näyttää
            tbvOpiskelija.refresh();
            showOpiskelijat();            
            //Tyhjä textfield muokkaan jälkeen
            txfOpiskelijaId.setText("");
            txfEtunimi.setText("");
            txfSukunimi.setText("");
            txfEmail.setText("");
            txfPuhelin.setText("");
            //Onnistu hälytys
            Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle ("Onnistu");
                alert.setHeaderText("Opiskelija");
                alert.setContentText("Opiskelija muokaaminen onnistu!");
                alert.showAndWait();  
              closeConnection();
        }catch(SQLException e) {   
            throw e;
        }  
    }

    /**
     * Poista tietokannasta opiskelijoita
     * @param event
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    @FXML
    private void poistaOpiskelijaClicked(ActionEvent event) throws SQLException, ClassNotFoundException, Exception {
        //Poista opas
        labelPoistaOpiskelija.setText("Opiskelijan poistaminen: syöte opiskelijaID");
        //Tietokanta yhteytää 
        Connect();
        try {
            //Poista opiskelija sql kysely
            pst = con.prepareStatement("DELETE FROM opiskelija WHERE opiskelijaId = ?");
            pst.setInt(1, Integer.parseInt(txfOpiskelijaId.getText()));
            //Suorita kysely              
            rs = pst.executeQuery();
           
            //Onnistu hälytys
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle ("Onnistu");
                alert.setHeaderText("Opiskelija");
                alert.setContentText("Opiskelija poistaminen onnistu");
                alert.showAndWait();
                //Tyhjätään textfield kenttä
                txfOpiskelijaIdS.setText("");
                txfKurssiIdS.setText("");
                txfArvosana.setText("");
                txfSuoritusPaiva.setText("");
            if (rs == null) { throw new Exception ("Opiskelija poistaminen ei onnistu");}
         } catch (SQLException e) {throw e;}
        showOpiskelijat();
        closeConnection();
    }
    //OPINTOSUORITUS
    /**
     * Luoda opinto suoritus lista 
     * @return opiskelijaList
     * @throws SQLException Manage sql exception
     * @throws java.lang.ClassNotFoundException  Manage class exception
     * @throws java.text.ParseException Manage ParseException exception
     */
    public ObservableList<Suoritus> getSuoritusList() throws ClassNotFoundException, SQLException, ParseException{
        ObservableList<Suoritus> suoritusList = FXCollections.observableArrayList();
        Connect();
        //Näytetaan suoritus taulut tietdot Sql kysely
        String sql = "SELECT opiskelijaId,kurssiId,arvosana,suoritusPvm FROM suoritus";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
               Suoritus suo = new Suoritus();
               suo.setOpiskelijaId(rs.getInt("opiskelijaId"));
               suo.setKurssiId(rs.getInt("kurssiId"));
               suo.setArvosana(rs.getInt("arvosana"));
               suo.setSuoritusPvm(rs.getString("suoritusPvm"));
               //Lisää suoritus olio listalle 
               suoritusList.add(suo);
               closeConnection();
            }
        } catch (SQLException e){
        }
        return suoritusList;
    }
    
    /**
     * Luoda suoritus talukko joka näytetään opiskelija lista
     * @throws ClassNotFoundException  Manage class exception
     * @throws SQLException Manage sql exception
     * @throws java.text.ParseException Manage ParseException exception
     */
    public void showSuoritukset() throws ClassNotFoundException, SQLException, ParseException{
        ObservableList<Suoritus> list = getSuoritusList();
            colOpiskelijaIdS.setCellValueFactory(new PropertyValueFactory<>("opiskelijaId"));
            colKurssiIdS.setCellValueFactory(new PropertyValueFactory<>("kurssiId"));
            colArvosana.setCellValueFactory(new PropertyValueFactory<>("arvosana"));
            colSuoritusPvm.setCellValueFactory(new PropertyValueFactory<>("suoritusPvm"));
            //Lisää tiedot suoritus talulle
            tbvSuoritus.setItems(list);
            closeConnection();
    }
    
    /**
     * Lisata tietokantaan suoritusta
     * @param event
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    @FXML
    private void lisaSuoritusClicked(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
        Connect();
        int opiskelijaId = Integer.parseInt(txfOpiskelijaIdS.getText());
        int kurssiId = Integer.parseInt(txfKurssiIdS.getText());
        int arvosana = Integer.parseInt(txfArvosana.getText());
        String suoritusPvm = txfSuoritusPaiva.getText();
    
         try {
            //Lisää suoritus sql kysely
            pst = con.prepareStatement("INSERT INTO suoritus (opiskelijaId,kurssiId, arvosana, suoritusPvm) VALUES (?, ?, ?, ?)");
            pst.setInt(1, opiskelijaId);
            pst.setInt(2, kurssiId);
            pst.setInt(3, arvosana);
            pst.setString(4, suoritusPvm);
            int status = pst.executeUpdate();
            //Lisää suoritus onistu hälytys
            if (status == 1) {
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle ("Onnistu");
                alert.setHeaderText("Suoritukset");
                alert.setContentText("Opiskelijan kurrsin suorituksen lisaaminen onnistui");
                alert.showAndWait();
                //Päivittää ja näyttää
                tbvSuoritus.refresh();
                showSuoritukset();
                //Tyhjätään textfield kenttä
                txfOpiskelijaIdS.setText("");
                txfKurssiIdS.setText("");
                txfArvosana.setText("");
                txfSuoritusPaiva.setText("");
                //Sulje yhteys
                closeConnection();
            }else {
                //Lisää ei onnistu hälytys
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle ("Ei onnistu");
                alert.setHeaderText("Suoritukset");
                alert.setContentText("Suoritukset lisä ei onnistu");
                alert.showAndWait();
            }  
        }catch(SQLException e){        
        }
    }
    
    /**
     * Muokata suorituksen tiedot
     * @param event
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    @FXML
    private void muokaSuoritusClicked(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException {
       //Tietokanta yhteytää
        Connect();
        try {
            int opiskelijaId = Integer.parseInt(txfOpiskelijaIdS.getText());
            int kurssiId = Integer.parseInt(txfKurssiIdS.getText());
            int arvosana = Integer.parseInt(txfArvosana.getText());
            String suoritusPvm = txfSuoritusPaiva.getText();
            //Muokka suoritus sql kysely
            pst = con.prepareStatement("UPDATE suoritus SET arvosana=?, suoritusPvm=? WHERE opiskelijaId=? AND kurssiId=? ");
            pst.setInt(1, arvosana);
            pst.setString(2,suoritusPvm);
            pst.setInt(2, opiskelijaId);
            pst.setInt(3, kurssiId);
            //Suorita kysely
            pst.executeUpdate();
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle ("Onnistu");
                alert.setHeaderText("Suoritukset");
                alert.setContentText("Opiskelijan kurrsin suorituksen muokaaminen onnistu");
                alert.showAndWait();
                //Tyhjätään textfield kenttä
                txfOpiskelijaIdS.setText("");
                txfKurssiIdS.setText("");
                txfArvosana.setText("");
                txfSuoritusPaiva.setText("");
        }catch(SQLException e) {   
            throw e;
        } 
        tbvSuoritus.refresh();
        showSuoritukset(); 
        closeConnection();
    }

    /**
     * Poista suorituksen tiedot tietokannasta
     * @param event
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    @FXML
    private void poistaSuoritusClicked(ActionEvent event) throws ClassNotFoundException, SQLException, ParseException, Exception {
        //Näyttää viestin poista opas
        lableMessage.setText("Poista suoritus: syöte opiskelijaID ja kurssiID");
        //Tietokanta yhteytää
        Connect();
        int opiskelijaId = Integer.parseInt(txfOpiskelijaIdS.getText());
        int kurssiId = Integer.parseInt(txfKurssiIdS.getText());
         
        try {
            //Poista suoritus sql kysely
            pst = con.prepareStatement("DELETE FROM suoritus WHERE opiskelijaId=? AND kurssiId=?");
            pst.setInt(1, opiskelijaId);
            pst.setInt(2, kurssiId);
            //Suorita kysely             
            rs = pst.executeQuery();
            //Poista onnistu hälytys
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle ("Onnistu");
                alert.setHeaderText("Suoritukset");
                alert.setContentText("Opiskelijan kurrsin suorituksen poistaminen onnistu");
                alert.showAndWait();  
            //Tyhjätään textfield kenttä
                txfOpiskelijaIdS.setText("");
                txfKurssiIdS.setText("");
                txfArvosana.setText("");
                txfSuoritusPaiva.setText("");    
            //Poista ei onnistu kysely
            if (rs == null) { throw new Exception ("Opiskelija poistaminen ei onnistu");}
         } catch (SQLException e) {throw e;}
        
        showSuoritukset();
        closeConnection();
    }

    //KURSSI
    
    /**
     * Luoda kurssit lista 
     * @return opiskelijaList
     * @throws SQLException Manage sql exception
     * @throws java.lang.ClassNotFoundException Manage Class Not Found exception
     */
    public ObservableList<Kurssi> getKurssiList() throws ClassNotFoundException, SQLException{
      //Tietokanta yhteytää
        Connect(); 
       ObservableList<Kurssi> kurssiList = FXCollections.observableArrayList();
       String sql = "SELECT kurssiId,kurssiNimi,opintopisteet,kuvaus FROM kurssi";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()){
               Kurssi kur = new Kurssi();
               kur.setKurssiId(rs.getInt("kurssiId"));
               kur.setKurssiNimi(rs.getString("kurssiNimi"));
               kur.setOpintopisteet(rs.getInt("opintopisteet"));
               kur.setKuvaus(rs.getString("kuvaus"));
               //Lisää kurssi olio listalle       
               kurssiList.add(kur);
               closeConnection();
            }
        } catch (SQLException e){
        }
        return kurssiList;
    }
   
    /**
     * Luoda kurssi talukko joka näytetään kurssi tiedot
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    public void showKurssit() throws ClassNotFoundException, SQLException{
        ObservableList<Kurssi> list = getKurssiList();
            //Aseta talun saraket
            colKurssiId.setCellValueFactory(new PropertyValueFactory<>("kurssiId"));
            colKurssiNimi.setCellValueFactory(new PropertyValueFactory<>("kurssiNimi"));
            colOpintopisteet.setCellValueFactory(new PropertyValueFactory<>("opintopisteet"));
            colKuvaus.setCellValueFactory(new PropertyValueFactory<>("kuvaus"));
            tbvKurssi.setItems(list);
            closeConnection();
    }
    
    /**
     * Lisata tietokantaan kurssin tiedot
     * @param event
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    @FXML
    private void lisaaKurssiClicked(ActionEvent event) throws SQLException, ClassNotFoundException { 
        //Tietokanta yhteyttää
        Connect();
        int kurssiId = Integer.parseInt(txfKurssiId.getText());
        String kurssiNimi = txfKurssiNimi.getText();
        int opintopisteet = Integer.parseInt(txfOpintopisteet.getText());
        String kuvaus = txfKuvaus.getText();  

         try {
            //Muokka kurssi tiedot sql kysely
            pst = con.prepareStatement("INSERT INTO kurssi ( kurssiId, kurssiNimi, opintopisteet, kuvaus ) VALUES (?, ?, ?, ?)");
            pst.setInt(1, kurssiId);
            pst.setString(2, kurssiNimi);
            pst.setInt(3, opintopisteet);
            pst.setString(4, kuvaus);

            int status = pst.executeUpdate();
            if (status == 1) {
                Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle ("Onnistu");
                alert.setHeaderText("Kurssi");
                alert.setContentText("Lisä kurssi onnistui");
                alert.showAndWait();
                
                //Pävittää ja näyttää
                tbvKurssi.refresh();
                showKurssit();
                //Tyhjennetaan textfield
                txfKurssiId.setText("");
                txfKurssiNimi.setText("");
                txfOpintopisteet.setText("");
                txfKuvaus.setText("");
                closeConnection();
            }else {
                //Ei onistu hälytys
                Alert alert = new Alert (Alert.AlertType.ERROR);
                alert.setTitle ("Ei onnistu");
                alert.setHeaderText("Kurssi");
                alert.setContentText("Kurssi lisä ei onnistu");
                alert.showAndWait();
            }  
        }catch(SQLException e) {
                
        }
    }

    /**
     * Muokata tietokantaan kurssin tiedot
     * @param event
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    @FXML
    private void muokaKurssiClicked(ActionEvent event) throws ClassNotFoundException, SQLException {
       //Tietokanta yhteytää
        Connect();
        try {
            int kurssiId = Integer.parseInt(txfKurssiId.getText());
            String kurssiNimi = txfKurssiNimi.getText();
            int opintopisteet = Integer.parseInt(txfOpintopisteet.getText());
            String kuvaus = txfKuvaus.getText();  
            //Muokka kurssi tiedot sql kysely
            pst = con.prepareStatement("UPDATE kurssi SET kurssiNimi= ?, opintopisteet= ?, kuvaus= ? WHERE kurssiId= ?");
            pst.setString(1, kurssiNimi);
            pst.setInt(2, opintopisteet);
            pst.setString(3, kuvaus);
            pst.setInt(4, kurssiId);
            //Suorita sql kysely
            pst.executeUpdate();
            tbvKurssi.refresh();
            showKurssit();
            //Tyhjennetaan textfield kenttä
            txfKurssiId.setText("");
            txfKurssiNimi.setText("");
            txfOpintopisteet.setText("");
            txfKuvaus.setText("");
            //Kurssi muokka onnistu hälytys
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle ("Onnistu");
                alert.setHeaderText("Kurssi");
                alert.setContentText("Kurssi muokaaminen onnistu");
                alert.showAndWait();
             //Tyhjätään textfield kenttä
                txfOpiskelijaIdS.setText("");
                txfKurssiIdS.setText("");
                txfArvosana.setText("");
                txfSuoritusPaiva.setText("");
            //Sulje tietokanta
            closeConnection();
        }catch(SQLException e) {   
            throw e;
        } 
    }
    
    /**
     * Poista tietokantaan kurssin tiedot
     * @param event
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    @FXML
    private void poistaKurssiClicked(ActionEvent event) throws Exception {
        labelPoistaKurssi.setText("Kurssin poistaminen: syöte kurssiID");
        //Tietokanta yhteytää
        Connect(); 
        try {
            //Poista kurssi sql kysely
            pst = con.prepareStatement("DELETE FROM kurssi WHERE kurssiId= ?");
            pst.setInt(1, Integer.parseInt(txfKurssiId.getText()));
            //Suorita kysely              
            rs = pst.executeQuery();
            //Onistu hälytys
             Alert alert = new Alert (Alert.AlertType.INFORMATION);
                alert.setTitle ("Onnistu");
                alert.setHeaderText("Kurssi");
                alert.setContentText("Kurssi poistaminen onnistu");
                alert.showAndWait();
            //Tyhjätään textfield kenttä
                txfOpiskelijaIdS.setText("");
                txfKurssiIdS.setText("");
                txfArvosana.setText("");
                txfSuoritusPaiva.setText("");
             showKurssit();    
            if (rs == null) { throw new Exception ("Kurssi poistaminen ei onnistu");}
         } catch (SQLException e) {throw e;}
        //Tietokanta sulje
        closeConnection();
    }
    
    /**Luoda OpiskelijaSuoritusTaulu ObservableList 
     * @return tauluList
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    public ObservableList<OpiskelijaSuoritusTaulu> getTaluList() throws ClassNotFoundException, SQLException {
        ObservableList<OpiskelijaSuoritusTaulu> tauluList = FXCollections.observableArrayList();
        Connect();
        try {
            st = con.createStatement();
            //Näyttä OpiskelijaSuoritusTaulu taulut tiedot sql kysely
            rs = st.executeQuery(
                    " SELECT opiskelija.etunimi, opiskelija.sukunimi, suoritus.arvosana,kurssi.kurssiNimi, kurssi.opintopisteet, suoritus.suoritusPvm"
                        + " FROM opiskelija, kurssi, suoritus"
                        + " WHERE suoritus.opiskelijaId = opiskelija.opiskelijaId AND suoritus.kurssiId = kurssi.kurssiId "
                        + " ORDER BY opiskelija.etunimi"
                    );
            while (rs.next()){
                OpiskelijaSuoritusTaulu opisSuoritus = new  OpiskelijaSuoritusTaulu();
                opisSuoritus.setEtunimi(rs.getString("etunimi"));
                opisSuoritus.setSukunimi(rs.getString("sukunimi"));
                opisSuoritus.setArvosana(rs.getInt("arvosana"));
                opisSuoritus.setKurssiNimi(rs.getString("kurssiNimi"));
                opisSuoritus.setOpintopisteet(rs.getInt("opintopisteet"));
                opisSuoritus.setSuoritusPvm(rs.getString("suoritusPvm"));
                tauluList.add(opisSuoritus);
                closeConnection();
            }
        } catch (SQLException e){
            }
             return tauluList;
    }
    /**
     * Näytetään taulun lista tableview:lle
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    public void showOpiskelijaSuoritusTaulu() throws ClassNotFoundException, SQLException{
        ObservableList<OpiskelijaSuoritusTaulu> list =  getTaluList();
            colEtu.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
            colSuku.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));
            colArvo.setCellValueFactory(new PropertyValueFactory<>("arvosana"));
            colKurssiN.setCellValueFactory(new PropertyValueFactory<>("kurssiNimi"));
            colOpinto.setCellValueFactory(new PropertyValueFactory<>("opintopisteet"));
            colPvm.setCellValueFactory(new PropertyValueFactory<>("suoritusPvm"));   
            tbvOpisSuoritus.setItems(list);
            closeConnection();
    }
  
    /**
     * Haku suoritus opiskelijaID:lla
     * @param event
     * @throws ClassNotFoundException Manage class exception
     * @throws SQLException Manage sql exception
     */
    @FXML
    private void InputOpisID(KeyEvent event) throws ClassNotFoundException, SQLException {
         Connect();
         //Luoda varaus lista
         ObservableList<OpiskelijaSuoritusTaulu> tauluList = FXCollections.observableArrayList();
         if(txfOpisID.getText().equals("")){
                showOpiskelijaSuoritusTaulu();
                
         } else {
            //Haku suoritus sql kysely
            pst = con.prepareStatement(
                    " SELECT opiskelija.etunimi, opiskelija.sukunimi, suoritus.arvosana,kurssi.kurssiNimi, kurssi.opintopisteet, suoritus.suoritusPvm"
                        + " FROM opiskelija, kurssi, suoritus"
                        + " WHERE suoritus.opiskelijaId = opiskelija.opiskelijaId AND suoritus.kurssiId = kurssi.kurssiId "
                        + " AND suoritus.opiskelijaId LIKE '%" + txfOpisID.getText()+ "%' "   
            );
            rs = pst.executeQuery();
            while(rs.next()){
                OpiskelijaSuoritusTaulu opisSuoritus = new  OpiskelijaSuoritusTaulu();
                opisSuoritus.setEtunimi(rs.getString("etunimi"));
                opisSuoritus.setSukunimi(rs.getString("sukunimi"));
                opisSuoritus.setArvosana(rs.getInt("arvosana"));
                opisSuoritus.setKurssiNimi(rs.getString("kurssiNimi"));
                opisSuoritus.setOpintopisteet(rs.getInt("opintopisteet"));
                opisSuoritus.setSuoritusPvm(rs.getString("suoritusPvm"));
                tauluList.add(opisSuoritus);
                colEtu.setCellValueFactory(new PropertyValueFactory<>("etunimi"));
                colSuku.setCellValueFactory(new PropertyValueFactory<>("sukunimi"));
                colArvo.setCellValueFactory(new PropertyValueFactory<>("arvosana"));
                colKurssiN.setCellValueFactory(new PropertyValueFactory<>("kurssiNimi"));
                colOpinto.setCellValueFactory(new PropertyValueFactory<>("opintopisteet"));
                colPvm.setCellValueFactory(new PropertyValueFactory<>("suoritusPvm"));
            
                tbvOpisSuoritus.setItems(tauluList);
                closeConnection();   
            }  
         }           
    }
    private void positaMouseClicked(MouseEvent event) {
        lableMessage.setText("Poista suoritus: syöte opiskelijaID ja kurssiID");
    }

    /**
     * Initializes the controller class.
     * @param url URL
     * @param rb RB
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //Tietokanta yhteytää
            Connect();
            //Kurssi tableview
            showKurssit();
            //Opiskelijat tableview
            showOpiskelijat(); 
            //Suorituksettableview
            showSuoritukset();
            //Opiskelijan suorituksettableview
            showOpiskelijaSuoritusTaulu();
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(OpKuSuDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    

   
    
}
