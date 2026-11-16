package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

/** Type renvoyé par une méthode générique de bibliothèque, dans une variable var : getBean(JdbcTemplate.class). */
@RestController
public class SqliTypesGetBean {
    private final ApplicationContext context;

    public SqliTypesGetBean(ApplicationContext context) {
        this.context = context;
    }

    @GetMapping("/sqli/types/getbean")
    public List<Map<String, Object>> handle(@RequestParam String name) {
        var template = context.getBean(JdbcTemplate.class);
        return template.queryForList("SELECT * FROM products WHERE name = '" + name + "'");
    }
}
