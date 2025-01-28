package Entities;

import jakarta.persistence.*;

@Entity
@Table (name = "Tram")
public class Tram extends Mezzi {

    private final int capienza= 135;

    // Costruttore
    public Tram(Stato stato,String descrizione,Tratta tratta) {
        super(stato, descrizione, tratta);

    }

    // Getter e Setter


    // Getter e Setter per capienza
    public int getCapienza() {
        return capienza;
    }


    // Metodo toString()
    @Override
    public String toString() {
        return "Tram{" +
                //"id=" + id +
                ", capienza=" + capienza +
                ", stato=" + getStato() +
                '}';
    }
}