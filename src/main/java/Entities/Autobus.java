package Entities;

import jakarta.persistence.*;

@Entity
@Table (name = "Autobus")
public class  Autobus extends Mezzi {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int capienza;

    //Costruttore vuoto
    public Autobus(){}

    //costruttore
    public Autobus(Stato stato, String descrizione, int capienza){
        super(stato, descrizione);
        this.capienza = capienza;
    }

//Getter e Setters
    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString(){
        return "Autobus{" +
                "id=" + id +
                ", capienza=" + capienza +
                ", stato=" + getStato() +
                '}';
    }
}
