package vulnlab.pathtraver.spring;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.*;

/** Chemin normalisé PUIS comparé au dossier de base : sûr. */
@RestController
public class PathSpringCanonicalNio {
    private static final Path BASE = Paths.get("/var/app/reports");

    @GetMapping("/path/canonicalnio")
    public byte[] handle(@RequestParam String name) throws Exception {
        Path target = BASE.resolve(name).normalize();
        if (!target.startsWith(BASE)) {
            throw new SecurityException("Chemin hors du dossier");
        }
        return Files.readAllBytes(target);
    }
}
