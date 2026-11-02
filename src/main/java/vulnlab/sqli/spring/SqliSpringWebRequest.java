package vulnlab.sqli.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
public class SqliSpringWebRequest {
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/springwebrequest")
    public Object handle(WebRequest request) {
        String name = request.getParameter("name");
        return jdbcTemplate.queryForList("SELECT * FROM users WHERE name = '" + name + "'");
    }
}
