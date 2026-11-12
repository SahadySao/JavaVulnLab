package vulnlab.jndi;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.directory.InitialDirContext;
import org.springframework.web.bind.annotation.*;

/** Adresse du serveur d'annuaire choisie par l'utilisateur : PROVIDER_URL. */
@RestController
public class JndiProviderUrl {
    @GetMapping("/jndi/provider")
    public String handle(@RequestParam String server) throws Exception {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, server);
        return new InitialDirContext(env).getNameInNamespace();
    }
}
