package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/** Liste NOIRE : refuse quelques valeurs, laisse passer tout le reste ("name, (SELECT ...)"). */
@RestController
public class SqliSpringOrderByBlocklist {
    private static final Set<String> FORBIDDEN = Set.of("password", "secret");
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringOrderByBlocklist(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/orderbyblocklist")
    public List<Map<String, Object>> handle(@RequestParam String sort) {
        if (FORBIDDEN.contains(sort)) {
            throw new IllegalArgumentException("Tri non autorisé");
        }
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort);
    }
}
