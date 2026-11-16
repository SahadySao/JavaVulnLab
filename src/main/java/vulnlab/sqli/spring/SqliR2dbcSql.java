package vulnlab.sqli.spring;

import java.util.Map;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

/** R2DBC : DatabaseClient.sql(...) avec une concaténation. */
@RestController
public class SqliR2dbcSql {
    private final DatabaseClient databaseClient;

    public SqliR2dbcSql(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @GetMapping("/sqli/r2dbc")
    public Flux<Map<String, Object>> handle(@RequestParam String name) {
        return databaseClient.sql("SELECT * FROM products WHERE name = '" + name + "'").fetch().all();
    }
}
