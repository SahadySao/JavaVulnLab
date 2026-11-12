package vulnlab.journal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/** Lombok @Slf4j : le champ log est généré (type non résolu sans les jars). */
@Slf4j
@RestController
public class JournalLombok {
    @PostMapping("/journal/comment")
    public String comment(@RequestParam String author) {
        log.info("Commentaire posté par " + author);
        return "ok";
    }
}
