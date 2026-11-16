package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.*;
import org.springframework.web.bind.annotation.*;

/** Import générique (org.springframework.jdbc.core.*) et nom de champ non conventionnel : sans le jar, le type du
  * champ reste inconnu. */
@RestController
public class SqliTypesWildcardImport {
    private final JdbcOperations ops;

    public SqliTypesWildcardImport(JdbcOperations ops) {
        this.ops = ops;
    }

    @GetMapping("/sqli/types/wildcard")
    public List<Map<String, Object>> handle(@RequestParam String name) {
        return ops.queryForList("SELECT * FROM products WHERE name = '" + name + "'");
    }
}
