package vulnlab.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/** Le message journalisé ne contient pas la donnée de l'utilisateur. */
@RestController
public class JournalConstantMessage {
    private static final Logger log = LoggerFactory.getLogger(JournalConstantMessage.class);

    @PostMapping("/journal/export-all")
    public String export(@RequestParam String format) {
        log.info("Export complet demandé");
        return "export." + format;
    }
}
