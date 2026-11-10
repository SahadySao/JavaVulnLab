package vulnlab.deser.messaging;

import jakarta.jms.ObjectMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/** Écouteur JMS : ObjectMessage.getObject() désérialise le message. Attendu dans « aVerifier ». */
@Component
public class DeserJmsObjectMessage {
    @JmsListener(destination = "factures.entrantes")
    public void onMessage(ObjectMessage message) throws Exception {
        Object invoice = message.getObject();
        System.out.println(invoice);
    }
}
