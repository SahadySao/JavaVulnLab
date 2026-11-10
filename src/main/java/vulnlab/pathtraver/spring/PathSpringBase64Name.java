package vulnlab.pathtraver.spring;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.springframework.web.bind.annotation.*;

/** Le nom est encodé en Base64 URL : ni point ni barre oblique, ".." est impossible. */
@RestController
public class PathSpringBase64Name {
    private static final String CACHE = "/var/app/cache";

    @GetMapping("/path/base64name")
    public boolean handle(@RequestParam String key) {
        String name = Base64.getUrlEncoder().withoutPadding().encodeToString(key.getBytes(StandardCharsets.UTF_8));
        return new File(CACHE, name).exists();
    }
}
