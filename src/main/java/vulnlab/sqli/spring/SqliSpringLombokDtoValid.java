package vulnlab.sqli.spring;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.lombok.*;

/** Lombok @Data + @Pattern sur le champ, @Valid sur la source : sûr. */
@RestController
public class SqliSpringLombokDtoValid {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringLombokDtoValid(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/lombok/dtovalid")
    public List<Map<String, Object>> handle(@Valid @RequestBody LombokPatternDto sort) {
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort.getColumn());
    }
}
