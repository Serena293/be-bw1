package Entities;

import java.time.LocalDate;
import java.time.Period;


//Classe per controllare i periodi in cui i Mezzi sono InServizio o InManutenzione
public class Periodo {

    public enum TipoPeriodo {
        InServizio,
        InManutenzione
    }
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private TipoPeriodo tipoPeriodo;

    //costruttore vuot0
    public Periodo(){};

    //costruttori
    public Periodo(LocalDate dataInizio, LocalDate dataFine, TipoPeriodo tipoPeriodo){
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.tipoPeriodo = tipoPeriodo;
    }

    //la data di fine potrebbe non esserci
    public Periodo(LocalDate dataInizio, TipoPeriodo tipoPeriodo){
        this.dataInizio =dataInizio;
        this.tipoPeriodo = tipoPeriodo;

    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public TipoPeriodo getTipoPeriodo() {
        return tipoPeriodo;
    }

    public void setTipoPeriodo(TipoPeriodo tipoPeriodo) {
        this.tipoPeriodo = tipoPeriodo;
    }


//metodo per calcolare durata periodo
//Period per calcolare la differenza tra due date
public Period calcolaDurataPeriodo() {
    return Period.between(dataInizio, dataFine);
}


}
