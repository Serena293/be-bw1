package DAO;

import Entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class TrattaDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public TrattaDAO(EntityManager em) {
        emf = Persistence.createEntityManagerFactory("defaultdb");
        this.em = emf.createEntityManager();
    }

    public void save(Tratta tratta) {
        em.getTransaction().begin();
        em.persist(tratta);
        em.getTransaction().commit();
    }

    public Tratta findById(Long id) {
        return em.find(Tratta.class, id);
    }

    public void update(Tratta tratta) {
        em.getTransaction().begin();
        em.merge(tratta);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        Tratta tratta = findById(id);
        if (tratta != null) {
            em.getTransaction().begin();
            em.remove(tratta);
            em.getTransaction().commit();
        }
    }

    public List<Tratta> findAll() {
        return em.createQuery("SELECT t FROM Tratta t", Tratta.class).getResultList();
    }

    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }

    public long getTotalePercorrenze(String startDate, String endDate) {
        return em.createQuery("SELECT COUNT(t) FROM Tratta t WHERE t.dataPartenza BETWEEN :start AND :end", Long.class)
            .setParameter("start", startDate)
            .setParameter("end", endDate)
            .getSingleResult();
    }

    public long getTotalePercorrenzePerMezzo(Long mezzoId, String startDate, String endDate) {
        return em.createQuery("SELECT COUNT(t) FROM Tratta t WHERE t.mezzo.id = :mezzoId AND t.dataPartenza BETWEEN :start AND :end", Long.class)
            .setParameter("mezzoId", mezzoId)
            .setParameter("start", startDate)
            .setParameter("end", endDate)
            .getSingleResult();
    }

    public double getTempoMedioPercorrenza(Long mezzoId, String startDate, String endDate) {
        return em.createQuery("SELECT AVG(t.tempoEffettivo) FROM Tratta t WHERE t.mezzo.id = :mezzoId AND t.dataPartenza BETWEEN :start AND :end", Double.class)
            .setParameter("mezzoId", mezzoId)
            .setParameter("start", startDate)
            .setParameter("end", endDate)
            .getSingleResult();
    }
}
