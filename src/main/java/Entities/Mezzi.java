package Entities;

//l'esercizio chide di tenere traccia del cambiamento di stato, log?!


import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Mezzi {

    public Mezzi(Long id,Stato stato, String descrizione, Tratta tratta) {
    }

    public Mezzi(Stato stato, String descrizione, Tratta tratta) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public enum Stato {
        InManutenzione,
        InServizio
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Stato stato;  // Variabile di istanza per memorizzare lo stato
    private String descrizione;
    //Ogni tratta può essere percorsa da più mezzi contemporaneamente, quindi la relazione è molti ad uno
    @ManyToOne
    @JoinColumn(name="codice_tratta")
    private Tratta tratta;
    //TODO: aggiungere biglietto, quando un biglietto viene validato viene automaticamente annullato.

    // Costruttore vuoto
    public Mezzi() {}

    // Costruttore con solo lo stato
    public Mezzi(Stato stato) {
        this.stato = stato;
    }

    // Costruttore
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