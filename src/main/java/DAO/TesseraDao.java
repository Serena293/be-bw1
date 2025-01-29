package DAO;

import Entities.Tessera;

import java.util.List;

public interface TesseraDao {
	void salvaTessera(Tessera tessera);
	Tessera trovaPerNumero(Long numeroTessera);
	List<Tessera> tessereEmessePerPeriodo(String startDate, String endDate);
}
