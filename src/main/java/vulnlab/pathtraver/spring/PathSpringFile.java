package vulnlab.pathtraver.spring;

import java.io.File;
import java.nio.file.Files;
import org.springframework.web.bind.annotation.*;

@RestController
public class PathSpringFile {
    private static final String BASE_DIR = "/var/app/files";

    @GetMapping("/files/read")
    public Object handle(@RequestParam String name) throws Exception {
        File file = new File(BASE_DIR, name);
        return Files.readAllBytes(file.toPath());
    }
}
