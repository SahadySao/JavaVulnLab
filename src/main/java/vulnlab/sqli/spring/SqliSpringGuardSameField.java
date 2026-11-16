package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SortDto;

/** La liste blanche vérifie le champ concaténé lui-même (free) : sûr. */
@RestController
public class SqliSpringGuardSameField {
    private static final Set<String> SORTABLE = Set.of("name", "price");
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringGuardSameField(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/guardsamefield")
    public List<Map<String, Object>> handle(@RequestBody SortDto sort) {
        if (!SORTABLE.contains(sort.getFree())) {
            throw new IllegalArgumentException("Tri non autorisé");
        }
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort.getFree());
    }
}
