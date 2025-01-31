package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tessere")
public class Tessera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numeroTessera;

	@Column(nullable = false)
	private LocalDate dataEmissione;

	@Column(nullable = false)
	private LocalDate dataScadenza;

	@OneToOne(mappedBy = "tessera", cascade = CascadeType.ALL)
	private Abbonamento abbonamento;


	@OneToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;


	public Abbonamento getAbbonamento() {
		return abbonamento;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Long getNumeroTessera() {
		return numeroTessera;
	}

	public void setNumeroTessera(Long numeroTessera) {
		this.numeroTessera = numeroTessera;
	}

	public LocalDate getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(LocalDate dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public boolean isAttiva() {
		return dataScadenza.isAfter(LocalDate.now());
	}

	public void rinnova() {
		this.dataScadenza = LocalDate.now().plusYears(1); // Aggiorna la data di scadenza di un anno
	}

	//costruttori
	public Tessera(){}

	public Tessera(Abbonamento abbonamento, Utente utente) {
		this.dataEmissione = LocalDate.now();
		this.dataScadenza = LocalDate.now().plusYears(1);
		this.abbonamento = abbonamento;
		this.utente = utente;
	}

	@Override
	public String toString() {
		return "Tessera{" +
				"  numeroTessera=" + numeroTessera +
				", dataEmissione=" + dataEmissione +
				", dataScadenza=" + dataScadenza +
				", abbonamento=" + abbonamento +
				'}';
	}

	public void setAbbonamento(Abbonamento abbonamento) {
		this.abbonamento = abbonamento;
		if (abbonamento != null) {
			abbonamento.setTessera(this);
		}
	}

	public void setUtente(UtenteSemplice utente) {
		this.utente = utente;
		if (utente != null) {
			utente.setTessera(this);
		}
	}

}
