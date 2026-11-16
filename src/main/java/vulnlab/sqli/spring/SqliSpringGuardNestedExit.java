package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/** La branche « refusé » ne sort que si strict = true : sans lui, la valeur refusée atteint la requête. */
@RestController
public class SqliSpringGuardNestedExit {
    private static final Set<String> SORTABLE = Set.of("name", "price");
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringGuardNestedExit(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/guardnestedexit")
    public List<Map<String, Object>> handle(@RequestParam String sort, @RequestParam boolean strict) {
        if (!SORTABLE.contains(sort)) {
            if (strict) {
                throw new IllegalArgumentException("Tri non autorisé");
            }
        }
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort);
    }
}
