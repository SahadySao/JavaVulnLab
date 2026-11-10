package vulnlab.cmdi.spring;

import java.util.regex.Pattern;
import org.springframework.web.bind.annotation.*;

/** Nom d'hôte validé par une regex précompilée : ni ; ni | ni $ ni espace. */
@RestController
public class CmdiSpringRegexHost {
    private static final Pattern HOST = Pattern.compile("[a-zA-Z0-9.-]+");

    @GetMapping("/cmdi/regexhost")
    public Object handle(@RequestParam String host) throws Exception {
        if (!HOST.matcher(host).matches()) {
            throw new IllegalArgumentException("Hôte invalide");
        }
        return Runtime.getRuntime().exec("ping -c 1 " + host).waitFor();
    }
}
