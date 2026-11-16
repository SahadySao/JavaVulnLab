package vulnlab.sqli.spring;

import jakarta.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/** @Pattern sur le paramètre, classe @Validated : Spring refuse tout ce qui n'est pas [a-zA-Z_]. Sûr. */
@RestController
@Validated
public class SqliSpringParamPattern {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringParamPattern(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/parampattern")
    public List<Map<String, Object>> handle(@RequestParam @Pattern(regexp = "[a-zA-Z_]+") String sort) {
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort);
    }
}
