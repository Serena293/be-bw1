package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "biglietti")
public class Biglietto implements Accesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codiceUnivoco;

	@ManyToOne
	@JoinColumn(name = "rivenditore_id", nullable = false)
	private Rivenditori rivenditore;

	@ManyToOne
	@JoinColumn(name = "utente_id")
	private Utente utente;

	@Column(nullable = false)
	private boolean annullato = false;

	@ManyToOne
	@JoinColumn(name = "mezzo_id", nullable = false)
	private Mezzi mezzi;

	@Column(nullable = false)
	private LocalDateTime dataEmissione;

	@Column(nullable = false)
	private LocalDateTime dataVidimazione;


	public Rivenditori getRivenditore() {
		return rivenditore;
	}

	public void setRivenditore(Rivenditori rivenditore) {
		this.rivenditore = rivenditore;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public boolean isAnnullato() {
		return annullato;
	}

	public void setAnnullato(boolean annullato) {
		this.annullato = annullato;
	}

	public Mezzi getMezzi() {
		return mezzi;
	}

	public void setMezzi(Mezzi mezzi) {
		this.mezzi = mezzi;
	}

	public LocalDate getDataEmissione() {
		return LocalDate.from(dataEmissione);
	}

	public void setDataEmissione(LocalDate dataEmissione) {
		this.dataEmissione = LocalDateTime.from(dataEmissione);
	}

	public LocalDate getDataVidimazione() {
		return LocalDate.from(dataVidimazione);
	}

	public void setDataVidimazione(LocalDate dataVidimazione) {
		this.dataVidimazione = LocalDateTime.from(dataVidimazione);
	}

	@Override
	public void setCodiceUnivoco(Long codice) {
		this.codiceUnivoco = codice;
	}

	@Override
	public Long getCodiceUnivoco() {
		return codiceUnivoco;
	}

	public void annulla() {
		this.annullato = true;
	}

	@Override
	public String toString() {
		return "Biglietto{" +
			"codiceUnivoco=" + codiceUnivoco +
			", annullato=" + annullato +
			'}';
	}
}
