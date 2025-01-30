package Entities;

import jakarta.persistence.*;

import java.util.Scanner;

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

	public Mezzi getMezzi() {
		return mezzi;
	}

	public void setMezzi(Mezzi mezzi) {
		this.mezzi = mezzi;
	}

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

	public void  partenza (){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Da dove vuoi partire?");
		System.out.println("Napoli, Roma, Milano");

		String cittaPartenza = scanner.next();

		switch (cittaPartenza){
			case "Napoli":
				System.out.println("Da Napoli puoi comprare un biglietto per Salerno");
				break;
			case "Roma":
				System.out.println("Da Roma puoi comprare un biglietto per Firenze");
				break;
			case "Milano":
				System.out.println("Da Milano puoi comprare un biglietto per Bologna");
				break;
			default:
				System.out.println("Ci dispiace ma non abbiamo biglietti da questa destinazione");
		}
	}



}

