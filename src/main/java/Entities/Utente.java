package Entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long utente_id;

    private String nome;
    private String cognome;
    private boolean possiedeTessera;

    // Relazione OneToOne tra Utente e Tessera
    @OneToOne(mappedBy = "utente")
    private Tessera tessera;

    // Relazione ManyToOne tra Utente e Biglietto
    @ManyToOne
    @JoinColumn(name = "biglietto_codiceUnivoco")  // La colonna nel DB dovrebbe corrispondere a questo nome
    private Biglietto biglietto;

    // Costruttori
    public Utente() {}

    public Utente(String nome, String cognome, boolean possiedeTessera, Tessera tessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.possiedeTessera = possiedeTessera;
        this.tessera = tessera;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public boolean isPossiedeTessera() {
        return possiedeTessera;
    }

    public void setPossiedeTessera(boolean possiedeTessera) {
        this.possiedeTessera = possiedeTessera;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }
}
