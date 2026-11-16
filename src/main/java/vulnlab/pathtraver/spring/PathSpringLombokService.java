package vulnlab.pathtraver.spring;

import java.io.File;
import java.nio.file.Files;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

/** Contrôleur et service Lombok (@RequiredArgsConstructor, champ final injecté) : le nom va jusqu'à new File. */
@RestController
@RequiredArgsConstructor
public class PathSpringLombokService {
    private final LombokFileService fileService;

    @GetMapping("/files/lombok")
    public byte[] handle(@RequestParam String name) throws Exception {
        return fileService.read(name);
    }
}

@Service
@RequiredArgsConstructor
class LombokFileService {
    private static final String BASE_DIR = "/var/app/files";

    byte[] read(String name) throws Exception {
        return Files.readAllBytes(new File(BASE_DIR, name).toPath());
    }
}
