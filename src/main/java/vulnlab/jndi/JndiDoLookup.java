package vulnlab.jndi;

import javax.naming.InitialContext;
import org.springframework.web.bind.annotation.*;

/** InitialContext.doLookup (statique). */
@RestController
public class JndiDoLookup {
    @GetMapping("/jndi/dolookup")
    public String handle(@RequestParam String name) throws Exception {
        Object found = InitialContext.doLookup(name);
        return String.valueOf(found);
    }
}
