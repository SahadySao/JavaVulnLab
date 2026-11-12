package vulnlab.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/** Seul \n est retiré : \r reste (réécrit la ligne affichée dans un terminal, coupe la ligne pour certains outils). */
@RestController
public class JournalOnlyLf {
    private static final Logger log = LoggerFactory.getLogger(JournalOnlyLf.class);

    @PostMapping("/journal/login-lf")
    public String login(@RequestParam String login) {
        log.warn("Connexion refusée pour {}", login.replace("\n", ""));
        return "refusé";
    }
}
