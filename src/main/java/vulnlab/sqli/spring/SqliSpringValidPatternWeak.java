package vulnlab.sqli.spring;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SortDto;

/** @Valid, mais la regex du champ `label` accepte l'apostrophe et l'espace. */
@RestController
public class SqliSpringValidPatternWeak {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringValidPatternWeak(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/validpatternweak")
    public List<Map<String, Object>> handle(@Valid @RequestBody SortDto sort) {
        return jdbcTemplate.queryForList("SELECT * FROM products WHERE label = '" + sort.getLabel() + "'");
    }
}
