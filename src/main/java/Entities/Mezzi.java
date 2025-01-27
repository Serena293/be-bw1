package Entities;

import jakarta.persistence.ManyToOne;

public abstract class Mezzi {

    public enum Stato {
        InManutenzione,
        InServizio
    }

    private Stato stato;  // Variabile di istanza per memorizzare lo stato
    private String descrizione;
    //Ogni tratta può essere percorsa da più mezzi contemporaneamente, quindi la relazione è molti ad uno
    private Tratta tratta;

    // Costruttore vuoto
    public Mezzi() {}

    // Costruttore con solo lo stato
    public Mezzi(Stato stato) {
        this.stato = stato;
    }

    // Costruttore con stato e descrizione
    public Mezzi(Stato stato, String descrizione) {
        this.stato = stato;
        this.descrizione = descrizione;
    }

    // Getter e Setter per 'stato'
    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    // Getter e Setter per 'descrizione'
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}