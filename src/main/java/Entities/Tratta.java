package Entities;

//TODO: tenere traccia del numero di volte che un mezzo percorre una tratta, (log?!)
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Tratte")
public class Tratta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codice_tratta;

    private String partenza;
    private String capolinea;
    private double tempoDiPercorrenza;

    //ogni mezzo può percorrere una sola tratta alla volta quindi la relazione è uno a molti
    @OneToMany(mappedBy = "tratta")
    private List<Mezzi> mezzi;

    public Tratta(){}

    public Tratta(String partenza, String capolinea, double tempoDiPercorrenza){
     this.partenza = partenza;
     this.capolinea = capolinea;
     this.tempoDiPercorrenza = tempoDiPercorrenza;
          }

    public Long getCodice_tratta() {
        return codice_tratta;
    }

    public void setCodice_tratta(Long codice_tratta) {
        this.codice_tratta = codice_tratta;
    }

    public String getPartenza() {
        return partenza;
    }

    public void setPartenza(String partenza) {
        this.partenza = partenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public double getTempoDiPercorrenza() {
        return tempoDiPercorrenza;
    }

    public void setTempoDiPercorrenza(double tempoDiPercorrenza) {
        this.tempoDiPercorrenza = tempoDiPercorrenza;
    }

    @Override
    public String toString() {
        return "Tratta{" +
                "codice_tratta=" + codice_tratta +
                ", partenza='" + partenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoDiPercorrenza=" + tempoDiPercorrenza +
                ", mezzi=" + mezzi +
                '}';
    }
}
