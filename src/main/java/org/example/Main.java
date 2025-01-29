package org.example;
//TODO:CONTROLLARE RELAZIONI BIGLIETTI, MEZZI EC..
import DAO.*;
import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import com.github.javafaker.Faker;


public class Main {
    public static void main(String[] args) {

        final Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Applicazione avviata.");


        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultdb");
        EntityManager em = emf.createEntityManager();



        Scanner scanner = new Scanner(System.in);

        Tratta tratta1 = new Tratta("Casa", "Lavoro", 20);

// Creazione della tessera prima di usarla
        Tessera tessera1 = new Tessera(LocalDate.of(2025, 01, 01),
                LocalDate.of(2025, 06, 01), null, null);  // Passo null per ora

// Ora posso creare abbonamento e utente con la tessera
        Abbonamento abbonamento1 = new Abbonamento(LocalDate.of(2025, 01, 01),
                LocalDate.of(2025, 06, 01), "Mario", "Rossi", tessera1);
        UtenteSemplice utente1 = new UtenteSemplice("Luigi", "Verdi", true, tessera1);

// Setto i valori mancanti sulla tessera
        tessera1.setAbbonamento(abbonamento1);
        tessera1.setUtente(utente1);

        Negozi negozio = new Negozi(100, 50, "Tabaccaio", "Via Roma");

        Autobus autobus407 = new Autobus(Mezzi.Stato.InServizio, "blabla", tratta1);
        Tram tram1 = new Tram(Mezzi.Stato.InManutenzione, "BLA", tratta1);

        Biglietto biglietto1 = new Biglietto(negozio, utente1,false);


        //System.out.println(biglietto1);


        System.out.println(autobus407);
       // System.out.println(tram1);







       // Faker faker = new Faker(Locale.ITALY);
       // String nome = faker.name().fullName();
       //  System.out.println(nome);


        /*
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
     scanner.close();
    }
}