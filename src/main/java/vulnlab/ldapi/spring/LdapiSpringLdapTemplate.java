package vulnlab.ldapi.spring;


import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class LdapiSpringLdapTemplate {
    private LdapTemplate ldapTemplate;
    private final AttributesMapper<Object> mapper = attrs -> attrs.get("cn").get();

    @GetMapping("/ldapi/ldapispringldaptemplate")
    public Object handle(@RequestParam String uid) {
        return ldapTemplate.search("ou=users", "(uid=" + uid + ")", mapper); // filtre concaténé : vulnérable
    }
}
