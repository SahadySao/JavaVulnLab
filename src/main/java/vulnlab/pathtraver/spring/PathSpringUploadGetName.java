package vulnlab.pathtraver.spring;

import java.io.File;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/** FilenameUtils.getName ne garde que le dernier segment : "../../x" devient "x". */
@RestController
public class PathSpringUploadGetName {
    private static final String UPLOAD_DIR = "/var/app/uploads";

    @PostMapping("/path/pathspringuploadgetname")
    public String handle(@RequestParam("file") MultipartFile file) throws Exception {
        file.transferTo(new File(UPLOAD_DIR, FilenameUtils.getName(file.getOriginalFilename())));
        return "ok";
    }
}
