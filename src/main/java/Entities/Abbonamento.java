package Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "abbonamenti")
public class Abbonamento implements Accesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codiceUnivoco;

	@Enumerated(EnumType.STRING)
	@Column
	private TipoAbbonamento tipo;

	@Column
	private LocalDate dataInizio;

	@Column
	private LocalDate dataScadenza;

	@Column
	private String nomeUtente;

	@Column
	private String cognomeUtente;

	@OneToOne
	@JoinColumn(name = "tessera_id")
	private Tessera tessera;

	public Abbonamento(LocalDate dataInizio, LocalDate dataScadenza, String nomeUtente, String cognomeUtente, Tessera tessera) {
		this.dataInizio = dataInizio;
		this.dataScadenza = dataScadenza;
		this.nomeUtente = nomeUtente;
		this.cognomeUtente = cognomeUtente;
		this.tessera = tessera;

		// Assicura la bidirezionalità
		if (tessera != null) {
			tessera.setAbbonamento(this);
		}
	}



	@Override
	public Long getCodiceUnivoco(){
		return codiceUnivoco;
	}

	@Override
	public void setCodiceUnivoco(Long codice){
		this.codiceUnivoco = codice;
	}

	public TipoAbbonamento getTipo(){
		return tipo;
	}

	public void setTipo(TipoAbbonamento tipo) {
		this.tipo = tipo;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio){
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(LocalDate dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getNomeUtente() {
		return nomeUtente;
	}

	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	public String getCognomeUtente() {
		return cognomeUtente;
	}

	public void setCognomeUtente(String cognomeUtente) {
		this.cognomeUtente = cognomeUtente;
	}

	public Tessera getTessera() {
		return tessera;
	}

	public void setTessera(Tessera tessera) {
		this.tessera = tessera;
	}

	public boolean isValido(){
		return LocalDate.now().isBefore(dataScadenza);
	}

	public Abbonamento(){}

	public Abbonamento(TipoAbbonamento tipo, LocalDate dataInizio, LocalDate dataScadenza, String nomeUtente,
	                   String cognomeUtente, Tessera tessera) {
		this.tipo = tipo;
		this.dataInizio = dataInizio;
		this.dataScadenza = dataScadenza;
		this.nomeUtente = nomeUtente;
		this.cognomeUtente = cognomeUtente;
		this.tessera = tessera;
	}

	@Override
	public String toString() {
		return "Abbonamento{" +
			"codiceUnivoco=" + codiceUnivoco +
			", tipo=" + tipo +
			", dataInizio=" + dataInizio +
			", dataScadenza=" + dataScadenza +
			", nomeUtente='" + nomeUtente + '\'' +
			", cognomeUtente='" + cognomeUtente + '\'' +
			", tessera=" + tessera +
			'}';
	}
}
