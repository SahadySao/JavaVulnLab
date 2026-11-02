package vulnlab.sqli.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class SqliSpringRequestAttribute {
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/springrequestattribute")
    public Object handle(@RequestAttribute("tenant") String tenant) {
        return jdbcTemplate.queryForList("SELECT * FROM " + tenant + "_users");
    }
}
