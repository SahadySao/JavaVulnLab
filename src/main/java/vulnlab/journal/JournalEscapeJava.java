package vulnlab.journal;

import org.apache.commons.text.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/** escapeJava : \r et \n deviennent les textes \\r et \\n. */
@RestController
public class JournalEscapeJava {
    private static final Logger log = LoggerFactory.getLogger(JournalEscapeJava.class);

    @PostMapping("/journal/feedback")
    public String feedback(@RequestParam String message) {
        log.info("Avis reçu : {}", StringEscapeUtils.escapeJava(message));
        return "merci";
    }
}
