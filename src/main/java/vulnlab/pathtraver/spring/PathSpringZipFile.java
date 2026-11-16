package vulnlab.pathtraver.spring;

import java.util.zip.ZipFile;
import org.springframework.web.bind.annotation.*;

/** new ZipFile(chemin) ouvre l'archive choisie par l'utilisateur : ../../ sort du dossier. */
@RestController
public class PathSpringZipFile {
    private static final String BASE_DIR = "/var/app/archives/";

    @GetMapping("/files/zip")
    public Object handle(@RequestParam String name) throws Exception {
        try (ZipFile zip = new ZipFile(BASE_DIR + name)) {
            return zip.size();
        }
    }
}
