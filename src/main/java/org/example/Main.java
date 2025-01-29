package org.example;


import DAO.AutobusDao;
import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Scanner;
import com.github.javafaker.Faker;


public class Main {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Main.class);

        //logger.info("Applicazione avviata.");

        Scanner scanner = new Scanner(System.in);
        Faker faker = new Faker(Locale.ITALY);
        String nome = faker.name().fullName();
        System.out.println(nome);

/*
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultdb");
        EntityManager em = emf.createEntityManager();


        Tram tram1 = new Tram(Mezzi.Stato.InServizio, "Centro storico", 100);
        System.out.println(tram1);
        //Tutti questi metodi andranno implementati con il dao
        em.getTransaction().begin();
        em.persist(tram1);
        em.getTransaction().commit();

        Autobus autobus1 = new Autobus(Mezzi.Stato.InManutenzione,"Ruote sgonfie", 50);
        System.out.println(autobus1);
        em.getTransaction().begin();
        em.persist(autobus1);
        em.getTransaction().commit();

        em.close();
        emf.close();

 */   /*
        Tratta tratta1 = new Tratta();
        tratta1.setCodice_tratta(1L);
        tratta1.setPartenza("Milano");
        tratta1.setCapolinea("Roma");
        tratta1.setTempoDiPercorrenza(6.5);

        AutobusDao autobusDao = new AutobusDao();

        // Creazione di un nuovo Autobus
        Autobus autobus = new Autobus(Mezzi.Stato.InServizio, "Autobus di linea", tratta1);
        autobusDao.save(autobus);

        // Recupero di un Autobus per ID
        Autobus trovato = autobusDao.findById(autobus.getId());
        System.out.println("Autobus trovato: " + trovato);

        // Aggiornamento di un Autobus
        autobusDao.update(trovato);

        // Eliminazione di un Autobus
        autobusDao.delete(trovato.getId());

        // Chiusura del DAO
        autobusDao.close();
        */

    }
}