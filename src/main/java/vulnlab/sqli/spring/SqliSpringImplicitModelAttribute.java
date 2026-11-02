package vulnlab.sqli.spring;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.SearchForm;

@RestController
public class SqliSpringImplicitModelAttribute {
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/springimplicitmodelattribute")
    public Object handle(SearchForm form) {
        return jdbcTemplate.queryForList("SELECT * FROM users WHERE name = '" + form.getName() + "'");
    }
}
