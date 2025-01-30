package AltreClassi;

import DAO.MezziDAO;
import Entities.Mezzi;
import Entities.Tratta;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PercorriTratta {
    private static final Logger logger = LoggerFactory.getLogger(PercorriTratta.class);

    private final MezziDAO mezziDAO;

    // Costruttore che riceve il MezziDAO
    public PercorriTratta(MezziDAO mezziDAO) {
        this.mezziDAO = mezziDAO;
    }

    // Metodo per incrementare il contatore delle percorrenze
    public void incrementaPercorrenza(Mezzi mezzo, Tratta tratta) {
        // Verifica se il mezzo e la tratta sono validi
        if (mezzo != null && tratta != null) {
            // Incrementa il contatore delle percorrenze del mezzo
            mezzo.incrementaPercorrenza();

            // Aggiorna il mezzo nel database usando il DAO
            mezziDAO.update(mezzo);

            System.out.println("Il mezzo ha percorso la tratta: " + tratta.getPartenza() + " -> " + tratta.getCapolinea());
            System.out.println("Numero di percorrenze aggiornato per il mezzo: " + mezzo.getNumeroPercorrenze());
        } else {
            System.out.println("Errore: mezzo o tratta non validi.");
        }
    }
}

