package vulnlab.sqli.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class SqliSpringPreparedQuery {
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/springpreparedquery")
    public Object handle(@RequestParam String name) {
        return jdbcTemplate.queryForList("SELECT * FROM users WHERE name = ?", name);
    }
}
