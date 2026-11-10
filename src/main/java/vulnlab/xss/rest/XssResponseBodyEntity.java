package vulnlab.xss.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** @Controller + @ResponseBody ResponseEntity<String>. */
@Controller
public class XssResponseBodyEntity {
    @ResponseBody
    @GetMapping("/xss/rest/entity")
    public ResponseEntity<String> search(@RequestParam String q) {
        return ResponseEntity.ok("<h1>Résultats pour " + q + "</h1>");
    }
}
