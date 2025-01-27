package Entities;

public abstract class Mezzi {
    public enum Stato {
        InManutenzione,
        InServizio
    }

    private Stato stato;  // Variabile di istanza per memorizzare lo stato

    public Mezzi() {}

    public Mezzi(Stato stato) {
        this.stato = stato;  // Assegna il parametro alla variabile di istanza
    }

    // Getter e Setter per la variabile di istanza 'stato'
    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }
}