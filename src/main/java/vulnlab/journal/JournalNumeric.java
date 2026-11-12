package vulnlab.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/** Identifiant numérique : aucun retour à la ligne possible. */
@RestController
public class JournalNumeric {
    private static final Logger log = LoggerFactory.getLogger(JournalNumeric.class);

    @DeleteMapping("/journal/invoice/{id}")
    public String delete(@PathVariable long id) {
        log.info("Suppression de la facture {}", id);
        return "ok";
    }
}
