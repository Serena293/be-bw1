package Entities;

import jakarta.persistence.Entity;

@Entity
public class Distributori extends Rivenditori {
    private String tipoDiDistributore;
    private String localizzazione;

    public Distributori(int bigliettiEmessi, int abbonamentiEmessi, String tipoDiDistributore, String localizzazione){
        super(bigliettiEmessi, abbonamentiEmessi);
        this.tipoDiDistributore = tipoDiDistributore;
        this.localizzazione = localizzazione;
    }

    public String getTipoDiDistributore() {
        return tipoDiDistributore;
    }

    public void setTipoDiDistributore(String tipoDiDistributore) {
        this.tipoDiDistributore = tipoDiDistributore;
    }

    public String getLocalizzazione() {
        return localizzazione;
    }

    public void setLocalizzazione(String localizzazione) {
        this.localizzazione = localizzazione;
    }

    @Override
    public String toString() {
        return "Distributori{" +
                "tipoDiDistributore='" + tipoDiDistributore + '\'' +
                ", localizzazione='" + localizzazione + '\'' +
                '}';
    }
}
