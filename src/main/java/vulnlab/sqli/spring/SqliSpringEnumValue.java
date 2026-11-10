package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.DocumentType;

/** DocumentType.valueOf(texte) lève une exception hors des constantes : liste blanche de fait. */
@RestController
public class SqliSpringEnumValue {
    private final JdbcTemplate jdbcTemplate;

    public SqliSpringEnumValue(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/sqli/enumvalue")
    public List<Map<String, Object>> handle(@RequestParam String type) {
        DocumentType documentType = DocumentType.valueOf(type.toUpperCase());
        return jdbcTemplate.queryForList("SELECT * FROM documents WHERE type = '" + documentType.name() + "'");
    }
}
