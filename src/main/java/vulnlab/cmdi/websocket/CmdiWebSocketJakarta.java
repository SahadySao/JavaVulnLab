package vulnlab.cmdi.websocket;

import jakarta.websocket.OnMessage;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

/** Jakarta : outil de supervision, l'hôte est envoyé par le navigateur. Vulnérable. */
@ServerEndpoint("/ws/ping")
public class CmdiWebSocketJakarta {

    @OnMessage
    public void onMessage(String host, Session session) throws Exception {
        Process p = Runtime.getRuntime().exec(new String[] {"sh", "-c", "ping -c 1 " + host});
        session.getBasicRemote().sendText(new String(p.getInputStream().readAllBytes()));
    }
}
