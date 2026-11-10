package vulnlab.xss.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/** produces = JSON : le navigateur n'interprète pas la réponse comme du HTML. */
@RestController
public class XssRestStringJson {
    @GetMapping(value = "/xss/rest/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello(@RequestParam String name) {
        return "{\"name\":\"" + name + "\"}";
    }
}
