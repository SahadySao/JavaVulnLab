package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.lombok.LombokSortDto;

/** Lombok @Data : liste blanche sur sort.getColumn() (getter généré), puis la même valeur dans ORDER BY. Sûr. */
@RestController
public class SqliSpringLombokGuard {
    private static final Set<String> SORTABLE = Set.of("name", "price");
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringLombokGuard(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/lombok/guard")
    public List<Map<String, Object>> handle(@RequestBody LombokSortDto sort) {
        if (!SORTABLE.contains(sort.getColumn())) {
            throw new IllegalArgumentException("Tri non autorisé");
        }
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort.getColumn());
    }
}
