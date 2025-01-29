package Entities;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Rivenditori {

    private int bigliettiEmessi;
    private int abbonamentiEmessi;

    @ManyToOne
    @JoinColumn( name = "codiceUnivoco" )
    private Biglietto biglietto;


    public Rivenditori(int bigliettiEmessi, int abbonamentiEmessi ) {
        this.bigliettiEmessi = bigliettiEmessi;
        this.abbonamentiEmessi = abbonamentiEmessi;
    }

    public int getBigliettiEmessi() {
        return bigliettiEmessi;
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
