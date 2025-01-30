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

        // Creazione e persistenza di Tratta e Autobus
        String partenza = faker.address().cityName();
        String capolinea = faker.address().cityName();
        double tempoDiPercorrenza = faker.number().randomDouble(2, 30, 120);
        Tratta tratta = new Tratta(partenza, capolinea, tempoDiPercorrenza);
        Mezzi.Stato stato = Mezzi.Stato.values()[faker.number().numberBetween(0, Mezzi.Stato.values().length)];
        String descrizione = faker.lorem().sentence();
        Autobus autobus = new Autobus(stato, descrizione, tratta);

        em.getTransaction().begin();
        try {
            em.persist(tratta);
            em.persist(autobus);
            em.getTransaction().commit();
            System.out.println("Le entità sono state salvate nel database.");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }

        // Ciclo principale per la scelta dell'utente
        while (true) {
            System.out.println("Premere 1 per Amministratore, Premere 2 per Utente, Premere Q per uscire");
            String input = scanner.next().toUpperCase();

            switch (input) {
                case "1":
                    System.out.println("Hai scelto Amministratore");
                    gestisciAmministratore(scanner, distributoriDao, mezziDAO);
                    break;
                case "2":
                    System.out.println("Hai scelto Utente");
                    gestisciUtente(scanner, utenteSempliceDAO, abbonamentoDao);
                    break;
                case "Q":
                    System.out.println("Uscita dal programma.");
                    scanner.close();
                    em.close();
                    emf.close();
                    return;
                default:
                    System.out.println("Input non valido. Per favore, premi 1 per Amministratore, 2 per Utente o Q per uscire.");
                    break;
            }
        }
    }

    private static void gestisciAmministratore(Scanner scanner, DistributoriDao distributoriDao, MezziDAO mezziDAO) {
        while (true) {
            System.out.println("Scegli un'opzione: 1. Controlla Distributori, 2. Controlla Mezzi, 3. Torna alla scelta iniziale");
            String input = scanner.next();

            switch (input) {
                case "1":
                    System.out.println("Hai scelto Controlla Distributori");
                    // Logica per controllare i distributori
                    break;
                case "2":
                    System.out.println("Hai scelto Controlla Mezzi");
                    // Logica per controllare i mezzi
                    break;
                case "3":
                    System.out.println("Torna alla scelta iniziale.");
                    return;
                default:
                    System.out.println("Input non valido.");
                    break;
            }
        }
    }

    private static void gestisciUtente(Scanner scanner, UtenteSempliceDAO utenteSempliceDAO, AbbonamentoDaoImpl abbonamentoDao) {
        while (true) {
            System.out.println("Hai la tessera? (SI/NO)");
            String rispostaTessera = scanner.next().toUpperCase();

            if (rispostaTessera.equals("SI")) {
                System.out.println("Inserisci il numero della tessera:");
                String numeroTessera = scanner.next();
                System.out.println("La tessera è attiva? (SI/NO)");
                String tesseraAttiva = scanner.next().toUpperCase();

                if (tesseraAttiva.equals("SI")) {
                    System.out.println("Scegli il tipo di abbonamento: 1. Settimanale, 2. Mensile");
                    int sceltaAbbonamento = scanner.nextInt();
                    switch (sceltaAbbonamento) {
                        case 1:
                            System.out.println("Hai scelto l'abbonamento Settimanale");
                            // Logica per l'abbonamento settimanale
                            break;
                        case 2:
                            System.out.println("Hai scelto l'abbonamento Mensile");
                            // Logica per l'abbonamento mensile
                            break;
                        default:
                            System.out.println("Scelta non valida.");
                            break;
                    }
                } else {
                    System.out.println("La tessera non è attiva.");
                }
            }else if (rispostaTessera.equals("NO")) {
                System.out.println("Vuoi fare la tessera? (SI/NO)");
                String creaTessera = scanner.next().toUpperCase();
                if (creaTessera.equals("SI")) {
                    System.out.println("Tessera creata.");
                    // Logica per creare la tessera
                } else {
                    System.out.println("Vuoi fare un biglietto? (SI/NO)");
                    String creaBiglietto = scanner.next().toUpperCase();
                    if (creaBiglietto.equals("SI")) {
                        System.out.println("Per quale mezzo? (autobus/tram)");
                        String mezzo = scanner.next().toUpperCase();
                        if (mezzo.equals("AUTOBUS")) {
                            System.out.println("Biglietto per autobus creato.");
                            // Logica per creare il biglietto per autobus
                        } else if (mezzo.equals("TRAM")) {
                            System.out.println("Biglietto per tram creato.");
                            // Logica per creare il biglietto per tram
                        } else {
                            System.out.println("Mezzo non valido. Riprova.");
                        }
                    } else if (creaBiglietto.equals("NO")) {
                        System.out.println("Operazione terminata.");
                    } else {
                        System.out.println("Input non valido. Riprova.");
                    }
                }
            } else {
                System.out.println("Input non valido.");
            }
        }
    }
}
