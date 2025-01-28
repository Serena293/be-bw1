package DAO;

import Entities.Tessera;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TesseraDaoImpl implements TesseraDao{
	private EntityManagerFactory emf = Persistence.createEntityManagerFactory("trasportiPU");

	public void salvaTessera(Tessera tessera) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		em.persist(tessera);
		em.getTransaction().commit();
		em.close();
	}


	public Tessera trovaPerNumero(Long numeroTessera) {
		EntityManager em = emf.createEntityManager();
		Tessera tessera = em.find(Tessera.class, numeroTessera);
		em.close();
		return tessera;
	}


	public List<Tessera> tessereEmessePerPeriodo(String startDate, String endDate) {
		EntityManager em = emf.createEntityManager();
		List<Tessera> result = em.createQuery("SELECT t FROM Tessera t WHERE t.dataInizio BETWEEN " +
				":start AND :end", Tessera.class)
			.setParameter("start", startDate)
			.setParameter("end", endDate)
			.getResultList();
		em.close();
		return result;
	}

}
