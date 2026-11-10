package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/** Regex trop large : l'apostrophe et l'espace sont acceptés ("x' OR 'a'='a"). */
@RestController
public class SqliSpringRegexWeak {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringRegexWeak(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/regexweak")
    public List<Map<String, Object>> handle(@RequestParam String name) {
        if (!name.matches("[a-zA-Z' =]+")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        return jdbcTemplate.queryForList("SELECT * FROM users WHERE name = '" + name + "'");
    }
}
