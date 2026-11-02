package vulnlab.sqli.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class SqliSpringPathVariableLong {
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/springpathvariablelong/{id}")
    public Object handle(@PathVariable Long id) {
        return jdbcTemplate.queryForList("SELECT * FROM users WHERE id = " + id);
    }
}
