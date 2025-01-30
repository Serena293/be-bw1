package Entities;

import DAO.AbbonamentoDao;
import DAO.TesseraDao;

import java.time.LocalDate;

public class ValidationService {

		private AbbonamentoDao abbonamentoDao;
		private TesseraDao tesseraDao;

		public ValidationService(AbbonamentoDao abbonamentoDao, TesseraDao tesseraDao) {
			this.abbonamentoDao = abbonamentoDao;
			this.tesseraDao = tesseraDao;
		}

	public boolean verificaTessera(Long numeroTessera) {
		Tessera tessera = tesseraDao.trovaPerNumero(numeroTessera);
		return tessera != null && tessera.getDataScadenza().isAfter(LocalDate.now());
	}

	public AbbonamentoDao getAbbonamentoDao() {
		return abbonamentoDao;
	}

	public void setAbbonamentoDao(AbbonamentoDao abbonamentoDao) {
		this.abbonamentoDao = abbonamentoDao;
	}

	public TesseraDao getTesseraDao() {
		return tesseraDao;
	}

	public void setTesseraDao(TesseraDao tesseraDao) {
		this.tesseraDao = tesseraDao;
	}

	public boolean verificaAbbonamento(Long numeroTessera) {
		// Converti il Long in String
		String numeroTesseraString = String.valueOf(numeroTessera);

		// Chiama il metodo trovaPerTessera con la String convertita
		Abbonamento abbonamento = abbonamentoDao.trovaPerTessera(numeroTesseraString);

		return abbonamento != null && abbonamento.isValido();
	}
}
