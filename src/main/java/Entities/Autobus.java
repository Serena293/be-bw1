package Entities;

import jakarta.persistence.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table (name = "Autobus")
public class  Autobus extends Mezzi {
    @Transient //annotazione per non aggiungere logger al db
    private static final Logger logger = LoggerFactory.getLogger(Autobus.class);


    private final int capienza = 55;

    //Costruttore vuoto
    public Autobus(){

    }
    //Costruttore
    public Autobus(Stato stato, String descrizione, Tratta tratta){
        super(stato, descrizione, tratta);
        logger.info("Istanza di autobus creata");
       // System.out.println("Autobus creato!");
    }




    //Getter e Setters
    public int getCapienza() {
        return capienza;
    }



    @Override
    public String toString() {
        return "Autobus{" +
                "tratta=" + getTratta() +  // Includi il metodo toString() di Tratta
                ", capienza=" + capienza +
                ", stato=" + getStato() +
                '}';
    }


}
