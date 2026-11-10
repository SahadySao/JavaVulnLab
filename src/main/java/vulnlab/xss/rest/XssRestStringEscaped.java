package vulnlab.xss.rest;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

/** HtmlUtils.htmlEscape : sûr. */
@RestController
public class XssRestStringEscaped {
    @GetMapping("/xss/rest/escaped")
    public String hello(@RequestParam String name) {
        return "<p>Bonjour " + HtmlUtils.htmlEscape(name) + "</p>";
    }
}
