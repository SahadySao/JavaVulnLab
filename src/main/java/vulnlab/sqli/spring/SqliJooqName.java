package vulnlab.sqli.spring;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.web.bind.annotation.*;

/** jOOQ : DSL.name(sort) met la colonne entre guillemets (identifiant), ce n'est pas du SQL brut : sûr. */
@RestController
public class SqliJooqName {
    private final DSLContext dsl;

    public SqliJooqName(DSLContext dsl) {
        this.dsl = dsl;
    }

    @GetMapping("/sqli/jooqname")
    public Object handle(@RequestParam String sort) {
        return dsl.select().from(DSL.table("products")).orderBy(DSL.field(DSL.name(sort))).fetch().formatJSON();
    }
}
