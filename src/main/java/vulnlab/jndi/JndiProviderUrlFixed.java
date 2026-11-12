package vulnlab.jndi;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import org.springframework.web.bind.annotation.*;

/** Schéma et serveur fixes, seule la base change : pas de serveur choisi par l'utilisateur. */
@RestController
public class JndiProviderUrlFixed {
    @GetMapping("/jndi/providerfixed")
    public String handle(@RequestParam String unit) throws Exception {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.PROVIDER_URL, "ldap://annuaire.interne:389/" + unit);
        return new InitialDirContext(env).getNameInNamespace();
    }
}
