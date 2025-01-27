package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Tram extends Mezzi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capienza;

    // Costruttore
    public Tram(Stato stato, int capienza) {
        super(stato);
        this.capienza = capienza;
    }

    // Getter e Setter per id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Getter e Setter per capienza
    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    // Metodo toString() corretto
    @Override
    public String toString() {
        return "Tram{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", stato=" + getStato() +
                '}';
    }
}