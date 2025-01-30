package DAO;

import Entities.Abbonamento;

import java.util.List;

public interface AbbonamentoDao {
	void salvaAbbonamento(Abbonamento abbonamento);
	Abbonamento trovaPerCodice(Long codice);
	List<Abbonamento> abbonamentiEmessiPerPeriodo(String startDate, String endDate);
	Abbonamento trovaPerTessera(String numeroTessera);

	long getTotaleAbbonamentiVenduti(String startDate, String endDate);
}
