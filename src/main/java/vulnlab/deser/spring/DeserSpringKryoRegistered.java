package vulnlab.deser.spring;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import org.springframework.web.bind.annotation.*;
import vulnlab.common.Order;

/** Kryo 5 par défaut : seules les classes enregistrées sont acceptées. */
@RestController
public class DeserSpringKryoRegistered {
    @PostMapping("/deser/deserspringkryoregistered")
    public Object handle(@RequestBody byte[] body) {
        Kryo kryo = new Kryo();
        kryo.register(Order.class);
        return kryo.readClassAndObject(new Input(body));
    }
}
