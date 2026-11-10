package vulnlab.xss.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/** @Controller sans @ResponseBody : la String est un NOM DE VUE, pas le corps de la réponse. */
@Controller
public class XssControllerView {
    @GetMapping("/xss/rest/view")
    public String page(@RequestParam String section) {
        return "sections/" + section;
    }
}
