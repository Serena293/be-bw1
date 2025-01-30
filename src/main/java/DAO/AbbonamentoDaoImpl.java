package DAO;

import Entities.Abbonamento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class AbbonamentoDaoImpl implements AbbonamentoDao {
	private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasportiPU");



    public void salvaAbbonamento(Abbonamento abbonamento){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(abbonamento);
		em.getTransaction().commit();
		em.close();
	}

	public Abbonamento trovaPerCodice(Long codice){
		EntityManager em = emf.createEntityManager();
		Abbonamento abbonamento = em.find(Abbonamento.class, codice);
		em.close();
		return abbonamento;
	}

	public List<Abbonamento> abbonamentiEmessiPerPeriodo(String startDate, String endDate){
		EntityManager em = emf.createEntityManager();
		List<Abbonamento> result = em.createQuery("SELECT a FROM Abbonamento a WHERE a.dataInizio BETWEEN " +
				":start AND :end", Abbonamento.class)
			.setParameter("start", startDate)
			.setParameter("end", endDate)
			.getResultList();
		em.close();
		return result;
	}


	public Abbonamento trovaPerTessera(String numeroTessera) {
		EntityManager em = emf.createEntityManager();
		try {
			// Cerca l'abbonamento associato al numero di tessera
			Abbonamento abbonamento = em.createQuery(
					"SELECT a FROM Abbonamento a WHERE a.tessera.numeroTessera = :numeroTessera", Abbonamento.class)
				.setParameter("numeroTessera", numeroTessera)
				.getSingleResult();
			return abbonamento;
		} catch (Exception e) {
			// Se non viene trovato nessun abbonamento, restituisci null
			return null;
		} finally {
			em.close();
		}
	}


}
