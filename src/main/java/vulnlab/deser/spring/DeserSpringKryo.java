package vulnlab.deser.spring;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import org.springframework.web.bind.annotation.*;

/** Kryo sans enregistrement obligatoire : le flux choisit la classe. */
@RestController
public class DeserSpringKryo {
    @PostMapping("/deser/deserspringkryo")
    public Object handle(@RequestBody byte[] body) {
        Kryo kryo = new Kryo();
        kryo.setRegistrationRequired(false);
        return kryo.readClassAndObject(new Input(body));
    }
}
