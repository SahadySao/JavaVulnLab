package vulnlab.xss.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** ResponseEntity en application/json : sûr pour le XSS. */
@RestController
public class XssRestEntityJson {
    @GetMapping("/xss/rest/entityjson")
    public ResponseEntity<String> search(@RequestParam String q) {
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body("{\"q\": \"" + q + "\"}");
    }
}
