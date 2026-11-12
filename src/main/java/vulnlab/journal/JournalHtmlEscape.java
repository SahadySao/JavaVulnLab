package vulnlab.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

/** Échappement HTML : laisse les retours à la ligne, ne protège pas le journal. */
@RestController
public class JournalHtmlEscape {
    private static final Logger log = LoggerFactory.getLogger(JournalHtmlEscape.class);

    @PostMapping("/journal/title")
    public String title(@RequestParam String title) {
        log.info("Titre modifié : {}", HtmlUtils.htmlEscape(title));
        return "ok";
    }
}
