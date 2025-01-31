package org.example;

import DAO.*;
import Entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("defaultdb");
        EntityManager em = emf.createEntityManager();
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


        while (true) {
            System.out.println("\nSeleziona il tipo di utente:");
            System.out.println("1. Utente semplice");
            System.out.println("2. Utente amministratore");
            System.out.print("Seleziona un'operazione: ");

            int tipoUtente = scanner.nextInt();
            scanner.nextLine();

            ValidationService validationService = new ValidationService(abbonamentoDAO,tesseraDao);


            switch (tipoUtente) {
                case 1 -> gestioneUtenteSemplice(scanner, em, validationService, bigliettoDao, tesseraDao);
                case 2 -> gestioneAmministratore(scanner, em, abbonamentoDAO, bigliettoDao, mezziDAO, trattaDAO);
                default -> System.out.println("Scelta non valida.");
            }
        }
    }

    private static void gestioneUtenteSemplice(Scanner scanner, EntityManager em, ValidationService validationService,
                                               BigliettoDao bigliettoDao, TesseraDao tesseraDao) {
        while (true) {
            System.out.println("\nMenu utente semplice:");
            System.out.println("1. Acquista biglietto");
            System.out.println("2. Acquista abbonamento");
            System.out.println("0. Torna al menu principale");
            System.out.print("Seleziona un'operazione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (scelta) {
                    case 1 -> acquistaBiglietto(scanner, em, validationService, bigliettoDao);
                    case 2 -> acquistaAbbonamento(scanner, em, validationService, tesseraDao);
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Scelta non valida.");
                }
            } catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
            }
        }
    }

    private static void acquistaBiglietto(Scanner scanner, EntityManager em, ValidationService validationService, BigliettoDao bigliettoDao) {
        System.out.println("Hai una tessera attiva? (si/no)");
        String risposta = scanner.nextLine();

        if (risposta.equalsIgnoreCase("si")) {
            System.out.println("Inserisci il numero della tessera per il controllo:");
            String numeroTessera = scanner.nextLine();
            if (validationService.verificaTessera(Long.parseLong(numeroTessera))) {
                System.out.println("Tessera verificata e attiva. Procedi con l'acquisto del biglietto singolo.");
            } else {
                System.out.println("Tessera non attiva o scaduta. Procedi comunque con l'acquisto del biglietto singolo.");
            }
        } else {
            System.out.println("Procedi con l'acquisto del biglietto singolo senza tessera.");
        }

        // Logica per l'acquisto del biglietto singolo
        // bigliettoDao.salvaBiglietto(nuovoBiglietto);

        System.out.println("Biglietto singolo acquistato.");
    }

    private static boolean acquistaAbbonamento(Scanner scanner, EntityManager em, ValidationService validationService, TesseraDao tesseraDao) {
        System.out.println("Hai una tessera? (si/no)");
        String risposta = scanner.nextLine();

        if (risposta.equalsIgnoreCase("no")) {
            System.out.println("Vuoi creare una tessera? (si/no)");
            String creaTessera = scanner.nextLine();
            if (creaTessera.equalsIgnoreCase("si")) {
                System.out.println("okay facciamo una tessera");
                Tessera tessera = new Tessera();
                tessera.creaTessera();
                System.out.println("Tessera creata e attivata. Numero tessera: " + tessera.getNumeroTessera());
            } else {
                System.out.println("Operazione annullata. Torna al menu principale.");
                return false;
            }
        } else if (risposta.equalsIgnoreCase("si")) {
            // Controllo dello stato della tessera
            System.out.println("Inserisci il numero della tessera:");
            Long numeroTessera = Long.parseLong(scanner.nextLine());
            boolean tesseraValida = validationService.verificaTessera(numeroTessera);

            if (tesseraValida) {
                System.out.println("Tessera attiva. Procedi con l'acquisto dell'abbonamento.");
            } else {
                System.out.println("Tessera scaduta. Vuoi procedere con il rinnovo? (si/no)");
                String rinnovoRisposta = scanner.nextLine();
                if (rinnovoRisposta.equalsIgnoreCase("si")) {
                    boolean rinnovoSuccesso = validationService.rinnovaTessera(numeroTessera);
                    if (rinnovoSuccesso) {
                        System.out.println("Rinnovo della tessera completato. Procedi con l'acquisto dell'abbonamento.");
                    } else {
                        System.out.println("Errore nel rinnovo della tessera. Operazione annullata.");
                        return false;
                    }
                } else {
                    System.out.println("Rinnovo annullato. Operazione annullata.");
                    return false;
                }
            }
        } else {
            System.out.println("Risposta non valida.");
            return false;
        }

        // Acquisto dell'abbonamento
        System.out.println("Seleziona il tipo di abbonamento:");
        System.out.println("1. Settimanale");
        System.out.println("2. Mensile");
        int tipoAbbonamento = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Abbonamento " + (tipoAbbonamento == 1 ? "settimanale" : "mensile") + " acquistato.");
        return true;
    }


    private static void visualizzaStatisticheTratte(Scanner scanner, TrattaDAO trattaDAO) {
        System.out.println("Inserisci la data di inizio (yyyy-MM-dd):");
        String startDate = scanner.nextLine();
        System.out.println("Inserisci la data di fine (yyyy-MM-dd):");
        String endDate = scanner.nextLine();
        System.out.println("Inserisci l'ID del mezzo (o lascia vuoto per tutte le tratte):");
        String mezzoIdInput = scanner.nextLine();

        long totalePercorrenze;
        double tempoMedioPercorrenza;
        if (mezzoIdInput.isEmpty()) {
            totalePercorrenze = trattaDAO.getTotalePercorrenze(startDate, endDate);
            System.out.println("Totale percorrenze nel periodo specificato: " + totalePercorrenze);
        } else {
            Long mezzoId = Long.parseLong(mezzoIdInput);
            totalePercorrenze = trattaDAO.getTotalePercorrenzePerMezzo(mezzoId, startDate, endDate);
            tempoMedioPercorrenza = trattaDAO.getTempoMedioPercorrenza(mezzoId, startDate, endDate);
            System.out.println("Totale percorrenze per il mezzo " + mezzoId + " nel periodo specificato: " + totalePercorrenze);
            System.out.println("Tempo medio di percorrenza per il mezzo " + mezzoId + ": " + tempoMedioPercorrenza + " minuti");
        }
    }

    private static void gestioneAmministratore(Scanner scanner, EntityManager em, AbbonamentoDao abbonamentoDao, BigliettoDao bigliettoDao, MezziDAO mezziDAO, TrattaDAO trattaDAO) {
        while (true) {
            System.out.println("\nMenu amministratore:");
            System.out.println("1. Visualizza biglietti/abbonamenti venduti");
            System.out.println("2. Visualizza periodo di servizio/manutenzione dei mezzi");
            System.out.println("3. Visualizza biglietti vidimati");
            System.out.println("4. Visualizza statistiche sulle tratte");
            System.out.println("0. Torna al menu principale");
            System.out.print("Seleziona un'operazione: ");

            int scelta = scanner.nextInt();
            scanner.nextLine(); // Consuma il newline

            try {
                switch (scelta) {
                    case 1 -> visualizzaBigliettiAbbonamentiVenduti(scanner, bigliettoDao, abbonamentoDao);
                    case 2 -> visualizzaPeriodoServizioManutenzione(scanner, mezziDAO);
                    case 3 -> visualizzaBigliettiVidimati(scanner, bigliettoDao);
                    case 4 -> visualizzaStatisticheTratte(scanner, trattaDAO);
                    case 0 -> {
                        return;
                    }
                    default -> System.out.println("Scelta non valida.");
                }
            } catch (Exception e) {
                System.err.println("Errore: " + e.getMessage());
            }
        }
    }

    private static void visualizzaBigliettiAbbonamentiVenduti(Scanner scanner, BigliettoDao bigliettoDao, AbbonamentoDao abbonamentoDao) {
        System.out.println("Inserisci la data di inizio (yyyy-MM-dd):");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Inserisci la data di fine (yyyy-MM-dd):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        long totaleBiglietti = bigliettoDao.getTotaleBigliettiVenduti(startDate, endDate);
        long totaleAbbonamenti = abbonamentoDao.getTotaleAbbonamentiVenduti(startDate, endDate);

        System.out.println("Totale biglietti venduti: " + totaleBiglietti);
        System.out.println("Totale abbonamenti venduti: " + totaleAbbonamenti);
    }

    private static void visualizzaPeriodoServizioManutenzione(Scanner scanner, MezziDAO mezziDAO) {
        System.out.println("Inserisci la data di inizio (yyyy-MM-dd):");
        String startDate = scanner.nextLine();
        System.out.println("Inserisci la data di fine (yyyy-MM-dd):");
        String endDate = scanner.nextLine();

        List<Mezzi> mezziInServizio = mezziDAO.findInServicePeriod(startDate, endDate);
        List<Mezzi> mezziInManutenzione = mezziDAO.findInMaintenancePeriod(startDate, endDate);

        System.out.println("Mezzi in servizio nel periodo specificato:");
        for (Mezzi mezzo : mezziInServizio) {
            System.out.println("ID Mezzo: " + mezzo.getId() + ", Stato: " + mezzo.getStato());
        }

        System.out.println("Mezzi in manutenzione nel periodo specificato:");
        for (Mezzi mezzo : mezziInManutenzione) {
            System.out.println("ID Mezzo: " + mezzo.getId() + ", Stato: " + mezzo.getStato());
        }
    }

    private static void visualizzaBigliettiVidimati(Scanner scanner, BigliettoDao bigliettoDao) {
        System.out.println("Inserisci la data di inizio (yyyy-MM-dd):");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());
        System.out.println("Inserisci la data di fine (yyyy-MM-dd):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        long totaleBigliettiVidimati = bigliettoDao.getTotaleBigliettiVidimati(startDate, endDate);

        System.out.println("Totale biglietti vidimati nel periodo specificato: " + totaleBigliettiVidimati);
    }
}
