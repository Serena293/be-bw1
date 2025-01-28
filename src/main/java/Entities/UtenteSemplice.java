package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "UtenteSemplice")
public class UtenteSemplice extends Utente {

    public UtenteSemplice(){}


    public UtenteSemplice(String nome, String cognome,boolean possiedeTessera, LocalDate dataScadenzaTessera) {
        super(nome, cognome,possiedeTessera, dataScadenzaTessera);
    }
}
