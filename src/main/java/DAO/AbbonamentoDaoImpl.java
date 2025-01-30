package DAO;

import Entities.Abbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AbbonamentoDaoImpl implements AbbonamentoDao {
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultdb");

	public AbbonamentoDaoImpl(EntityManager em) {}

	public void salvaAbbonamento(Abbonamento abbonamento) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(abbonamento);
		em.getTransaction().commit();
		em.close();
	}

	public Abbonamento trovaPerCodice(Long codice) {
		EntityManager em = emf.createEntityManager();
		Abbonamento abbonamento = em.find(Abbonamento.class, codice);
		em.close();
		return abbonamento;
	}

	public List<Abbonamento> abbonamentiEmessiPerPeriodo(String startDate, String endDate) {
		EntityManager em = emf.createEntityManager();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(startDate, formatter);
		LocalDate end = LocalDate.parse(endDate, formatter);
		List<Abbonamento> result = em.createQuery("SELECT a FROM Abbonamento a WHERE a.dataInizio BETWEEN :start AND :end", Abbonamento.class)
			.setParameter("start", start)
			.setParameter("end", end)
			.getResultList();
		em.close();
		return result;
	}

	public long getTotaleAbbonamentiVenduti(String startDate, String endDate) {
		EntityManager em = emf.createEntityManager();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(startDate, formatter);
		LocalDate end = LocalDate.parse(endDate, formatter);
		long total = em.createQuery("SELECT COUNT(a) FROM Abbonamento a WHERE a.dataInizio BETWEEN :start AND :end", Long.class)
			.setParameter("start", start)
			.setParameter("end", end)
			.getSingleResult();
		em.close();
		return total;
	}

	public Abbonamento trovaPerTessera(String numeroTessera) {
		EntityManager em = emf.createEntityManager();
		try {
			Abbonamento abbonamento = em.createQuery("SELECT a FROM Abbonamento a WHERE a.tessera.numeroTessera = :numeroTessera", Abbonamento.class)
				.setParameter("numeroTessera", numeroTessera)
				.getSingleResult();
			return abbonamento;
		} catch (Exception e) {
			return null;
		} finally {
			em.close();
		}
	}
}
