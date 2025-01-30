package Entities;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
public class StatoDistributori {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_statoDistr;

	public enum TipoStato{
		Attivo,
		FuoriServizio
	}

	private LocalDate dataInizio;
	private LocalDate dataFine;
	private TipoStato tipoStato;
	@ManyToOne
	@JoinColumn(name = "distributore_id")
	private Distributori distributore;

	public StatoDistributori(){}

	public StatoDistributori(LocalDate dataInizio, LocalDate dataFine, TipoStato tipoStato) {
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.tipoStato = tipoStato;
	}

	public Long getId_statoDistr(){
		return id_statoDistr;
	}

	public void setId_statoDistr(Long id_statoDistr) {
		this.id_statoDistr = id_statoDistr;
	}

	public Distributori getDistributori(){
		return distributore;
	}

	public void setDistributore(Distributori distributore) {
		this.distributore = distributore;
	}

	public StatoDistributori(LocalDate dataInizio, TipoStato tipoStato){
		this.dataInizio=dataInizio;
		this.tipoStato=tipoStato;
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

	public TipoStato getTipoStato() {
		return tipoStato;
	}

	public void setTipoStato(TipoStato tipoStato) {
		this.tipoStato = tipoStato;
	}

	public Period calcolaDurataStato(){
		return Period.between(dataInizio,dataFine);
	}
}
