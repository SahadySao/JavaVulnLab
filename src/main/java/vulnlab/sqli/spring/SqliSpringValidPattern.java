package vulnlab.sqli.spring;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SortDto;

/** @Valid + @Pattern("[a-zA-Z_]+") sur le champ : la colonne ne peut contenir ni quote ni espace. */
@RestController
public class SqliSpringValidPattern {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringValidPattern(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/validpattern")
    public List<Map<String, Object>> handle(@Valid @RequestBody SortDto sort) {
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort.getColumn());
    }
}
