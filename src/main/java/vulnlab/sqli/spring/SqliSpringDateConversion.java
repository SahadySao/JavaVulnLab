package vulnlab.sqli.spring;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/** La valeur est convertie en LocalDate avant d'entrer dans la requête : elle ne peut plus porter de texte. */
@RestController
public class SqliSpringDateConversion {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringDateConversion(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/dateconversion")
    public List<Map<String, Object>> handle(@RequestParam String depuis) {
        LocalDate date = LocalDate.parse(depuis);
        return jdbcTemplate.queryForList("SELECT * FROM factures WHERE date_emission >= '" + date + "'");
    }
}
