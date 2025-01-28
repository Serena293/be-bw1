package DAO;

import Entities.Tratta;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class TrattaDAO {

    private EntityManagerFactory emf;
    private EntityManager em;

    public TrattaDAO() {
        // Inizializza l'EntityManagerFactory e l'EntityManager
        emf = Persistence.createEntityManagerFactory("defaultdb");
        em = emf.createEntityManager();
    }

    // Metodo per salvare una Tratta nel database
    public void save(Tratta tratta) {
        em.getTransaction().begin();
        em.persist(tratta);
        em.getTransaction().commit();
    }

    // Metodo per trovare una Tratta per ID
    public Tratta findById(Long id) {
        return em.find(Tratta.class, id);
    }

    // Metodo per aggiornare una Tratta nel database
    public void update(Tratta tratta) {
        em.getTransaction().begin();
        em.merge(tratta);
        em.getTransaction().commit();
    }

    // Metodo per eliminare una Tratta dal database
    public void delete(Long id) {
        Tratta tratta = findById(id);
        if (tratta != null) {
            em.getTransaction().begin();
            em.remove(tratta);
            em.getTransaction().commit();
        }
    }

    // Metodo per ottenere tutte le Tratte dal database
    public List<Tratta> findAll() {
        return em.createQuery("SELECT t FROM Tratta t", Tratta.class).getResultList();
    }

    // Metodo per chiudere l'EntityManager e l'EntityManagerFactory
    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}