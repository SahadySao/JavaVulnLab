package vulnlab.sqli.spring;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/** Le terme est rangé dans une Map du service (singleton), puis relu et concaténé dans la requête. */
@RestController
public class SqliSpringSingletonCache {
    private final SearchHistoryService history;

    public SqliSpringSingletonCache(SearchHistoryService history) {
        this.history = history;
    }

    @GetMapping("/sqli/singletoncache")
    public List<Map<String, Object>> handle(@RequestParam String user, @RequestParam String term) {
        history.remember(user, term);
        return history.searchLast(user);
    }
}

@Service
class SearchHistoryService {
    private final Map<String, String> lastTerms = new HashMap<>();
    private final JdbcTemplate jdbcTemplate;

    SearchHistoryService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    void remember(String user, String term) {
        lastTerms.put(user, term);
    }

    List<Map<String, Object>> searchLast(String user) {
        return jdbcTemplate.queryForList("SELECT * FROM products WHERE name = '" + lastTerms.get(user) + "'");
    }
}
