package vulnlab.sqli.spring;

import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/** @Pattern sur le paramètre, mais classe sans @Validated : avant Spring 6.1, la contrainte n'est pas vérifiée. */
@RestController
public class SqliSpringParamPatternNoValidated {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringParamPatternNoValidated(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/parampatternnovalidated")
    public List<Map<String, Object>> handle(@RequestParam @Pattern(regexp = "[a-zA-Z_]+") String sort) {
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort);
    }
}
