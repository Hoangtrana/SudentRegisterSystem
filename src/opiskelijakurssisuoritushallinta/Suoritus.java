package opiskelijakurssisuoritushallinta;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Luokka määrittelee yksinkertaisen suorituksen tiedot,
 * joka muodostuu opiskeljia_idstä, kurssin id-stä, arvosanasta, päivämäärästä
 * @author hoang
 * @version 1.0
 * Date 18.03.2021 Aloitus päivä
 */
public class Suoritus {
	private final IntegerProperty opiskelijaId; 
	private final IntegerProperty kurssiId; 
	private final IntegerProperty arvosana;
	private final StringProperty suoritusPvm;


        /**
         * Alustaja joka saa parametreina kaikki Suorituksen tiedot
         */
	public Suoritus (){

            opiskelijaId = new SimpleIntegerProperty(this, "opiskelijaId");
            kurssiId = new SimpleIntegerProperty(this, "kurssiId");
            arvosana = new SimpleIntegerProperty(this, "arvosana");
            suoritusPvm = new SimpleStringProperty(this, "suoritusPvm");
	}
	
        /**
         * Metodi palauta opiskelijaId
         * @return opiskelijaId
         */
        public IntegerProperty OpiskelijaIdProperty() {
            return opiskelijaId;
        }
    
        /** Palauttaa kurssin id.
	 *  @return kurssin id.
	 */
        public int getOpiskelijaId() {
            return  opiskelijaId.get();
        }
    
        /**
         * opiskelija asetus
         * @param newOpiskelijaId uusi opiskelija
         */
         public void setOpiskelijaId(int newOpiskelijaId){
        opiskelijaId.set(newOpiskelijaId);
        }    
   
        /**
         * Palauttaa kurssin id
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
    
        /**
         * kurssi_id asetus
         * @param newKurssiId uusi kurssiId
         */
        public void setKurssiId(int newKurssiId){
            kurssiId.set(newKurssiId);
        }
    
        /**
         * Metodi palautaa arvosana
         * @return arvosana
         */
        public IntegerProperty arvosanaProperty() {
            return arvosana;
        }
    
        /**
         * Palauttaa kurssin arvosana.
         * @return kurssin arvosana.
         */
        public int getArvosana() {
            return arvosana.get();
        }
    
        /**
         * arvosana asetus     
         * @param newArvosana uusi arvosana
         */
        public void setArvosana(int newArvosana) {
		arvosana.set(newArvosana);
        }
    
        /**
         * Palauttaa kurssin suoritus päivämäärä
         * @return kurssin arvosana.
         */
        public StringProperty suoritusPvmProperty() {
            return suoritusPvm;
        }

        /**
         * Palauttaa kurssin suoritus päivämäärä
         * @return kurssin arvosana.
         */
        public String getSuoritusPvm() {
            return suoritusPvm.get();
        }
    
        /**
         * suoritus päivämäärä asetus     *
         * @param newSuoritusPvm uusi suoritus pvm
         */
        public void setSuoritusPvm(String newSuoritusPvm) {
		suoritusPvm.set(newSuoritusPvm);
        }
    
        /**
         * Metodi palauta suorituksen tiedot: opiskelija Id, kurssiId, arvosana ja suoritus päivä määrä
         * @return String
         */      	
        @Override
        public String toString() {
            return "Suoritus [opiskeljia_id=" + getOpiskelijaId()+ ", kurssin_id=" 
                        + getKurssiId() + ", arvosana=" + arvosana + ", suoritus_pvm="
			+ suoritusPvm + "]";
            }
}