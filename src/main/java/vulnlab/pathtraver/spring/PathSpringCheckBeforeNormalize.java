package vulnlab.pathtraver.spring;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.bind.annotation.*;

/** Vérifié AVANT normalisation : /var/app/reports/../../etc/passwd commence bien par /var/app/reports. */
@RestController
public class PathSpringCheckBeforeNormalize {
    private static final Path BASE = Paths.get("/var/app/reports");

    @GetMapping("/path/checkbeforenormalize")
    public byte[] handle(@RequestParam String name) throws Exception {
        Path target = BASE.resolve(name);
        if (!target.startsWith(BASE)) {
            throw new SecurityException("Chemin hors du dossier");
        }
        return Files.readAllBytes(target.normalize());
    }
}
