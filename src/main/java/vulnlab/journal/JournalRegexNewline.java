package vulnlab.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/** La regex accepte \n (retour à la ligne) : name = "x\n2026-11-12 INFO Connexion réussie pour admin". */
@RestController
public class JournalRegexNewline {
    private static final Logger log = LoggerFactory.getLogger(JournalRegexNewline.class);

    @PostMapping("/journal/rename")
    public String rename(@RequestParam String name) {
        if (!name.matches("[a-zA-Z0-9 \\n]+")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        log.info("Renommage en " + name);
        return "ok";
    }
}
