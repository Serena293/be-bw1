package Entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long utente_id;

    private String nome;
    private String cognome;
    private boolean possiedeTessera;

    // Relazione OneToOne tra Utente e Tessera
    @OneToOne(mappedBy = "utente")
    private Tessera tessera;

    // Relazione ManyToOne tra Utente e Biglietto
    @OneToMany(mappedBy = "utente")
    private List<Biglietto> biglietti = new ArrayList<>();

    // Costruttori
    public Utente() {}

    public Utente(String nome, String cognome, boolean possiedeTessera, Tessera tessera) {
        this.nome = nome;
        this.cognome = cognome;
        this.possiedeTessera = possiedeTessera;
        this.tessera = tessera;
    }

    public Long getUtente_id() {
        return utente_id;
    }

    public void setUtente_id(Long utente_id) {
        this.utente_id = utente_id;
    }

    public List<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }

    public Utente(String nome, Long utente_id, String cognome, Tessera tessera, boolean possiedeTessera, List<Biglietto> biglietti) {
        this.nome = nome;
        this.utente_id = utente_id;
        this.cognome = cognome;
        this.tessera = tessera;
        this.possiedeTessera = possiedeTessera;
        this.biglietti = biglietti;
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
