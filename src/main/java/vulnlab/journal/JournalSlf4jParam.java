package vulnlab.journal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/** SLF4J paramétré : les arguments {} sont écrits tels quels, sans échappement. */
@RestController
public class JournalSlf4jParam {
    private static final Logger LOGGER = LoggerFactory.getLogger(JournalSlf4jParam.class);

    @GetMapping("/journal/search")
    public String search(@RequestParam String query) {
        LOGGER.info("Recherche : {}", query);
        return "[]";
    }
}
