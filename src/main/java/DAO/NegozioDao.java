package DAO;

import Entities.Negozi;
import jakarta.persistence.*;
import java.util.List;

public class NegozioDao {
    private EntityManagerFactory emf;
    private EntityManager entityManager;


    public NegozioDao(EntityManager em) {
        emf = Persistence.createEntityManagerFactory("defaultdb");
        entityManager = emf.createEntityManager();
    }


    public void addNegozio(Negozi negozio) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(negozio);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }


    public List<Negozi> getAllNegozi() {
        TypedQuery<Negozi> query = entityManager.createQuery("SELECT n FROM Negozi n", Negozi.class);
        return query.getResultList();
    }


    public Negozi getNegozioById(int id) {
        return entityManager.find(Negozi.class, id);
    }


    public void updateNegozio(Negozi negozio) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(negozio);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }


    public void deleteNegozio(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Negozi negozio = entityManager.find(Negozi.class, id);
            if (negozio != null) {
                entityManager.remove(negozio);
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
