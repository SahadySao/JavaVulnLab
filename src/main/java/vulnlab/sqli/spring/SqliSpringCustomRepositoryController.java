package vulnlab.sqli.spring;

import java.util.List;
import org.springframework.web.bind.annotation.*;

/** Contrôleur du cas SqliSpringCustomRepository (le sink est dans le repository). */
@RestController
public class SqliSpringCustomRepositoryController {
    private final SqliSpringCustomRepository documentRepository;

    public SqliSpringCustomRepositoryController(SqliSpringCustomRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @GetMapping("/sqli/sqlispringcustomrepository")
    public List<?> handle(@RequestParam String title) {
        return documentRepository.findByTitleUnsafe(title);
    }
}
