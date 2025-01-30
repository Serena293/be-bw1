package DAO;

import Entities.Rivenditori;
import jakarta.persistence.*;
import java.util.List;

public class RivenditoriDao {
    private EntityManagerFactory emf;
    private EntityManager entityManager;

    public RivenditoriDao(EntityManager em) {
        emf = Persistence.createEntityManagerFactory("defaultdb");
        entityManager = emf.createEntityManager();
    }


    public void addRivenditore(Rivenditori rivenditore) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(rivenditore);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }


    public List<Rivenditori> getAllRivenditori() {
        TypedQuery<Rivenditori> query = entityManager.createQuery("SELECT r FROM Rivenditori r", Rivenditori.class);
        return query.getResultList();
    }


    public Rivenditori getRivenditoreById(int id) {
        return entityManager.find(Rivenditori.class, id);
    }


    public void updateRivenditore(Rivenditori rivenditore) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(rivenditore);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }


    public void deleteRivenditore(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Rivenditori rivenditore = entityManager.find(Rivenditori.class, id);
            if (rivenditore != null) {
                entityManager.remove(rivenditore);
            }
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }


    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}
