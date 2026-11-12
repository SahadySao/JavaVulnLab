package vulnlab.jndi;

import org.springframework.jndi.JndiTemplate;
import org.springframework.web.bind.annotation.*;

/** Spring JndiTemplate.lookup avec un nom choisi par l'utilisateur. */
@RestController
public class JndiTemplateLookup {
    private final JndiTemplate jndiTemplate = new JndiTemplate();

    @GetMapping("/jndi/template")
    public String handle(@RequestParam String name) throws Exception {
        return String.valueOf(jndiTemplate.lookup(name));
    }
}
