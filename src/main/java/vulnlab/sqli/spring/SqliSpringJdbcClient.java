package vulnlab.sqli.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.jdbc.core.simple.JdbcClient;

@RestController
public class SqliSpringJdbcClient {
    private JdbcClient jdbcClient;

    @GetMapping("/springjdbcclient")
    public Object handle(@RequestParam String name) {
        return jdbcClient.sql("SELECT * FROM users WHERE name = '" + name + "'").query().listOfRows();
    }
}
