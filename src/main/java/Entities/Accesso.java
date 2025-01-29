package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public interface Accesso {

	Long getCodiceUnivoco();
	void setCodiceUnivoco(Long codice);
}