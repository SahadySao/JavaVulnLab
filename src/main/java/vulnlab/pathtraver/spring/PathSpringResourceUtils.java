package vulnlab.pathtraver.spring;

import java.io.File;
import java.nio.file.Files;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

/** ResourceUtils.getFile("file:" + ...) : un chemin du système de fichiers choisi par l'utilisateur. */
@RestController
public class PathSpringResourceUtils {
    @GetMapping("/files/resourceutils")
    public Object handle(@RequestParam String name) throws Exception {
        File file = ResourceUtils.getFile("file:/var/app/files/" + name);
        return Files.readAllBytes(file.toPath());
    }
}
