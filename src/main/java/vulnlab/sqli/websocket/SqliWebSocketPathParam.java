package vulnlab.sqli.websocket;

import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.jdbc.core.JdbcTemplate;

/** Jakarta : le nom du salon vient de l'URL /ws/rooms/{room}, choisie par le client. Vulnérable. */
@ServerEndpoint("/ws/rooms/{room}")
public class SqliWebSocketPathParam {
    private JdbcTemplate jdbcTemplate;

    @OnOpen
    public void open(Session session, @PathParam("room") String room) {
        jdbcTemplate.update("INSERT INTO presence(room, session) VALUES ('" + room + "', '" + session.getId() + "')");
    }
}
