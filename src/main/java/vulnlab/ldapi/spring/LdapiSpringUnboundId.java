package vulnlab.ldapi.spring;

import com.unboundid.ldap.sdk.Filter;
import com.unboundid.ldap.sdk.LDAPConnection;
import com.unboundid.ldap.sdk.SearchScope;
import org.springframework.web.bind.annotation.*;

@RestController
public class LdapiSpringUnboundId {
    private LDAPConnection connection;

    @GetMapping("/ldapi/unboundid")
    public Object handle(@RequestParam String uid) throws Exception {
        Filter filter = Filter.create("(uid=" + uid + ")"); // filtre concaténé : vulnérable
        return connection.search("ou=users,dc=exemple,dc=com", SearchScope.SUB, filter).getEntryCount();
    }
}
