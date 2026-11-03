package vulnlab.sqli.websocket;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/** Spring WebSocket brut : le message arrive dans handleTextMessage. Vulnérable. */
public class SqliWebSocketHandler extends TextWebSocketHandler {
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String user = message.getPayload();
        jdbcTemplate.queryForList("SELECT * FROM orders WHERE owner = '" + user + "'");
    }
}
