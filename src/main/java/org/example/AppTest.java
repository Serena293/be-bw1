package org.example;

import AltreClassi.PercorriTratta;
import DAO.*;
import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.Scanner;
import com.github.javafaker.Faker;

public class AppTest{
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultdb");
        EntityManager em = emf.createEntityManager();
        final Logger logger = LoggerFactory.getLogger(Autobus.class);


        Scanner scanner = new Scanner(System.in);

        //DAO
        AmministratoreDAO amministratoreDAO = new AmministratoreDAO(em);
        AutobusDao autobusDao = new AutobusDao(em);
        AbbonamentoDao abbonamentoDAO = new AbbonamentoDaoImpl(em);
        BigliettoDao bigliettoDao = new BigliettoDaoImpl(em);
        DistributoriDao distributoriDao = new DistributoriDao(em);
        MezziDAO mezziDAO = new MezziDAO(em);
        NegozioDao negozioDao = new NegozioDao(em);
        RivenditoriDao rivenditoriDao = new RivenditoriDao(em);
        TesseraDao tesseraDao = new TesseraDaoImpl(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        TramDao tramDao = new TramDao(em);
        UtenteSempliceDAO utenteSempliceDAO = new UtenteSempliceDAO(em);

        //creiamo le istanze delle varie classi e le salviamo nel db
        try {
            // Avvio della transazione
            em.getTransaction().begin();

            // Creazione delle istanze delle tratte
            Tratta tratta1 = new Tratta("Napoli", "Salerno", 45.0);
            Tratta tratta2 = new Tratta("Roma", "Firenze", 90.0);
            Tratta tratta3 = new Tratta("Milano", "Bologna", 120.0);

            // Creazione delle istanze degli autobus
            Autobus autobus1 = new Autobus(Mezzi.Stato.InServizio, "Linea A", tratta1);
            Autobus autobus2 = new Autobus(Mezzi.Stato.InManutenzione, "Linea B", tratta2);
            Autobus autobus3 = new Autobus(Mezzi.Stato.InServizio, "Linea C", tratta3);

            // Creazione delle istanze dei tram
            Tram tram1 = new Tram(Mezzi.Stato.InServizio, "Tram A", tratta1);
            Tram tram2 = new Tram(Mezzi.Stato.InManutenzione, "Tram B", tratta2);
            Tram tram3 = new Tram(Mezzi.Stato.InServizio, "Tram C", tratta3);

            // Creazione delle istanze dei distributori
            Distributori distributore1 = new Distributori(1000, 200, "Automatico", "Piazza del Plebiscito, Napoli");
            Distributori distributore2 = new Distributori(1500, 300, "Manuale", "Stazione Termini, Roma");
            Distributori distributore3 = new Distributori(1200, 250, "Automatico", "Piazza del Duomo, Milano");

            //Creazione delle istanze dei negozi
            Negozi negozio1 = new Negozi(100, 50, "Negozio A", "Via Roma 1");
            Negozi negozio2 = new Negozi(200, 75, "Negozio B", "Corso Italia 5");
            Negozi negozio3 = new Negozi(150, 60, "Negozio C", "Piazza Duomo 3");

            // Salvataggio tramite DAO
            trattaDAO.save(tratta1);
            trattaDAO.save(tratta2);
            trattaDAO.save(tratta3);

            autobusDao.save(autobus1);
            autobusDao.save(autobus2);
            autobusDao.save(autobus3);

            tramDao.save(tram1);
            tramDao.save(tram2);
            tramDao.save(tram3);

            distributoriDao.save(distributore1);
            distributoriDao.save(distributore2);
            distributoriDao.save(distributore3);

            negozioDao.save(negozio1);
            negozioDao.save(negozio2);
            negozioDao.save(negozio3);


            // Commit della transazione
            em.getTransaction().commit();
        } catch (Exception e) {
            // Gestione degli errori
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            // Chiusura del EntityManager
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

        System.out.println("Premere 1 per Amministratore, 2 per Utente");
        char tipoUtente;
        while (true){
            tipoUtente = scanner.next().charAt(0);
            if(tipoUtente == '1')
            {

                System.out.println("Hai selezionato amministratore");
                break;
            } else if (tipoUtente == '2') {
                System.out.println("Vuoi comprare un biglietto? (si/no)");
                String risposta = scanner.next();
                if(Objects.equals(risposta, "si"))
                {
                    System.out.println("Hai selezionato Utente");
                    Biglietto biglietto = new Biglietto();
                    biglietto.partenza();
                } else if (Objects.equals(risposta, "no")) {
                    System.out.println("Vuoi fare una tessera?");
                    String risposta1 = scanner.next();
                    if(Objects.equals(risposta1, "si")){
                        System.out.println("okay facciamo una tessera");
                        Tessera tessera = new Tessera();
                        tessera.creaTessera();
                        logger.info("Tessera creata e salvata nel fb");
                    } else if (Objects.equals(risposta1, "no")) {
                        System.out.println("Non ci sono altre opzioni, ciao");
                        //logia per ricominciare
                    }
                } else {
                    System.out.println("Dare una risposta valida");
                }

                break;
            }  else {
                System.out.println("Input non valido");
            }


            scanner.close();
        }
    }}
