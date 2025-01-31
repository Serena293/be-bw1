package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

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
		this.dataScadenza = LocalDate.now().plusYears(1);;
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
	public void creaTessera() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Inserisci nome: ");
		String nomeUtente = scanner.next();

		System.out.println("Inserisci cognome: ");
		String cognomeUtente = scanner.next();

		// Creazione delle date
		LocalDate dataInizio = LocalDate.now();
		LocalDate dataFine = dataInizio.plusMonths(6);

		// Creazione dell'abbonamento (senza tessera per ora)
		Abbonamento abbonamento = new Abbonamento(dataInizio, dataFine, nomeUtente, cognomeUtente, null);
		UtenteSemplice utenteSemplice = new UtenteSemplice(nomeUtente, cognomeUtente, true, null);

		// Creazione della tessera con l'abbonamento
		Tessera tessera = new Tessera(dataInizio, dataFine, abbonamento, utenteSemplice);

		// Aggiorniamo l'abbonamento con la tessera
		abbonamento.setTessera(tessera);
		utenteSemplice.setTessera(tessera);

		// Salvataggio nel database
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultdb");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(utenteSemplice);
			em.persist(tessera);
			em.persist(abbonamento);
			em.getTransaction().commit();

			// Hibernate ora ha generato gli ID, possiamo stamparli
			System.out.println("Tessera e abbonamento salvati con successo!");
			System.out.println("ID Utente: " + utenteSemplice.getUtente_id());
			System.out.println("ID Tessera: " + tessera.getNumeroTessera());
			System.out.println("ID Abbonamento: " + abbonamento.getCodiceUnivoco());
			System.out.println("Nome: " + utenteSemplice.getNome() + " " + utenteSemplice.getCognome());
				} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}



}
