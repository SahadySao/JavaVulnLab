package vulnlab.ldapi.spring;

import org.springframework.ldap.support.LdapEncoder;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class LdapiSpringLdapEncoder {
    private LdapTemplate ldapTemplate;
    private final AttributesMapper<Object> mapper = attrs -> attrs.get("cn").get();

    @GetMapping("/ldapi/ldapispringldapencoder")
    public Object handle(@RequestParam String uid) {
        return ldapTemplate.search("ou=users", "(uid=" + LdapEncoder.filterEncode(uid) + ")", mapper); // échappé : sûr
    }
}
