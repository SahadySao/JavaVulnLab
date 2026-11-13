package vulnlab.bfla;

import org.springframework.web.bind.annotation.*;

/** /legacy-xml/admin/** : rôle ADMIN exigé par la configuration XML (src/main/resources/legacy-security.xml). */
@RestController
public class BflaXmlAdmin {
    @PostMapping("/legacy-xml/admin/purge")
    public void purge() {
    }
}
