package Entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Amministratore extends Utente {

    public Amministratore(){}

    public Amministratore(String nome, String cognome,boolean possiedeTessera, Tessera tessera){
        super(nome, cognome,possiedeTessera, tessera);
    }
}
