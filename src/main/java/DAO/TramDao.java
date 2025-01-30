package DAO;

import Entities.Tram;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class TramDao {

    private final EntityManager em;

    public TramDao(EntityManager em) {

        this.em = Persistence.createEntityManagerFactory("defaultdb").createEntityManager();
    }

    // Trova un Tram tramite ID
    public Tram findById(Long id) {
        return em.find(Tram.class, id);
    }

    // Recupera tutti i Tram
    public List<Tram> findAll() {
        TypedQuery<Tram> query = em.createQuery("SELECT t FROM Tram t", Tram.class);
        return query.getResultList();
    }

    // Save
    public void save(Tram tram) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(tram);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Update
    public void update(Tram tram) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(tram);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // Delete
    public void delete(Tram tram) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.remove(em.contains(tram) ? tram : em.merge(tram));
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}