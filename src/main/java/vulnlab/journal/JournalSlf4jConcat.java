package vulnlab.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/** SLF4J, message concaténé : login = "x\n2026-11-12 INFO Connexion réussie pour admin". */
@RestController
public class JournalSlf4jConcat {
    private static final Logger log = LoggerFactory.getLogger(JournalSlf4jConcat.class);

    @PostMapping("/journal/login")
    public String login(@RequestParam String login, @RequestParam String password) {
        log.warn("Connexion refusée pour " + login);
        return "refusé";
    }
}
