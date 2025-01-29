package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
@Entity
public abstract class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long tesseraId;

    private String nome;
    private String cognome;
    private boolean possiedeTessera;

    @OneToOne(mappedBy = "tessera")
    private Tessera tessera;

    @ManyToOne
    @JoinColumn (name = "codiceUnivoco")
    private Biglietto biglietto;

    public Utente(){}

public Utente(String nome,String cognome,  boolean possiedeTessera,Tessera tessera){
    this.nome = nome;
    this.cognome = cognome;
    this.possiedeTessera = possiedeTessera;
    this.tessera = tessera;
}



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
