package vulnlab.pathtraver.spring;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import org.owasp.esapi.ESAPI;
import org.springframework.web.bind.annotation.*;

@RestController
public class PathSpringEsapiValidator {
    private static final String BASE_DIR = "/var/app/files";

    @GetMapping("/files/read-validated")
    public Object handle(@RequestParam String name) throws Exception {
        String safeName = ESAPI.validator().getValidFileName("download", name, List.of(".txt"), false); // nettoyage
        return Files.readAllBytes(new File(BASE_DIR, safeName).toPath());
    }
}
