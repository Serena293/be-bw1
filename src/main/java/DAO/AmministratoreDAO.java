package DAO;

import Entities.Amministratore;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AmministratoreDAO {
    private EntityManager em;

    public AmministratoreDAO(EntityManager em){
        this.em = em;
    }

    public void saveAmministratore(Amministratore amministratore){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(amministratore);
        et.commit();
    }

    public Amministratore findAmministratoreById(Long id){
        return em.find(Amministratore.class, id);
    }

    public void updateAmministratore (Amministratore amministratore){
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.merge(amministratore);
        et.commit();
    }
}