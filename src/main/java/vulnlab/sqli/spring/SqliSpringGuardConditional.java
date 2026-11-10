package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/** La vérification ne s'applique que si strict = true : sans lui, rien n'est vérifié. */
@RestController
public class SqliSpringGuardConditional {
    private static final Set<String> SORTABLE = Set.of("name", "price");
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringGuardConditional(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/guardconditional")
    public List<Map<String, Object>> handle(@RequestParam String sort, @RequestParam boolean strict) {
        if (strict && !SORTABLE.contains(sort)) {
            throw new IllegalArgumentException("Tri non autorisé");
        }
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort);
    }
}
