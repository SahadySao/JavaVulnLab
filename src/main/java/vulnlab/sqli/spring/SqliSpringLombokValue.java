package vulnlab.sqli.spring;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.lombok.*;

/** Lombok @Value (immuable) : getName() généré, concaténé. */
@RestController
public class SqliSpringLombokValue {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringLombokValue(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/lombok/value")
    public List<Map<String, Object>> handle(@RequestBody LombokImmutableDto dto) {
        return jdbcTemplate.queryForList("SELECT * FROM products WHERE name = '" + dto.getName() + "'");
    }
}
