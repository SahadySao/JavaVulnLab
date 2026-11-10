package vulnlab.sqli.spring;

import java.util.List;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.TitleSearchRepository;

/** Contrôleur du cas SqliSpringRepositoryImpl : le champ est typé par l'interface. */
@RestController
public class SqliSpringRepositoryImplController {
    private final TitleSearchRepository titleRepository;

    public SqliSpringRepositoryImplController(TitleSearchRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    @GetMapping("/sqli/sqlispringrepositoryimpl")
    public List<?> handle(@RequestParam String title) {
        return titleRepository.findByTitleUnsafe(title);
    }
}
