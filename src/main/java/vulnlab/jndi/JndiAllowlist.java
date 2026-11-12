package vulnlab.jndi;

import java.util.Set;
import javax.naming.InitialContext;
import org.springframework.web.bind.annotation.*;

/** Liste blanche des noms autorisés. */
@RestController
public class JndiAllowlist {
    private static final Set<String> ALLOWED = Set.of("java:comp/env/jdbc/main", "java:comp/env/jdbc/archive");

    @GetMapping("/jndi/allowlist")
    public String handle(@RequestParam String name) throws Exception {
        if (!ALLOWED.contains(name)) {
            throw new IllegalArgumentException("nom refusé");
        }
        return String.valueOf(new InitialContext().lookup(name));
    }
}
