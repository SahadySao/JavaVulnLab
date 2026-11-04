package vulnlab.deser.spring;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Base64;
import org.springframework.web.bind.annotation.*;

/** Objet sérialisé dans un cookie, encodé en Base64 ("rO0AB...") : le cas réel le plus courant. */
@RestController
public class DeserSpringCookieBase64 {
    @GetMapping("/deser/deserspringcookiebase64")
    public Object handle(@CookieValue("session") String session) throws Exception {
        byte[] data = Base64.getDecoder().decode(session);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(data))) {
            return ois.readObject();
        }
    }
}
