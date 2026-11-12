package vulnlab.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/** Retours à la ligne retirés avant le log. */
@RestController
public class JournalCrlfRemoved {
    private static final Logger log = LoggerFactory.getLogger(JournalCrlfRemoved.class);

    @PostMapping("/journal/login-safe")
    public String login(@RequestParam String login) {
        String clean = login.replaceAll("[\r\n]", "_");
        log.warn("Connexion refusée pour {}", clean);
        return "refusé";
    }
}
