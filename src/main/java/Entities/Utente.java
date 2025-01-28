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
    private LocalDate dataScadenzaTessera;

    public Utente(){}

public Utente(String nome,String cognome,  boolean possiedeTessera,LocalDate dataScadenzaTessera){
    this.nome = nome;
    this.cognome = cognome;
    this.possiedeTessera = possiedeTessera;
    this.dataScadenzaTessera = dataScadenzaTessera;
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

    public LocalDate getDataScadenzaTessera() {
        return dataScadenzaTessera;
    }

    public void setDataScadenzaTessera(LocalDate dataScadenzaTessera) {
        this.dataScadenzaTessera = dataScadenzaTessera;
    }
}
