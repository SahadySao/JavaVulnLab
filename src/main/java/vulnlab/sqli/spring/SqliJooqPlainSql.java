package vulnlab.sqli.spring;

import org.jooq.DSLContext;
import org.springframework.web.bind.annotation.*;

/** jOOQ en SQL brut : la chaîne est envoyée telle quelle. */
@RestController
public class SqliJooqPlainSql {
    private final DSLContext dsl;

    public SqliJooqPlainSql(DSLContext dsl) {
        this.dsl = dsl;
    }

    @GetMapping("/sqli/jooqplain")
    public Object handle(@RequestParam String name) {
        return dsl.fetch("SELECT * FROM products WHERE name = '" + name + "'").formatJSON();
    }
}
