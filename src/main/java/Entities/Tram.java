package Entities;

import jakarta.persistence.*;

@Entity
@Table (name = "Tram")
public class Tram extends Mezzi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //la capienza potrebbe essere final, tutti i tam hanno una certa capienza e gli autobus ne hanno un'altra
    private int capienza;

    // Costruttore
    public Tram(Stato stato,String descrizione, int capienza) {
        super(stato, descrizione);

        this.capienza = capienza;
    }

    // Getter e Setter
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

    // Metodo toString()
    @Override
    public String toString() {
        return "Tram{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", stato=" + getStato() +
                '}';
    }
}