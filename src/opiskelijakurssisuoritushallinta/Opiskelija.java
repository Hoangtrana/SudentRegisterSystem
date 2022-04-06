package opiskelijakurssisuoritushallinta;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**Luokka määrittelee yksinkertaisen opiskelijan tiedot,
 * joka muodostuu opiskelijan id-stä, etunimista, sukunimista, sähköpostista,puhelimeesta
 * @author hoang
 * @version 1.0
 * Date 18.03.2021  aloitus päivä
 */
public class Opiskelija {
    
    /**
     * Opiskelijan attribuuttit jotka vastaavat tietokantataulun sarakkeita
     */
    private final IntegerProperty opiskelijaId;
    private final StringProperty sukunimi;
    private final StringProperty etunimi; 
    private final StringProperty email;
    private final StringProperty puhelin;
 
    /**
     * Opiskelija constructori joka saa parametreina kaikki opiskelijan tiedot.
     */
    public Opiskelija(){
    opiskelijaId = new SimpleIntegerProperty(this, "opiskelijaId");
    etunimi = new SimpleStringProperty(this, "etunimi");
    sukunimi = new SimpleStringProperty(this, "sukunimi");
    email = new SimpleStringProperty(this, "email");  
    puhelin = new SimpleStringProperty(this, "puhelin"); 
   
}
    
    /**
     * Metodi palauta opiskelijaId
     * @return opiskelijaId
     */
    public IntegerProperty OpiskelijaIdProperty() {
        return opiskelijaId;
    }
    
    /**
     * Metodi palauta opiskelijaId
     * @return opiskelijaId
     */
    public int getOpiskelijaId(){
        return opiskelijaId.get();}
    
    /**
     * OpiskelijaId asetus
     * @param newOpiskelijaId uusi opiskelijaId
     */
    public void setOpiskelijaId(int newOpiskelijaId){
        opiskelijaId.set(newOpiskelijaId);
    }

    /**
     * Metodi palauta opiskelija etunimi
     * @return etunimi
     */
    public StringProperty EtunimiProperty() {
        return etunimi;
    }
    
   /**
    * Metodi palauta opiskelija etunimi
    * @return etunimi
    */
    public String getEtunimi(){return etunimi.get();}
    
    /**
     * Opiskelijan etunimi asetus
     * @param newEtunimi uusiEtunimi
     */
    public void setEtunimi(String newEtunimi){
      etunimi.set(newEtunimi);
    }
    
    
    /**
     * Metodi palauta opiskelija sukunimi
     * @return sukunimi
     */

    public StringProperty SukunimiProperty() {
        return sukunimi;
    }
    
   /**
    * Metodi palauta opiskelija sukunimi
    * @return sukunimi
    */    
    public String getSukunimi(){
        return sukunimi.get();}
    
    /**
     * Opiskelija sukunimi asetus
     * @param newSukunimi uusiSukunimi
     */    
    public void setSukunimi(String newSukunimi){
       sukunimi.set(newSukunimi);
    }   
    
    /**
     * Metodi palauta opiskelija email
     * @return email
     */
    public StringProperty EmailProperty() {
        return email;
    }
    
    /**
     * Metodi palauta opiskelija email
     * @return email
     */ 
    public String getEmail(){
        return email.get();}
    
    /**
     * Opiskelija Emai asetus
     * @param newEmail uusi email
     */    
    public void setEmail(String newEmail){
        email.set(newEmail);
    }
    

    /**
     * Meotodi palauta opiskelijan puhelin numero
     * @return puhelin
     */
    public StringProperty PuhelinProperty() {
        return puhelin;
    }
    
    /**
     * Metodi palauta opiskelijan puhelin numero
     * @return puhelin
     */
    public String getPuhelin(){return puhelin.get();}
    
    /**
     * Opiskelijan puhelin numero asetus
     * @param newPuhelin uusipuhelin numero
     */
    public void setPuhelin(String newPuhelin){
        puhelin.set(newPuhelin);
    }
 
    /**
     * Metodi palauta opiskelija kaikki tiedot: opsikelijaid, etunimi, sukunimi, email, puhelin 
     * @return opiskelija kaikki tiedot
     */
    @Override
    public String toString() {
		return (opiskelijaId + "  " + etunimi  + "  " +  sukunimi + "  " + email + " " + puhelin + "");
		} 

}
