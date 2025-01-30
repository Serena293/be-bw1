package DAO;

import Entities.Biglietto;
import java.util.List;

public interface BigliettoDao {
	void salvaBiglietto(Biglietto biglietto);
	Biglietto trovaPerCodice(Long codice);
	List<Biglietto> bigliettiEmessiInPeriodo(String startDate, String endDate);
	void annullaBiglietto(Long codice);

	long getTotaleBigliettiVenduti(String startDate, String endDate);

	long getTotaleBigliettiVidimati(String startDate, String endDate);
}
