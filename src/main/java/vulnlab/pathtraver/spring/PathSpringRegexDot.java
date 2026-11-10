package vulnlab.pathtraver.spring;

import java.io.File;
import org.springframework.web.bind.annotation.*;

/** La regex accepte le point : name = ".." remonte d'un dossier. */
@RestController
public class PathSpringRegexDot {
    private static final String REPORTS = "/var/app/reports";

    @GetMapping("/path/regexdot")
    public String[] handle(@RequestParam String name) {
        if (!name.matches("[a-zA-Z0-9._-]+")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        return new File(REPORTS, name).list();
    }
}
