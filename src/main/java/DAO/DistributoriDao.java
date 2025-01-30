package DAO;

import Entities.Distributori;
import jakarta.persistence.*;

import java.util.List;

public class DistributoriDao {
    private EntityManagerFactory emf;
    private EntityManager entityManager;


    public DistributoriDao(EntityManager em) {
        emf = Persistence.createEntityManagerFactory("defaultdb");
        entityManager = emf.createEntityManager();
    }


    public void addDistributore(Distributori distributore) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(distributore);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }


    public List<Distributori> getAllDistributori() {
        TypedQuery<Distributori> query = entityManager.createQuery("SELECT d FROM Distributori d", Distributori.class);
        return query.getResultList();
    }


    public Distributori getDistributoreById(int id) {
        return entityManager.find(Distributori.class, id);
    }


    public void updateDistributore(Distributori distributore) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(distributore);
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw e;
        }
    }


    public void deleteDistributore(int id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Distributori distributore = entityManager.find(Distributori.class, id);
            if (distributore != null) {
                entityManager.remove(distributore);
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
