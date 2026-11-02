package vulnlab.sqli.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class SqliSpringImplicitParam {
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/springimplicitparam")
    public Object handle(String name) {
        return jdbcTemplate.queryForList("SELECT * FROM users WHERE name = '" + name + "'");
    }
}
