package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SortDto;

/** La liste blanche vérifie un AUTRE champ du DTO (column) : free, concaténé dans la requête, n'est pas vérifié. */
@RestController
public class SqliSpringGuardOtherField {
    private static final Set<String> SORTABLE = Set.of("name", "price");
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringGuardOtherField(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/guardotherfield")
    public List<Map<String, Object>> handle(@RequestBody SortDto sort) {
        if (!SORTABLE.contains(sort.getColumn())) {
            throw new IllegalArgumentException("Tri non autorisé");
        }
        return jdbcTemplate.queryForList("SELECT * FROM products WHERE label = '" + sort.getFree() + "'");
    }
}
