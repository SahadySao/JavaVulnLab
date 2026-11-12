package vulnlab.entete;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/** Spring HttpHeaders : Refresh construit avec une URL de l'utilisateur (redirection). */
@RestController
public class EnteteSpringHttpHeaders {
    @GetMapping("/entete/refresh")
    public ResponseEntity<String> refresh(@RequestParam String next) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Refresh", "0;url=" + next);
        return new ResponseEntity<>("Redirection...", headers, HttpStatus.OK);
    }
}
