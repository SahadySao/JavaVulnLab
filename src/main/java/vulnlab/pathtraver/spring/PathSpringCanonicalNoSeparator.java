package vulnlab.pathtraver.spring;

import java.io.File;
import org.springframework.web.bind.annotation.*;

/** Comparaison de chaînes sans séparateur : /var/app/reports-archive/... passe le contrôle. */
@RestController
public class PathSpringCanonicalNoSeparator {
    private static final String DIR = "/var/app/reports";

    @GetMapping("/path/canonicalnoseparator")
    public long handle(@RequestParam String name) throws Exception {
        File f = new File(DIR, name);
        if (!f.getCanonicalPath().startsWith(DIR)) {
            throw new SecurityException("Chemin hors du dossier");
        }
        return f.length();
    }
}
