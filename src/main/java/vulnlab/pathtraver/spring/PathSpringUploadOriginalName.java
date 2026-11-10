package vulnlab.pathtraver.spring;

import java.io.File;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/** Le nom du fichier envoyé est choisi par le client : filename="../../../etc/cron.d/x". */
@RestController
public class PathSpringUploadOriginalName {
    private static final String UPLOAD_DIR = "/var/app/uploads";

    @PostMapping("/path/pathspringuploadoriginalname")
    public String handle(@RequestParam("file") MultipartFile file) throws Exception {
        file.transferTo(new File(UPLOAD_DIR, file.getOriginalFilename()));
        return "ok";
    }
}
