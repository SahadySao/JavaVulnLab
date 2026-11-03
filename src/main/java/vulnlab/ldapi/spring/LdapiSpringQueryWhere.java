package vulnlab.ldapi.spring;

import static org.springframework.ldap.query.LdapQueryBuilder.query;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
public class LdapiSpringQueryWhere {
    private LdapTemplate ldapTemplate;
    private final AttributesMapper<Object> mapper = attrs -> attrs.get("cn").get();

    @GetMapping("/ldapi/ldapispringquerywhere")
    public Object handle(@RequestParam String uid) {
        return ldapTemplate.search(query().base("ou=users").where("uid").is(uid), mapper); // where().is() échappe : sûr
    }
}
