package vulnlab.sqli.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

@RestController
public class SqliSpringNamedParameterJdbc {
    private NamedParameterJdbcTemplate namedJdbc;

    @GetMapping("/springnamedparameterjdbc")
    public Object handle(@RequestParam String name) {
        return namedJdbc.queryForList("SELECT * FROM users WHERE name = '" + name + "'", new HashMap<String, Object>());
    }
}
