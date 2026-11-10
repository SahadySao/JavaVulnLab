package vulnlab.pathtraver.spring;

import java.io.File;
import org.springframework.web.bind.annotation.*;

/** Nom validé : lettres, chiffres, _ et - ; ni point ni barre oblique. */
@RestController
public class PathSpringRegexName {
    private static final String REPORTS = "/var/app/reports";

    @GetMapping("/path/regexname")
    public long handle(@RequestParam String name) {
        if (!name.matches("[a-zA-Z0-9_-]+")) {
            throw new IllegalArgumentException("Nom invalide");
        }
        return new File(REPORTS, name + ".pdf").length();
    }
}
