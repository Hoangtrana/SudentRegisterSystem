package opiskelijakurssisuoritushallinta;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/** Luokka määrittelee yksinkertaisen opiskelijan kurssin suoritus tiedot,
 * @author hoang
 * @version 3.0
 * Date 11.05.2021
 */
public class OpiskelijaSuoritusTaulu {
    /**
     * OpiskelijaSuoritusTaulun attribuuttit jotka vastaavat tietokantataulun sarakkeita
     */
    private final StringProperty etunimi;
    private final StringProperty sukunimi;
    private final IntegerProperty arvosana;
    private final StringProperty kurssiNimi;
    private final IntegerProperty opintopisteet;
    private final StringProperty suoritusPvm;

    /**
     *  OpiskelijaSuoritusTaulun joka saa parametreina kaikki opiskelijan kurssin suorituksen tiedot.
     */
    public OpiskelijaSuoritusTaulu() {
        etunimi = new SimpleStringProperty(this, "etunimi");
        sukunimi = new SimpleStringProperty(this, "sukunimi");
        arvosana = new SimpleIntegerProperty(this, "arvosana");
        kurssiNimi = new SimpleStringProperty(this, "kurssiNimi");
        opintopisteet = new SimpleIntegerProperty(this, "opintopisteet");
        suoritusPvm = new SimpleStringProperty(this, "suoritusPvm");
    }
    /**
     *  Metodi palauta opiskelijan etunimi 
     * @return etunimi
     */
    public final String getEtunimi() {
        return etunimi.get();
    }
    /**
     *  Metodi palauta opiskelijan sukunimi 
     * @param value etunimi value
     */
    public final void setEtunimi(String value) {
        etunimi.set(value);
    }
    /**
     *  Metodi palauta opiskelijan etunimi 
     * @return etunimi
     */
    public StringProperty etunimiProperty() {
        return etunimi;
    }
    /**
     *  Metodi palauta opiskelijansukunimi 
     * @return sukunimi
     */
    public final String getSukunimi() {
        return sukunimi.get();
    }
    /**
     *  Metodi palauta opiskelijan sukunimi 
     * @param value sukunimi value
     */
    public final void setSukunimi(String value) {
        sukunimi.set(value);
    }
    /**
     *  Metodi palauta opiskelijan sukunimi 
     * @return sukunimi
     */
    public StringProperty sukunimiProperty() {
        return sukunimi;
    }
    /**
     *  Metodi palauta kurssi nimi 
     * @return kurssiNimi
     */
    public final String getKurssiNimi() {
        return kurssiNimi.get();
    }
    /**
     *  Metodi palauta kurssin nimi 
     * @param value kurssinimi
     */
    public final void setKurssiNimi(String value) {
        kurssiNimi.set(value);
    }
    /**
     *  Metodi palauta kurssiNimi 
     * @return kurssiNimi
     */
    public StringProperty kurssiNimiProperty() {
        return kurssiNimi;
    }
    /**
     *  Metodi palauta opintopisteet 
     * @return opintopisteet
     */
    public final int getOpintopisteet() {
        return opintopisteet.get();
    }
    /**
     *  Metodi palauta opintopistet
     * @param value opintopisteet
     */
    public final void setOpintopisteet(int value) {
        opintopisteet.set(value);
    }
    /**
     *  Metodi palauta opintopisteet 
     * @return opintopisteet
     */
    public IntegerProperty opintopisteetProperty() {
        return opintopisteet;
    }
    /**
     *  Metodi palauta arvosana 
     * @return arvosana
     */
    public final int getArvosana() {
        return arvosana.get();
    }
    /**
     *  Metodi palauta arvosana 
     * @param value arvosana
     */
    public final void setArvosana(int value) {
        arvosana.set(value);
    }
    /**
     *  Metodi palauta arvosana
     * @return arvosana
     */
    public IntegerProperty arvosanaProperty() {
        return arvosana;
    }
     /**
     *  Metodi palauta suoritus päivämäärä
     * @return suoritusPvm
     */
    public final String getSuoritusPvm() {
        return suoritusPvm.get();
    }
    /**
     *  Metodi palauta suoritus päivämäärä 
     * @param value suoritus pvm
     */
    public final void setSuoritusPvm(String value) {
        suoritusPvm.set(value);
    }
    /**
     *  Metodi palauta suoritus päivämäärä
     * @return suoritusPvm
     */
    public StringProperty suoritusPvmProperty() {
        return suoritusPvm;
    }
    
   /**
    * Metodi palauta opiskelijan kurssin suoritus tiedot
    * @return opiskelijan kurssin suoritus tiedot
    */
     @Override
    public String toString() {
        return "OpiskelijaSuoritusTaulu{" + "etunimi=" + etunimi +
                ", sukunimi=" + sukunimi + ", kurssiNimi=" + kurssiNimi + 
                ", opintopisteet=" + opintopisteet + ", arvosana=" + arvosana + 
                ", suoritusPvm=" + suoritusPvm + '}';
    }
    
}
