package DAO;

import Entities.Biglietto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class BigliettoDaoImpl implements BigliettoDao{
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultdb");

	public BigliettoDaoImpl(EntityManager em) {
	}

	public void salvaBiglietto(Biglietto biglietto){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(biglietto);
		em.getTransaction().commit();
		em.close();
	}

	public Biglietto trovaPerCodice(Long codice){
		EntityManager em = emf.createEntityManager();
		Biglietto biglietto = em.find(Biglietto.class, codice);
		em.close();
		return biglietto;
	}

	public List<Biglietto> bigliettiEmessiInPeriodo(String startDate, String endDate){
		EntityManager em = emf.createEntityManager();
		List<Biglietto> result = em.createQuery("SELECT b FROM Biglietto b WHERE b.dataEmissione BETWEEN " +
				":start AND :end", Biglietto.class)
			.setParameter("start", startDate)
			.setParameter("end", endDate)
			.getResultList();
		em.close();
		return result;
	}

	public void annullaBiglietto(Long codice){
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		Biglietto biglietto = em.find(Biglietto.class, codice);
		if(biglietto!= null){
			biglietto.annulla();
			em.merge(biglietto);
		}
		em.getTransaction().commit();
		em.close();
	}
}
