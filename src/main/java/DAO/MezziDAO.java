package DAO;

import Entities.Mezzi;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MezziDAO {
    private static final Logger logger = LoggerFactory.getLogger(MezziDAO.class);
    private EntityManager em;

    // Costruttore
    public MezziDAO(EntityManager em) {
        this.em = em;
    }

    // Metodo per salvare un mezzo
    public void save(Mezzi mezzo) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(mezzo);
            tx.commit();
            logger.info("Salvato mezzo con ID: " + mezzo.getId());
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            logger.error("Errore nel salvataggio del mezzo", e);
        }
    }

    // Metodo per trovare un mezzo per ID
    public Mezzi findById(Long id) {
        Mezzi mezzo = em.find(Mezzi.class, id);
        if (mezzo != null) {
            logger.info("Trovato mezzo con ID: " + id);
        } else {
            logger.warn("Nessun mezzo trovato con ID: " + id);
        }
        return mezzo;
    }

    // Metodo per aggiornare un mezzo
    public void update(Mezzi mezzo) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.merge(mezzo);
            tx.commit();
            logger.info("Aggiornato mezzo con ID: " + mezzo.getId());
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            logger.error("Errore nell'aggiornamento del mezzo", e);
        }
    }

    // Metodo per eliminare un mezzo
    public void delete(Long id) {
        EntityTransaction tx = em.getTransaction();
        try {
            Mezzi mezzo = em.find(Mezzi.class, id);
            if (mezzo != null) {
                tx.begin();
                em.remove(mezzo);
                tx.commit();
                logger.info("Eliminato mezzo con ID: " + id);
            } else {
                logger.warn("Tentativo di eliminazione fallito: nessun mezzo trovato con ID: " + id);
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }
            logger.error("Errore nell'eliminazione del mezzo", e);
        }
    }

    // Metodo per ottenere tutti i mezzi
    public List<Mezzi> findAll() {
        try {
            List<Mezzi> mezzi = em.createQuery("SELECT m FROM Mezzi m", Mezzi.class).getResultList();
            logger.info("Recuperati " + mezzi.size() + " mezzi dal database.");
            return mezzi;
        } catch (Exception e) {
            logger.error("Errore nel recupero dei mezzi", e);
            return null;
        }
    }
}
