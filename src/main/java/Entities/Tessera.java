package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tessere")
public class Tessera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long numeroTessera;

	@Column(nullable = false)
	private LocalDate dataEmissione;

	@Column(nullable = false)
	private LocalDate dataScadenza;

	@OneToMany(mappedBy = "tessera")
	private Abbonamento abbonamento;

	@OneToOne
	@JoinColumn(name = "tesseraId")
	private Utente utente;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Tessera(){}

	public Tessera(LocalDate dataEmissione, LocalDate dataScadenza, Abbonamento abbonamento) {
		this.dataEmissione = dataEmissione;
		this.dataScadenza = dataScadenza;
		this.abbonamento = abbonamento;

	}

	@Override
	public String toString() {
		return "Tessera{" +
				"id=" + id +
				", numeroTessera=" + numeroTessera +
				", dataEmissione=" + dataEmissione +
				", dataScadenza=" + dataScadenza +
				", abbonamento=" + abbonamento +
				'}';
	}
}
