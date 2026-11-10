package vulnlab.pathtraver.spring;

import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/** StringUtils.cleanPath normalise mais NE PROTÈGE PAS : cleanPath("../x") renvoie "../x". */
@RestController
public class PathSpringUploadCleanPath {
    private static final Path UPLOAD_DIR = Paths.get("/var/app/uploads");

    @PostMapping("/path/pathspringuploadcleanpath")
    public String handle(@RequestParam("file") MultipartFile file) throws Exception {
        String name = StringUtils.cleanPath(file.getOriginalFilename());
        file.transferTo(UPLOAD_DIR.resolve(name));
        return "ok";
    }
}
