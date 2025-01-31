package DAO;

import Entities.Abbonamento;

import java.time.LocalDate;
import java.util.List;

public interface AbbonamentoDao {
	void salvaAbbonamento(Abbonamento abbonamento);
	Abbonamento trovaPerCodice(Long codice);
	List<Abbonamento> abbonamentiEmessiPerPeriodo(LocalDate startDate, LocalDate endDate);
	Abbonamento trovaPerTessera(String numeroTessera);

	long getTotaleAbbonamentiVenduti(LocalDate startDate, LocalDate endDate);
}
