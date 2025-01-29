package Entities;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table (name = "Autobus")
public class  Autobus extends Mezzi {

    private final int capienza = 55;

    //Costruttore vuoto
    public Autobus(){}

    //Costruttore
    public Autobus(Stato stato, String descrizione, Tratta tratta){
        super(stato, descrizione, tratta);

    }

//Getter e Setters
    public int getCapienza() {
        return capienza;
    }


    @Override
    public String toString(){
        return "Autobus{" +
               // "id=" + id +
                ", capienza=" + capienza +
                ", stato=" + getStato() +
                '}';
    }
}
