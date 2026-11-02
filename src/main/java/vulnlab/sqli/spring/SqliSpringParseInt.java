package vulnlab.sqli.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class SqliSpringParseInt {
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/sqli/parseint")
    public Object handle(@RequestParam String id) {
        int userId = Integer.parseInt(id); // nettoyage : ne peut plus contenir de quote
        return jdbcTemplate.queryForList("SELECT * FROM users WHERE id = " + userId);
    }
}
