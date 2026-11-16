package vulnlab.sqli.spring;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.lombok.*;

/** Lombok @Getter : getId() renvoie un Long, aucun caractère SQL possible : sûr. */
@RestController
public class SqliSpringLombokDtoLong {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringLombokDtoLong(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/sqli/lombok/dtolong")
    public List<Map<String, Object>> handle(@RequestBody LombokIdDto dto) {
        return jdbcTemplate.queryForList("SELECT * FROM products WHERE id = " + dto.getId());
    }
}
