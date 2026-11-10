package vulnlab.pathtraver.spring;

import java.io.File;
import java.io.FileInputStream;
import org.springframework.web.bind.annotation.*;

/** getCanonicalPath() comparé au dossier canonique + séparateur : sûr. */
@RestController
public class PathSpringCanonicalFile {
    private static final String DIR = "/var/app/reports";

    @GetMapping("/path/canonicalfile")
    public byte[] handle(@RequestParam String name) throws Exception {
        File f = new File(DIR, name);
        if (!f.getCanonicalPath().startsWith(new File(DIR).getCanonicalPath() + File.separator)) {
            throw new SecurityException("Chemin hors du dossier");
        }
        try (FileInputStream in = new FileInputStream(f)) {
            return in.readAllBytes();
        }
    }
}
