package vulnlab.journal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

/** Log4j 2. */
@RestController
public class JournalLog4j2 {
    private static final Logger logger = LogManager.getLogger(JournalLog4j2.class);

    @PostMapping("/journal/order")
    public String order(@RequestParam String reference) {
        logger.error("Commande introuvable : {}", reference);
        return "ko";
    }
}
