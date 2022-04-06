package opiskelijakurssisuoritushallinta;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**Luokka määrittelee yksinkertaisen kurssin,
 * joka muodostuu kurssinId-stä, nimestä, opintopistestä ja kurssin kuvausta.
 * @author hoang
 * @version 1.0
 * Date: 30.03.2021
**/
public class Kurssi {  
    /**
     * Kurssin attribuuttit jotka vastaavat tietokantataulun sarakkeita
     */
    private final IntegerProperty kurssiId;
    private final StringProperty kurssiNimi;
    private final IntegerProperty opintopisteet;
    private final StringProperty kuvaus;
 
    /** Kurssi constructori joka saa parametreina kaikki kursin tiedot.
     */
    public Kurssi(){
    kurssiId = new SimpleIntegerProperty(this, "kurssiId");
    kurssiNimi = new SimpleStringProperty(this, "kurssiNimi");
    opintopisteet = new SimpleIntegerProperty(this, "opintopisteet");
    kuvaus = new SimpleStringProperty(this, "kuvaus");  

}
    
    /**KurssiId **/
    /**
     * KurssiId asetus
     * @return kurssiId
     */
    public IntegerProperty kurssiIdProperty() {
        return kurssiId;
    }
    
    /**
     * Metodi palauttaa kurssiId.
     * @return kurssiId.
     */
    public int getKurssiId(){return kurssiId.get();}
    
    public void setKurssiId(int newKurssiId){
        kurssiId.set(newKurssiId);
    }
    
    /**Metodi palauta kurssiNimi
     * @return kurssiNimi
     */
    public StringProperty KurssiNimiProperty() {
        return kurssiNimi;
    }
    
    /**
     * Metodi palautaa kurssiNimi
     * @return kurssiNimi 
     */
    public String getKurssiNimi(){return kurssiNimi.get();}
    
    
    /**
     * KurssiNimi asetus
     * @param newKurssiNimi uusikurssiNimi
     */
    public void setKurssiNimi(String newKurssiNimi){
      kurssiNimi.set(newKurssiNimi);
    }
    
    
    /**opintopisteet**/  
    /**
     * Metodi palauta opintopisteet
     * @return opintopisteet
     */
    public IntegerProperty opintopisteetProperty() {
        return opintopisteet;
    }
    
    /**
     * metodi palautaa 
     * @return opintopisteet
     */
    public int getOpintopisteet(){return opintopisteet.get();}
    
    /**
     * opintopisteet asetus
     * @param newOpintopisteet uusiopintopisteet
     */    
    public void setOpintopisteet(int newOpintopisteet){
       opintopisteet.set(newOpintopisteet);
    }
    
    /**kuvaus**/
    /**
     * Metodi palautaa kurssin kuvaus
     * @return kuvaus
     */
    public StringProperty kuvausProperty() {
        return kuvaus;
    }
    
    /**
     * Metodi palauta kuvaus
     * @return kuvaus.get()
     */
    public String getKuvaus(){return kuvaus.get();}
    
    /**
     * Kurssin kuvaus asetus
     * @param newKuvaus uusikuvaus
     */   
    public void setKuvaus(String newKuvaus){
        kuvaus.set(newKuvaus);
    }

   /**
    * Palauta kurssin kaikki tiedot: kurssiId, kurssin nimi, kurssin opintopisteet ja kurssin kuvaus.
    * @return String
    */
@Override
	public String toString() {
		return (kurssiId + "  " + kurssiNimi  + "  " +  opintopisteet + "  " + kuvaus + " ");
		} 



}

