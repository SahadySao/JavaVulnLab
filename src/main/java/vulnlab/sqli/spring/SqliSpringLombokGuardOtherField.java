package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.lombok.LombokSortDto;

/** Lombok @Data : la liste blanche vérifie direction, mais c'est column qui part dans ORDER BY. */
@RestController
public class SqliSpringLombokGuardOtherField {
    private static final Set<String> DIRECTIONS = Set.of("ASC", "DESC");
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringLombokGuardOtherField(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/lombok/guardotherfield")
    public List<Map<String, Object>> handle(@RequestBody LombokSortDto sort) {
        if (!DIRECTIONS.contains(sort.getDirection())) {
            throw new IllegalArgumentException("Sens non autorisé");
        }
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort.getColumn());
    }
}
