package vulnlab.xss.rest;

import org.springframework.web.bind.annotation.*;

/** @RestController qui renvoie une String : Spring l'envoie en text/html si le navigateur le demande. */
@RestController
public class XssRestString {
    @GetMapping("/xss/rest/hello")
    public String hello(@RequestParam String name) {
        return "<p>Bonjour " + name + "</p>";
    }
}
