package vulnlab.sqli.spring;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.web.bind.annotation.*;

/** Type hérité d'une bibliothèque : getJdbcTemplate() vient de JdbcDaoSupport (dans le jar). Sans le jar, Joern ne
  * sait pas qu'il renvoie un JdbcTemplate. */
@RestController
public class SqliTypesJdbcDaoSupport extends JdbcDaoSupport {

    public SqliTypesJdbcDaoSupport(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @GetMapping("/sqli/types/daosupport")
    public List<Map<String, Object>> handle(@RequestParam String name) {
        return getJdbcTemplate().queryForList("SELECT * FROM products WHERE name = '" + name + "'");
    }
}
