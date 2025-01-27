package Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "biglietti")
public class Biglietto implements Accesso{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codiceUnivoco;

	@Column(nullable = false)
	private boolean annullato = false;

	@Override
	public Long getCodiceUnivoco(){
		return codiceUnivoco;
	}

	@Override
	public void setCodiceUnivoco(Long codice){
		this.codiceUnivoco = codice;
	}

	public void annulla(){
		this.annullato = true;
	}

	public boolean isAnnullato() {
		return annullato;
	}

	public Biglietto(){}

	public Biglietto(boolean annullato) {
		this.annullato = annullato;
	}

	@Override
	public String toString() {
		return "Biglietto{" +
			"codiceUnivoco=" + codiceUnivoco +
			", annullato=" + annullato +
			'}';
	}
}
