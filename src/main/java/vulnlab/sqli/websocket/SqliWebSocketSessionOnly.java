package vulnlab.sqli.websocket;

import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.jdbc.core.JdbcTemplate;

/** Jakarta : seul l'identifiant de session (généré par le serveur) est utilisé. Sûr. */
@ServerEndpoint("/ws/presence")
public class SqliWebSocketSessionOnly {
    private JdbcTemplate jdbcTemplate;

    @OnOpen
    public void open(Session session) {
        jdbcTemplate.update("INSERT INTO presence(session) VALUES ('" + session.getId() + "')");
    }
}
