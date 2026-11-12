package vulnlab.entete;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Nom de fichier encodé (URLEncoder) : ; " et les retours à la ligne deviennent %3B %22 %0D %0A. */
@RestController
public class EnteteContentDispositionEncoded {
    @GetMapping("/entete/download-encoded")
    public ResponseEntity<byte[]> download(@RequestParam String name) {
        String safe = URLEncoder.encode(name, StandardCharsets.UTF_8);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename*=UTF-8''" + safe)
                .body("rapport".getBytes());
    }
}
