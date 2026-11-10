package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/** ORDER BY ne peut pas être paramétré : la colonne est vérifiée par une liste blanche. */
@RestController
public class SqliSpringOrderByAllowlist {
    private static final Set<String> SORTABLE = Set.of("name", "created_at", "price");
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringOrderByAllowlist(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/orderbyallowlist")
    public List<Map<String, Object>> handle(@RequestParam String sort) {
        if (!SORTABLE.contains(sort)) {
            throw new IllegalArgumentException("Tri non autorisé");
        }
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort);
    }
}
