package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "biglietti")
public class Biglietto implements Accesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column (name = "codiceunivoco")
	private Long codiceUnivoco;  // Modifica il nome del campo per coerenza

	@ManyToOne
	@JoinColumn(name = "rivenditore_id", nullable = false)  // Assicurati che il campo esista
	private Rivenditori rivenditore;

	@OneToOne
	@JoinColumn(name = "utente_id")  // Modificato in utente_id per corrispondere alla convenzione
	private Utente utente;

	@Column(nullable = false)
	private boolean annullato = false;

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
