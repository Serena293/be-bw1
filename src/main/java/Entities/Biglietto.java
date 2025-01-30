package Entities;

import jakarta.persistence.*;

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

	// Costruttori
	public Biglietto() {}

	public Biglietto(Rivenditori rivenditore, Utente utente, boolean annullato) {
		this.rivenditore = rivenditore;
		this.utente = utente;
		this.annullato = annullato;
	}

	// Implementazione dei metodi di Accesso
	@Override
	public Long getCodiceUnivoco() {
		return codiceUnivoco;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Rivenditori getRivenditore() {
		return rivenditore;
	}

	public void setRivenditore(Rivenditori rivenditore) {
		this.rivenditore = rivenditore;
	}

	public void setAnnullato(boolean annullato) {
		this.annullato = annullato;
	}

	@Override
	public void setCodiceUnivoco(Long codice) {
		this.codiceUnivoco = codice;
	}

	public void annulla() {
		this.annullato = true;
	}

	public boolean isAnnullato() {
		return annullato;
	}

	@Override
	public String toString() {
		return "Biglietto{" +
				"codiceUnivoco=" + codiceUnivoco +
				", annullato=" + annullato +
				'}';
	}
}
