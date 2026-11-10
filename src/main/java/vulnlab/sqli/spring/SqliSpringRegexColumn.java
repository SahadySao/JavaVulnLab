package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/** Regex restrictive : lettres et _ seulement, ni apostrophe ni espace ni parenthèse. */
@RestController
public class SqliSpringRegexColumn {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringRegexColumn(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/regexcolumn")
    public List<Map<String, Object>> handle(@RequestParam String sort) {
        if (!sort.matches("[a-zA-Z_]+")) {
            throw new IllegalArgumentException("Tri non autorisé");
        }
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort);
    }
}
