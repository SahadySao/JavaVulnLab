package vulnlab.cmdi.websocket;

import jakarta.websocket.MessageHandler;

/** Jakarta, API programmatique : gestionnaire enregistré avec session.addMessageHandler(...). Vulnérable. */
public class CmdiWebSocketMessageHandler implements MessageHandler.Whole<String> {

    @Override
    public void onMessage(String host) {
        try {
            Runtime.getRuntime().exec("nslookup " + host);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
