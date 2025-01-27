package org.example;


import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultdb");
        EntityManager em = emf.createEntityManager();

        Tram tram1 = new Tram(Mezzi.Stato.InServizio, 150);
        System.out.println(tram1);
        em.getTransaction().begin();
        em.persist(tram1);
        em.getTransaction().commit();

        em.close();
        emf.close();

    }
}