package vulnlab.sqli.spring;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.lombok.*;

/** Lombok @Data : getColumn() généré, la colonne choisie part dans ORDER BY. */
@RestController
public class SqliSpringLombokDto {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringLombokDto(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/lombok/dto")
    public List<Map<String, Object>> handle(@RequestBody LombokSortDto sort) {
        return jdbcTemplate.queryForList("SELECT * FROM products ORDER BY " + sort.getColumn());
    }
}
