package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/** Le sink est DANS la branche où la liste blanche est vérifiée. */
@RestController
public class SqliSpringGuardInside {
    private static final Set<String> SORTABLE = Set.of("name", "price");
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringGuardInside(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/guardinside")
    public List<Map<String, Object>> handle(@RequestParam String sort) {
        if (SORTABLE.contains(sort)) {
            return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort);
        }
        return List.of();
    }
}
