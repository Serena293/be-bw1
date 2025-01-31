package DAO;

import Entities.Biglietto;

import java.time.LocalDate;
import java.util.List;

public interface BigliettoDao {
	void salvaBiglietto(Biglietto biglietto);
	Biglietto trovaPerCodice(Long codice);
	List<Biglietto> bigliettiEmessiInPeriodo(LocalDate startDate, LocalDate endDate);
	void annullaBiglietto(Long codice);

	long getTotaleBigliettiVenduti(LocalDate startDate, LocalDate endDate);

	long getTotaleBigliettiVidimati(LocalDate startDate, LocalDate endDate);
}
