package DAO;

import Entities.Autobus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AutobusDao {

    private EntityManagerFactory emf;
    private EntityManager em;

    private static final Logger logger = LoggerFactory.getLogger(AutobusDao.class);

    public AutobusDao(EntityManager em) {
        emf = Persistence.createEntityManagerFactory("defaultdb");
        this.em = emf.createEntityManager();
    }

    // Metodo per salvare un Autobus nel database
    public void save(Autobus autobus) {
        em.getTransaction().begin();
        em.persist(autobus);
        em.getTransaction().commit();

        logger.info("Autobus salvato nel DB");
    }

    // Metodo per trovare un Autobus per ID
    public Autobus findById(Long id) {
        return em.find(Autobus.class, id);
    }

    // Metodo per aggiornare un Autobus nel database
    public void update(Autobus autobus) {
        em.getTransaction().begin();
        em.merge(autobus);
        em.getTransaction().commit();
        logger.info("L'istanza autobus è stata modificata");
    }

    // Metodo per eliminare un Autobus dal database
    public void delete(Long id) {
        Autobus autobus = findById(id);
        if (autobus != null) {
            em.getTransaction().begin();
            em.remove(autobus);
            em.getTransaction().commit();
            logger.info("Autobus eliminato dal  DB!");
        }
    }

    // Metodo per ottenere tutti gli Autobus dal database
    public List<Autobus> findAll() {
        return em.createQuery("SELECT a FROM Autobus a", Autobus.class).getResultList();
    }

    // Metodo per chiudere l'EntityManager e l'EntityManagerFactory
    public void close() {
        if (em != null) {
            em.close();
        }
        if (emf != null) {
            emf.close();
        }
    }
}