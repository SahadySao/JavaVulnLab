package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.web.bind.annotation.*;

/** Précision : queryForList avec des paramètres nommés liés (:name). Le texte de la requête est fixe, la valeur part
  * dans la Map des paramètres : sûr. Avec l'import générique, le type n'est connu qu'avec le jar. */
@RestController
public class SqliTypesHomonym {
    private final NamedParameterJdbcOperations named;

    public SqliTypesHomonym(NamedParameterJdbcOperations named) {
        this.named = named;
    }

    @GetMapping("/sqli/types/homonym")
    public List<Map<String, Object>> handle(@RequestParam String name) {
        return named.queryForList("SELECT * FROM products WHERE name = :name", Map.of("name", name));
    }
}
