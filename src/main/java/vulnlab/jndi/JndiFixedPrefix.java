package vulnlab.jndi;

import javax.naming.InitialContext;
import org.springframework.web.bind.annotation.*;

/** Préfixe fixe java:comp/env/ : contexte local, pas de ldap:// possible. */
@RestController
public class JndiFixedPrefix {
    @GetMapping("/jndi/prefix")
    public String handle(@RequestParam String datasource) throws Exception {
        return String.valueOf(new InitialContext().lookup("java:comp/env/jdbc/" + datasource));
    }
}
