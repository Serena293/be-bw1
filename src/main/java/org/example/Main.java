package org.example;

import AltreClassi.PercorriTratta;
import DAO.*;
import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;
import com.github.javafaker.Faker;

public class Main {
    public static void main(String[] args) {
        // Log dell'avvio dell'applicazione
        final Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Applicazione avviata.");

        // Crea l'EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultdb");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);
        Faker faker = new Faker();

        // Creazione dei DAO
        AbbonamentoDaoImpl abbonamentoDao = new AbbonamentoDaoImpl(em);
        AutobusDao autobusDao = new AutobusDao(em);
        BigliettoDaoImpl bigliettoDao = new BigliettoDaoImpl(em);
        DistributoriDao distributoriDao = new DistributoriDao(em);
        MezziDAO mezziDAO = new MezziDAO(em);
        NegozioDao negozioDao = new NegozioDao(em);
        RivenditoriDao rivenditoriDao = new RivenditoriDao(em);
        TramDao tramDao = new TramDao(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        UtenteSempliceDAO utenteSempliceDAO = new UtenteSempliceDAO(em);

        // 1. Crea un'istanza di Tratta con valori casuali
        String partenza = faker.address().cityName();
        String capolinea = faker.address().cityName();
        double tempoDiPercorrenza = faker.number().randomDouble(2, 30, 120);
        Tratta tratta = new Tratta(partenza, capolinea, tempoDiPercorrenza);
        Mezzi.Stato stato = Mezzi.Stato.values()[faker.number().numberBetween(0, Mezzi.Stato.values().length)];
        String descrizione = faker.lorem().sentence();
        Autobus autobus = new Autobus(stato, descrizione, tratta);

        em.getTransaction().begin();
        try {
            // 5. Utilizza i DAO per persistere le entità nel database
            trattaDAO.save(tratta);  // salva la tratta
            autobusDao.save(autobus);  // salva l'autobus

            // Commit della transazione
            em.getTransaction().commit();
            System.out.println("Le entità sono state salvate nel database.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }


        PercorriTratta percorrerTratta = new PercorriTratta(mezziDAO);
        percorrerTratta.incrementaPercorrenza(autobus, tratta);


        // Ciclo per verificare il tipo di utente
        while (true) {
            System.out.println("Premere 1 per Amministratore, Premere 2 per Utente, Premere Q per uscire");
            String input = scanner.next().toUpperCase();

            if (tipoUtente == '1') {
                System.out.println("Hai scelto Amministratore");
                logger.info("Tipo utente = amministratore");
                break;
            } else if (tipoUtente == '2') {
                System.out.println("Hai scelto Utente");
                logger.info("Tipo utente = utente semplice");
                break;
            }
        }
    }

        // Chiudi le risorse
        scanner.close();
        em.close();
        emf.close();
    }
}
