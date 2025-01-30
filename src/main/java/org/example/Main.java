package org.example;

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
        // Crea l'EntityManager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultdb");
        EntityManager em = emf.createEntityManager();
        Scanner scanner = new Scanner(System.in);

        // Crea un'istanza di Faker per generare dati casuali
        Faker faker = new Faker();
        char tipoUtente;

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
        double tempoDiPercorrenza = faker.number().randomDouble(2, 30, 120); // Tempo di percorrenza tra 30 e 120 minuti
        Tratta tratta = new Tratta(partenza, capolinea, tempoDiPercorrenza);

        // 2. Crea un'istanza di Stato (se Stato è un enum, scegli un valore casuale)
        Mezzi.Stato stato = Mezzi.Stato.values()[faker.number().numberBetween(0, Mezzi.Stato.values().length)]; // Se Stato è un enum

        // 3. Crea un'istanza di Autobus usando la Tratta
        String descrizione = faker.lorem().sentence(); // Una descrizione casuale
        Autobus autobus = new Autobus(stato, descrizione, tratta);

        // 4. Stampa i dettagli
        System.out.println("Tratta: " + tratta.getPartenza() + " -> " + tratta.getCapolinea() + " | Tempo di percorrenza: " + tratta.getTempoDiPercorrenza() + " minuti");
        System.out.println("Autobus: " + autobus.getDescrizione() + " | Stato: " + autobus.getStato());

        // Avvia una transazione per aggiungere le entità al database
        em.getTransaction().begin();

        try {
            // 5. Persisti le entità nel database
            em.persist(tratta);
            em.persist(autobus);

            // Commit della transazione
            em.getTransaction().commit();
            System.out.println("Le entità sono state salvate nel database.");

        } catch (Exception e) {
            // Se c'è un errore, rollback della transazione
            em.getTransaction().rollback();
            e.printStackTrace();
        }

        // Ciclo per verificare il tipo di utente
        while (true) {
            System.out.println("Premere 1 per amministratore, Premere 2 per utente");
            tipoUtente = scanner.next().charAt(0);

            if (tipoUtente == '1') {
                System.out.println("Hai scelto Amministratore");
                break;
            } else if (tipoUtente == '2') {
                System.out.println("Hai scelto Utente");
                break;
            } else {
                System.out.println("Input non valido. Per favore, premi 1 per amministratore o 2 per utente.");
            }
        }

        

        // Log dell'avvio dell'applicazione
        final Logger logger = LoggerFactory.getLogger(Main.class);
        logger.info("Applicazione avviata.");

        // Chiudi le risorse
        scanner.close();
        em.close();
        emf.close();
    }
}
