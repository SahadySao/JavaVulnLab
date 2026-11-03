package vulnlab.sqli.websocket;

import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/** Spring STOMP : recherche en direct, terme envoyé par le navigateur. Vulnérable. */
@Controller
public class SqliWebSocketStomp {
    private JdbcTemplate jdbcTemplate;

    @MessageMapping("/search")
    @SendToUser("/queue/results")
    public List<Map<String, Object>> search(String term) {
        return jdbcTemplate.queryForList("SELECT name, email FROM users WHERE name LIKE '%" + term + "%'");
    }
}
