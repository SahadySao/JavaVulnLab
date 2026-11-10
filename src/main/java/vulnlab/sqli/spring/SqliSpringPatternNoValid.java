package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SortDto;

/** @Pattern sur le champ, mais PAS de @Valid sur le paramètre : la contrainte n'est jamais vérifiée. */
@RestController
public class SqliSpringPatternNoValid {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringPatternNoValid(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/patternnovalid")
    public List<Map<String, Object>> handle(@RequestBody SortDto sort) {
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort.getColumn());
    }
}
