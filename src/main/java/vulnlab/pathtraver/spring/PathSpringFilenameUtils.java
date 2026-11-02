package vulnlab.pathtraver.spring;

import java.io.File;
import java.nio.file.Files;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;

@RestController
public class PathSpringFilenameUtils {
    private static final String BASE_DIR = "/var/app/files";

    @GetMapping("/files/read-safe")
    public Object handle(@RequestParam String name) throws Exception {
        File file = new File(BASE_DIR, FilenameUtils.getName(name)); // nettoyage : "../../x" -> "x"
        return Files.readAllBytes(file.toPath());
    }
}
