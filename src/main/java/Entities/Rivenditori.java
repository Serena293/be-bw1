package Entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Rivenditori {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rivenditore_id;

    private int bigliettiEmessi;
    private int abbonamentiEmessi;

    // Relazione OneToMany tra Rivenditore e Biglietto
    @OneToMany(mappedBy = "rivenditore")
    private List<Biglietto> biglietti;

    // Costruttore
    public  Rivenditori(){}

    public Rivenditori(int bigliettiEmessi, int abbonamentiEmessi) {
        this.bigliettiEmessi = bigliettiEmessi;
        this.abbonamentiEmessi = abbonamentiEmessi;
    }

    // Getters e Setters
    public int getBigliettiEmessi() {
        return bigliettiEmessi;
    }

    public Long getRivenditore_id() {
        return rivenditore_id;
    }

    public void setRivenditore_id(Long rivenditore_id) {
        this.rivenditore_id = rivenditore_id;
    }

    public List<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }

    public void setBigliettiEmessi(int bigliettiEmessi) {
        this.bigliettiEmessi = bigliettiEmessi;
    }

    public int getAbbonamentiEmessi() {
        return abbonamentiEmessi;
    }

    public void setAbbonamentiEmessi(int abbonamentiEmessi) {
        this.abbonamentiEmessi = abbonamentiEmessi;
    }

}
