package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.StoredDocument;
import vulnlab.common.StoredDocumentRepository;

/** La requête concatène le TITRE lu en base, pas la valeur de l'utilisateur (second ordre au plus). */
@RestController
public class SqliSpringRepositoryLookup {
    private final StoredDocumentRepository documentRepository;
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringRepositoryLookup(StoredDocumentRepository documentRepository, JdbcTemplate jdbcTemplate) {
        this.documentRepository = documentRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/sqlispringrepositorylookup")
    public List<Map<String, Object>> handle(@RequestParam String id) {
        StoredDocument doc = documentRepository.findById(id).orElseThrow();
        return jdbcTemplate.queryForList("SELECT * FROM audit WHERE title = '" + doc.getTitle() + "'");
    }
}
