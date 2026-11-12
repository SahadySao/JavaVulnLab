package vulnlab.entete;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Nom de fichier du téléchargement choisi par l'utilisateur : name = x.html" ou x.bat;. */
@RestController
public class EnteteContentDisposition {
    @GetMapping("/entete/download")
    public ResponseEntity<byte[]> download(@RequestParam String name) {
        byte[] content = "rapport".getBytes();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + name + "\"")
                .body(content);
    }
}
