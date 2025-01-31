package Entities;

import jakarta.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.time.LocalDate;
import java.util.List;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Mezzi {

    private static final Logger logger = LoggerFactory.getLogger(Mezzi.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Stato stato;
    private String descrizione;

    private int numeroPercorrenze;

    //Ogni tratta può essere percorsa da più mezzi contemporaneamente, quindi la relazione è molti ad uno
    @ManyToOne
    @JoinColumn(name="codice_tratta")
    private Tratta tratta;

    @OneToMany(mappedBy = "mezzi", cascade = CascadeType.ALL)
    private List<Biglietto> biglietti;
    //TODO: aggiungere biglietto, quando un biglietto viene validato viene automaticamente annullato.

    @OneToMany(mappedBy = "mezzi", cascade = CascadeType.ALL)
    private List<Periodo> periodi;

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public List<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }

    public List<Periodo> getPeriodi() {
        return periodi;
    }

    public void setPeriodi(List<Periodo> periodi) {
        this.periodi = periodi;
    }
    public int getNumeroPercorrenze() {
        return numeroPercorrenze;
    }

    public void setNumeroPercorrenze(int numeroPercorrenze) {
        this.numeroPercorrenze = numeroPercorrenze;
    }



    // Costruttore vuoto
    public Mezzi() {}

    // Costruttori
    public Mezzi(Stato stato, String descrizione) {
        this.stato = stato;
        this.descrizione = descrizione;
        this.numeroPercorrenze = 0;
    }

    public Mezzi(Stato stato, String descrizione, Tratta tratta) {
        this.stato =stato;
        this.descrizione=descrizione;
        this.tratta = tratta;
    }

    // Getter e Setter
    public Stato getStato() {
        return stato;
    }

    public void setStato(Stato stato) {
        this.stato = stato;
    }

    public Tratta getTratta() {
        return tratta;
    }
    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //metodo per incrementare il numero di percorrenze
    public void incrementaPercorrenza() {
        this.numeroPercorrenze++;
        logger.info("Numero di percorrenze aumentato di 1, totale: " + numeroPercorrenze);
    }

    public enum Stato {
        InManutenzione,
        InServizio
    }
    //metodo per aggiornare lo stato
    public void aggiornaStato(){
            //TODO: aggiorna "periodi di tempo" all'interno di questo metodo
             /*
             * controllo che periodi(lista) non sia vuoto
             * recupero ultimo elemento della lista
             * imposta valore di data fine
             * crea nuovo periodo (senza dataFine)
             * aggiungi il nuovo periodo alla lista
              */
        if(!periodi.isEmpty()){
            periodi.get(periodi.size() - 1).setDataFine(LocalDate.now());
                if(stato == Stato.InManutenzione ) {
                stato = Stato.InServizio;
                Periodo periodo = new Periodo(LocalDate.now(), Periodo.TipoPeriodo.InServizio);
                periodi.add(periodo);
            } else if (stato == Stato.InServizio) {
                stato = Stato.InManutenzione;
                Periodo periodo = new Periodo(LocalDate.now(), Periodo.TipoPeriodo.InManutenzione);
                periodi.add(periodo);
            }

        }


    }
}